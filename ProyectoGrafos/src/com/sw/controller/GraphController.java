package com.sw.controller;

import com.sw.model.Grafo;
import com.sw.model.Vertice;
import static com.sw.model.util.Utilidades.distanciaEntreDosPuntos;
import static com.sw.model.util.Utilidades.numeroAleatorio;
import com.sw.view.GraficoGrafo;
import static com.sw.view.GraficoGrafo.RADIO_CIRCULO;
import com.sw.view.GraficoRecorrido;
import com.sw.view.UIConstants;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.event.PopupMenuEvent;

/**
 *
 * @author Nicolás
 */
public class GraphController implements UIConstants
{

    private static final String CADENA_VACIA = "";
    private static final int EDITOR_ETIQUETA_WIDTH = GraficoGrafo.DIAMETRO_CIRCULO;
    private static final int EDITOR_ETIQUETA_HEIGHT = 23;

    private final GraficoGrafo graficoGrafo;
    private final GraficoRecorrido graficoRecorrido;
    private final Grafo<String> grafo;
    private final JTextField editorNombreVertice;
    private final JPopupMenu popupMenu;

    private int nVerticeAMover;
    private int verticeSeleccionado;
    private int offsetX;
    private int offsetY;

    private boolean moviendoVertice;
    private boolean nombrandoVertice;
    private boolean anadiendoArco;

    public GraphController(Grafo<String> grafo, GraficoGrafo grafico, GraficoRecorrido graficoRecorrido)
    {
        this.grafo = grafo;
        this.graficoGrafo = grafico;
        this.graficoRecorrido = graficoRecorrido;
        this.editorNombreVertice = new JTextField();
        this.popupMenu = new JPopupMenu("Acciones emergentes");
        initComponentes();
    }

    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponentes()
    {
        editorNombreVertice.setVisible(false);
        graficoGrafo.add(editorNombreVertice);
        editorNombreVertice.setSize(EDITOR_ETIQUETA_WIDTH, EDITOR_ETIQUETA_HEIGHT);
        editorNombreVertice.addKeyListener(new KeyAdapter()
        {
            @Override public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    if (GraphController.this.nombrandoVertice)
                    {
                        nombrandoVertice = false;
                        cambiarNombreVertice(GraphController.this.verticeSeleccionado);
                        GraphController.this.quitarEditorNombreVertice();
                    }
            }
        });

        popupMenu.add(new JMenuItem(new AccionEmergente("Editar nombre", EDITAR_ICON, e -> editarNombreVertice(verticeSeleccionado))));
        popupMenu.add(new JMenuItem(new AccionEmergente("Eliminar vértice", DELETE_ICON, e -> eliminarVertice(verticeSeleccionado))));

        graficoGrafo.setComponentPopupMenu(popupMenu);

