package com.sw.controller;

import com.sw.model.ArbolBinario;
import com.sw.model.DAO;
import com.sw.model.Egresado;
import com.sw.util.LinkedList;
import com.sw.view.UIConstants;
import com.sw.view.Vista;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.io.File;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.AbstractButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

/**
 *
 * @author Nicolás
 */
public class VistaController implements UIConstants
{

    public static final String ARBOL_BB = "Arbol BB";
    public static final String ARBOL_AVL = "Arbol AVL";
    public static final String ARBOL_B = "Arbol B";

    public static Comparador<LinkedList<Integer>, String> COMPARADOR_POR_NOMBRE;
    public static Comparador<LinkedList<Integer>, String> COMPARADOR_POR_PROFESION;
    public static Comparador<LinkedList<Integer>, Double> COMPARADOR_POR_PROMEDIO;

    private ArbolBinario<LinkedList<Integer>, String> arbolNombres;
    private ArbolBinario<LinkedList<Integer>, String> arbolProfesiones;
    private ArbolBinario<LinkedList<Integer>, Double> arbolPromedios;

    private final Vista vista;
    private final SeleccionadorArchivos seleccionadorArchivos;
    private final TreeFactory myTreeFactory;
    private final ComboBoxManager comboBoxManager;
    private final TableManager tableManager;
    private Egresado[] egresados;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        this.myTreeFactory = new TreeFactory();
        this.seleccionadorArchivos = SeleccionadorArchivos.getInstance();
        this.comboBoxManager = ComboBoxManager.getInstance();
        this.tableManager = TableManager.getInstance();

