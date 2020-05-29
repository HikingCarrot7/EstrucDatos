package com.sw.view;

import com.sw.model.Arco;
import com.sw.model.Grafo;
import com.sw.model.Vertice;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nicolás
 */
public final class DibujadorGrafo extends JPanel
{

    public static final int DIAMETRO_CIRCULO = 60;
    public static final int RADIO_CIRCULO = DIAMETRO_CIRCULO / 2;

    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color CIRCLE_COLOR = Color.RED;
    private final Color MARKED_CIRCLE_COLOR = Color.DARK_GRAY;
    private final Color ORIGEN_CIRCLE_COLOR = Color.BLUE;
    private final Color ARCO_COLOR = Color.BLACK;
    private final Color MARKED_ARCO_COLOR = Color.MAGENTA;

    private final int TRIANGLE_SIZE = 8;

    private final Grafo<?> grafo;
    private final Point[] coordenadasVertices;
    private final Redimensionador redimensionador;
    private int idxVerticeMarcado;
    private int idxVerticeOrigen;
    private Arco arcoMarcado;

    private Point origenArcoIndicador;
    private Point destinoArcoIndicador;
    private boolean dibujarArcoIndicador;

    public DibujadorGrafo(Grafo<?> grafo)
    {
        this.grafo = grafo;
        this.redimensionador = new Redimensionador(this);
        this.coordenadasVertices = new Point[Grafo.MAX_NUMERO_VERTICES];
        this.idxVerticeMarcado = -1;
        this.idxVerticeOrigen = -1;
        this.arcoMarcado = null;
        setBackground(BACKGROUND_COLOR);
        setLayout(null);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(BACKGROUND_COLOR);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        if (dibujarArcoIndicador)
            dibujarLinea(g2d, origenArcoIndicador, destinoArcoIndicador, ARCO_COLOR);

        dibujarGrafo((Graphics2D) g);

        if (!grafo.isEmpty())
            redimensionador.redimensionar();

        g.dispose();
    }

    private void dibujarLimites(Graphics2D g)
    {
        g.setColor(Color.BLACK);
        g.drawRect(RADIO_CIRCULO, RADIO_CIRCULO, getWidth() - DIAMETRO_CIRCULO, getHeight() - DIAMETRO_CIRCULO);
        g.setColor(BACKGROUND_COLOR);
    }

    private void dibujarGrafo(Graphics2D g)
    {
        Vertice<?>[] vertices = grafo.getVertices();
        List<Arco> arcos = grafo.getArcos();

        arcos.forEach(arco -> dibujarArco(g, coordenadasVertices[arco.getOrigen()], coordenadasVertices[arco.getDestino()], ARCO_COLOR));

        if (arcoMarcado != null)
            dibujarArco(g, coordenadasVertices[arcoMarcado.getOrigen()], coordenadasVertices[arcoMarcado.getDestino()], MARKED_ARCO_COLOR);

        for (int i = 0; i < grafo.getNumeroVertices(); i++)
            dibujarVertice(g, vertices[i], CIRCLE_COLOR);

        if (idxVerticeMarcado >= 0)
            dibujarVertice(g, vertices[idxVerticeMarcado], MARKED_CIRCLE_COLOR);

        if (idxVerticeOrigen >= 0)
            dibujarVertice(g, vertices[idxVerticeOrigen], ORIGEN_CIRCLE_COLOR);
    }

    private void dibujarVertice(Graphics2D g, Vertice<?> vertice, Color colorVertice)
    {
        dibujarCirculo(g, coordenadasVertices[vertice.getNumVertice()], colorVertice);
        dibujarContenidoVertice(g, coordenadasVertices[vertice.getNumVertice()], vertice.getNumVertice(), vertice.getDato().toString());
    }

    private void dibujarCirculo(Graphics2D g, Point coordenada, Color colorCirculo)
    {
        g.setColor(colorCirculo);
        g.fillOval(coordenada.x - RADIO_CIRCULO, coordenada.y - RADIO_CIRCULO, DIAMETRO_CIRCULO, DIAMETRO_CIRCULO);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(coordenada.x - RADIO_CIRCULO, coordenada.y - RADIO_CIRCULO, DIAMETRO_CIRCULO, DIAMETRO_CIRCULO);
        g.setColor(BACKGROUND_COLOR);
    }

    private void dibujarContenidoVertice(Graphics2D g, Point coordenadaVertice, int nVertice, String contenido)
    {
        g.setColor(Color.WHITE);
        dibujarStringEnPunto(g, contenido, coordenadaVertice.x, coordenadaVertice.y);
        dibujarStringEnPunto(g, String.valueOf(nVertice), coordenadaVertice.x, coordenadaVertice.y - RADIO_CIRCULO + 8);
        g.setColor(BACKGROUND_COLOR);
    }

    private void dibujarArco(Graphics2D g, Point origen, Point destino, Color colorArco)
    {
        dibujarLinea(g, origen, destino, colorArco);
        g.setColor(colorArco);
        dibujarTriangulo(g, origen, destino);
        dibujarTriangulo(g, destino, origen);
        g.setColor(BACKGROUND_COLOR);
    }

    private void dibujarLinea(Graphics2D g, Point origen, Point destino, Color colorLinea)
    {
        g.setColor(colorLinea);
        g.setStroke(new BasicStroke(2));
        g.drawLine(origen.x, origen.y, destino.x, destino.y);
        g.setColor(BACKGROUND_COLOR);
    }

