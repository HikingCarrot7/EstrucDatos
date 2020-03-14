package com.sw.model;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class Grafico
{

    private ArrayList<Vertice> vertices;
    private ArrayList<Arista> aristas;

    public Grafico()
    {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }

    public void addVertice(Vertice vertice)
    {
        vertices.add(vertice);
    }

    public void addVertice(double centerX, double centerY)
    {
        addVertice(new Vertice(centerX, centerY));
    }

    public void addArista(Vertice verticeInicial, Vertice verticeFinal)
    {
        addArista(new Arista(verticeInicial, verticeFinal));
    }

    public void addArista(Arista arista)
    {
        aristas.add(arista);
    }

    public void eliminarArista(Arista arista)
    {
        aristas.remove(arista);
    }

    public ArrayList<Vertice> getVertices()
    {
        return vertices;
    }

    public ArrayList<Arista> getAristas()
    {
        return aristas;
    }

}
