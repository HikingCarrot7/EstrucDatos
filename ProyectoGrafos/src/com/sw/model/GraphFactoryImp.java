package com.sw.model;

import com.sw.controller.VistaController;

/**
 *
 * @author Nicolás
 */
public class GraphFactoryImp implements GraphFactory
{

    private static GraphFactoryImp instance;

    private GraphFactoryImp()
    {
    }

    @Override
    public Grafo<String> createGraph(String tipo)
    {
        return VistaController.GRAFO_MATRIZ_ADYACENCIA.equals(tipo) ? new GrafoMatrizAdy<>() : new GrafoListaAdy<>();
    }

    public synchronized static GraphFactoryImp getInstance()
    {
        if (instance == null)
            instance = new GraphFactoryImp();

        return instance;
    }

}