    private void dibujarTriangulo(Graphics2D g, Point origen, Point destino)
    {
        dibujarTriangulo(g, origen.x, origen.y, destino.x, destino.y);
    }

    private void dibujarTriangulo(Graphics2D g, int origenX, int origenY, int destinoX, int destinoY)
    {
        double dx = destinoX - origenX;
        double dy = destinoY - origenY;
        double angle = Math.atan2(dy, dx);
        int distancia = (int) (Math.sqrt(dx * dx + dy * dy) - RADIO_CIRCULO);

        AffineTransform originalTransform = g.getTransform();
        AffineTransform at = AffineTransform.getTranslateInstance(origenX, origenY);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        g.fillPolygon(new int[]
        {
            distancia, distancia - TRIANGLE_SIZE, distancia - TRIANGLE_SIZE, distancia

        }, new int[]
        {
            0, -TRIANGLE_SIZE, TRIANGLE_SIZE, 0

        }, 4);

        g.setTransform(originalTransform);
    }

    private void dibujarStringEnPunto(Graphics2D g, String text, Point p)
    {
        dibujarStringEnPunto(g, text, (float) p.getX(), (float) p.getY());
    }

    private void dibujarStringEnPunto(Graphics2D g, String text, float x, float y)
    {
        Rectangle bounds = getStringBounds(g, text);
        g.drawString(text, x - (float) bounds.getWidth() / 2, y + (float) bounds.getHeight() / 2);
    }

    private Rectangle getStringBounds(Graphics2D g, String text)
    {
        return new Rectangle(new Dimension(
                SwingUtilities.computeStringWidth(g.getFontMetrics(), text),
                g.getFontMetrics().getAscent()));
    }

    public void setVerticeMarcado(int idxVerticeMarcado)
    {
        this.idxVerticeMarcado = idxVerticeMarcado;
    }

    public void quitarVerticeMarcado()
    {
        this.idxVerticeMarcado = -1;
    }

    public void setVerticeOrigen(int idxVerticeOrigen)
    {
        this.idxVerticeOrigen = idxVerticeOrigen;
    }

    public void quitarVerticeOrigen()
    {
        this.idxVerticeOrigen = -1;
    }

    public void dibujarArcoIndicador(Point origen, Point destino)
    {
        origenArcoIndicador = origen;
        destinoArcoIndicador = destino;
        dibujarArcoIndicador = true;
    }

    public void setArcoMarcado(Arco arcoMarcado)
    {
        this.arcoMarcado = arcoMarcado;
    }

    public void quitarArcoIndicador()
    {
        dibujarArcoIndicador = false;
    }

    public void quitarArcoMarcado()
    {
        arcoMarcado = null;
    }

    public Point[] getCoordenadasVertices()
    {
        return coordenadasVertices;
    }

    private class Redimensionador
    {

        private final DibujadorGrafo graficoGrafo;
        private Container parent;

        public Redimensionador(DibujadorGrafo graficoGrafo)
        {
            this.graficoGrafo = graficoGrafo;
        }

        private void redimensionar()
        {
            parent = graficoGrafo.getParent();

            if (deboRedimensionarHorizontalmente())
                redimensionarHorizontalmente();
            else
                normalizarDimensionHorizontalmente();

            EventQueue.invokeLater(() ->
            {
                if (deboRedimensionarVerticalmente())
                    redimensionarVerticalmente();
                else
                    normalizarDimensionVerticalmente();
            });
        }

        private void normalizarDimensionHorizontalmente()
        {
            Container topParent = parent.getParent();
            parent.setPreferredSize(new Dimension((int) topParent.getSize().getWidth(), (int) parent.getSize().getHeight()));
            topParent.revalidate();
        }

        private void normalizarDimensionVerticalmente()
        {
            Container topParent = parent.getParent();
            parent.setPreferredSize(new Dimension((int) parent.getSize().getWidth(), (int) topParent.getSize().getHeight()));
            topParent.revalidate();
        }

        private void redimensionarHorizontalmente()
        {
            parent.setPreferredSize(new Dimension(buscarCoordenadaMasAlejadaEnX() + RADIO_CIRCULO, (int) parent.getSize().getHeight()));
            parent.revalidate();
        }

        private void redimensionarVerticalmente()
        {
            parent.setPreferredSize(new Dimension((int) parent.getSize().getWidth(), buscarCoordenadaMasAlejadaEnY() + RADIO_CIRCULO));
            parent.revalidate();
        }

        private boolean deboRedimensionarHorizontalmente()
        {
            return buscarCoordenadaMasAlejadaEnX() + RADIO_CIRCULO >= parent.getParent().getSize().getWidth();
        }

        private boolean deboRedimensionarVerticalmente()
        {
            return buscarCoordenadaMasAlejadaEnY() + RADIO_CIRCULO >= parent.getParent().getSize().getHeight();
        }

        private int buscarCoordenadaMasAlejadaEnX()
        {
            int xMasAlejado = graficoGrafo.getCoordenadasVertices()[0].x;

            for (Point coordenada : graficoGrafo.getCoordenadasVertices())
                if (coordenada != null && xMasAlejado < coordenada.x)
                    xMasAlejado = coordenada.x;

            return xMasAlejado;
        }

        private int buscarCoordenadaMasAlejadaEnY()
        {
            int yMasAlejado = graficoGrafo.getCoordenadasVertices()[0].y;

            for (Point coordenada : graficoGrafo.getCoordenadasVertices())
                if (coordenada != null && yMasAlejado < coordenada.y)
                    yMasAlejado = coordenada.y;

            return yMasAlejado;
        }

    }

}
