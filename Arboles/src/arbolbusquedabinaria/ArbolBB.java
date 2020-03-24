package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 * @param <T>
 */
public class ArbolBB<T extends Comparable<T>>
{

    private NodoABB<T> raiz;

    public void insertar(T elemento)
    {
        insertar(raiz, elemento);
    }

    private void insertar(NodoABB<T> origen, T elemento)
    {
        if (isEmpty())
            raiz = new NodoABB<>(elemento);

        else if (origen.getDato().compareTo(elemento) > 0)

            if (origen.getIzq() == null)
                origen.setIzq(new NodoABB<>(elemento));
            else
                insertar(origen.getIzq(), elemento);

        else if (origen.getDato().compareTo(elemento) < 0)

            if (origen.getDer() == null)
                origen.setDer(new NodoABB<>(elemento));
            else
                insertar(origen.getDer(), elemento);
    }

    public T buscar(T elemento)
    {
        return buscar(raiz, elemento);
    }

    private T buscar(NodoABB<T> nodo, T elemento) throws ItemNotFoundException
    {
        if (elemento.compareTo(nodo.getDato()) < 0)
            if (nodo.getIzq() == null)
                throw new ItemNotFoundException();
            else
                buscar(nodo.getIzq(), elemento);

        else if (elemento.compareTo(nodo.getDato()) > 0)
            if (nodo.getDer() == null)
                throw new ItemNotFoundException();
            else
                buscar(nodo.getDer(), elemento);

        else
            return elemento;

        return null;
    }

    public void eliminar(T elemento) throws ItemNotFoundException
    {
        eliminar(raiz, elemento);
    }

    private NodoABB<T> eliminar(NodoABB<T> nodo, T elemento) throws ItemNotFoundException
    {
        if (nodo == null)
            throw new ItemNotFoundException();

        else if (elemento.compareTo(nodo.getDato()) < 0)
            nodo.setIzq(eliminar(nodo.getIzq(), elemento));

        else if (elemento.compareTo(nodo.getDato()) > 0)
            nodo.setDer(eliminar(nodo.getDer(), elemento));

        else if (nodo.getDer() != null && nodo.getIzq() != null)
        {
            NodoABB<T> minimo = buscarMin(nodo.getDer());
            nodo.setDato(minimo.getDato());
            nodo.setDer(borrarMin(nodo.getDer()));

        } else
            nodo = nodo.getIzq() != null ? nodo.getIzq() : nodo.getDer();

        return nodo;
    }

    private NodoABB<T> buscarMin(NodoABB<T> nodo)
    {
        while (nodo.getIzq() != null)
            nodo = nodo.getIzq();

        return nodo;
    }

    private NodoABB<T> borrarMin(NodoABB<T> n)
    {
        if (n.getIzq() != null)
        {
            n.setIzq(borrarMin(n.getIzq()));
            return n;

        } else
            return n.getDer();
    }

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

    public boolean esHoja(NodoABB<?> nodo)
    {
        return nodo.getIzq() == null && nodo.getDer() == null;
    }

    public boolean esInterno(NodoABB<?> nodo)
    {
        return !esHoja(nodo);
    }

    public boolean esRaiz(NodoABB<?> nodo)
    {
        return nodo == raiz;
    }

    public boolean isEmpty()
    {
        return raiz == null;
    }

    public NodoABB<T> getRaiz()
    {
        return raiz;
    }

}
