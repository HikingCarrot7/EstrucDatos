package com.sw.model;

/**
 *
 * @author Nicolás
 */
@FunctionalInterface
public interface GraphFactory
{

    public Grafo<String> createGraph(String tipo);
}
