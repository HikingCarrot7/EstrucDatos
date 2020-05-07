package com.sw.model;

/**
 *
 * @author Nicolás
 */
@FunctionalInterface
public interface Factory
{

    public Grafo<String> createGraph(String tipo);

}
