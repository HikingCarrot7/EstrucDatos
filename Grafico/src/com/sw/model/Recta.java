package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class Recta
{

    private Punto puntoInicial;
    private Punto puntoFinal;

    public Recta(Punto puntoInicial, Punto puntoFinal)
    {
        this.puntoInicial = puntoInicial;
        this.puntoFinal = puntoFinal;
    }

    public Recta(double xInicial, double yInicial, double xFinal, double yFinal)
    {
        this(new Punto(xInicial, yInicial), new Punto(xFinal, yFinal));
    }

    public Recta(Movable puntoInicial, Movable puntoFinal)
    {
        this(puntoInicial.getXDoubleValue(),
                puntoInicial.getYDoubleValue(),
                puntoFinal.getXDoubleValue(),
                puntoFinal.getYDoubleValue());
    }

    public Punto getPuntoInicial()
    {
        return puntoInicial;
    }

    public void setPuntoInicial(Punto puntoInicial)
    {
        this.puntoInicial = puntoInicial;
    }

    public Punto getPuntoFinal()
    {
        return puntoFinal;
    }

    public void setPuntoFinal(Punto puntoFinal)
    {
        this.puntoFinal = puntoFinal;
    }

}
