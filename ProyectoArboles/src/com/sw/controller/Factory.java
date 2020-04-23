package com.sw.controller;

import com.sw.model.trees.ArbolBinario;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public interface Factory
{

    public ArbolBinario<LinkedList<Integer>, String> crearArbolNombres(String tipoArbol);

    public ArbolBinario<LinkedList<Integer>, String> crearArbolProfesiones(String tipoArbol);

    public ArbolBinario<LinkedList<Integer>, Double> crearArbolPromedios(String tipoArbol);
}
