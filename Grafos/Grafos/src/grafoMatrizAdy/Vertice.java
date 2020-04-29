package grafoMatrizAdy;

import java.util.Objects;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class Vertice<E>
{

    private E dato;
    private int numVertice;

    public Vertice(E dato)
    {
        this.dato = dato;
        numVertice = -1;
    }

    public E getDato()
    {
        return dato;
    }

    public void setDato(E dato)
    {
        this.dato = dato;
    }

    public int getNumVertice()
    {
        return numVertice;
    }

    public void setNumVertice(int numVertice)
    {
        this.numVertice = numVertice;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.dato);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Vertice<?> other = (Vertice<?>) obj;

        return Objects.equals(this.dato, other.dato);
    }

}
