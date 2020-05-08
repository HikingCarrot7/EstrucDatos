package com.sw.controller;

import com.sw.model.Grafo;
import com.sw.model.GrafoMatrizAdy;
import com.sw.view.GraficoGrafo;
import com.sw.view.Vista;
import java.awt.BorderLayout;
import java.awt.EventQueue;

/**
 *
 * @author Nicolás
 */
public class VistaController
{

    private final Vista vista;
    private GraficoGrafo graficoGrafo;

    public VistaController(Vista vista)
    {
        this.vista = vista;

        Grafo<String> grafo = new GrafoMatrizAdy<>();
        this.graficoGrafo = new GraficoGrafo(grafo);
        vista.getPanelGraficoGrafo().add(graficoGrafo, BorderLayout.CENTER);
        GraphMouseManager graphMouseManager = new GraphMouseManager(graficoGrafo);
        graficoGrafo.addMouseListener(graphMouseManager);
        graficoGrafo.addMouseMotionListener(graphMouseManager);

        EventQueue.invokeLater(() ->
        {
            grafo.nuevoVertice("Nicolás");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Antonio");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Javier");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Carlos");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Juan");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Eusebio");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);
            grafo.nuevoVertice("Emmanuel");
            this.graficoGrafo.anadirVerticeAlGrafico(grafo.getNumeroVertices() - 1);

            grafo.nuevoArco("Nicolás", "Antonio");
            grafo.nuevoArco("Nicolás", "Carlos");
            grafo.nuevoArco("Carlos", "Antonio");
            grafo.nuevoArco("Carlos", "Javier");
            grafo.nuevoArco("Nicolás", "Juan");
            grafo.nuevoArco("Juan", "Emmanuel");
            grafo.nuevoArco("Eusebio", "Emmanuel");

            graficoGrafo.repintarGrafico();
        });
    }

}
