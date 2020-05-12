package com.sw.controller;

import com.sw.model.*;
import com.sw.model.dao.DAO;
import com.sw.model.dao.Loader;
import com.sw.model.exceptions.*;
import com.sw.model.util.LinkedList;
import java.awt.Toolkit;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.css.Styleable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Nicolás
 */
public class VistaController implements Initializable, Controller
{

    @FXML private ToggleGroup grupoRadioButtons;
    @FXML private RadioButton rbArbolAVL;
    @FXML private RadioButton rbArbolB;
    @FXML private RadioButton rbArbolBB;
    @FXML private ProgressIndicator progreso;
    @FXML private BorderPane panelPrincipal;
    @FXML private BorderPane panelIzq;
    @FXML private BorderPane panelDerecho;
    @FXML private TextField txtRuta;
    @FXML private TextField txtNombre;
    @FXML private TextField txtPromedio;
    @FXML private CheckBox chbNombre;
    @FXML private CheckBox chbProfesion;
    @FXML private CheckBox chbPromedio;
    @FXML private ComboBox<String> cmbProfesion;
    @FXML private Label tiempoTranscurrido;
    @FXML private Button btnBuscarDirectorio;
    @FXML private Button btnBuscar;
    @FXML private Button btnGenerar;
    @FXML private TableView<Egresado> tablaEgresados;

    public static final String ARBOL_BB = "Arbol BB";
    public static final String ARBOL_AVL = "Arbol AVL";
    public static final String ARBOL_B = "Arbol B";

    public static Comparador<LinkedList<Integer>, String> COMPARADOR_POR_NOMBRE;
    public static Comparador<LinkedList<Integer>, String> COMPARADOR_POR_PROFESION;
    public static Comparador<LinkedList<Integer>, Double> COMPARADOR_POR_PROMEDIO;

    private Arbol<LinkedList<Integer>, String> arbolNombres;
    private Arbol<LinkedList<Integer>, String> arbolProfesiones;
    private Arbol<LinkedList<Integer>, Double> arbolPromedios;

    private final SeleccionadorArchivos seleccionadorArchivos;
    private final Factory treeFactory;

    private Stage vista;
    private Egresado[] egresados;

    public VistaController()
    {
        this.seleccionadorArchivos = SeleccionadorArchivos.getInstance();
        this.treeFactory = TreeFactory.getInstance();
    }

    @Override
    public void initStage(Stage stage)
    {
        vista = stage;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        initComparadores();
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="initComparators">
    private void initComparadores()
    {
        COMPARADOR_POR_NOMBRE = (lista, nombre) -> nombre.compareTo(egresados[lista.first()].getNombre());
        COMPARADOR_POR_PROFESION = (lista, profesion) -> profesion.compareTo(egresados[lista.first()].getProfesion());
        COMPARADOR_POR_PROMEDIO = (lista, promedio) -> promedio.compareTo(egresados[lista.first()].getPromedio());
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponents()
    {
        habilitarUI(false);
        progreso.setVisible(false);

        txtPromedio.setTextFormatter(new TextFormatter<>(new FormatterPromedio()));

        txtNombre.disableProperty().bind(chbNombre.selectedProperty().not());
        cmbProfesion.disableProperty().bind(chbProfesion.selectedProperty().not());
        txtPromedio.disableProperty().bind(chbPromedio.selectedProperty().not());

        txtRuta.setText("data/Egresados.csv");

        rbArbolBB.setId(ARBOL_BB);
        rbArbolAVL.setId(ARBOL_AVL);
        rbArbolB.setId(ARBOL_B);

        tablaEgresados.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tablaEgresados.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("profesion"));
        tablaEgresados.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("promedio"));
    }// </editor-fold>

    @FXML private void accionBtnBuscarDirectorio(ActionEvent e)
    {
        File file = seleccionadorArchivos.seleccionarArchivo(vista, "csv and xls files", "*.csv", "*.xls");

        if (file != null)
            txtRuta.setText(file.getAbsolutePath());
    }

    @FXML private void accionBtnGenerar(ActionEvent e)
    {
        habilitarCheckBoxes(false);
        btnBuscarDirectorio.setDisable(true);
        btnGenerar.setDisable(true);
        progreso.setVisible(true);
        crearArboles();
        rellenarArboles();
    }

    @FXML private void accionBtnBuscar(ActionEvent e)
    {
        if (ningunaOpcionSeleccionada())
            mostrarTodosEgresados();

        else
            buscarCoincidencias();
    }

    private void crearArboles()
    {
        String arbolACrear = getArbolSeleccionado();
        arbolNombres = treeFactory.crearArbolNombres(arbolACrear);
        arbolProfesiones = treeFactory.crearArbolProfesiones(arbolACrear);
        arbolPromedios = treeFactory.crearArbolPromedios(arbolACrear);
    }

    private void rellenarArboles()
    {
        Service<Long> backgroundTask = new Service<Long>()
        {
            @Override
            protected Task<Long> createTask()
            {
                return new Task<Long>()
                {
                    @Override
                    protected Long call() throws Exception
                    {
                        long before = System.currentTimeMillis();

                        try
                        {
                            cargarEgresados();

                            for (int i = 0; i < egresados.length; i++)
                            {
                                arbolNombres.insertar(i, egresados[i].getNombre());
                                arbolProfesiones.insertar(i, egresados[i].getProfesion());
                                arbolPromedios.insertar(i, egresados[i].getPromedio());
                            }

                        } catch (RutaInvalidaException | ArchivoExtranoException ex)
                        {
                            btnGenerar.setDisable(false);
                            btnBuscarDirectorio.setDisable(false);
                            progreso.setVisible(false);
                            reiniciarUI();
                            mostrarError("Error", ex.getMessage());
                            throw new Exception();
                        }

                        return System.currentTimeMillis() - before;
                    }
                };
            }
        };

        backgroundTask.setOnSucceeded(this::esperarLlenadoDeArboles);
        backgroundTask.start();
    }

