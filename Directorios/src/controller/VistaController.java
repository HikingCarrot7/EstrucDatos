package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
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

    private DirectoryManager directoryManager;
    private TableManager tableManager;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        directoryManager = new DirectoryManager();
        tableManager = new TableManager();
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
     * Método que se llama cuando presionamos el botón <em>Buscar</em>.
     *
     * @param e El {@link ActionEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void busqueda(ActionEvent e)
    {

    }

    /**
     * Método que se llama cuando presionamos el botón <em>Buscar directorio</em>.
     *
     * @param e El {@link ActionEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void seleccionarDirectorio(ActionEvent e)
    {
        limpiarTablas();

        File file = obtenerRutaDirectorio();

        if (file != null)
        {
            entradaDirectorio.setText(file.getAbsolutePath());
            procesarDirectorios(file);
        }

    }

    /**
     * Inicia el proceso para recuperar y ordenar todos los directorios encontrados en el {@link File} especificado.
     *
     * @param file El {@link File} que representa el directorio ha buscar todos los archivos.
     */
    private void procesarDirectorios(File file)
    {
        /**
         * No debe someterse al hilo <em>JavaFx Application Thread</em> a una tarea pesada, ya que congelaríamos toda la GUI de la aplicación mientras se realiza todo el proceso.
         */
        Task<Void> task = new Task<Void>()
        {
            @Override
            protected Void call() throws Exception
            {
                long antes = System.currentTimeMillis();

                Scene currentScene = buscar.getScene();
                currentScene.setCursor(Cursor.WAIT);
                ObservableList<Directorio> directorios = FXCollections.observableArrayList();

                rellenarTablaEncontrados(directorios);
                directoryManager.obtenerTodosArchivos(directorios, file);

                rellenarTablaEncontrados(directorios);
                rellenarTablaOrdenada(directorios);

                currentScene.setCursor(Cursor.DEFAULT);

                actualizarReloj(System.currentTimeMillis() - antes);

                return null;
            }

        };

        Thread t = new Thread(task);
        t.setDaemon(true);
        t.start();
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
     * Método que se llama cuándo se presiona una tecla en el {@link TextField} para insertar la ruta del directorio.
     *
     * @param e El {@link KeyEvent} que se crea al realizar la acción anterior.
     */
    @FXML
    private void analizarEntradaDirectorio(KeyEvent e)
    {
        if (e.getCode() == KeyCode.ENTER)
            System.out.println("A buscar!");
    }

    /**
     * Rellena la tabla con la lista de directorios ordenados.
     *
     * Primeros se ordenan los directorios según el método de ordenamiento seleccionado y luego se muestra en la tabla correspondiente.
     *
     * @param directorios Los directorios rescatados de la ruta insertada.
     *
     */
    private void rellenarTablaOrdenada(ObservableList<Directorio> directorios)
    {
        ObservableList<Directorio> direc = directorios
                .stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        tableManager.rellenarTabla(direc, tablaListaOrdenada);
        ordenarDirectorios(direc);
        tableManager.rellenarTabla(direc, tablaListaOrdenada);
    }

    /**
     * Rellena la tabla con los registros encontrados en la ruta especificada.
     *
     * @param directorios Los directorios encontrados.
     */
    private void rellenarTablaEncontrados(ObservableList<Directorio> directorios)
    {
        tableManager.rellenarTabla(directorios, tablaListaEncontrada);
    }

    /**
     * Procede a buscar cuál {@link RadioButton} está seleccionado para aplicar el método de ordenamiento adecuando.
     *
     * @param directorios La lista de directorios.
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
     * Limpia 2 tablas que muestran los directorios.
     */
    private void limpiarTablas()
    {
        tableManager.limpiarTabla(tablaListaEncontrada);
        tableManager.limpiarTabla(tablaListaOrdenada);
    }

}
