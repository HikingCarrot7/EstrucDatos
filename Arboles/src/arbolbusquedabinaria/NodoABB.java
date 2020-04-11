package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class NodoABB<E>
{

    protected E dato;
    protected NodoABB<E> izq;
    protected NodoABB<E> der;

    public NodoABB(E dato)
    {
        this(dato, null, null);
    }

    public NodoABB(E dato, NodoABB<E> izq, NodoABB<E> der)
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

    public NodoABB<E> getIzq()
    {
        return izq;
    }

    public void setIzq(NodoABB<E> izq)
    {
        this.izq = izq;
    }

    public NodoABB<E> getDer()
    {
        return der;
    }

    public void setDer(NodoABB<E> der)
    {
        this.der = der;
    }

}
