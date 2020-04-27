package arbolb;

/**
 *
 * @author Nicolás
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class NodoB<E>
{

    private int llavesUsadas;
    private final Object[] claves;
    private final Object[] hijos;
    private NodoB<E> padre;

    public NodoB(int numClaves)
    {
        claves = new Object[numClaves];
        hijos = new Object[numClaves + 1];
    }

    public int getLlavesUsadas()
    {
        return llavesUsadas;
    }

    public void setLlavesUsadas(int llavesUsadas)
    {
        this.llavesUsadas = llavesUsadas;
    }

    public E getClave(int idx)
    {
        return (E) claves[idx];
    }

    public void setClave(int idx, E clave)
    {
        claves[idx] = clave;
    }

    public NodoB<E> getPadre()
    {
        return padre;
    }

    public void setPadre(NodoB<E> padre)
    {
        this.padre = padre;
    }

    public NodoB<E> getHijo(int idx)
    {
        return (NodoB<E>) hijos[idx];
    }

    public void setHijo(int idx, NodoB<E> hijo)
    {
        hijos[idx] = hijo;
    }

}
