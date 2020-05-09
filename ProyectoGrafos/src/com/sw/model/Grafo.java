package com.sw.model;

import com.sw.model.exceptions.ArcoNoExistenteException;
import com.sw.model.exceptions.GrafoLlenoException;
import com.sw.model.exceptions.NoHayCoincidenciasException;
import com.sw.model.exceptions.VerticeNoExistenteException;
import com.sw.model.exceptions.VerticeYaExisteException;
import java.util.List;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public abstract class Grafo<E>
{

    public final static int MAX_NUMERO_VERTICES = 50;

    protected int numeroVertices;

    public abstract void nuevoVertice(E datoVertice) throws GrafoLlenoException, VerticeYaExisteException;

    public abstract int eliminarVertice(E datoVertice) throws VerticeNoExistenteException;

    public abstract void nuevoArco(E origen, E destino) throws VerticeNoExistenteException;

    public abstract void eliminarArco(E origen, E destino) throws ArcoNoExistenteException, VerticeNoExistenteException;

    public abstract boolean sonAdyacentes(E origen, E destino) throws VerticeNoExistenteException;

    public abstract int numeroVertice(E dato);

    public abstract List<E> recorridoAnchura();

    public abstract List<E> recorridoProfundidad();

    public abstract Vertice<E>[] getVertices();

    public Arco getArco(E origen, E destino) throws VerticeNoExistenteException, ArcoNoExistenteException
    {
        if (sonAdyacentes(origen, destino))
            return new Arco(numeroVertice(origen), numeroVertice(destino));

        throw new ArcoNoExistenteException();
    }

    public abstract List<Arco> getArcos();

    public int buscarAnchura(E dato) throws NoHayCoincidenciasException
    {
        if (recorridoAnchura().indexOf(dato) < 0)
            throw new NoHayCoincidenciasException("No hay coincidencias para: " + dato);

        return numeroVertice(dato);
    }

    public int buscarProfundidad(E dato) throws NoHayCoincidenciasException
    {
        if (recorridoProfundidad().indexOf(dato) < 0)
            throw new NoHayCoincidenciasException("No hay coincidencias para: " + dato);

        return numeroVertice(dato);
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
