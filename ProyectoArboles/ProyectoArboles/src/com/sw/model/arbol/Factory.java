package com.sw.model.arbol;

import com.sw.model.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public interface Factory
{

    public Arbol<LinkedList<Integer>, String> crearArbolNombres(String tipoArbol);

    public Arbol<LinkedList<Integer>, String> crearArbolProfesiones(String tipoArbol);

    public Arbol<LinkedList<Integer>, Double> crearArbolPromedios(String tipoArbol);
}
