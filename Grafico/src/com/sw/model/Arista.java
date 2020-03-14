package com.sw.model;

import javafx.scene.shape.Line;

/**
 *
 * @author HikingCarrot7
 */
public class Arista extends Line
{

    private Vertice verticeInicial;
    private Vertice verticeFinal;

    public Arista(Vertice verticeInicial, Vertice verticeFinal)
    {
        this.verticeInicial = verticeInicial;
        this.verticeFinal = verticeFinal;

        startXProperty().bind(verticeInicial.centerXProperty());
        startYProperty().bind(verticeInicial.centerYProperty());
        endXProperty().bind(verticeFinal.centerXProperty());
        endYProperty().bind(verticeFinal.centerYProperty());
    }

    public Arista(Vertice verticeInicial, Punto puntoFinal)
    {
        this.verticeInicial = verticeInicial;

        startXProperty().bind(verticeInicial.centerXProperty());
        startYProperty().bind(verticeInicial.centerYProperty());
        endXProperty().bind(puntoFinal.getXProperty());
        endYProperty().bind(puntoFinal.getYProperty());
    }

    public Vertice getVerticeInicial()
    {
        return verticeInicial;
    }

    public void setVerticeInicial(Vertice verticeInicial)
    {
        this.verticeInicial = verticeInicial;
    }

    public Vertice getVerticeFinal()
    {
        return verticeFinal;
    }

    public void setVerticeFinal(Vertice verticeFinal)
    {
        this.verticeFinal = verticeFinal;
    }

}
