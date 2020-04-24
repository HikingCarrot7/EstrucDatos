package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 * @param <T>
 */
public class ArbolBB<T extends Comparable<T>> extends ArbolBinario<T>
{

    private NodoBinario<T> raiz;

    public void insertar(T elemento)
    {
        insertar(raiz, elemento);
    }

    private void insertar(NodoBinario<T> origen, T elemento)
    {
        if (isEmpty())
            raiz = new NodoBinario<>(elemento);

        else if (origen.getDato().compareTo(elemento) > 0)

            if (origen.getIzq() == null)
                origen.setIzq(new NodoBinario<>(elemento));
            else
                insertar(origen.getIzq(), elemento);

        else if (origen.getDato().compareTo(elemento) < 0)

            if (origen.getDer() == null)
                origen.setDer(new NodoBinario<>(elemento));
            else
                insertar(origen.getDer(), elemento);
    }

    public T buscar(T elemento)
    {
        return buscar(raiz, elemento);
    }

    private T buscar(NodoBinario<T> nodo, T elemento) throws ItemNotFoundException
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

    private NodoBinario<T> eliminar(NodoBinario<T> nodo, T elemento) throws ItemNotFoundException
    {
        if (nodo == null)
            throw new ItemNotFoundException();

        else if (elemento.compareTo(nodo.getDato()) < 0)
            nodo.setIzq(eliminar(nodo.getIzq(), elemento));

        else if (elemento.compareTo(nodo.getDato()) > 0)
            nodo.setDer(eliminar(nodo.getDer(), elemento));

        else if (nodo.getDer() != null && nodo.getIzq() != null)
        {
            NodoBinario<T> minimo = buscarMin(nodo.getDer());
            nodo.setDato(minimo.getDato());
            nodo.setDer(borrarMin(nodo.getDer()));

        } else
            nodo = nodo.getIzq() != null ? nodo.getIzq() : nodo.getDer();

        return nodo;
    }

    private NodoBinario<T> buscarMin(NodoBinario<T> nodo)
    {
        while (nodo.getIzq() != null)
            nodo = nodo.getIzq();

        return nodo;
    }

    private NodoBinario<T> borrarMin(NodoBinario<T> n)
    {
        if (n.getIzq() != null)
        {
            n.setIzq(borrarMin(n.getIzq()));
            return n;

        } else
            return n.getDer();
    }

    public boolean esHoja(NodoBinario<?> nodo)
    {
        return nodo.getIzq() == null && nodo.getDer() == null;
    }

    public boolean esInterno(NodoBinario<?> nodo)
    {
        return !esHoja(nodo);
    }

    public boolean esRaiz(NodoBinario<?> nodo)
    {
        return nodo == raiz;
    }

    public boolean isEmpty()
    {
        return raiz == null;
    }

}
