package com.sw.model.util;

import java.awt.Point;

/**
 *
 * @author Nicolás
 */
public class Utilidades
{

    public static int numeroAleatorio(int minimo, int maximo)
    {
        return (int) (Math.random() * ((maximo + 1) - minimo)) + minimo;
    }

    public static double distanciaEntreDosPuntos(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow((p2.getX() - p1.getX()), 2) + Math.pow((p2.getY() - p1.getY()), 2));
    }
}
