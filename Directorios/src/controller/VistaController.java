package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.DirectoryChooser;
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
    private Label reloj;
    @FXML
    private Button actualizar;
    @FXML
    private Button cancelar;

    private DirectoryManager directoryManager;
    private TableManager<Directorio> tableManager;
    private Task<Long> taskThread;
    private Thread t;

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        directoryManager = new DirectoryManager();
        tableManager = new TableManager<>();
        initTabla(tablaListaOrdenada);
        initTabla(tablaListaEncontrada);
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
    }

    /**
     * Método llamado cuando presionamos el botón <em>Buscar</em>.
     *
     * @param e El {@link ActionEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void nuevaBusqueda(ActionEvent e)
    {
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
    private void buscarArchivo(KeyEvent e)
    {
        if (e.getCode() == KeyCode.ENTER)
            iniciarProcesamientoDirectorios();
    }

    @FXML
    private void actualizar(ActionEvent e)
    {
        iniciarProcesamientoDirectorios();
    }

    @FXML
    private void cancelar(ActionEvent event)
    {
        if (existeTaskThread())
        {
            taskThread.cancel();
            t.interrupt();
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

        /**
         * No debe someterse al hilo <em>JavaFx Application Thread</em> a una tarea pesada, ya que congelaríamos toda la GUI de la aplicación mientras se realiza todo el proceso (similar a lo que pasa con Swing).
         */
        taskThread = new Task<Long>()
        {
            private ObservableList<Directorio> directorios;


            {
                directorios = FXCollections.observableArrayList();
            }

            @Override
            protected Long call() throws Exception
            {
                long antes = System.currentTimeMillis();

                Scene currentScene = buscar.getScene();
                currentScene.setCursor(Cursor.WAIT);

                //Rellena la lista de directorios.
                rellenarDirectorios(directorios, file);

                //Rellena la tabla con la lista de directorios.
                rellenarTablaDirectoriosEncontrados(directorios);
                rellenarTablaDirectoriosOrdenados(directorios);

                currentScene.setCursor(Cursor.DEFAULT);

                //Actualizamos el tiempo transcurrido desde que se inició el proceso.
                return System.currentTimeMillis() - antes;
            }

            @Override
            protected void done()
            {
                try
                {
                    super.done();
                    actualizarTablas();
                    actualizarReloj(get());

                } catch (Exception ex)
                {
                    System.out.println("El proceso para buscar y ordenar los directotios fue cancelado.");
                    limpiarTodosCampos(false);
                }
            }

        };

        t = new Thread(taskThread);
        t.setDaemon(true);
        t.start();
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
     * Regresa la ruta del directorio seleccionado.
     *
     * @return El {@link File} que representa al directorio.
     */
    private File obtenerRutaDirectorio()
    {
        DirectoryChooser directorio = new DirectoryChooser();
        return directorio.showDialog(null);
    }

    /**
     * Método que se llama cuando se presiona una tecla en el {@link TextField} para insertar la ruta del directorio.
     *
     * @param e El {@link KeyEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void analizarEntradaDirectorio(KeyEvent e)
    {
        if (e.getCode() == KeyCode.ENTER)
            iniciarProcesamientoDirectorios();
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
     * Rellena la tabla con la lista de directorios ordenados.
     *
     * Primeros se ordenan los directorios según el método de ordenamiento seleccionado y luego se muestra en la tabla correspondiente.
     *
     * @param directorios Los directorios rescatados de la ruta insertada.
     *
     */
    private void rellenarTablaDirectoriosOrdenados(ObservableList<Directorio> directorios)
    {
        ObservableList<Directorio> direc = FXCollections.observableArrayList(directorios);
        ordenarDirectorios(direc);

        if (hayQueBuscarArchivoEspecifico())
        {
            int index = BinarySearch.binarySearch(direc, getEntradaNombreArchivoBuscar());

            if (index >= 0)
                tableManager.rellenarTabla(direc.get(index), tablaListaOrdenada);

        } else
            tableManager.rellenarTabla(direc, tablaListaOrdenada);
    }

    /**
     * Rellena la tabla con los registros encontrados en la ruta especificada.
     *
     * @param directorios La {@link ObservableList} de los directorios.
     */
    private void rellenarTablaDirectoriosEncontrados(ObservableList<Directorio> directorios)
    {
        tableManager.rellenarTabla(directorios, tablaListaEncontrada);
    }

    /**
     * Procede a buscar cuál {@link RadioButton} está seleccionado para aplicar el método de ordenamiento adecuando.
     *
     * @param directorios La {@link ObservableList} de los directorios.
     */
    public void ordenarDirectorios(ObservableList<Directorio> directorios)
    {
        if (ordenamiento.getSelectedToggle() == burbuja)
            SorterManager.ordenarPor(SorterManager.CLAVE_BURBUJA, directorios);

        else if (ordenamiento.getSelectedToggle() == insercion)
            SorterManager.ordenarPor(SorterManager.CLAVE_INSERCION, directorios);

        else if (ordenamiento.getSelectedToggle() == shellsort)
            SorterManager.ordenarPor(SorterManager.CLAVE_SHELL_SORT, directorios);

        else if (ordenamiento.getSelectedToggle() == mergesort)
            SorterManager.ordenarPor(SorterManager.CLAVE_MERGE_SORT, directorios);

        else if (ordenamiento.getSelectedToggle() == quicksort)
            SorterManager.ordenarPor(SorterManager.CLAVE_QUICK_SORT, directorios);

        else if (ordenamiento.getSelectedToggle() == mezclaDirecta)
            SorterManager.ordenarPor(SorterManager.CLAVE_MEZCLA_DIRECTA, directorios);
    }

    /**
     * Actualiza el tiempo que ha transcurrido desde que se inició el proceso de búsqueda y ordenamiento.
     *
     * @param tiempoTrancurrido El tiempo (en milisegundos) que ha transcurrido desde que se inició todo el proceso.
     */
    private void actualizarReloj(long tiempoTrancurrido)
    {
        Platform.runLater(() ->
        {
            reloj.setText("Tiempo transcurrido: " + tiempoTrancurrido + " ms");
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
        reloj.setText("");
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
            reloj.setText("");
            burbuja.setSelected(true);

            if (limpiarEntradas)
                limpiarEntradas();

            limpiarTablas();
        });
    }

    private void limpiarEntradas()
    {
        entradaArchivo.setText("");
        entradaDirectorio.setText("");
    }

    /**
     * ¿Se insertó el nombre de un archivo para buscar?
     *
     * @return <code>true</code> si la hay una palabra a buscar, <code>false</code> en caso contrario.
     */
    private boolean hayQueBuscarArchivoEspecifico()
    {
        return !getEntradaNombreArchivoBuscar().equals("");
    }

    /**
     * Muestra un error en pantalla.
     *
     * @param mensaje El mensaje de error a mostrar.
     */
    private void mostrarError(String mensaje)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText(mensaje);
        alert.showAndWait();
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
    private String getEntradaNombreArchivoBuscar()
    {
        return entradaArchivo.getText().trim();
    }

    private boolean existeTaskThread()
    {
        return taskThread != null;
    }

}
