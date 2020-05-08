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

        boolean esta = numeroVertice(dato) >= 0;

        if (esta)
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
            matrizAdy[numFila][j] = 0;

        for (int i = numFila; i < getNumeroVertices() - 1; i++)
            matrizAdy[i] = matrizAdy[i + 1];
    }

    private void eliminarColumnaMatrizAdy(int numColumna)
    {
        for (int i = 0; i < getNumeroVertices(); i++)
            matrizAdy[i][numColumna] = 0;

        for (int i = 0; i < getNumeroVertices() - 1; i++)
            for (int j = numColumna; j < getNumeroVertices() - 1; j++)
                matrizAdy[i][j] = matrizAdy[i][j + 1];
    }

    @Override
    public void nuevoArco(E inicio, E destino) throws VerticeNoExistenteException
    {
        int verticeInicio = numeroVertice(inicio);
        int verticeDestino = numeroVertice(destino);

        if (verticeInicio < 0)
            throw new VerticeNoExistenteException("El vértice " + inicio + " no existe!");

        if (verticeDestino < 0)
            throw new VerticeNoExistenteException("El vértice " + destino + " no existe!");

        matrizAdy[verticeInicio][verticeDestino] = 1;
        matrizAdy[verticeDestino][verticeInicio] = 1;
    }

    @Override
    public void eliminarArco(E inicio, E destino) throws ArcoNoExistenteException, VerticeNoExistenteException
    {
        int verticeInicio = numeroVertice(inicio);
        int verticeDestino = numeroVertice(destino);

        if (verticeInicio < 0)
            throw new VerticeNoExistenteException("El vértice " + inicio + " no existe!");

        if (verticeDestino < 0)
            throw new VerticeNoExistenteException("El vértice " + destino + " no existe!");

        if (matrizAdy[verticeInicio][verticeDestino] == 0)
            throw new ArcoNoExistenteException();

        matrizAdy[verticeInicio][verticeDestino] = 0;
        matrizAdy[verticeDestino][verticeInicio] = 0;
    }

    @Override
    public boolean sonAdyacentes(E inicio, E destino) throws VerticeNoExistenteException
    {
        int verticeInicio = numeroVertice(inicio);
        int verticeDestino = numeroVertice(destino);

        if (verticeInicio < 0)
            throw new VerticeNoExistenteException("El vértice " + inicio + " no existe!");

        if (verticeDestino < 0)
            throw new VerticeNoExistenteException("El vértice " + destino + " no existe!");

        return matrizAdy[verticeInicio][verticeDestino] == 1;
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
    public ArrayList<E> recorridoAnchura()
    {
        return recorridoAnchura(0);
    }

    public ArrayList<E> recorridoAnchura(int numeroVertice)
    {
        ArrayList<E> recorrido = new ArrayList<>();

        if (!isEmpty())
        {
            Deque<Vertice<E>> colaRecorrido = new DequeList<>();
            ArrayList<Vertice<E>> verticesRecorridos = new ArrayList<>();

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
    public ArrayList<E> recorridoProfundidad()
    {
        return recorridoProfundidad(0);
    }

    public ArrayList<E> recorridoProfundidad(int numeroVertice)
    {
        ArrayList<E> recorrido = new ArrayList<>();
        Deque<Vertice<E>> pila = new DequeList<>();
        List<Vertice<E>> verticesRecorridos = new ArrayList<>();

        pila.insertLast(vertices[numeroVertice]);
        verticesRecorridos.add(vertices[numeroVertice]);

        return recorridoProfundidad(pila, verticesRecorridos, recorrido);
    }

    private ArrayList<E> recorridoProfundidad(Deque<Vertice<E>> pila, List<Vertice<E>> verticesRecorridos, ArrayList<E> recorrido)
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
    public ArrayList<Arco> getArcos()
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
