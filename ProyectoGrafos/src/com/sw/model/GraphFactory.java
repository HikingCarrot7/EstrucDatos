package com.sw.model;

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
        return null;
    }

    public synchronized static GraphFactory getInstance()
    {
        if (instance == null)
            instance = new GraphFactory();

        return instance;
    }

}
