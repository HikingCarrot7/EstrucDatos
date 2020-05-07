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

        boolean esta = numVertice(dato) >= 0;

        if (esta)
            throw new VerticeYaExisteException();

        Vertice<E> v = new Vertice<>(dato);
        v.setNumVertice(numeroVertices);
        vertices[numeroVertices] = v;
        numeroVertices++;
    }

    @Override
    public void eliminarVertice(E datoVertice) throws VerticeNoExistenteException
    {

    }

    @Override
    public void nuevoArco(E inicio, E destino) throws VerticeNoExistenteException
    {
        int verticeInicio = numVertice(inicio);
        int verticeDestino = numVertice(destino);

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
        int verticeInicio = numVertice(inicio);
        int verticeDestino = numVertice(destino);

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
        int verticeInicio = numVertice(inicio);
        int verticeDestino = numVertice(destino);

        if (verticeInicio < 0)
            throw new VerticeNoExistenteException("El vértice " + inicio + " no existe!");

        if (verticeDestino < 0)
            throw new VerticeNoExistenteException("El vértice " + destino + " no existe!");

        return matrizAdy[verticeInicio][verticeDestino] == 1;
    }

    public int numVertice(E nombre)
    {
        Vertice<E> vertice = new Vertice<>(nombre);

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

    @Override
    public ArrayList<Arco> getArcos()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
