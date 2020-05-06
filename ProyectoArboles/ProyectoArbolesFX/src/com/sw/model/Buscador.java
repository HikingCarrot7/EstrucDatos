package com.sw.model;

import com.sw.model.exceptions.ItemNotFoundException;
import com.sw.model.exceptions.NohayCoincidenciasException;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public class Buscador
{

    private final Arbol<LinkedList<Integer>, String> arbolNombres;
    private final Arbol<LinkedList<Integer>, String> arbolProfesiones;
    private final Arbol<LinkedList<Integer>, Double> arbolPromedios;

    public Buscador(Arbol<LinkedList<Integer>, String> arbolNombres,
            Arbol<LinkedList<Integer>, String> arbolProfesiones,
            Arbol<LinkedList<Integer>, Double> arbolPromedios)
    {
        this.arbolNombres = arbolNombres;
        this.arbolProfesiones = arbolProfesiones;
        this.arbolPromedios = arbolPromedios;
    }

    public LinkedList<Integer> realizarBusqueda(
            boolean nombreSeleccionado, boolean profesionSeleccionada, boolean promedioSeleccionado,
            String nombre, String profesion, double promedio) throws NohayCoincidenciasException, ItemNotFoundException
    {
        boolean buscarCoincidencias = false;
        LinkedList<Integer> coincidenciasAcumuladas = null;

        if (nombreSeleccionado)
        {
            coincidenciasAcumuladas = new LinkedList<>(arbolNombres.buscar(nombre));
            buscarCoincidencias = true;
        }

        if (profesionSeleccionada)
            if (buscarCoincidencias)
                coincidenciasAcumuladas = buscarMasCoincidencias(arbolProfesiones, coincidenciasAcumuladas, profesion);
            else
            {
                coincidenciasAcumuladas = new LinkedList<>(arbolProfesiones.buscar(profesion));
                buscarCoincidencias = true;
            }

        if (promedioSeleccionado)
            if (buscarCoincidencias)
                coincidenciasAcumuladas = buscarMasCoincidencias(arbolPromedios, coincidenciasAcumuladas, promedio);
            else
                coincidenciasAcumuladas = new LinkedList<>(arbolPromedios.buscar(promedio));

        return coincidenciasAcumuladas;
    }

    @SuppressWarnings("unchecked")
    private <T> LinkedList<Integer> buscarMasCoincidencias(Arbol arbol, LinkedList<Integer> coincidenciasAcumuladas, Comparable<?> datoABuscar)
    {
        LinkedList<Integer> busqueda = (LinkedList<Integer>) arbol.buscar(datoABuscar);
        LinkedList<Integer> mergedList = LinkedList.mergeLists(coincidenciasAcumuladas, busqueda);
        LinkedList<Integer> duplicados = new LinkedList<>();
        LinkedList.removeDuplicates(mergedList, duplicados);

        if (duplicados.isEmpty())
            throw new NohayCoincidenciasException();

        return duplicados;
    }

}
