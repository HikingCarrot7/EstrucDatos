package com.sw.controller;

import com.sw.model.Grafo;
import com.sw.model.Vertice;
import static com.sw.model.util.Utilidades.distanciaEntreDosPuntos;
import static com.sw.model.util.Utilidades.numeroAleatorio;
import com.sw.view.DibujadorGrafo;
import static com.sw.view.DibujadorGrafo.RADIO_CIRCULO;
import com.sw.view.DibujadorRecorrido;
import com.sw.view.UIConstants;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.event.PopupMenuEvent;

/**
 *
 * @author Nicolás
 */
public class GraphController implements UIConstants
{

    private static final String CADENA_VACIA = "";
    private static final int EDITOR_ETIQUETA_WIDTH = DibujadorGrafo.DIAMETRO_CIRCULO;
    private static final int EDITOR_ETIQUETA_HEIGHT = 23;

    private final DibujadorGrafo dibujadorGrafo;
    private final DibujadorRecorrido dibujadorRecorrido;
    private final Grafo<String> grafo;
    private final JTextField editorNombreVertice;
    private final JPopupMenu popupMenu;

    private int nVerticeAMover;
    private int verticeSeleccionado;
    private int verticeOrigen;

    private int offsetX;
    private int offsetY;

    private boolean moviendoVertice;
    private boolean nombrandoVertice;
    private boolean anadiendoArco;
    private boolean eliminandoArco;

    public GraphController(Grafo<String> grafo, DibujadorGrafo grafico, DibujadorRecorrido graficoRecorrido)
    {
        this.grafo = grafo;
        this.dibujadorGrafo = grafico;
        this.dibujadorRecorrido = graficoRecorrido;
        this.editorNombreVertice = new JTextField();
        this.popupMenu = new JPopupMenu("Acciones emergentes");
        initComponentes();
    }

