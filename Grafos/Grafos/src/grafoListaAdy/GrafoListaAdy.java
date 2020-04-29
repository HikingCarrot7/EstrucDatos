package grafoListaAdy;

import java.util.ArrayList;

/**
 *
 * @author Nicolás
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class GrafoListaAdy<E>
{

    private int numeroVertices;
    private final VerticeAdy<E>[] tablaAdy;

    public GrafoListaAdy(int maxVertices)
    {
        tablaAdy = (VerticeAdy<E>[]) new VerticeAdy<?>[maxVertices];
    }

    public ArrayList<Arco> listaAdy(int vertice)
    {
        if (vertice < 0 || vertice > numeroVertices)
            throw new RuntimeException("El vértice está fuera de rango.");

        return tablaAdy[vertice].getListaAdy();
    }

    public void nuevoVertice(E dato)
    {
        boolean esta = numVertice(dato) >= 0;

        if (!esta)
        {
            VerticeAdy<E> v = new VerticeAdy<>(dato);
            v.setNumVertice(numeroVertices);
            tablaAdy[numeroVertices] = v;
            numeroVertices++;
        }
    }

    public void nuevoArco(E inicio, E destino) throws RuntimeException
    {
        if (!sonAdyacentes(inicio, destino))
        {
            int verticeInicio = numVertice(inicio);
            int verticeDestino = numVertice(destino);

            tablaAdy[verticeInicio].getListaAdy().add(new Arco(verticeDestino));
            tablaAdy[verticeDestino].getListaAdy().add(new Arco(verticeInicio));
        }
    }

    public void eliminarArco(E inicio, E destino)
    {
        int verticeInicio = numVertice(inicio);
        int verticeDestino = numVertice(destino);

        if (verticeInicio < 0 || verticeDestino < 0)
            throw new RuntimeException("Algún vertice no existe.");

        if (sonAdyacentes(inicio, destino))
        {
            tablaAdy[verticeInicio].getListaAdy().remove(new Arco(verticeDestino));
            tablaAdy[verticeDestino].getListaAdy().remove(new Arco(verticeInicio));
        }
    }

    public boolean sonAdyacentes(E inicio, E destino) throws RuntimeException
    {
        int verticeInicio = numVertice(inicio);
        int verticeDestino = numVertice(destino);

        if (verticeInicio < 0 || verticeDestino < 0)
            throw new RuntimeException("Algún vertice no existe.");

        return tablaAdy[verticeInicio].getListaAdy().contains(new Arco(verticeDestino));
    }

    public int numVertice(E nombre)
    {
        VerticeAdy<E> vertice = new VerticeAdy<>(nombre);

        for (int i = 0; i < numeroVertices; i++)
            if (tablaAdy[i].equals(vertice))
                return i;

        return -1;
    }

    public void mostrarGradoListaAdy()
    {
        for (int i = 0; i < numeroVertices; i++)
        {
            System.out.print(String.format("%15s:[%d]>", tablaAdy[i].getDato(), i));

            if (!tablaAdy[i].getListaAdy().isEmpty())
                System.out.print(String.format("[%d]", tablaAdy[i].getListaAdy().get(0).getDestino()));

            for (int j = 1; j < tablaAdy[i].getListaAdy().size(); j++)
                System.out.print(String.format("->[%d]", tablaAdy[i].getListaAdy().get(j).getDestino()));

            System.out.println("");
        }
    }

    public int getNumVerts()
    {
        return numeroVertices;
    }

    public void setNumVerts(int numVerts)
    {
        this.numeroVertices = numVerts;
    }

    public VerticeAdy<E>[] getTablaAdy()
    {
        return tablaAdy;
    }

}
