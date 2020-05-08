package com.sw.controller;

import com.sw.model.Utilidades;
import com.sw.view.GraficoGrafo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author Nicolás
 */
public class GraphMouseManager extends MouseAdapter
{

    private final GraficoGrafo grafico;

    private int nVertice;
    private int offsetX;
    private int offsetY;
    private boolean moviendoVertice;

    public GraphMouseManager(GraficoGrafo grafico)
    {
        this.grafico = grafico;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if (moviendoVertice)
        {
            int nuevaCoordenadaX = e.getX() - offsetX;
            int nuevaCoordenadaY = e.getY() - offsetY;

            if (nuevaCoordenadaX - GraficoGrafo.RADIO_CIRCULO >= 0)
                grafico.getCoordenadas()[nVertice].x = nuevaCoordenadaX;

            if (nuevaCoordenadaY - GraficoGrafo.RADIO_CIRCULO - 15 >= 0)
                grafico.getCoordenadas()[nVertice].y = nuevaCoordenadaY;

            grafico.repintarGrafico();
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        nVertice = verticePresionado(e.getPoint());

        if (nVertice >= 0)
        {
            moviendoVertice = true;
            offsetX = e.getX() - grafico.getCoordenadas()[nVertice].x;
            offsetY = e.getY() - grafico.getCoordenadas()[nVertice].y;

        } else
            moviendoVertice = false;
    }

    private int verticePresionado(Point mouse)
    {
        Point[] coordenadas = grafico.getCoordenadas();

        for (int i = 0; i < coordenadas.length; i++)
            if (coordenadas[i] != null && Utilidades.distanciaEntreDosPuntos(coordenadas[i], mouse) <= GraficoGrafo.RADIO_CIRCULO)
                return i;

        return -1;
    }

}
