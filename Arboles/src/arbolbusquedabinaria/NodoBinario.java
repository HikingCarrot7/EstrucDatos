package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class NodoBinario<E>
{

    protected E dato;
    protected NodoBinario<E> izq;
    protected NodoBinario<E> der;

    public NodoBinario(E dato)
    {
        this(dato, null, null);
    }

    public NodoBinario(E dato, NodoBinario<E> izq, NodoBinario<E> der)
    {
        this.dato = dato;
        this.izq = izq;
        this.der = der;
    }

    public E getDato()
    {
        return dato;
    }

    public void setDato(E dato)
    {
        this.dato = dato;
    }

    public NodoBinario<E> getIzq()
    {
        return izq;
    }

    public void setIzq(NodoBinario<E> izq)
    {
        this.izq = izq;
    }

    public NodoBinario<E> getDer()
    {
        return der;
    }

    public void setDer(NodoBinario<E> der)
    {
        this.der = der;
    }

}