        COMPARADOR_POR_NOMBRE = (lista, nombre) -> nombre.compareTo(egresados[lista.first()].getNombre().trim());
        COMPARADOR_POR_PROFESION = (lista, profesion) -> profesion.compareTo(egresados[lista.first()].getProfesion().trim());
        COMPARADOR_POR_PROMEDIO = (lista, promedio) -> promedio.compareTo(egresados[lista.first()].getPromedio());

        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponents()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), false);
        setPanelEnabled(vista.getPanelLateralDer(), false);
        vista.getProgressBar().setEnabled(true);
        setProgressBarVisible(false);

        vista.getTxtDireccion().setText("data/Egresados.csv");
        vista.getTxtNombre().setText("Voctor");

        vista.getBtnBuscarDirectorio().addActionListener(this::accionBtnBuscarDirectorio);
        vista.getBtnGenerar().addActionListener(this::accionBtnGenerarArbol);
        vista.getBtnBuscar().addActionListener(this::accionBtnBuscar);

        vista.getChbNombre().addActionListener(e -> vista.getTxtNombre().setEnabled(vista.getChbNombre().isSelected()));
        vista.getChbProfesion().addActionListener(e -> vista.getCmbProfesiones().setEnabled(vista.getChbProfesion().isSelected()));
        vista.getChbPromedio().addActionListener(e -> vista.getTxtPromedio().setEnabled(vista.getChbPromedio().isSelected()));

    }// </editor-fold>

    private void accionBtnBuscarDirectorio(ActionEvent e)
    {
        File file = seleccionadorArchivos.seleccionarArchivo(vista, "csv and xls files", "csv", "xls");
        vista.getTxtDireccion().setText(file.getAbsolutePath());
    }

    private void accionBtnGenerarArbol(ActionEvent e)
    {
        setBtnGenerarEnabled(false);
        setProgressBarVisible(true);
        cargarEgresados();
        crearArboles();
        rellenarArboles();
    }

    private void accionBtnBuscar(ActionEvent e)
    {
        realizarBusqueda();
    }

    private void crearArboles()
    {
        switch (getTipoArbolSeleccionado())
        {
            case ARBOL_BB:
                arbolNombres = myTreeFactory.crearArbolNombres(ARBOL_BB);
                arbolProfesiones = myTreeFactory.crearArbolProfesiones(ARBOL_BB);
                arbolPromedios = myTreeFactory.crearArbolPromedio(ARBOL_BB);
                break;
            case ARBOL_AVL:
            case ARBOL_B:
            default:
                throw new AssertionError();
        }
    }

    private void rellenarArboles()
    {
        Service service = new Service(egresados, arbolNombres, arbolProfesiones, arbolPromedios);
        service.addPropertyChangeListener(this::esperarLlenadoDeArboles);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(service);
        executorService.shutdown();
    }

    public void esperarLlenadoDeArboles(PropertyChangeEvent e)
    {
        if (e.getNewValue() == SwingWorker.StateValue.DONE)
            try
            {
                setTiempoTranscurrido(((Service) e.getSource()).get() + " milisegundos.");
                cargarDatosCmbProfesiones();
                setBtnGenerarEnabled(true);
                setProgressBarVisible(false);
                actualizarUI();
                cargarDatosTabla(egresados);

            } catch (InterruptedException | ExecutionException ex)
            {
                System.out.println(ex.getMessage());
            }
    }

    private void realizarBusqueda()
    {
        boolean buscarCoincidencias = false;

        if (nombreSeleccionado())
        {
            buscarCoincidencias = true;
            LinkedList<Integer> lista = arbolNombres.buscar(getNombreEgresado());
            System.out.println(lista);
        }

        if (profesionSeleccionado())
        {

        }

        if (promedioSeleccionado())
        {

        }
    }

    private void cargarEgresados()
    {
        DAO dao = new DAO(getRutaCSV());
        egresados = dao.loadData();
    }

    private void cargarDatosCmbProfesiones()
    {
        comboBoxManager.vaciarComboBox(vista.getCmbProfesiones());
        LinkedList<LinkedList<Integer>> listaIdxProfesiones = new LinkedList<>();
        arbolProfesiones.inorder(listaIdxProfesiones);

        while (!listaIdxProfesiones.isEmpty())
            comboBoxManager.anadirElementoAlComboBox(vista.getCmbProfesiones(),
                    egresados[listaIdxProfesiones.removeFirst().first()].getProfesion());
    }

    private void cargarDatosTabla(Egresado[] egresados)
    {
        tableManager.vaciarTabla(vista.getTablaEgresados());

        for (Egresado egresado : egresados)
            tableManager.anadirFila(vista.getTablaEgresados(), new Object[]
            {
                egresado.getNombre(), egresado.getProfesion(), egresado.getPromedio()
            });
    }

    private String getRutaCSV()
    {
        return vista.getTxtDireccion().getText().trim();
    }

    private String getNombreEgresado()
    {
        return vista.getTxtNombre().getText().trim();
    }

    private String getProfesionEgresado()
    {
        return vista.getCmbProfesiones().getSelectedItem().toString();
    }

    private double getPromedioEgresado()
    {
        return (double) vista.getTxtPromedio().getValue();
    }

    private boolean nombreSeleccionado()
    {
        return vista.getChbNombre().isSelected();
    }

    private boolean profesionSeleccionado()
    {
        return vista.getChbProfesion().isSelected();
    }

    private boolean promedioSeleccionado()
    {
        return vista.getChbPromedio().isSelected();
    }

    private boolean todasOpcionesSeleccionadas()
    {
        return nombreSeleccionado() && profesionSeleccionado() && promedioSeleccionado();
    }

    private String getTipoArbolSeleccionado()
    {
        for (Enumeration<AbstractButton> buttons = vista.getGrupoRadioButtons().getElements(); buttons.hasMoreElements();)
        {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected())
                return button.getActionCommand();
        }

        return null;
    }

    private void setTiempoTranscurrido(String texto)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getTiempoTranscurrido().setText(texto);
        });
    }

    private void setProgressBarVisible(boolean enable)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getProgressBar().setVisible(enable);
        });
    }

    private void setBtnGenerarEnabled(boolean enable)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getBtnGenerar().setEnabled(enable);
        });
    }

    private void actualizarUI()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), true);
        setPanelEnabled(vista.getPanelLateralDer(), true);

        vista.getTxtNombre().setEnabled(false);
        vista.getCmbProfesiones().setEnabled(false);
        vista.getTxtPromedio().setEnabled(false);
    }

    private void setPanelEnabled(JPanel panel, boolean isEnabled)
    {
        panel.setEnabled(isEnabled);

        Component[] components = panel.getComponents();

        for (Component component : components)
        {
            if (component instanceof JPanel)
                setPanelEnabled((JPanel) component, isEnabled);

            component.setEnabled(isEnabled);
        }
    }

}
