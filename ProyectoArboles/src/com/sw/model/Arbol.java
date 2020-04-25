package com.sw.model;

import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <I>
 */
public interface Arbol<L, I>
{

    public void insertar(int idx, I item);

    public L buscar(I item);

    public LinkedList<L> preorder();

    public LinkedList<L> inorder();

    public LinkedList<L> postorder();
}
