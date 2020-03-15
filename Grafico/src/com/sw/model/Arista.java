package com.sw.model;

import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;

/**
 *
 * @author HikingCarrot7
 */
public class Arista extends Path
{

    private Movable puntoInicial;
    private Movable puntoFinal;

    public Arista(Movable puntoInicial, Movable puntoFinal)
    {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;

        createShape(puntoInicial, puntoFinal);
    }

    private void createShape(Movable puntoInicial, Movable puntoFinal)
    {
        MoveTo mt = new MoveTo(puntoInicial.getXDoubleValue(), puntoInicial.getYDoubleValue());
        mt.xProperty().bind(puntoInicial.getXProperty());
        mt.yProperty().bind(puntoInicial.getYProperty());

        LineTo lt = new LineTo(puntoFinal.getXDoubleValue(), puntoFinal.getYDoubleValue());
        lt.xProperty().bind(puntoFinal.getXProperty());
        lt.yProperty().bind(puntoFinal.getYProperty());

        getElements().addAll(mt, lt);
    }

    public Movable getPuntoInicial()
    {
        return puntoInicial;
    }

    public void setPuntoInicial(Movable puntoInicial)
    {
        this.puntoInicial = puntoInicial;
    }

    public Movable getPuntoFinal()
    {
        return puntoFinal;
    }

    public void setPuntoFinal(Movable puntoFinal)
    {
        this.puntoFinal = puntoFinal;
    }

}
