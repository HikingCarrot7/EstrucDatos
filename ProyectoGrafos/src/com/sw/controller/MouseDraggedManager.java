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
public class MouseDraggedManager extends MouseAdapter
{

    private final GraficoGrafo grafico;

    public MouseDraggedManager(GraficoGrafo grafico)
    {
        this.grafico = grafico;
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        int nVertice = verticePresionado(e.getPoint());

        if (nVertice >= 0)
            grafico.moverVertice(e.getPoint(), nVertice);
    }

    private int verticePresionado(Point origen)
    {
        Point[] coordenadas = grafico.getCoordenadas();

        for (int i = 0; i < coordenadas.length; i++)
            if (coordenadas[i] != null && Utilidades.distanciaEntreDosPuntos(coordenadas[i], origen) <= GraficoGrafo.RADIO_CIRCULO)
                return i;

        return -1;
    }

}
