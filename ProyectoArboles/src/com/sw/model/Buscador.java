package com.sw.model;

import com.sw.model.trees.ArbolBinario;
import com.sw.model.exceptions.NohayCoincidenciasException;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public class Buscador
{

    private final ArbolBinario<LinkedList<Integer>, String> arbolNombres;
    private final ArbolBinario<LinkedList<Integer>, String> arbolProfesiones;
    private final ArbolBinario<LinkedList<Integer>, Double> arbolPromedios;

    public Buscador(ArbolBinario<LinkedList<Integer>, String> arbolNombres,
            ArbolBinario<LinkedList<Integer>, String> arbolProfesiones,
            ArbolBinario<LinkedList<Integer>, Double> arbolPromedios)
    {
        this.arbolNombres = arbolNombres;
        this.arbolProfesiones = arbolProfesiones;
        this.arbolPromedios = arbolPromedios;
    }

    public LinkedList<Integer> realizarBusqueda(boolean nombreSeleccionado, boolean profesionSeleccionada, boolean promedioSeleccionado,
            String nombre, String profesion, double promedio)
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
    private LinkedList<Integer> buscarMasCoincidencias(ArbolBinario arbolBinario, LinkedList<Integer> coincidenciasAcumuladas, Object datoABuscar)
    {
        LinkedList<Integer> busquedaProfesiones = (LinkedList<Integer>) arbolBinario.buscar(datoABuscar);
        LinkedList<Integer> mergedList = LinkedList.mergeLists(coincidenciasAcumuladas, busquedaProfesiones);
        LinkedList<Integer> duplicados = new LinkedList<>();
        LinkedList.removeDuplicates(mergedList, duplicados);

        if (duplicados.isEmpty())
            throw new NohayCoincidenciasException();

        return duplicados;
    }

}
