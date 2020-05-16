package com.sw.controller;

import com.sw.model.Arco;
import com.sw.model.Factory;
import com.sw.model.Grafo;
import com.sw.model.GraphFactory;
import com.sw.model.exceptions.ArcoNoExistenteException;
import com.sw.model.exceptions.GrafoLlenoException;
import com.sw.model.exceptions.NoHayCoincidenciasException;
import com.sw.model.exceptions.VerticeNoExistenteException;
import com.sw.model.exceptions.VerticeYaExisteException;
import com.sw.view.GraficoGrafo;
import com.sw.view.GraficoRecorrido;
import com.sw.view.Vista;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Nicolás
 */
public class VistaController
{

    public static final String GRAFO_MATRIZ_ADYACENCIA = "matriz";
    public static final String GRAFO_LISTA_ADYACENCIA = "lista";

    public static final String ANCHURA = "anchura";
    public static final String PROFUNDIDAD = "profundidad";

    private final Vista vista;
    private final Factory graphFactory;
    private final GraficoRecorrido graficoRecorrido;

    private GraphController graphController;
    private GraficoGrafo graficoGrafo;
    private Grafo<String> grafo;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        this.graphFactory = GraphFactory.getInstance();
        this.graficoRecorrido = GraficoRecorrido.getInstance();
        initComponents();
    }

    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponents()
    {
        vista.getBtnGenerar().addActionListener(this::accionBtnGenerarGrafo);
        vista.getBtnCrearVertice().addActionListener(this::accionBtnAnadirVertice);
        vista.getBtnCrearArco().addActionListener(this::accionBtnCrearArco);
        vista.getBtnRecorrerGrafo().addActionListener(this::accionBtnRecorrer);
        vista.getBtnEliminarVertice().addActionListener(this::accionBtnEliminarVertice);
        vista.getBtnEliminarArco().addActionListener(this::accionBtnEliminarArco);
        vista.getBtnChecarAdyacencia().addActionListener(this::accionBtnChecarAdyacencia);
        vista.getBtnBuscar().addActionListener(this::accionBtnBuscar);

        vista.getPanelRecorrido().add(graficoRecorrido, BorderLayout.CENTER);
        vista.getPanelRecorrido().revalidate();
        vista.addComponentListener(new ComponentAdapter()
        {
            @Override
            public void componentResized(ComponentEvent e)
            {
                vista.requestFocus();
            }
        });

        setUpTextField(vista.getTxtNuevoVertice(), vista.getBtnCrearVertice());
        setUpTextField(vista.getTxtCrearArcoFrom(), vista.getBtnCrearArco());
        setUpTextField(vista.getTxtCrearArcoTo(), vista.getBtnCrearArco());
        setUpTextField(vista.getTxtEliminarVertice(), vista.getBtnEliminarVertice());
        setUpTextField(vista.getTxtEliminarArcoFrom(), vista.getBtnEliminarArco());
        setUpTextField(vista.getTxtEliminarArcoTo(), vista.getBtnEliminarArco());
        setUpTextField(vista.getTxtAdyacenciaArcoFrom(), vista.getBtnChecarAdyacencia());
        setUpTextField(vista.getTxtAdyacenciaArcoTo(), vista.getBtnChecarAdyacencia());
        setUpTextField(vista.getTxtVerticeBuscar(), vista.getBtnBuscar());

        setPanelEnabled(vista.getPanelManipulacionGrafo(), false);
    }

    private void setUpTextField(JTextField textField, JButton button)
    {
        textField.addKeyListener(new KeyAdapter()
        {
            @Override
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    button.doClick();
            }
        });
    }// </editor-fold>

    private void accionBtnGenerarGrafo(ActionEvent e)
    {
        generarGrafo();
        setPanelEnabled(vista.getPanelManipulacionGrafo(), true);
        setPanelEnabled(vista.getPanelPreparacion(), false);
    }

    private void accionBtnAnadirVertice(ActionEvent e)
    {
        try
        {
            grafo.nuevoVertice(getNuevoVertice());
            graphController.anadirVerticeAlGrafico();
            limpiarGraficos();

        } catch (GrafoLlenoException | VerticeYaExisteException ex)
        {
            mostrarMensaje("Error!", ex.getMessage());
            limpiarGraficos();
        }
    }

    private void accionBtnCrearArco(ActionEvent e)
    {
        try
        {
            grafo.nuevoArco(getCrearArcoFrom(), getCrearArcoTo());
            limpiarGraficos();

        } catch (VerticeNoExistenteException ex)
        {
            mostrarMensaje("Error!", ex.getMessage());
            limpiarGraficos();
        }
    }

    private void accionBtnEliminarVertice(ActionEvent e)
    {
        try
        {
            int nVertice = grafo.eliminarVertice(getVerticeAEliminar());
            graphController.eliminarCoordenadasVertice(nVertice);
            limpiarGraficos();

        } catch (VerticeNoExistenteException ex)
        {
            mostrarMensaje("Error!", ex.getMessage());
        }
    }

    private void accionBtnEliminarArco(ActionEvent e)
    {
        try
        {
            grafo.eliminarArco(getEliminarArcoFrom(), getEliminarArcoTo());
            limpiarGraficos();

        } catch (ArcoNoExistenteException | VerticeNoExistenteException ex)
        {
            mostrarMensaje("Error!", ex.getMessage());
            limpiarGraficos();
        }
    }

    private void accionBtnBuscar(ActionEvent e)
    {
        limpiarGraficos();

        try
        {
            switch (getRadioButtonSeleccionado(vista.getGrupoBusqueda()))
            {
                case ANCHURA:
                    int idxAnchura = grafo.buscarAnchura(getVerticeBuscar());
                    graficoGrafo.setVerticeMarcado(idxAnchura);
                    break;
                case PROFUNDIDAD:
                    int idxProfundidad = grafo.buscarProfundidad(getVerticeBuscar());
                    graficoGrafo.setVerticeMarcado(idxProfundidad);
                    break;
                default:
                    throw new AssertionError();
            }

            mostrarMensaje("Encontrado!", "Se ha encontrado el vértice!");

        } catch (NoHayCoincidenciasException ex)
        {
            mostrarMensaje("Error!", ex.getMessage());
            limpiarGraficos();
        }
    }

    private void accionBtnRecorrer(ActionEvent e)
    {
        switch (getRadioButtonSeleccionado(vista.getGrupoRecorrido()))
        {
            case ANCHURA:
                graficoRecorrido.actualizarElementos(grafo.recorridoAnchura());
                break;
            case PROFUNDIDAD:
                graficoRecorrido.actualizarElementos(grafo.recorridoProfundidad());
                break;
            default:
                throw new AssertionError();
        }

        graficoRecorrido.repaint();
    }

    private void accionBtnChecarAdyacencia(ActionEvent e)
    {
        try
        {
            Arco arco = grafo.getArco(getAdyacenciaArcoFrom(), getAdyacenciaArcoTo());
            limpiarGraficos();
            graficoGrafo.setArcoMarcado(arco);
            mostrarMensaje("Enhorabuena!", "Los vértices son adyacentes!");

        } catch (ArcoNoExistenteException | VerticeNoExistenteException ex)
        {
            mostrarMensaje("Error!", ex.getMessage());
            limpiarGraficos();
        }
    }

    private void generarGrafo()
    {
        grafo = graphFactory.createGraph(getRadioButtonSeleccionado(vista.getGrupoCreacionGrafo()));
        this.graficoGrafo = new GraficoGrafo(grafo);
        vista.getPanelGraficoGrafo().add(graficoGrafo, BorderLayout.CENTER);

        graphController = new GraphController(grafo, graficoGrafo, graficoRecorrido);
        vista.getPanelGraficoGrafo().revalidate();
        graficoGrafo.requestFocus();

        /*EventQueue.invokeLater(() ->
        {
            grafo.nuevoVertice("Nicolás");
            graphController.anadirVerticeAlGrafico();
            grafo.nuevoVertice("Antonio");
            graphController.anadirVerticeAlGrafico();
            grafo.nuevoVertice("Javier");
            graphController.anadirVerticeAlGrafico();
            grafo.nuevoVertice("Carlos");
            graphController.anadirVerticeAlGrafico();
            grafo.nuevoVertice("Juan");
            graphController.anadirVerticeAlGrafico();
            grafo.nuevoVertice("Eusebio");
            graphController.anadirVerticeAlGrafico();
            grafo.nuevoVertice("Emmanuel");
            graphController.anadirVerticeAlGrafico();

            grafo.nuevoArco("Nicolás", "Antonio");
            grafo.nuevoArco("Nicolás", "Carlos");
            grafo.nuevoArco("Carlos", "Antonio");
            grafo.nuevoArco("Carlos", "Javier");
            grafo.nuevoArco("Nicolás", "Juan");
            grafo.nuevoArco("Juan", "Emmanuel");
            grafo.nuevoArco("Eusebio", "Emmanuel");
        });*/
    }

    private String getNuevoVertice()
    {
        return vista.getTxtNuevoVertice().getText().trim();
    }

    private String getCrearArcoFrom()
    {
        return vista.getTxtCrearArcoFrom().getText().trim();
    }

    private String getCrearArcoTo()
    {
        return vista.getTxtCrearArcoTo().getText().trim();
    }

    private String getVerticeAEliminar()
    {
        return vista.getTxtEliminarVertice().getText().trim();
    }

    private String getEliminarArcoFrom()
    {
        return vista.getTxtEliminarArcoFrom().getText().trim();
    }

    private String getEliminarArcoTo()
    {
        return vista.getTxtEliminarArcoTo().getText().trim();
    }

    private String getAdyacenciaArcoFrom()
    {
        return vista.getTxtAdyacenciaArcoFrom().getText().trim();
    }

    private String getAdyacenciaArcoTo()
    {
        return vista.getTxtAdyacenciaArcoTo().getText().trim();
    }

    private String getVerticeBuscar()
    {
        return vista.getTxtVerticeBuscar().getText().trim();
    }

    private String getRadioButtonSeleccionado(ButtonGroup grupo)
    {
        for (Enumeration<AbstractButton> buttons = grupo.getElements(); buttons.hasMoreElements();)
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

    private void limpiarGraficos()
    {
        graficoGrafo.quitarVerticeMarcado();
        graficoGrafo.quitarArcoMarcado();
        graficoGrafo.repaint();
        graficoRecorrido.repaint();
    }

    private void mostrarMensaje(String titulo, String text)
    {
        JOptionPane.showMessageDialog(vista, text, titulo, JOptionPane.ERROR_MESSAGE);
    }

}
