package com.sw.model;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <E>
 */
public abstract class ArbolBinario<L, E>
{

    protected NodoABB<L> raiz;

    public void preorder()
    {
        if (raiz != null)
            preorder(raiz);
    }

    private void preorder(NodoABB<L> nodo)
    {
        System.out.printf("[" + nodo.getDato().toString() + "] ");

        if (nodo.getIzq() != null)
            preorder(nodo.getIzq());

        if (nodo.getDer() != null)
            preorder(nodo.getDer());
    }

    public void inorder()
    {
        if (raiz != null)
            inorder(raiz);
    }

    private void inorder(NodoABB<L> nodo)
    {
        if (nodo.getIzq() != null)
            inorder(nodo.getIzq());

        System.out.printf("[" + nodo.getDato().toString() + "] ");

        if (nodo.getDer() != null)
            inorder(nodo.getDer());
    }

    public void postorder()
    {
        if (raiz != null)
            postorder(raiz);
    }

    private void postorder(NodoABB<L> nodo)
    {
        if (nodo.getIzq() != null)
            postorder(nodo.getIzq());

        if (nodo.getDer() != null)
            postorder(nodo.getDer());

        System.out.printf("[" + nodo.getDato().toString() + "] ");
    }

    public abstract void insertar(int idx, E element);

    public abstract L buscar(E element);

    public NodoABB<L> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(NodoABB<L> raiz)
    {
        this.raiz = raiz;
    }

}