        popupMenu.addPopupMenuListener(new PopupMenuAdapter()
        {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                debeMostrarsePopupMenu();
            }
        });

        graficoGrafo.addMouseListener(new MouseAdapter()
        {
            @Override public void mouseClicked(MouseEvent e)
            {
                GraphController.this.mouseClicked(e);
            }

            @Override public void mousePressed(MouseEvent e)
            {
                GraphController.this.mousePressed(e);
            }
        });

        graficoGrafo.addMouseMotionListener(new MouseAdapter()
        {
            @Override public void mouseDragged(MouseEvent e)
            {
                GraphController.this.mouseDragged(e);
            }

            @Override public void mouseMoved(MouseEvent e)
            {
                GraphController.this.mouseMoved(e);
            }
        });

    }// </editor-fold>

    private void mouseMoved(MouseEvent e)
    {
        if (anadiendoArco)
        {
            Point coordenadasVerticePresionado = graficoGrafo.getCoordenadasVertices()[verticeSeleccionado];
            graficoGrafo.dibujarArcoIndicador(coordenadasVerticePresionado, e.getPoint());
            graficoGrafo.repaint();
        }
    }

    private void mouseClicked(MouseEvent e)
    {
        System.out.println("Hola");
        if (e.getButton() == MouseEvent.BUTTON3)
        {
            if (!anadiendoArco)
                verticeSeleccionado = verticePresionado(e.getPoint());

            return;
        }

        if (nombrandoVertice)
        {
            cambiarNombreVertice(verticeSeleccionado);
            quitarEditorNombreVertice();
            nombrandoVertice = false;

        } else if (verticePresionado(e.getPoint()) < 0 && !anadiendoArco)
        {
            anadirVertice(String.valueOf(grafo.getNumeroVertices()), e.getPoint());
            posicionarEditorNombreVertice(e.getPoint());
            verticeSeleccionado = grafo.getNumeroVertices() - 1;
            nombrandoVertice = true;

        } else if (verticePresionado(e.getPoint()) >= 0)
            if (anadiendoArco)
            {
                int verticePresionado = verticePresionado(e.getPoint());

                String origen = grafo.getVertices()[verticeSeleccionado].getDato();
                String destino = grafo.getVertices()[verticePresionado].getDato();

                verticeSeleccionado = verticePresionado;
                anadirArco(origen, destino);
                anadiendoArco = false;

            } else
            {
                verticeSeleccionado = verticePresionado(e.getPoint());
                Point coordenadasVerticePresionado = graficoGrafo.getCoordenadasVertices()[verticeSeleccionado];
                graficoGrafo.dibujarArcoIndicador(coordenadasVerticePresionado, e.getPoint());
                anadiendoArco = true;
            }
    }

    private void mousePressed(MouseEvent e)
    {
        nVerticeAMover = verticePresionado(e.getPoint());

        if (nVerticeAMover >= 0)
        {
            moviendoVertice = true;
            offsetX = e.getX() - graficoGrafo.getCoordenadasVertices()[nVerticeAMover].x;
            offsetY = e.getY() - graficoGrafo.getCoordenadasVertices()[nVerticeAMover].y;

        } else
            moviendoVertice = false;
    }

    private void mouseDragged(MouseEvent e)
    {
        if (!nombrandoVertice && !anadiendoArco && moviendoVertice)
        {
            int nuevaCoordenadaX = e.getX() - offsetX;
            int nuevaCoordenadaY = e.getY() - offsetY;

            if (nuevaCoordenadaX - RADIO_CIRCULO >= 0)
                graficoGrafo.getCoordenadasVertices()[nVerticeAMover].x = nuevaCoordenadaX;

            if (nuevaCoordenadaY - RADIO_CIRCULO - 15 >= 0)
                graficoGrafo.getCoordenadasVertices()[nVerticeAMover].y = nuevaCoordenadaY;

            graficoGrafo.repintarGrafico();
        }
    }

    private void anadirVertice(String nombreVertice, Point coordenadasVertice)
    {
        grafo.nuevoVertice(String.valueOf(nombreVertice));
        anadirVerticeAlGrafico(coordenadasVertice);
    }

    private void editarNombreVertice(int nVertice)
    {
        nombrandoVertice = true;
        editorNombreVertice.setText(grafo.getVertices()[nVertice].getDato());
        editorNombreVertice.selectAll();
        posicionarEditorNombreVertice(graficoGrafo.getCoordenadasVertices()[nVertice]);
    }

    private void eliminarVertice(int nVertice)
    {
        grafo.eliminarVertice(grafo.getVertices()[nVertice].getDato());
        graficoRecorrido.limpiarGrafico();
        eliminarCoordenadasVertice(nVertice);
        quitarMarcasGraficoGrafo();
        graficoGrafo.repaint();
    }

    private void anadirArco(String origen, String destino)
    {
        grafo.nuevoArco(origen, destino);
        graficoGrafo.quitarArcoIndicador();
        graficoGrafo.repaint();
    }

    private void cambiarNombreVertice(int nVertice)
    {
        String nombreVertice = editorNombreVertice.getText().trim();

        if (grafo.numeroVertice(nombreVertice) < 0)
        {
            Vertice<String> vertice = grafo.getVertices()[nVertice];
            vertice.setDato(nombreVertice.isEmpty() ? String.valueOf(nVertice) : nombreVertice);

        } else
            System.out.println("El vértice ya existe.");
    }

    private void posicionarEditorNombreVertice(Point point)
    {
        editorNombreVertice.setLocation(
                (int) point.getX() - EDITOR_ETIQUETA_WIDTH / 2,
                (int) point.getY() - EDITOR_ETIQUETA_HEIGHT / 2);

        editorNombreVertice.setVisible(true);
        editorNombreVertice.requestFocus();
        EventQueue.invokeLater(editorNombreVertice::repaint);
    }

    private void quitarEditorNombreVertice()
    {
        editorNombreVertice.setVisible(false);
        editorNombreVertice.setText(CADENA_VACIA);
    }

    public void anadirVerticeAlGrafico()
    {
        anadirVerticeAlGrafico(situarVerticeAleatoriamente());
    }

    public void anadirVerticeAlGrafico(Point coordenadas)
    {
        anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1, coordenadas);
    }

    public void anadirVerticeAlGrafico(int numVertice, Point coordenadas)
    {
        graficoGrafo.getCoordenadasVertices()[numVertice] = coordenadas;
        graficoGrafo.repaint();
    }

    public void eliminarCoordenadasVertice(int numVertice)
    {
        for (int i = numVertice; i < grafo.getNumeroVertices(); i++)
            graficoGrafo.getCoordenadasVertices()[i] = graficoGrafo.getCoordenadasVertices()[i + 1];

        graficoGrafo.repaint();
    }

    private Point situarVerticeAleatoriamente()
    {
        Container topParent = graficoGrafo.getParent().getParent();

        return new Point(
                numeroAleatorio(RADIO_CIRCULO + 15, topParent.getWidth() - RADIO_CIRCULO),
                numeroAleatorio(RADIO_CIRCULO + 15, topParent.getHeight() - RADIO_CIRCULO));
    }

    private int verticePresionado(Point mouse)
    {
        Point[] coordenadas = graficoGrafo.getCoordenadasVertices();

        for (int i = 0; i < grafo.getNumeroVertices(); i++)
            if (distanciaEntreDosPuntos(coordenadas[i], mouse) <= RADIO_CIRCULO)
                return i;

        return -1;
    }

    private void debeMostrarsePopupMenu()
    {
        final Point mousePosition = graficoGrafo.getMousePosition();
        EventQueue.invokeLater(() -> popupMenu.setVisible(!nombrandoVertice && !anadiendoArco && verticePresionado(mousePosition) >= 0));
    }

    private void quitarMarcasGraficoGrafo()
    {
        graficoGrafo.quitarVerticeMarcado();
        graficoGrafo.quitarArcoMarcado();
    }

}
