package com.sw.controller;

import com.sw.model.Factory;
import com.sw.model.Grafo;
import com.sw.model.GraphFactory;
import com.sw.model.exceptions.GrafoLlenoException;
import com.sw.model.exceptions.VerticeNoExistenteException;
import com.sw.model.exceptions.VerticeYaExisteException;
import com.sw.view.GraficoGrafo;
import com.sw.view.GraficoRecorrido;
import com.sw.view.Vista;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
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

    private GraficoGrafo graficoGrafo;
    private Grafo<String> grafo;

    public VistaController(Vista vista)
    {
        this.vista = vista;
        this.graphFactory = GraphFactory.getInstance();
        this.graficoRecorrido = GraficoRecorrido.getInstance();
        initComponents();
    }

    private void initComponents()
    {
        vista.getBtnGenerar().addActionListener(this::accionBtnGenerarGrafo);
        vista.getBtnCrearVertice().addActionListener(this::accionBtnAnadirVertice);
        vista.getBtnCrearArco().addActionListener(this::accionBtnCrearArco);
        vista.getBtnRecorrerGrafo().addActionListener(this::accionBtnRecorrer);

        vista.getPanelRecorrido().add(graficoRecorrido, BorderLayout.CENTER);
        vista.getPanelRecorrido().revalidate();

        setUpTextField(vista.getTxtNuevoVertice(), vista.getBtnCrearVertice());

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

    }

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
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            graficoGrafo.repintarGrafico();
            graficoRecorrido.limpiarGrafico();

        } catch (GrafoLlenoException | VerticeYaExisteException ex)
        {
            mostrarError("Error!", ex.getMessage());
        }
    }

    private void accionBtnCrearArco(ActionEvent e)
    {
        try
        {
            grafo.nuevoArco(getCrearArcoFrom(), getCrearArcoTo());
            graficoGrafo.repintarGrafico();
            graficoRecorrido.limpiarGrafico();

        } catch (VerticeNoExistenteException ex)
        {
            mostrarError("Error!", ex.getMessage());
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

        graficoRecorrido.repintarGrafico();
    }

    private void generarGrafo()
    {
        grafo = graphFactory.createGraph(getRadioButtonSeleccionado(vista.getGrupoCreacionGrafo()));
        this.graficoGrafo = new GraficoGrafo(grafo);
        vista.getPanelGraficoGrafo().add(graficoGrafo, BorderLayout.CENTER);
        GraphMouseManager graphMouseManager = new GraphMouseManager(graficoGrafo);
        graficoGrafo.addMouseListener(graphMouseManager);
        graficoGrafo.addMouseMotionListener(graphMouseManager);
        vista.getPanelGraficoGrafo().revalidate();

        EventQueue.invokeLater(() ->
        {
            grafo.nuevoVertice("Nicolás");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Antonio");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Javier");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Carlos");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Juan");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Eusebio");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Emmanuel");
            graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);

            grafo.nuevoArco("Nicolás", "Antonio");
            grafo.nuevoArco("Nicolás", "Carlos");
            grafo.nuevoArco("Carlos", "Antonio");
            grafo.nuevoArco("Carlos", "Javier");
            grafo.nuevoArco("Nicolás", "Juan");
            grafo.nuevoArco("Juan", "Emmanuel");
            grafo.nuevoArco("Eusebio", "Emmanuel");
        });

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

    private void mostrarError(String titulo, String text)
    {
        JOptionPane.showMessageDialog(vista, text, titulo, JOptionPane.ERROR_MESSAGE);
    }

}
