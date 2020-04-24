package com.sw.controller;

import com.sw.model.Comparador;
import com.sw.model.ArbolBinario;
import com.sw.model.Buscador;
import com.sw.model.Egresado;
import com.sw.model.exceptions.ItemNotFoundException;
import com.sw.model.exceptions.NohayCoincidenciasException;
import com.sw.model.exceptions.RutaInvalidaException;
import com.sw.model.persistence.DAO;
import com.sw.model.persistence.Loader;
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
import javax.swing.JOptionPane;
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

        initComparators();
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="initComparators">
    private void initComparators()
    {
        COMPARADOR_POR_NOMBRE = (lista, nombre) -> nombre.compareTo(egresados[lista.first()].getNombre());
        COMPARADOR_POR_PROFESION = (lista, profesion) -> profesion.compareTo(egresados[lista.first()].getProfesion());
        COMPARADOR_POR_PROMEDIO = (lista, promedio) -> promedio.compareTo(egresados[lista.first()].getPromedio());
    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponents()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), false);
        setPanelEnabled(vista.getPanelLateralDer(), false);
        vista.getProgressBar().setEnabled(true);
        setProgressBarVisible(false);

        vista.getTxtPromedio().setFormatterFactory(new PromedioFormatter());
        vista.getTxtDireccion().setText("data/Egresados.csv");
        vista.getTxtNombre().setText("Emmanuel");

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

        if (file != null)
            vista.getTxtDireccion().setText(file.getAbsolutePath());
    }

    private void accionBtnGenerarArbol(ActionEvent e)
    {
        habilitarCheckBoxes(false);
        setBtnBuscarDirectorioEnabled(false);
        setBtnGenerarEnabled(false);
        setProgressBarVisible(true);
        crearArboles();
        rellenarArboles();
    }

    private void accionBtnBuscar(ActionEvent e)
    {
        if (ningunOpcionSeleccionada())
            mostrarTodosEgresados();

        else
            buscarCoincidencias();
    }

    private void crearArboles()
    {
        String arbolACrear = getTipoArbolSeleccionado();
        arbolNombres = myTreeFactory.crearArbolNombres(arbolACrear);
        arbolProfesiones = myTreeFactory.crearArbolProfesiones(arbolACrear);
        arbolPromedios = myTreeFactory.crearArbolPromedios(arbolACrear);
    }

    private void rellenarArboles()
    {
        SwingWorker<Long, Long> backgroundTask = new SwingWorker<Long, Long>()
        {
            @Override
            protected Long doInBackground() throws Exception
            {
                long now = System.currentTimeMillis();

                try
                {
                    cargarEgresados();

                    for (Egresado egresado : egresados)
                        System.out.println(egresado.getPromedio());

                    for (int i = 0; i < egresados.length; i++)
//                        arbolNombres.insertar(i, egresados[i].getNombre());
//                        arbolProfesiones.insertar(i, egresados[i].getProfesion());
                        arbolPromedios.insertar(i, egresados[i].getPromedio());

                } catch (RutaInvalidaException ex)
                {
                    setBtnBuscarDirectorioEnabled(true);
                    setBtnGenerarEnabled(true);
                    setProgressBarVisible(false);
                    mostrarError("Error", ex.getMessage());
                    throw new Exception();
                }

                return System.currentTimeMillis() - now;
            }
        };

        backgroundTask.addPropertyChangeListener(this::esperarLlenadoDeArboles);
        ejecutarTareaEnSegundoPlano(backgroundTask);
    }

    public void esperarLlenadoDeArboles(PropertyChangeEvent e)
    {
        try
        {
            if (e.getNewValue() == SwingWorker.StateValue.DONE)
            {
                setTiempoTranscurrido(((SwingWorker<?, ?>) e.getSource()).get() + " milisegundos.");
                cargarDatosCmbProfesiones();
                setBtnBuscarDirectorioEnabled(true);
                setBtnGenerarEnabled(true);
                setProgressBarVisible(false);
                habilitarUI();
                mostrarTodosEgresados();
            }

        } catch (InterruptedException | ExecutionException ex)
        {
            ex.printStackTrace();
        }
    }

    private void buscarCoincidencias()
    {
        SwingWorker<Long, Long> backgroundTask = new SwingWorker<Long, Long>()
        {
            @Override
            protected Long doInBackground() throws Exception
            {
                long now = System.currentTimeMillis();

                try
                {
                    Buscador buscador = new Buscador(arbolNombres, arbolProfesiones, arbolPromedios);
                    mostrarResultadosBusqueda(buscador.realizarBusqueda(
                            nombreSeleccionado(), profesionSeleccionado(), promedioSeleccionado(),
                            getNombreEgresado(), getProfesionEgresado(), promedioSeleccionado() ? getPromedioEgresado() : 0));

                } catch (ItemNotFoundException | NohayCoincidenciasException ex)
                {
                    vaciarTablaEgresados();
                    mostrarError("Error", ex.getMessage());
                    throw new Exception();

                } catch (NullPointerException ex)
                {
                    mostrarError("Error", "Algún campo es incorrecto.");
                    throw new Exception();
                }

                return System.currentTimeMillis() - now;
            }
        };

        backgroundTask.addPropertyChangeListener(this::esperarBusquedaDeCoincidencias);
        ejecutarTareaEnSegundoPlano(backgroundTask);
    }

    private void esperarBusquedaDeCoincidencias(PropertyChangeEvent e)
    {
        try
        {
            if (e.getNewValue() == SwingWorker.StateValue.DONE)
                setTiempoTranscurrido(((SwingWorker<?, ?>) e.getSource()).get() + " milisegundos.");

        } catch (InterruptedException | ExecutionException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private void ejecutarTareaEnSegundoPlano(Runnable tarea)
    {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(tarea);
        executorService.shutdown();
    }

    private void cargarEgresados()
    {
        Loader<Egresado[]> dao = new DAO(getRutaCSV());
        egresados = dao.load();
    }

    private void cargarDatosCmbProfesiones()
    {
        comboBoxManager.vaciarComboBox(vista.getCmbProfesiones());
        LinkedList<LinkedList<Integer>> listaIdxProfesiones = arbolProfesiones.inorder();

        while (!listaIdxProfesiones.isEmpty())
            comboBoxManager.anadirElemento(vista.getCmbProfesiones(), egresados[listaIdxProfesiones.removeFirst().first()].getProfesion());
    }

    private void mostrarTodosEgresados()
    {
        vaciarTablaEgresados();

        for (Egresado egresado : egresados)
            tableManager.anadirFila(vista.getTablaEgresados(), new Object[]
            {
                egresado.getNombre(), egresado.getProfesion(), egresado.getPromedio()
            });
    }

    private void mostrarResultadosBusqueda(LinkedList<Integer> resultados)
    {
        vaciarTablaEgresados();

        while (!resultados.isEmpty())
        {
            Egresado egresado = egresados[resultados.removeFirst()];
            tableManager.anadirFila(vista.getTablaEgresados(), new Object[]
            {
                egresado.getNombre(), egresado.getProfesion(), egresado.getPromedio()
            });
        }
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

    private boolean ningunOpcionSeleccionada()
    {
        return !(nombreSeleccionado() || profesionSeleccionado() || promedioSeleccionado());
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

    private void setBtnBuscarDirectorioEnabled(boolean enable)
    {
        EventQueue.invokeLater(() ->
        {
            vista.getBtnBuscarDirectorio().setEnabled(enable);
        });
    }

    private void habilitarUI()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), true);
        setPanelEnabled(vista.getPanelLateralDer(), true);

        vista.getTxtNombre().setEnabled(false);
        vista.getCmbProfesiones().setEnabled(false);
        vista.getTxtPromedio().setEnabled(false);
    }

    private void inhabilitarUI()
    {
        setPanelEnabled(vista.getPanelLateralIzq(), false);
        setPanelEnabled(vista.getPanelLateralDer(), false);

        tableManager.vaciarTabla(vista.getTablaEgresados());
        comboBoxManager.vaciarComboBox(vista.getCmbProfesiones());

        vista.getTxtNombre().setText("");
        vista.getTxtPromedio().setText("");

        habilitarCheckBoxes(false);
    }

    private void habilitarCheckBoxes(boolean habilitar)
    {
        vista.getChbNombre().setSelected(habilitar);
        vista.getChbProfesion().setSelected(habilitar);
        vista.getChbPromedio().setSelected(habilitar);
    }

    private void mostrarError(String titulo, String text)
    {
        JOptionPane.showMessageDialog(vista, text, titulo, JOptionPane.ERROR_MESSAGE);
    }

    private void vaciarTablaEgresados()
    {
        tableManager.vaciarTabla(vista.getTablaEgresados());
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
