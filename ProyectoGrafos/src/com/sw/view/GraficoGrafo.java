package com.sw.view;

import com.sw.model.Grafo;
import com.sw.model.Utilidades;
import com.sw.model.Vertice;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author Nicolás
 */
public class GraficoGrafo extends JPanel
{

    public static final int DIAMETRO_CIRCULO = 70;
    public static final int RADIO_CIRCULO = DIAMETRO_CIRCULO / 2;
    private final Color BACKGROUND_COLOR = Color.WHITE;
    private final Color CIRCLE_COLOR = Color.RED;

    private final Grafo<?> grafo;
    private final Point[] coordenadas;

    public GraficoGrafo(Grafo<?> grafo)
    {
        this.grafo = grafo;
        this.coordenadas = new Point[Grafo.MAX_NUMERO_VERTICES];
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g.setColor(BACKGROUND_COLOR);

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        dibujarGrafo((Graphics2D) g);

        g.dispose();
    }

    private void dibujarGrafo(Graphics2D g)
    {
        Vertice<?>[] vertices = grafo.getVertices();

        for (int i = 0; i < grafo.getNumeroVertices(); i++)
            dibujarVertice(g, vertices[i]);

        //Dibujar arcos xd...
    }

    private void dibujarVertice(Graphics2D g, Vertice<?> vertice)
    {
        dibujarCirculo(g, coordenadas[vertice.getNumVertice()]);
        dibujarContenidoVertice(g, coordenadas[vertice.getNumVertice()], vertice.getNumVertice(), vertice.getDato().toString());
    }

    private void dibujarCirculo(Graphics2D g, Point coordenada)
    {
        g.setColor(CIRCLE_COLOR);
        g.fillOval(coordenada.x - RADIO_CIRCULO, coordenada.y - RADIO_CIRCULO, DIAMETRO_CIRCULO, DIAMETRO_CIRCULO);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.BLACK);
        g.drawOval(coordenada.x - RADIO_CIRCULO, coordenada.y - RADIO_CIRCULO, DIAMETRO_CIRCULO, DIAMETRO_CIRCULO);
        g.setColor(BACKGROUND_COLOR);
    }

    private void dibujarContenidoVertice(Graphics2D g, Point coordenada, int nVertice, String contenido)
    {
        g.setColor(Color.WHITE);
        dibujarStringEnPunto(g, contenido, coordenada.x, coordenada.y);
        g.setColor(Color.BLACK);
        dibujarStringEnPunto(g, String.valueOf(nVertice), coordenada.x, coordenada.y - RADIO_CIRCULO - 10);
        g.setColor(BACKGROUND_COLOR);
    }

    public void anadirVerticeAlGrafico(int numVertice)
    {
        coordenadas[numVertice] = situarVertice();
        repaint();
    }

    public void eliminarVertice(int numVertice)
    {
        for (int i = numVertice; i < coordenadas.length - 1; i++)
            coordenadas[i] = coordenadas[i + 1];

        repaint();
    }

    public void moverVertice(Point nuevaCoordenada, int numVertice)
    {
        coordenadas[numVertice] = nuevaCoordenada;
        repaint();
    }

    private Point situarVertice()
    {
        return new Point(Utilidades.aleatorio(RADIO_CIRCULO + 15, getWidth() - RADIO_CIRCULO), Utilidades.aleatorio(RADIO_CIRCULO + 15, getHeight() - RADIO_CIRCULO));
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
        Rectangle r = g.getFontMetrics().getStringBounds(text, g).getBounds();
        r.setSize(new Dimension((int) r.getWidth(), g.getFontMetrics().getAscent()));
        return r;
    }

    public Point[] getCoordenadas()
    {
        return coordenadas;
    }

}
