package controller;

import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import model.BinarySearch;

/**
 * FXML Controller class
 *
 * @author HikingCarrot7
 */
public class VistaController implements Initializable
{

    private static final int COL_NOMBRE = 0;
    private static final int COL_RUTA = 1;
    private static final int COL_FECHA = 2;

    @FXML
    private Button buscar;
    @FXML
    private ToggleGroup ordenamiento;
    @FXML
    private TextField entradaArchivo;
    @FXML
    private TextField entradaDirectorio;
    @FXML
    private CheckBox incluirSubcarpetas;
    @FXML
    private CheckBox todasCoincidencias;
    @FXML
    private RadioButton burbuja;
    @FXML
    private RadioButton insercion;
    @FXML
    private RadioButton shellsort;
    @FXML
    private RadioButton mergesort;
    @FXML
    private RadioButton quicksort;
    @FXML
    private RadioButton mezclaDirecta;
    @FXML
    private TableView<Directorio> tablaListaOrdenada;
    @FXML
    private TableView<Directorio> tablaListaEncontrada;
    @FXML
    private Button buscarDirectorio;
    @FXML
    private Label tiempoTranscurrido;
    @FXML
    private Button actualizar;
    @FXML
    private Button cancelar;

    private DirectoryManager directoryManager;
    private TableManager<Directorio> tableManager;
    private Task<Long> taskThread;
    private Thread t;
    private ObservableList<Directorio> directoriosEncontrados;
    private Stage stage;
    @FXML
    private ProgressIndicator progressIndicator;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initMyComponents();
    }

    /**
     * Iniciamos algunos de los componentes importantes de la vista.
     */
    private void initMyComponents()
    {
        directoryManager = new DirectoryManager();
        tableManager = new TableManager<>();
        directoriosEncontrados = FXCollections.observableArrayList();
        buscar.setTooltip(new Tooltip("Realizar una nueva búsqueda."));
        buscarDirectorio.setTooltip(new Tooltip("Seleccionar un directorio."));
        actualizar.setTooltip(new Tooltip("Actualiza la búsqueda de los directorios."));
        cancelar.setTooltip(new Tooltip("Cancelar la operación de búsqueda y ordenamiento de directorios."));
        progressIndicator.setVisible(false);
        initTabla(tablaListaOrdenada);
        initTabla(tablaListaEncontrada);
    }

    /**
     * Inicia el {@link Stage} para este controlador.
     *
     * @param stage El {@link Stage} para este controlador.
     */
    public void initStage(Stage stage)
    {
        this.stage = stage;
    }

    /**
     * Iniciamos las columnas de las tablas donde se mostrarán los directorios.
     *
     * @param table La {@link TableView} a inicializar.
     */
    private void initTabla(TableView<Directorio> table)
    {
        for (int i = 0; i < table.getColumns().size(); i++)
        {
            TableColumn<Directorio, ?> column = table.getColumns().get(i);

            switch (i)
            {
                case COL_NOMBRE:
                    column.setCellValueFactory(new PropertyValueFactory<>("nombre"));
                    break;
                case COL_RUTA:
                    column.setCellValueFactory(new PropertyValueFactory<>("ruta"));
                    break;
                case COL_FECHA:
                    column.setCellValueFactory(new PropertyValueFactory<>("fecha"));
                    break;
                default:
                    throw new AssertionError();
            }
        }

        FileManager handler = new FileManager();

        tablaListaOrdenada.setOnMouseClicked(handler);
        tablaListaEncontrada.setOnMouseClicked(handler);
    }

    /**
     * Método llamado cuando presionamos el botón <em>Buscar</em>.
     *
     * @param e El {@link ActionEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void nuevaBusqueda(ActionEvent e)
    {
        if (existeTaskThread())
        {
            taskThread.cancel();
            t.interrupt();
        }

        limpiarTodosCampos(true);
    }

    /**
     * Método llamado cuando presionamos el botón <em>Buscar directorio</em>.
     *
     * @param e El {@link ActionEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void seleccionarDirectorio(ActionEvent e)
    {
        File file = obtenerRutaDirectorio();

        if (file != null)
        {
            entradaDirectorio.setText(file.getAbsolutePath());
            procesarDirectorios(file);
        }

    }

    /**
     * Método llamado cuándo se presiona una tecla en el {@link TextField} para insertar el nombre del archivo que queremos buscar.
     *
     * @param e El {@link KeyEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void insertarNombreArchivo(KeyEvent e)
    {
        Platform.runLater(() ->
        {
            rellenarTablaDirectoriosOrdenados(directoriosEncontrados);
        });
    }

    /**
     * Método llamado cuando presionamos en el {@link CheckBox} <em>Todas las coincidencias</em>. La acción que se realiza aquí es la misma que se produce cuando presionamos una tecla en el {@link TextField} para insertar el nombre del archivo que queremos buscar.
     *
     * @param e El {@link KeyEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void todasLasCoincidencias(ActionEvent e)
    {
        Platform.runLater(() ->
        {
            rellenarTablaDirectoriosOrdenados(directoriosEncontrados);
        });
    }

    /**
     * Método que se llama cuando se presiona una tecla en el {@link TextField} para insertar la ruta del directorio.
     *
     * @param e El {@link KeyEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void analizarEntradaDirectorio(KeyEvent e)
    {
        Platform.runLater(() ->
        {
            if (e.getCode() == KeyCode.ENTER)
                iniciarProcesamientoDirectorios();
        });
    }

    /**
     * Método llamado cuando presionamos el botón <em>Buscar directorio</em>. Lo único que hace es reiniciar la búsqueda y rodenamiento de los archivos.
     *
     * @param e El {@link ActionEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void actualizar(ActionEvent e)
    {
        if (!existeTaskThread())
            iniciarProcesamientoDirectorios();
    }

    @FXML
    private void cancelar(ActionEvent e)
    {
        if (existeTaskThread())
        {
            taskThread.cancel();
            t.interrupt();
            limpiarTodosCampos(false);
        }

    }

    /**
     * Inicia el proceso para recuperar y ordenar todos los directorios encontrados en el {@link File} especificado.
     *
     * @param file El {@link File} que representa el directorio ha buscar todos los archivos.
     */
    private void procesarDirectorios(File file)
    {
        limpiarTablas();
        directoriosEncontrados.clear();

        /**
         * No debe someterse al hilo <em>JavaFx Application Thread</em> a una tarea pesada, ya que congelaríamos toda la GUI de la aplicación mientras se realiza todo el proceso.
         */
        taskThread = new Task<Long>()
        {
            @Override
            protected Long call() throws Exception
            {
                long antes = System.currentTimeMillis();

                Scene currentScene = buscar.getScene();
                currentScene.setCursor(Cursor.WAIT);
                progressIndicator.setVisible(true);

                //Rellena la lista de directorios.
                rellenarDirectorios(directoriosEncontrados, file);

                //Rellena la tabla con la lista de directorios.
                rellenarTablaDirectoriosEncontrados(directoriosEncontrados);
                rellenarTablaDirectoriosOrdenados(ordenarDirectorios(directoriosEncontrados));
                currentScene.setCursor(Cursor.DEFAULT);
                progressIndicator.setVisible(false);

                //Actualizamos el tiempo transcurrido desde que se inició el proceso.
                return System.currentTimeMillis() - antes;
            }

            @Override
            protected void done()
            {
                try
                {
                    super.done();
                    actualizarTablas(); // El proceso ha terminado, actualizamos las tablas.
                    actualizarTiempoTranscurrido(get()); // Actualizamos el tiempo transcurrido desde que se inició esta operación.
                    return;

                    //Algún error ocurrió o la operación fue cancelada.
                } catch (ExecutionException e)
                {
                    e.printStackTrace();
                    mostrarError("¡Hubo un error o no podemos analizar esa carpeta!");

                } catch (Exception ex)
                {
                    System.out.println("El proceso para buscar y ordenar los directorios fue cancelado.");
                }

                limpiarTodosCampos(false);
            }

        };

        /**
         * Hilo encargado de iniciar la tarea anterior.
         */
        t = new Thread(taskThread);
        t.setDaemon(true);
        t.start();
    }

    /**
     * Inicia el proceso para buscar y ordenar los directorios de la ruta insertada.
     */
    private void iniciarProcesamientoDirectorios()
    {
        File file = new File(getEntradaRutaDirectorio());

        if (file.exists())
            procesarDirectorios(file);

        else
            mostrarError("La ruta que insertó no existe.");
    }

    /**
     * Rellena la {@link ObservableList} tomando en cuenta la opción <em>Incluir subcarpetas</em>
     *
     * @param directorios La {@link ObservableList} que se rellenará con los directorios.
     * @param file El {@link File} que representa la ruta de los directorios.
     */
    private void rellenarDirectorios(ObservableList<Directorio> directorios, File file)
    {
        try
        {
            if (incluirSubcarpetas.isSelected())
                directoryManager.obtenerTodosArchivos(directorios, file);

            else
                directoryManager.obtenerArchivosDirectorio(directorios, file);

        } catch (InterruptedException ex)
        {
            System.out.println(ex.getMessage()); // La operación para la búsqueda de directorios fue cancelada.
            limpiarTodosCampos(false); // Limpiamos todos los campos.
        }
    }

    /**
     * Rellena la tabla con la lista de directorios ordenados.
     *
     * @param directorios Los directorios rescatados de la ruta insertada.
     *
     */
    private void rellenarTablaDirectoriosOrdenados(ObservableList<Directorio> directorios)
    {
        if (hayQueBuscarArchivoEspecifico())
            if (todasCoincidencias.isSelected()) // ÚNICAMENTO FILTRAMOS LOS ARCHIVOS SI LA OPCIÓN INCLUIR TODAS LAS COINDIDENCIAS ESTÁ SELECCIONADA, EN CASO CONTRARIO USAMOS EL ALGORITMO BINARY SEARCH PARA BUSCAR EL ARCHIVO QUE QUEREMOS.
                tableManager.rellenarTabla(filtrarDirectorios(directorios, getNombreArchivoBuscar()), tablaListaOrdenada);

            else
            {
                int index = encontrarArchivoEspecifico(directorios, getNombreArchivoBuscar());

                if (index >= 0)
                    tableManager.rellenarTabla(directorios.get(index), tablaListaOrdenada);

                else
                    tableManager.limpiarTabla(tablaListaOrdenada);
            }

        else
            tableManager.rellenarTabla(directorios, tablaListaOrdenada);

    }

    /**
     * Rellena la tabla con los registros encontrados en la ruta especificada. Nótese que esta tabla será rellenada con la referencia de una nueva lista.
     *
     * @param directorios La {@link ObservableList} de los directorios.
     */
    private void rellenarTablaDirectoriosEncontrados(ObservableList<Directorio> directorios)
    {
        tableManager.rellenarTabla(directorios, tablaListaEncontrada);
    }

    /**
     * Procede a buscar cuál {@link RadioButton} está seleccionado para aplicar el método de ordenamiento adecuado.
     *
     * @param directorios La {@link ObservableList} de los directorios.
     *
     * @return Los directorios ordenados según el método de ordenamiento seleccionado.
     */
    public ObservableList<Directorio> ordenarDirectorios(ObservableList<Directorio> directorios)
    {
        switch (((RadioButton) ordenamiento.getSelectedToggle()).getText())
        {
            case "Burbuja":
                SorterManager.ordenarPor(SorterManager.CLAVE_BURBUJA, directorios);
                break;
            case "Inserción":
                SorterManager.ordenarPor(SorterManager.CLAVE_INSERCION, directorios);
                break;
            case "ShellSort":
                SorterManager.ordenarPor(SorterManager.CLAVE_SHELL_SORT, directorios);
                break;
            case "MergeSort":
                SorterManager.ordenarPor(SorterManager.CLAVE_MERGE_SORT, directorios);
                break;
            case "QuickSort":
                SorterManager.ordenarPor(SorterManager.CLAVE_QUICK_SORT, directorios);
                break;
            case "Mezcla directa":
                SorterManager.ordenarPor(SorterManager.CLAVE_MEZCLA_DIRECTA, directorios);
                break;
            default:
                throw new AssertionError();
        }

        return directorios;
    }

    /**
     * Filtramos la {@link ObservableList} dependiendo del nombre del archivo que se nos especifica.
     *
     * @param directoriosAFiltrar La {@link ObservableList} a filtrar.
     * @param key La llave para hacer el filtro.
     *
     * @return Una {@link FilteredList} con los valores que nos interesan.
     */
    private FilteredList<Directorio> filtrarDirectorios(ObservableList<Directorio> directoriosAFiltrar, String key)
    {
        return directoriosAFiltrar.filtered(direc -> direc.getNombre().toLowerCase().contains(key.toLowerCase()));
    }

    /**
     * Encontramos un archivo en específico usando el algoritmo <em>Binary search</em>.
     *
     * @param directoriosAFiltrar La {@link ObservableList} a filtrar.
     * @param key La llave para hacer la búsqueda.
     *
     * @return El índice del {@link Directorio} que nos interesa.
     */
    private int encontrarArchivoEspecifico(ObservableList<Directorio> directorios, String key)
    {
        return BinarySearch.binarySearch(directorios, key);
    }

    /**
     * Actualiza el tiempo que ha transcurrido desde que se inició el proceso de búsqueda y ordenamiento.
     *
     * @param tiempoTrancurrido El tiempo (en milisegundos) que ha transcurrido desde que se inició todo el proceso.
     */
    private void actualizarTiempoTranscurrido(long tiempoTrancurrido)
    {
        Platform.runLater(() ->
        {
            tiempoTranscurrido.setText("Tiempo transcurrido: " + tiempoTrancurrido + " ms");
        });
    }

    /**
     * Actualiza las tablas para que el contenido se muestre de manera correcta.
     */
    private void actualizarTablas()
    {
        Platform.runLater(() ->
        {
            tablaListaEncontrada.refresh();
            tablaListaOrdenada.refresh();
        });
    }

    /**
     * Limpia las 2 tablas que muestran los directorios.
     */
    private void limpiarTablas()
    {
        tiempoTranscurrido.setText("");
        tableManager.limpiarTabla(tablaListaEncontrada);
        tableManager.limpiarTabla(tablaListaOrdenada);
    }

    /**
     * Limpia todos los datos de la vista.
     *
     * @param limpiarEntradas Establecemos <code>true</code> si queremos limpiar el texto que esté en las entradas, <code>false</code> en caso contrario.
     */
    private void limpiarTodosCampos(boolean limpiarEntradas)
    {
        Platform.runLater(() ->
        {
            buscar.getScene().setCursor(Cursor.DEFAULT);
            tiempoTranscurrido.setText("");
            burbuja.setSelected(true);
            directoriosEncontrados.clear();
            progressIndicator.setVisible(false);

            if (limpiarEntradas)
                limpiarEntradas();

            limpiarTablas();
        });
    }

    /**
     * Limpia las entradas de texto.
     */
    private void limpiarEntradas()
    {
        entradaArchivo.setText("");
        entradaDirectorio.setText("");
    }

    /**
     * Regresa la ruta del directorio seleccionado.
     *
     * @return El {@link File} que representa al directorio.
     */
    private File obtenerRutaDirectorio()
    {
        DirectoryChooser directorio = new DirectoryChooser();
        return directorio.showDialog(stage);
    }

    /**
     * Muestra un error en pantalla.
     *
     * @param mensaje El mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje)
    {
        Platform.runLater(() ->
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText(mensaje);
            Toolkit.getDefaultToolkit().beep();
            alert.showAndWait();
        });
    }

    /**
     * ¿Se insertó el nombre de un archivo para buscar?
     *
     * @return <code>true</code> si la hay una palabra a buscar, <code>false</code> en caso contrario.
     */
    private boolean hayQueBuscarArchivoEspecifico()
    {
        return !getNombreArchivoBuscar().equals("");
    }

    /**
     * Obtenemos el texto insertado en el {@link TextField} de la ruta del directorio.
     *
     * @return El {@link String} que representa la ruta del directorio insertado.
     */
    private String getEntradaRutaDirectorio()
    {
        return entradaDirectorio.getText().trim();
    }

    /**
     * Obtenemos el texto insertado en el {@link TextField} del nombre del archivo que queremos buscar.
     *
     * @return El {@link String} que representa el nombre del archivo que queremos buscar.
     */
    private String getNombreArchivoBuscar()
    {
        return entradaArchivo.getText().trim();
    }

    /**
     * ¿Ya se ha creado la tarea para la búsqueda y ordenamiento de los archivos?
     *
     * @return <code>true</code> si la ya se ha creado la tarea, <code>false</code> en caso contrario.
     */
    private boolean existeTaskThread()
    {
        return taskThread != null && taskThread.isRunning();
    }

}
