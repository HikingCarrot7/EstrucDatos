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
public class GrafoMatrizAdy<E> extends Grafo<E>
{

    private final Vertice<E>[] vertices;
    private final int[][] matrizAdy;

    public GrafoMatrizAdy()
    {
        matrizAdy = new int[MAX_NUMERO_VERTICES][MAX_NUMERO_VERTICES];
        vertices = (Vertice<E>[]) new Vertice<?>[MAX_NUMERO_VERTICES];
    }

    @Override
    public void nuevoVertice(E dato) throws GrafoLlenoException, VerticeYaExisteException
    {
        if (getNumeroVertices() == MAX_NUMERO_VERTICES)
            throw new GrafoLlenoException();

        if (existeVertice(dato))
            throw new VerticeYaExisteException();

        Vertice<E> v = new Vertice<>(dato);
        v.setNumVertice(numeroVertices);
        vertices[numeroVertices] = v;
        numeroVertices++;
    }

    @Override
    public int eliminarVertice(E datoVertice) throws VerticeNoExistenteException
    {
        int numVertice = numeroVertice(datoVertice);

        if (numVertice < 0)
            throw new VerticeNoExistenteException("El vértice no existe!");

        eliminarFilaMatrizAdy(numVertice);
        eliminarColumnaMatrizAdy(numVertice);
        desplazarVertices(numVertice);
        numeroVertices--;

        return numVertice;
    }

    private void desplazarVertices(int inicioIdx)
    {
        vertices[inicioIdx] = null;

        for (int i = inicioIdx; i < getNumeroVertices() - 1; i++)
        {
            vertices[i + 1].setNumVertice(vertices[i + 1].getNumVertice() - 1);
            vertices[i] = vertices[i + 1];
        }
    }

    private void eliminarFilaMatrizAdy(int numFila)
    {
        for (int j = 0; j < getNumeroVertices(); j++)
            for (int i = numFila; i < getNumeroVertices() - 1; i++)
                matrizAdy[i][j] = matrizAdy[i + 1][j];

        for (int j = 0; j < getNumeroVertices(); j++)
            matrizAdy[getNumeroVertices() - 1][j] = 0;
    }

    private void eliminarColumnaMatrizAdy(int numColumna)
    {
        for (int i = 0; i < getNumeroVertices() - 1; i++)
            for (int j = numColumna; j < getNumeroVertices() - 1; j++)
                matrizAdy[i][j] = matrizAdy[i][j + 1];

        for (int i = 0; i < getNumeroVertices(); i++)
            matrizAdy[i][getNumeroVertices() - 1] = 0;
    }

    @Override
    public void nuevoArco(E origen, E destino) throws VerticeNoExistenteException
    {
        if (!sonAdyacentes(origen, destino) && !origen.equals(destino))
        {
            int verticeOrigen = numeroVertice(origen);
            int verticeDestino = numeroVertice(destino);

            matrizAdy[verticeOrigen][verticeDestino] = 1;
            matrizAdy[verticeDestino][verticeOrigen] = 1;
        }
    }

    @Override
    public void eliminarArco(E origen, E destino) throws ArcoNoExistenteException, VerticeNoExistenteException
    {
        if (sonAdyacentes(origen, destino))
        {
            int verticeOrigen = numeroVertice(origen);
            int verticeDestino = numeroVertice(destino);

            if (matrizAdy[verticeOrigen][verticeDestino] == 0)
                throw new ArcoNoExistenteException();

            matrizAdy[verticeOrigen][verticeDestino] = 0;
            matrizAdy[verticeDestino][verticeOrigen] = 0;
        }
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

        return matrizAdy[verticeOrigen][verticeDestino] == 1;
    }

    @Override
    public int numeroVertice(E dato)
    {
        Vertice<E> vertice = new Vertice<>(dato);

        for (int i = 0; i < numeroVertices; i++)
            if (vertices[i].equals(vertice))
                return i;

        return -1;
    }

    @Override
    public List<E> recorridoAnchura()
    {
        return recorridoAnchura(0);
    }

    public List<E> recorridoAnchura(int numeroVertice)
    {
        List<E> recorrido = new ArrayList<>();

        if (!isEmpty())
        {
            Deque<Vertice<E>> colaRecorrido = new DequeList<>();
            List<Vertice<E>> verticesRecorridos = new ArrayList<>();

            colaRecorrido.insertFirst(vertices[numeroVertice]);
            verticesRecorridos.add(vertices[numeroVertice]);
            recorrido.add(vertices[numeroVertice].getDato());

            while (!colaRecorrido.isEmpty())
            {
                Vertice<E> verticeActual = colaRecorrido.removeFirst();

                for (int i = 0; i < matrizAdy[verticeActual.getNumVertice()].length; i++)
                    if (matrizAdy[verticeActual.getNumVertice()][i] == 1 && !verticesRecorridos.contains(vertices[i]))
                    {
                        verticesRecorridos.add(vertices[i]);
                        colaRecorrido.insertLast(vertices[i]);
                        recorrido.add(vertices[i].getDato());
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

        if (!isEmpty())
        {
            pila.insertLast(vertices[numeroVertice]);
            verticesRecorridos.add(vertices[numeroVertice]);
        }

        return recorridoProfundidad(pila, verticesRecorridos, recorrido);
    }

    private List<E> recorridoProfundidad(Deque<Vertice<E>> pila, List<Vertice<E>> verticesRecorridos, List<E> recorrido)
    {
        if (!pila.isEmpty())
        {
            Vertice<E> verticeActual = pila.removeLast();

            for (int i = 0; i < matrizAdy[verticeActual.getNumVertice()].length; i++)
                if (matrizAdy[verticeActual.getNumVertice()][i] == 1 && !verticesRecorridos.contains(vertices[i]))
                {
                    pila.insertLast(vertices[i]);
                    verticesRecorridos.add(vertices[i]);
                }

            recorrido.add(verticeActual.getDato());
            recorridoProfundidad(pila, verticesRecorridos, recorrido);
        }

        return recorrido;
    }

    public void mostrarMatrizAdy()
    {
        for (int i = 0; i < numeroVertices; i++)
        {
            System.out.print(String.format("%15s:", vertices[i].getDato()));

            for (int j = 0; j < numeroVertices; j++)
                System.out.print(String.format("[%d]", matrizAdy[i][j]));

            System.out.println("");
        }
    }

    @Override
    public List<Arco> getArcos()
    {
        ArrayList<Arco> arcos = new ArrayList<>();

        for (int i = 0; i < matrizAdy.length; i++)
            for (int j = i; j < matrizAdy.length; j++)
                if (matrizAdy[i][j] == 1)
                    arcos.add(new Arco(i, j));

        return arcos;
    }

    @Override
    public Vertice<E>[] getVertices()
    {
        return vertices;
    }

    public int[][] getMatrizAdy()
    {
        return matrizAdy;
    }

}
