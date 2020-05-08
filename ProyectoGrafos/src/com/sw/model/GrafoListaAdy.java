package com.sw.model;

import com.sw.model.exceptions.ArcoNoExistenteException;
import com.sw.model.exceptions.GrafoLlenoException;
import com.sw.model.exceptions.VerticeNoExistenteException;
import com.sw.model.exceptions.VerticeYaExisteException;
import com.sw.model.util.Deque;
import com.sw.model.util.DequeList;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Nicolás
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class GrafoListaAdy<E> extends Grafo<E>
{

    private final VerticeAdy<E>[] tablaAdy;

    public GrafoListaAdy()
    {
        tablaAdy = (VerticeAdy<E>[]) new VerticeAdy<?>[MAX_NUMERO_VERTICES];
    }

    @Override
    public void nuevoVertice(E dato) throws GrafoLlenoException, VerticeYaExisteException
    {
        if (getNumeroVertices() == MAX_NUMERO_VERTICES)
            throw new GrafoLlenoException();

        if (numeroVertice(dato) >= 0)
            throw new VerticeYaExisteException();

        VerticeAdy<E> v = new VerticeAdy<>(dato);
        v.setNumVertice(numeroVertices);
        tablaAdy[numeroVertices] = v;
        numeroVertices++;
    }

    @Override
    public int eliminarVertice(E datoVertice) throws VerticeNoExistenteException
    {
        int numVertice = numeroVertice(datoVertice);

        if (numVertice < 0)
            throw new VerticeNoExistenteException("El vértice no existe!");

        eliminarAdyacenciasVertice(numVertice);
        desplazarVertices(numVertice);

        numeroVertices--;
        return numVertice;
    }

    private void eliminarAdyacenciasVertice(int numeroVertice)
    {
        for (int i = 0; i < getNumeroVertices(); i++)
        {
            Arco arco = new Arco(tablaAdy[i].getNumVertice(), numeroVertice);

            if (tablaAdy[i].getListaAdy().contains(arco))
                tablaAdy[i].getListaAdy().remove(arco);
        }
    }

    private void desplazarVertices(int idxInicioDesplazamiento)
    {
        tablaAdy[idxInicioDesplazamiento] = null;

        for (int i = idxInicioDesplazamiento; i < getNumeroVertices() - 1; i++)
        {
            tablaAdy[i] = tablaAdy[i + 1];
            tablaAdy[i].setNumVertice(tablaAdy[i].getNumVertice() - 1);
            tablaAdy[i].getListaAdy().forEach(arco -> arco.setOrigen(arco.getOrigen() - 1));
        }

        for (int i = 0; i < getNumeroVertices() - 1; i++)
            tablaAdy[i].getListaAdy().stream()
                    .filter(arco -> arco.getDestino() > idxInicioDesplazamiento)
                    .forEach(arco -> arco.setDestino(arco.getDestino() - 1));
    }

    @Override
    public void nuevoArco(E origen, E destino) throws VerticeNoExistenteException
    {
        if (!sonAdyacentes(origen, destino))
        {
            int verticeOrigen = numeroVertice(origen);
            int verticeDestino = numeroVertice(destino);

            tablaAdy[verticeOrigen].getListaAdy().add(new Arco(verticeOrigen, verticeDestino));
            tablaAdy[verticeDestino].getListaAdy().add(new Arco(verticeDestino, verticeOrigen));
        }
    }

    @Override
    public void eliminarArco(E origen, E destino) throws ArcoNoExistenteException, VerticeNoExistenteException
    {
        if (sonAdyacentes(origen, destino))
        {
            int verticeOrigen = numeroVertice(origen);
            int verticeDestino = numeroVertice(destino);

            for (int i = 0; i < getNumeroVertices(); i++)
                tablaAdy[i].getListaAdy().removeIf(arco -> arco.equals(new Arco(verticeOrigen, verticeDestino)));

        } else
            throw new ArcoNoExistenteException();
    }

    @Override
    public boolean sonAdyacentes(E origen, E destino) throws VerticeNoExistenteException
    {
        int verticeOrigen = numeroVertice(origen);
        int verticeDestino = numeroVertice(destino);

        if (verticeOrigen < 0)
            throw new VerticeNoExistenteException("El vértice " + origen + " no existe!");

        if (verticeDestino < 0)
            throw new VerticeNoExistenteException("El vértice " + destino + " no existe!");

        return tablaAdy[verticeOrigen].getListaAdy().contains(new Arco(verticeOrigen, verticeDestino));
    }

    @Override
    public int numeroVertice(E dato)
    {
        VerticeAdy<E> vertice = new VerticeAdy<>(dato);

        for (int i = 0; i < numeroVertices; i++)
            if (tablaAdy[i].equals(vertice))
                return i;

        return -1;
    }

    @Override
    public ArrayList<E> recorridoAnchura()
    {
        return recorridoAnchura(0);
    }

    public ArrayList<E> recorridoAnchura(int numeroVertice)
    {
        ArrayList<E> recorrido = new ArrayList<>();

        if (!isEmpty())
        {
            Deque<VerticeAdy<E>> colaRecorrido = new DequeList<>();
            ArrayList<VerticeAdy<E>> verticesRecorridos = new ArrayList<>();

            colaRecorrido.insertFirst(tablaAdy[numeroVertice]);
            verticesRecorridos.add(tablaAdy[numeroVertice]);
            recorrido.add(tablaAdy[numeroVertice].getDato());

            while (!colaRecorrido.isEmpty())
            {
                Vertice<E> verticeActual = colaRecorrido.removeFirst();

                for (int i = 0; i < tablaAdy[verticeActual.getNumVertice()].getListaAdy().size(); i++)
                {
                    int verticeAdy = tablaAdy[verticeActual.getNumVertice()].getListaAdy().get(i).getDestino();

                    if (!verticesRecorridos.contains(tablaAdy[verticeAdy]))
                    {
                        verticesRecorridos.add(tablaAdy[verticeAdy]);
                        colaRecorrido.insertLast(tablaAdy[verticeAdy]);
                        recorrido.add(tablaAdy[verticeAdy].getDato());
                    }
                }
            }
        }

        return recorrido;
    }

    @Override
    public List<E> recorridoProfundidad()
    {
        return recorridoProfundidad(0);
    }

    public List<E> recorridoProfundidad(int numeroVertice)
    {
        List<E> recorrido = new ArrayList<>();
        Deque<Vertice<E>> pila = new DequeList<>();
        List<Vertice<E>> verticesRecorridos = new ArrayList<>();

        pila.insertLast(tablaAdy[numeroVertice]);
        verticesRecorridos.add(tablaAdy[numeroVertice]);

        return recorridoProfundidad(pila, verticesRecorridos, recorrido);
    }

    private List<E> recorridoProfundidad(Deque<Vertice<E>> pila, List<Vertice<E>> verticesRecorridos, List<E> recorrido)
    {
        if (!pila.isEmpty())
        {
            Vertice<E> verticeActual = pila.removeLast();

            for (int i = 0; i < tablaAdy[verticeActual.getNumVertice()].getListaAdy().size(); i++)
            {
                int verticeAdy = tablaAdy[verticeActual.getNumVertice()].getListaAdy().get(i).getDestino();

                if (!verticesRecorridos.contains(tablaAdy[verticeAdy]))
                {
                    pila.insertLast(tablaAdy[verticeAdy]);
                    verticesRecorridos.add(tablaAdy[verticeAdy]);
                }
            }

            recorrido.add(verticeActual.getDato());
            recorridoProfundidad(pila, verticesRecorridos, recorrido);
        }

        return recorrido;
    }

    public void mostrarListaAdy()
    {
        for (int i = 0; i < getNumeroVertices(); i++)
        {
            System.out.print(String.format("%15s:[%d]>", tablaAdy[i].getDato(), i));

            if (!tablaAdy[i].getListaAdy().isEmpty())
                System.out.print(String.format("[%d]", tablaAdy[i].getListaAdy().get(0).getDestino()));

            for (int j = 1; j < tablaAdy[i].getListaAdy().size(); j++)
                System.out.print(String.format("->[%d]", tablaAdy[i].getListaAdy().get(j).getDestino()));

            System.out.println("");
        }
    }

    @Override
    public Vertice<E>[] getVertices()
    {
        return tablaAdy;
    }

    @Override
    public List<Arco> getArcos()
    {
        List<Arco> arcos = new ArrayList<>();

        for (int i = 0; i < getNumeroVertices(); i++)
            tablaAdy[i].getListaAdy().forEach(arco ->
            {
                if (!arcos.contains(arco))
                    arcos.add(arco);
            });

        return arcos;
    }

}
