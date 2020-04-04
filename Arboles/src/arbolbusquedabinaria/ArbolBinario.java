package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 * @param <T>
 */
public abstract class ArbolBinario<T extends Comparable<T>>
{

    protected NodoABB<T> raiz;

    public void preorder()
    {
        preorder(raiz);
    }

    private void preorder(NodoABB<T> nodo)
    {
        System.out.printf("[" + nodo.getDato().toString() + "] ");

        if (nodo.getIzq() != null)
            preorder(nodo.getIzq());

        if (nodo.getDer() != null)
            preorder(nodo.getDer());
    }

    public void inorder()
    {
        inorder(raiz);
    }

    private void inorder(NodoABB<T> nodo)
    {
        if (nodo.getIzq() != null)
            inorder(nodo.getIzq());

        System.out.printf("[" + nodo.getDato().toString() + "] ");

        if (nodo.getDer() != null)
            inorder(nodo.getDer());
    }

    public void postorder()
    {
        postorder(raiz);
    }

    private void postorder(NodoABB<T> nodo)
    {
        if (nodo.getIzq() != null)
            postorder(nodo.getIzq());

        if (nodo.getDer() != null)
            postorder(nodo.getDer());

        System.out.printf("[" + nodo.getDato().toString() + "] ");
    }

    public NodoABB<T> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(NodoABB<T> raiz)
    {
        this.raiz = raiz;
    }

}
