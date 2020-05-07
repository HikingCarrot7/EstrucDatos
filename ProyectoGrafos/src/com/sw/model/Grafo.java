package com.sw.model;

import com.sw.model.exceptions.ArcoNoExistenteException;
import com.sw.model.exceptions.GrafoLlenoException;
import com.sw.model.exceptions.VerticeNoExistenteException;
import com.sw.model.exceptions.VerticeYaExisteException;
import java.util.ArrayList;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public abstract class Grafo<E>
{

    protected int numeroVertices;
    public final static int MAX_NUMERO_VERTICES = 10;

    public abstract void nuevoVertice(E datoVertice) throws GrafoLlenoException, VerticeYaExisteException;

    public abstract void eliminarVertice(E datoVertice) throws VerticeNoExistenteException;

    public abstract void nuevoArco(E inicio, E destino) throws VerticeNoExistenteException;

    public abstract void eliminarArco(E inicio, E destino) throws ArcoNoExistenteException, VerticeNoExistenteException;

    public abstract boolean sonAdyacentes(E inicio, E destino) throws VerticeNoExistenteException;

    public abstract ArrayList<E> recorridoAnchura();

    public abstract ArrayList<E> recorridoProfundidad();

    public boolean buscarAnchura(E dato)
    {
        return recorridoAnchura().contains(dato);
    }

    public boolean buscarProfundidad(E dato)
    {
        return recorridoProfundidad().contains(dato);
    }

    public boolean isEmpty()
    {
        return numeroVertices == 0;
    }

    public int getNumeroVertices()
    {
        return numeroVertices;
    }

}
