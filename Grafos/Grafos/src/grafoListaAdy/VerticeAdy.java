package grafoListaAdy;

import grafoMatrizAdy.Vertice;
import java.util.ArrayList;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class VerticeAdy<E> extends Vertice<E>
{

    private ArrayList<Arco> listaAdy;

    public VerticeAdy(E dato)
    {
        super(dato);
        listaAdy = new ArrayList<>();
    }

    public ArrayList<Arco> getListaAdy()
    {
        return listaAdy;
    }

    public void setListaAdy(ArrayList<Arco> listaAdy)
    {
        this.listaAdy = listaAdy;
    }

}
