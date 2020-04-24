package com.sw.model;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <I>
 */
public interface Arbol<L, I>
{

    public abstract void insertar(int idx, I item);

    public abstract L buscar(I item);
}
