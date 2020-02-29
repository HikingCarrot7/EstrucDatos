package controller;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    private DirectoryManager dm;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        dm = new DirectoryManager();
        initTabla(tablaListaOrdenada);
        initTabla(tablaListaEncontrada);
    }

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

    @FXML
    private void busqueda(ActionEvent event)
    {

    }

    @FXML
    private void seleccionarDirectorio(ActionEvent event)
    {
        DirectoryChooser directorio = new DirectoryChooser();
        File file = directorio.showDialog(null);

        if (file != null)
        {
            entradaDirectorio.setText(file.getAbsolutePath());

            Thread t = new Thread(() ->
            {
                Scene currentScene = buscar.getScene();
                currentScene.setCursor(Cursor.WAIT);
                ObservableList<Directorio> directorios = FXCollections.observableArrayList();
                tablaListaEncontrada.setItems(directorios);
                dm.obtenerTodosArchivos(directorios, file);
                rellenarTablaEncontrados(directorios);
                rellenarTablaOrdenada(directorios);
                currentScene.setCursor(Cursor.DEFAULT);
            });

            t.setDaemon(true);
            t.start();
        }
    }

    @FXML
    private void analizarEntradaDirectorio(KeyEvent event)
    {
        if (event.getCode() == KeyCode.ENTER)
            System.out.println("A buscar!");
    }

    private void rellenarTablaOrdenada(ObservableList<Directorio> directorios)
    {
        ObservableList<Directorio> direc = directorios
                .stream()
                .collect(Collectors.toCollection(FXCollections::observableArrayList));

        tablaListaOrdenada.setItems(direc);
        ordenarDirectorios(direc);
        rellenarTabla(direc, tablaListaOrdenada);
    }

    private void rellenarTablaEncontrados(ObservableList<Directorio> directorios)
    {
        rellenarTabla(directorios, tablaListaEncontrada);
    }

    private void rellenarTabla(ObservableList<Directorio> directorios, TableView<Directorio> table)
    {
        table.setItems(directorios);
        table.refresh();
    }

    public void ordenarDirectorios(ObservableList<Directorio> directorios)
    {
        if (ordenamiento.getSelectedToggle() == insercion)
            SorterManager.ordenarPor(SorterManager.CLAVE_INSERCION, directorios);

        else if (ordenamiento.getSelectedToggle() == burbuja)
            SorterManager.ordenarPor(SorterManager.CLAVE_BURBUJA, directorios);
    }

}
