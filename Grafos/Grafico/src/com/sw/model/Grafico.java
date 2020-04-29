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

    public void eliminarVertice(Vertice v)
    {
        vertices.remove(v);
        eliminarAristasRelacionadosConVertice(v);
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

    public void eliminarAristasRelacionadosConVertice(Vertice v)
    {
        aristas.removeIf(a -> a.getPuntoInicial() == v || a.getPuntoFinal() == v);
    }

    public ArrayList<Vertice> getVertices()
    {
        return vertices;
    }

    public ArrayList<Arista> getAristas()
    {
        return aristas;
    }

    public boolean existenVerticesSuficientesParaCrearArista()
    {
        return vertices.size() > 1;
    }

}
