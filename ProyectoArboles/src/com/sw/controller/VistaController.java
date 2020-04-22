package com.sw.controller;

import com.sw.model.ArbolBinario;
import com.sw.model.DAO;
import com.sw.model.Egresado;
import com.sw.util.LinkedList;
import com.sw.view.Vista;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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
public class VistaController
{

    public static final String ARBOL_BB = "Arbol BB";
    public static final String ARBOL_AVL = "Arbol AVL";
    public static final String ARBOL_B = "Arbol B";

    public static Comparador<LinkedList<Integer>, Egresado> COMPARADOR_POR_NOMBRE;
    public static Comparador<LinkedList<Integer>, Egresado> COMPARADOR_POR_PROFESION;
    public static Comparador<LinkedList<Integer>, Egresado> COMPARADOR_POR_PROMEDIO;

    private ArbolBinario<LinkedList<Integer>, Egresado> arbolNombres;
    private ArbolBinario<LinkedList<Integer>, Egresado> arbolProfesiones;
    private ArbolBinario<LinkedList<Integer>, Egresado> arbolPromedios;

    private final Vista vista;
    private final SeleccionadorArchivos seleccionadorArchivos;
    private final MyTreeFactory myTreeFactory;
    private Egresado[] egresados;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        this.seleccionadorArchivos = SeleccionadorArchivos.getInstance();
        this.myTreeFactory = MyTreeFactory.getInstance();

        COMPARADOR_POR_NOMBRE = (lista, egresado) -> egresados[lista.first()].getNombre().compareTo(egresado.getNombre());
        COMPARADOR_POR_PROFESION = (lista, egresado) -> egresados[lista.first()].getProfesion().compareTo(egresado.getProfesion());
        COMPARADOR_POR_PROMEDIO = (lista, egresado) -> egresados[lista.first()].getPromedio().compareTo(egresado.getPromedio());

        initComponents();
    }

    private void initComponents()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), false);
        setPanelEnabled(vista.getPanelLateralDer(), false);
        vista.getProgressBar().setEnabled(true);
        setProgressBarVisible(false);

        vista.getTxtDireccion().setText("data/Egresados.csv");//<--
        vista.getBtnBuscarDirectorio().addActionListener(this::accionBtnBuscarDirectorio);
        vista.getBtnGenerar().addActionListener(this::accionBtnGenerarArbol);
    }

    private void accionBtnBuscarDirectorio(ActionEvent e)
    {
        File file = seleccionadorArchivos.seleccionarArchivo(vista, "csv and xls files", "csv", "xls");
        vista.getTxtDireccion().setText(file.getAbsolutePath());
    }

    private void accionBtnGenerarArbol(ActionEvent e)
    {
        setBtnGenerarEnable(false);
        setProgressBarVisible(true);
        cargarEgresados();
        crearArboles();
        inicializarArboles();
    }

    private void cargarEgresados()
    {
        DAO dao = new DAO(getRutaCSV());
        egresados = dao.loadData();
    }

    private void inicializarArboles()
    {
        Service service = new Service(egresados, arbolNombres, arbolProfesiones, arbolPromedios);
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(service);
        executorService.shutdown();

        service.addPropertyChangeListener(e ->
        {
            if (e.getNewValue() == SwingWorker.StateValue.DONE)
                try
                {
                    System.out.println(service.get() + " nanosegundos");
                    setBtnGenerarEnable(true);
                    setProgressBarVisible(false);

                } catch (InterruptedException | ExecutionException ex)
                {
                    ex.printStackTrace();
                }
        });
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

    private String getRutaCSV()
    {
        return vista.getTxtDireccion().getText();
    }

    private void setProgressBarVisible(boolean enable)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getProgressBar().setValue(0);
            vista.getProgressBar().setVisible(enable);
        });
    }

    private void setBtnGenerarEnable(boolean enable)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getBtnGenerar().setEnabled(enable);
        });
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
