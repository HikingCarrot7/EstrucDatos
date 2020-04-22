package com.sw.controller;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <E>
 */
@FunctionalInterface
public interface Comparador<L, E>
{

    public int comparar(L lista, E egresado);
}
