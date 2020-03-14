package com.sw.controller;

import com.sw.model.GeometriaUtils;
import com.sw.model.Grafico;
import com.sw.model.Vertice;
import java.util.Comparator;
import javafx.scene.control.Label;

/**
 *
 * @author HikingCarrot7
 */
public class VerticeHandler
{

    public static void setEvents(Vertice vertice)
    {
        vertice.setOnMouseEntered(e ->
        {
            if (!vertice.isSelected())
            {
                vertice.toFront();
                vertice.getNombre().toFront();
                vertice.setFill(Vertice.HOVER_SKIN);
            }
        });

        vertice.setOnMouseExited(e ->
        {
            if (!vertice.isSelected())
                vertice.setFill(Vertice.DEFAULT_SKIN);
        });

        vertice.setOnMouseDragged(e ->
        {
            Label nombre = vertice.getNombre();

            vertice.setCenterX(e.getSceneX());
            vertice.setCenterY(e.getSceneY());

            nombre.setTranslateX(vertice.getCenterX() - nombre.getWidth() / 2);
            nombre.setTranslateY(vertice.getCenterY() - nombre.getHeight() / 2);
        });
    }

    public static boolean existeVerticeEnPosicion(Grafico grafo, double centerX, double centerY)
    {
        return grafo.getVertices().stream().anyMatch(v -> v.intersects(centerX, centerY, 1, 1));
    }

    public static Vertice verticeMasCerca(Grafico grafo, double x, double y)
    {
        return grafo.getVertices().stream().min(Comparator.comparing(v -> GeometriaUtils.distanciaEntreDosPuntos(v.getCenterX(), v.getCenterY(), x, y))).get();
    }

}
