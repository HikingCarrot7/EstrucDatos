package grafoListaAdy;

/**
 *
 * @author Nicolás
 */
public class Arco
{

    private int destino;
    private double peso;

    public Arco(int destino)
    {
        this.destino = destino;
    }

    public Arco(int destino, double peso)
    {
        this.destino = destino;
        this.peso = peso;
    }

    public int getDestino()
    {
        return destino;
    }

    public void setDestino(int destino)
    {
        this.destino = destino;
    }

    public double getPeso()
    {
        return peso;
    }

    public void setPeso(double peso)
    {
        this.peso = peso;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 17 * hash + this.destino;
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

        final Arco other = (Arco) obj;

        return this.destino == other.destino;
    }

}
