package com.sw.view;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.GeneralPath;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Nicolás
 */
public final class GraficoRecorrido extends JPanel
{

    private static GraficoRecorrido instance;

    private final int OFFSET_X = 25;
    private final int OFFSET_Y = 5;

    private final int ITEM_TO_ITEM = 15;
    private final int LADO_TRIANGULO = 7;
    private final int RECT_WIDTH = 70;
    private final int RECT_HEIGHT = 20;
    private final int SEPARACION_POR_LINEA = 10;

    private final ArrayList<Object> elementos;
    private Container topParent;

    private GraficoRecorrido()
    {
        elementos = new ArrayList<>();
        setBackground(Color.WHITE);
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        topParent = getParent().getParent();

        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);

        dibujarLista(g2d, g2d.getFontMetrics());
        g2d.dispose();
    }

    public void actualizarElementos(List<?> elementosNuevos)
    {
        this.elementos.clear();
        this.elementos.addAll(elementosNuevos);
    }

    public void limpiarGrafico()
    {
        this.elementos.clear();
        repintarGrafico();
    }

    private void dibujarLista(Graphics2D g, FontMetrics fm)
    {
        Dimension topParentSize = topParent.getSize();
        final int MAX_COLUMNS = ((int) topParentSize.getWidth() - OFFSET_X) / (ITEM_TO_ITEM + RECT_WIDTH);
        final int MAX_ROWS = ((int) topParentSize.getHeight() - OFFSET_Y) / (SEPARACION_POR_LINEA + RECT_HEIGHT);
        int y = OFFSET_Y;

        if (deboEstirarPanel(MAX_COLUMNS, MAX_ROWS))
            estirarPanel(getNewHeight(MAX_COLUMNS));
        else
            normalizarPanel();

        for (int i = 0, x = OFFSET_X; i < elementos.size(); i++, x += ITEM_TO_ITEM + RECT_WIDTH)
        {
            String elemento = String.valueOf(elementos.get(i));
            drawItem(g, fm, x, y, elemento);

            if (elementos.size() - (i + 1) > 0)
                if ((i + 1) % MAX_COLUMNS == 0)
                {
                    drawLargeLine(g, x + RECT_WIDTH, y + RECT_HEIGHT / 2);
                    y += SEPARACION_POR_LINEA + RECT_HEIGHT;
                    x = OFFSET_X - (ITEM_TO_ITEM + RECT_WIDTH);

                } else
                {
                    g.drawLine(x + RECT_WIDTH, y + RECT_HEIGHT / 2, x + RECT_WIDTH + ITEM_TO_ITEM, y + RECT_HEIGHT / 2);
                    drawTriangle(g, x + RECT_WIDTH + ITEM_TO_ITEM, y + RECT_HEIGHT / 2, LADO_TRIANGULO);
                }
        }

    }

    private boolean deboEstirarPanel(final int MAX_COLUMNS, final int MAX_ROWS)
    {
        return elementos.size() > MAX_COLUMNS * MAX_ROWS;
    }

    private int getNewHeight(final int MAX_COLUMNS)
    {
        return (int) (OFFSET_Y * 2 + (SEPARACION_POR_LINEA + RECT_HEIGHT) * (Math.ceil(elementos.size() / (double) MAX_COLUMNS))) - SEPARACION_POR_LINEA;
    }

    private void estirarPanel(int newHeight)
    {
        getParent().setPreferredSize(new Dimension(topParent.getWidth(), newHeight));
        getParent().revalidate();
    }

    private void normalizarPanel()
    {
        getParent().setPreferredSize(topParent.getSize());
        getParent().revalidate();
    }

    public void repintarGrafico()
    {
        EventQueue.invokeLater(this::repaint);
    }

    private void drawItem(Graphics2D g, FontMetrics fm, int x, int y, String item)
    {
        g.drawString(item,
                x + (RECT_WIDTH - SwingUtilities.computeStringWidth(fm, item)) / 2,
                y + (RECT_HEIGHT + fm.getAscent()) / 2);

        g.drawRect(x, y, RECT_WIDTH, RECT_HEIGHT);
    }

    private void drawTriangle(Graphics2D g, int x, int y, int length)
    {
        int offset = (int) Math.sqrt(Math.pow(length, 2) - Math.pow((length / 2), 2));

        int[] puntosX =
        {
            x - offset, x, x - offset
        };

        int[] puntosY =
        {
            y - offset, y, y + offset
        };

        GeneralPath triangle = new GeneralPath();

        triangle.moveTo(puntosX[0], puntosY[0]);

        for (int i = 0; i < puntosX.length; i++)
            triangle.lineTo(puntosX[i], puntosY[i]);

        triangle.closePath();
        g.fill(triangle);
    }

    private void drawLargeLine(Graphics2D g, int x, int y)
    {
        g.drawLine(x, y, x + ITEM_TO_ITEM, y);
        g.drawLine(x + ITEM_TO_ITEM, y, x + ITEM_TO_ITEM, y + RECT_HEIGHT / 2 + SEPARACION_POR_LINEA / 2);
        g.drawLine(x + ITEM_TO_ITEM, y + RECT_HEIGHT / 2 + SEPARACION_POR_LINEA / 2, OFFSET_X - OFFSET_X / 2, y + RECT_HEIGHT / 2 + SEPARACION_POR_LINEA / 2);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + RECT_HEIGHT / 2 + SEPARACION_POR_LINEA / 2, OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + RECT_HEIGHT);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + RECT_HEIGHT, OFFSET_X, y + SEPARACION_POR_LINEA + RECT_HEIGHT);
        drawTriangle(g, OFFSET_X, y + SEPARACION_POR_LINEA + RECT_HEIGHT, LADO_TRIANGULO);
    }

    public synchronized static GraficoRecorrido getInstance()
    {
        if (instance == null)
            instance = new GraficoRecorrido();

        return instance;
    }

}
