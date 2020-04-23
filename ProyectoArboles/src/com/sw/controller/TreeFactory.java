package com.sw.controller;

import com.sw.model.ArbolBB;
import com.sw.model.ArbolBinario;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public class TreeFactory implements Factory
{

    @Override
    public ArbolBinario<LinkedList<Integer>, String> crearArbolNombres(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_NOMBRE);
            case VistaController.ARBOL_AVL:
            case VistaController.ARBOL_B:
            default:
                throw new AssertionError();
        }
    }

    @Override
    public ArbolBinario<LinkedList<Integer>, String> crearArbolProfesiones(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_PROFESION);
            case VistaController.ARBOL_AVL:
            case VistaController.ARBOL_B:
            default:
                throw new AssertionError();
        }
    }

    @Override
    public ArbolBinario<LinkedList<Integer>, Double> crearArbolPromedio(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_PROMEDIO);
            case VistaController.ARBOL_AVL:
            case VistaController.ARBOL_B:
            default:
                throw new AssertionError();
        }
    }

}
