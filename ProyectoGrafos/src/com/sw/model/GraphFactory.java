package com.sw.model;

import com.sw.controller.VistaController;

/**
 *
 * @author Nicolás
 */
public class GraphFactory implements Factory
{

    private static GraphFactory instance;

    private GraphFactory()
    {
    }

    @Override
    public Grafo<String> createGraph(String tipo)
    {
        return VistaController.GRAFO_MATRIZ_ADYACENCIA.equals(tipo) ? new GrafoMatrizAdy<>() : null;
    }

    public synchronized static GraphFactory getInstance()
    {
        if (instance == null)
            instance = new GraphFactory();

        return instance;
    }

}
