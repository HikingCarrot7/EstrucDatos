package com.sw.controller;

import com.sw.model.ArbolBinario;

/**
 *
 * @author Nicolás
 * @param <L>
 * @param <E>
 */
public interface Factory<L, E>
{

    public ArbolBinario<L, E> crearArbolNombres(String tipoArbol);

    public ArbolBinario<L, E> crearArbolProfesiones(String tipoArbol);

    public ArbolBinario<L, E> crearArbolPromedio(String tipoArbol);
}
