package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 * @param <T>
 */
public abstract class ArbolBinario<T extends Comparable<T>>
{

    protected NodoBinario<T> raiz;

    public void preorder()
    {
        if (raiz != null)
            preorder(raiz);
    }

    private void preorder(NodoBinario<T> nodo)
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

    private void inorder(NodoBinario<T> nodo)
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

    private void postorder(NodoBinario<T> nodo)
    {
        if (nodo.getIzq() != null)
            postorder(nodo.getIzq());

        if (nodo.getDer() != null)
            postorder(nodo.getDer());

        System.out.printf("[" + nodo.getDato().toString() + "] ");
    }

    public NodoBinario<T> getRaiz()
    {
        return raiz;
    }

    public void setRaiz(NodoBinario<T> raiz)
    {
        this.raiz = raiz;
    }

}
