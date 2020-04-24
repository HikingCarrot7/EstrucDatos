package arbolesavl;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public abstract class Nodo<E>
{

    private E dato;

    public Nodo(E dato)
    {
        this.dato = dato;
    }

    public E getDato()
    {
        return dato;
    }

    public void setDato(E dato)
    {
        this.dato = dato;
    }

}