    // <editor-fold defaultstate="collapsed" desc="initComponents">
    private void initComponentes()
    {
        initEditorNombreVertice();
        initPopupMenu();
        initMouseEvents();

        Container topParent = getTopParent();
        ((JViewport) topParent).addChangeListener(e -> topParent.requestFocus());

        dibujadorGrafo.addKeyListener(new KeyAdapter()
        {
            @Override public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
                    manejarEscape();
            }
        });
    }

    private void initEditorNombreVertice()
    {
        editorNombreVertice.setVisible(false);
        dibujadorGrafo.add(editorNombreVertice);
        editorNombreVertice.setSize(EDITOR_ETIQUETA_WIDTH, EDITOR_ETIQUETA_HEIGHT);

        editorNombreVertice.addKeyListener(new KeyAdapter()
        {
            @Override public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_ENTER)
                    manejarEnterEnEditorNombreVertice();
            }
        });

        editorNombreVertice.addFocusListener(new FocusAdapter()
        {
            @Override public void focusLost(FocusEvent e)
            {
                manejarPerdidaFocoEditorNombreVertice();
            }
        });
    }

    private void initPopupMenu()
    {
        popupMenu.add(new JMenuItem(new AccionEmergente("Editar nombre",
                EDITAR_ICON,
                e -> editarNombreVertice(verticeSeleccionado))));

        popupMenu.add(new JMenuItem(new AccionEmergente("Eliminar vértice",
                DELETE_ICON,
                e -> eliminarVertice(verticeSeleccionado))));

        dibujadorGrafo.setComponentPopupMenu(popupMenu);

        popupMenu.addPopupMenuListener(new PopupMenuAdapter()
        {
            @Override public void popupMenuWillBecomeVisible(PopupMenuEvent e)
            {
                manejarClicDerecho(dibujadorGrafo.getMousePosition());
            }
        });
    }

    private void initMouseEvents()
    {
        dibujadorGrafo.addMouseListener(new MouseAdapter()
        {
            @Override public void mouseClicked(MouseEvent e)
            {
                GraphController.this.manejarClicIzquierdo(e);
            }

            @Override public void mousePressed(MouseEvent e)
            {
                GraphController.this.mousePressed(e);
            }
        });

        dibujadorGrafo.addMouseMotionListener(new MouseAdapter()
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

    private void manejarEscape()
    {
        if (anadiendoArco)
            cancelarAnadirArco();

        if (eliminandoArco)
            cancelarEliminarArco();
    }

    private void manejarPerdidaFocoEditorNombreVertice()
    {
        quitarEditorNombreVertice();
        nombrandoVertice = false;
        dibujadorGrafo.requestFocus();
    }

    private void manejarEnterEnEditorNombreVertice()
    {
        if (nombrandoVertice)
        {
            cambiarNombreVertice(verticeSeleccionado);
            quitarEditorNombreVertice();
            nombrandoVertice = false;
        }
    }

    private void manejarClicDerecho(Point mousePosition)
    {
        int verticePresionado = verticePresionado(mousePosition);
        verticeSeleccionado = verticePresionado >= 0 ? verticePresionado : verticeSeleccionado;
        dibujadorGrafo.requestFocus();

        mostrarPopupMenu();

        if (anadiendoArco)
            cancelarAnadirArco();

        if (eliminandoArco)
            cancelarEliminarArco();
    }

    private void manejarClicIzquierdo(MouseEvent e)
    {
        if (e.getButton() == esClicDerecho() || fueraDeRango(e.getPoint()))
            return;

        int verticePresionado = verticePresionado(e.getPoint());

        if (eliminandoArco)
        {
            manejarEliminacionArco(verticePresionado);
            return;

        } else if (grafo.getNumeroVertices() > 1 && e.isControlDown() && verticePresionado >= 0)
        {
            manejarEliminacionArco(verticePresionado);
            return;
        }

        if (nombrandoVertice)
        {
            cambiarNombreVertice(verticeSeleccionado);
            quitarEditorNombreVertice();
            limpiarGraficos();
            nombrandoVertice = false;

        } else if (verticePresionado < 0 && !anadiendoArco)
        {
            if (grafo.getNumeroVertices() < Grafo.MAX_NUMERO_VERTICES)
            {
                anadirVertice(getNombrePosibleVertice(verticePresionado), e.getPoint());
                posicionarEditorNombreVertice(e.getPoint());
                verticeSeleccionado = grafo.getNumeroVertices() - 1;
                nombrandoVertice = true;
            }

        } else if (verticePresionado >= 0)
            if (anadiendoArco)
            {
                String origen = grafo.getVertices()[verticeOrigen].getDato();
                String destino = grafo.getVertices()[verticePresionado].getDato();

                grafo.nuevoArco(origen, destino);
                limpiarGraficos();
                cancelarAnadirArco();

            } else if (grafo.getNumeroVertices() > 1)
            {
                verticeOrigen = verticePresionado;
                Point coordenadasVerticePresionado = dibujadorGrafo.getCoordenadasVertices()[verticeOrigen];
                dibujadorGrafo.dibujarArcoIndicador(coordenadasVerticePresionado, e.getPoint());
                dibujadorGrafo.setVerticeOrigen(verticeOrigen);
                dibujadorGrafo.repaint();
                anadiendoArco = true;
            }
    }

    private void manejarEliminacionArco(int verticePresionado)
    {
        if (eliminandoArco)
        {
            if (verticePresionado >= 0)
            {
                String origen = grafo.getVertices()[verticeOrigen].getDato();
                String destino = grafo.getVertices()[verticePresionado].getDato();

                grafo.eliminarArco(origen, destino);
                limpiarGraficos();
                cancelarEliminarArco();
            }

        } else
        {
            verticeOrigen = verticePresionado;
            dibujadorGrafo.setVerticeOrigen(verticeOrigen);
            limpiarGraficos();
            eliminandoArco = true;
        }
    }

    private void mousePressed(MouseEvent e)
    {
        nVerticeAMover = verticePresionado(e.getPoint());

        if (nVerticeAMover >= 0)
        {
            moviendoVertice = true;
            offsetX = e.getX() - dibujadorGrafo.getCoordenadasVertices()[nVerticeAMover].x;
            offsetY = e.getY() - dibujadorGrafo.getCoordenadasVertices()[nVerticeAMover].y;

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
                dibujadorGrafo.getCoordenadasVertices()[nVerticeAMover].x = nuevaCoordenadaX;

            if (nuevaCoordenadaY - RADIO_CIRCULO >= 0)
                dibujadorGrafo.getCoordenadasVertices()[nVerticeAMover].y = nuevaCoordenadaY;

            dibujadorGrafo.repaint();
        }
    }

    private void mouseMoved(MouseEvent e)
    {
        if (anadiendoArco)
        {
            Point coordenadasVerticeOrigen = dibujadorGrafo.getCoordenadasVertices()[verticeOrigen];
            dibujadorGrafo.dibujarArcoIndicador(coordenadasVerticeOrigen, e.getPoint());
            dibujadorGrafo.repaint();
        }
    }

    private void anadirVertice(String nombreVertice, Point coordenadasVertice)
    {
        grafo.nuevoVertice(String.valueOf(nombreVertice));
        anadirVerticeAlGrafico(coordenadasVertice);
        limpiarGraficos();
    }

    private void editarNombreVertice(int nVertice)
    {
        nombrandoVertice = true;
        editorNombreVertice.setText(grafo.getVertices()[nVertice].getDato());
        editorNombreVertice.selectAll();
        posicionarEditorNombreVertice(dibujadorGrafo.getCoordenadasVertices()[nVertice]);
        limpiarGraficos();
    }

    private void eliminarVertice(int nVertice)
    {
        grafo.eliminarVertice(grafo.getVertices()[nVertice].getDato());
        eliminarCoordenadasVertice(nVertice);
        limpiarGraficos();
    }

    private void cancelarAnadirArco()
    {
        limpiarGraficos();
        anadiendoArco = false;
    }

    private void cancelarEliminarArco()
    {
        limpiarGraficos();
        eliminandoArco = false;
    }

    private void cambiarNombreVertice(int nVertice)
    {
        String nombreVertice = editorNombreVertice.getText().trim();

        if (!grafo.existeVertice(nombreVertice))
        {
            Vertice<String> vertice = grafo.getVertices()[nVertice];
            vertice.setDato(nombreVertice.isEmpty() ? getNombrePosibleVertice(nVertice) : nombreVertice);
            limpiarGraficos();
        }
    }

    private void posicionarEditorNombreVertice(Point point)
    {
        editorNombreVertice.setLocation(
                (int) point.getX() - EDITOR_ETIQUETA_WIDTH / 2,
                (int) point.getY() - EDITOR_ETIQUETA_HEIGHT / 2);

        editorNombreVertice.setVisible(true);
        editorNombreVertice.requestFocus();
        repintarEditorNombreVertice();
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
        dibujadorGrafo.getCoordenadasVertices()[numVertice] = coordenadas;
        dibujadorGrafo.repaint();
    }

    public void eliminarCoordenadasVertice(int numVertice)
    {
        dibujadorGrafo.getCoordenadasVertices()[numVertice] = null;

        for (int i = numVertice; i < grafo.getNumeroVertices(); i++)
            dibujadorGrafo.getCoordenadasVertices()[i] = dibujadorGrafo.getCoordenadasVertices()[i + 1];

        dibujadorGrafo.repaint();
    }

    private Point situarVerticeAleatoriamente()
    {
        Container topParent = getTopParent();

        return new Point(
                numeroAleatorio(RADIO_CIRCULO + 15, topParent.getWidth() - RADIO_CIRCULO),
                numeroAleatorio(RADIO_CIRCULO + 15, topParent.getHeight() - RADIO_CIRCULO));
    }

    private String getNombrePosibleVertice(int nVertice)
    {
        int nombrePosible = nVertice >= 0 ? nVertice : 0;

        while (grafo.existeVertice(String.valueOf(nombrePosible).trim()))
            nombrePosible++;

        return String.valueOf(nombrePosible).trim();
    }

    private int verticePresionado(Point mouse)
    {
        Point[] coordenadas = dibujadorGrafo.getCoordenadasVertices();

        for (int i = 0; i < grafo.getNumeroVertices(); i++)
            if (distanciaEntreDosPuntos(coordenadas[i], mouse) <= RADIO_CIRCULO)
                return i;

        return -1;
    }

    private void limpiarGraficos()
    {
        dibujadorGrafo.quitarArcoIndicador();
        dibujadorGrafo.quitarVerticeOrigen();
        dibujadorGrafo.quitarVerticeMarcado();
        dibujadorGrafo.quitarArcoMarcado();
        dibujadorRecorrido.limpiarGrafico();
        dibujadorGrafo.repaint();
        dibujadorRecorrido.repaint();
    }

    private boolean mostrarPopupMenu()
    {
        final Point mousePosition = dibujadorGrafo.getMousePosition();
        final boolean debeMostrarsePopupMenu = !nombrandoVertice && !anadiendoArco && verticePresionado(mousePosition) >= 0;

        EventQueue.invokeLater(() ->
        {
            popupMenu.setVisible(debeMostrarsePopupMenu);
            repintarEditorNombreVertice();
        });

        return debeMostrarsePopupMenu;
    }

    private void repintarEditorNombreVertice()
    {
        EventQueue.invokeLater(editorNombreVertice::repaint);
    }

    private int esClicDerecho()
    {
        return MouseEvent.BUTTON3;
    }

    private boolean fueraDeRango(Point mouse)
    {
        Container topParent = getTopParent();

        if (mouse.x >= RADIO_CIRCULO && mouse.x <= topParent.getWidth() - RADIO_CIRCULO)
            if (mouse.y >= RADIO_CIRCULO && mouse.y <= topParent.getHeight() - RADIO_CIRCULO)
                return false;

        return true;
    }

    private Container getTopParent()
    {
        return dibujadorGrafo.getParent().getParent();
    }

}