    private void esperarLlenadoDeArboles(WorkerStateEvent e)
    {
        setTiempoTranscurrido(e.getSource().getValue() + " milisegundos.");
        cargarDatosCmbProfesiones();
        btnBuscarDirectorio.setDisable(false);
        btnGenerar.setDisable(false);
        progreso.setVisible(false);
        habilitarUI(true);
        mostrarTodosEgresados();
    }

    private void buscarCoincidencias()
    {
        Service<Long> service = new Service<Long>()
        {
            @Override
            protected Task<Long> createTask()
            {
                return new Task<Long>()
                {
                    @Override
                    protected Long call() throws Exception
                    {
                        long before = System.currentTimeMillis();

                        try
                        {
                            Buscador buscador = new Buscador(arbolNombres, arbolProfesiones, arbolPromedios);
                            mostrarResultadosBusqueda(buscador.realizarBusqueda(
                                    nombreSeleccionado(), profesionSeleccionado(), promedioSeleccionado(),
                                    getNombreEgresado(), getProfesionEgresado(), promedioSeleccionado() ? getPromedioEgresado() : 0));

                        } catch (ItemNotFoundException | NohayCoincidenciasException | NumberFormatException ex)
                        {
                            vaciarTablaEgresados();
                            mostrarError("Error", ex.getMessage());
                            throw new Exception();

                        } catch (NullPointerException ex)
                        {
                            mostrarError("Error", "Algún campo es incorrecto.");
                            throw new Exception();
                        }

                        return System.currentTimeMillis() - before;
                    }
                };
            }
        };

        service.setOnSucceeded(this::esperarBusquedaDeCoincidencias);
        service.start();
    }

    private void esperarBusquedaDeCoincidencias(WorkerStateEvent e)
    {
        setTiempoTranscurrido(e.getSource().getValue() + " milisegundos.");
    }

    private void cargarEgresados()
    {
        Loader<ArrayList<Egresado>> dao = new DAO(getRutaCSV());
        ArrayList<Egresado> lista = dao.load();
        egresados = new Egresado[lista.size()];

        for (int i = 0; i < egresados.length; i++)
            egresados[i] = lista.get(i);
    }

    private void cargarDatosCmbProfesiones()
    {
        Platform.runLater(() ->
        {
            vaciarComboBoxProfesiones();
            LinkedList<LinkedList<Integer>> listaIdxProfesiones = arbolProfesiones.inorder();

            while (!listaIdxProfesiones.isEmpty())
                cmbProfesion.getItems().add(egresados[listaIdxProfesiones.removeFirst().first()].getProfesion());

            cmbProfesion.getSelectionModel().select(0);
        });
    }

    private void mostrarTodosEgresados()
    {
        Platform.runLater(() ->
        {
            vaciarTablaEgresados();
            tablaEgresados.getItems().addAll(Arrays.asList(egresados));
        });
    }

    private void mostrarResultadosBusqueda(LinkedList<Integer> resultados)
    {
        Platform.runLater(() ->
        {
            vaciarTablaEgresados();

            while (!resultados.isEmpty())
                tablaEgresados.getItems().add(egresados[resultados.removeFirst()]);
        });
    }

    private String getRutaCSV()
    {
        return txtRuta.getText().trim();
    }

    private String getArbolSeleccionado()
    {
        return ((Styleable) grupoRadioButtons.getSelectedToggle()).getId();
    }

    private String getNombreEgresado()
    {
        return txtNombre.getText().trim();
    }

    private String getProfesionEgresado()
    {
        return cmbProfesion.getSelectionModel().getSelectedItem();
    }

    private double getPromedioEgresado()
    {
        return Double.parseDouble(txtPromedio.getText());
    }

    private boolean nombreSeleccionado()
    {
        return chbNombre.isSelected();
    }

    private boolean profesionSeleccionado()
    {
        return chbProfesion.isSelected();
    }

    private boolean promedioSeleccionado()
    {
        return chbPromedio.isSelected();
    }

    private boolean ningunaOpcionSeleccionada()
    {
        return !(nombreSeleccionado() || profesionSeleccionado() || promedioSeleccionado());
    }

    private void habilitarCheckBoxes(boolean habilitar)
    {
        chbNombre.setSelected(habilitar);
        chbProfesion.setSelected(habilitar);
        chbPromedio.setSelected(habilitar);
    }

    private void habilitarUI(boolean habilitar)
    {
        panelIzq.setDisable(!habilitar);
        panelDerecho.setDisable(!habilitar);
    }

    private void reiniciarUI()
    {
        Platform.runLater(() ->
        {
            habilitarUI(false);
            habilitarCheckBoxes(false);

            txtNombre.setText("");
            txtPromedio.setText("");

            vaciarComboBoxProfesiones();
            vaciarTablaEgresados();
        });
    }

    private void setTiempoTranscurrido(String tiempo)
    {
        Platform.runLater(() -> tiempoTranscurrido.setText(tiempo));
    }

    private void vaciarTablaEgresados()
    {
        tablaEgresados.getItems().clear();
    }

    private void vaciarComboBoxProfesiones()
    {
        cmbProfesion.getItems().clear();
    }

    private void mostrarError(String title, String text)
    {
        Platform.runLater(() ->
        {
            Toolkit.getDefaultToolkit().beep();
            Notificacion notificacion = new Notificacion(panelPrincipal, title, text);
            notificacion.setCloseAction(e -> panelPrincipal.getChildren().remove(notificacion));
            notificacion.mostrar();
        });
    }

}
