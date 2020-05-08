package com.sw.model;

import java.util.Arrays;

/**
 *
 * @author Nicolás
 */
public class PruebaGrafoMatrizAdy
{

    public static void main(String[] args)
    {
        GrafoMatrizAdy<String> grafo = new GrafoMatrizAdy<>();

        grafo.nuevoVertice("Nicolás");
        grafo.nuevoVertice("Antonio");
        grafo.nuevoVertice("Javier");
        grafo.nuevoVertice("Carlos");
        grafo.nuevoVertice("Juan");
        grafo.nuevoVertice("Eusebio");
        grafo.nuevoVertice("Emmanuel");

        grafo.nuevoArco("Nicolás", "Antonio");
        grafo.nuevoArco("Nicolás", "Carlos");
        grafo.nuevoArco("Carlos", "Antonio");
        grafo.nuevoArco("Carlos", "Javier");
        grafo.nuevoArco("Nicolás", "Juan");
        grafo.nuevoArco("Juan", "Emmanuel");
        grafo.nuevoArco("Eusebio", "Emmanuel");

        System.out.println("\nMatriz de adyacencia");
        grafo.mostrarMatrizAdy();

        System.out.println("\n\nEliminando a Nicolás");
        grafo.eliminarVertice("Carlos");
        grafo.mostrarMatrizAdy();

        System.out.println(Arrays.toString(grafo.getVertices()));
    }

}
