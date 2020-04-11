package arbolesavl;

import arbolbusquedabinaria.NodoABB;

/**
 *
 * @author Nicolás
 */
public class NodoAVL<E> extends NodoABB<E>
{

    private int FE;
    private NodoAVL<E> padre;

    public NodoAVL(E dato)
    {
        super(dato);
    }

    public NodoAVL(E dato, NodoABB<E> izq, NodoABB<E> der)
    {
        super(dato, izq, der);
    }

    public NodoAVL(NodoAVL<E> padre, E dato, NodoABB<E> izq, NodoABB<E> der)
    {
        this(dato, izq, der);
        this.padre = padre;
    }

    public static int altura(NodoABB<?> nodo)
    {
        if (nodo == null)
            return -1;

        return 1 + Math.max(altura(nodo.getIzq()), altura(nodo.getDer()));
    }

    public int getFE()
    {
        return FE;
    }

    public void setFE(int FE)
    {
        this.FE = FE;
    }

    public NodoAVL<E> getPadre()
    {
        return padre;
    }

    public void setPadre(NodoAVL<E> padre)
    {
        this.padre = padre;
    }

}
