package com.sw.controller;

import com.sw.model.GeometriaUtils;
import com.sw.model.Grafico;
import com.sw.model.Vertice;
import java.util.Comparator;

/**
 *
 * @author HikingCarrot7
 */
public class VerticeUtils
{

    public static boolean existeVerticeEnPosicion(Grafico grafo, double centerX, double centerY)
    {
        return grafo.getVertices().stream().anyMatch(v -> v.intersects(centerX, centerY, 1, 1));
    }

    public static Vertice verticeMasCerca(Grafico grafo, double x, double y)
    {
        return grafo.getVertices().stream().min(Comparator.comparing(v -> GeometriaUtils.distanciaEntreDosPuntos(v.getCenterX(), v.getCenterY(), x, y))).get();
    }

}
