package com.sw.model;

import com.sw.controller.VistaController;
import com.sw.model.TreeFactory;
import com.sw.model.ArbolAVL;
import com.sw.model.ArbolBB;
import com.sw.model.ArbolBinario;
import com.sw.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public class BinaryTreeFactory implements TreeFactory
{

    @Override
    public ArbolBinario<LinkedList<Integer>, String> crearArbolNombres(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_NOMBRE);
            case VistaController.ARBOL_AVL:
                return new ArbolAVL<>(VistaController.COMPARADOR_POR_NOMBRE);
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
                return new ArbolAVL<>(VistaController.COMPARADOR_POR_PROFESION);
            case VistaController.ARBOL_B:
            default:
                throw new AssertionError();
        }
    }

    @Override
    public ArbolBinario<LinkedList<Integer>, Double> crearArbolPromedios(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_PROMEDIO);
            case VistaController.ARBOL_AVL:
                return new ArbolAVL<>(VistaController.COMPARADOR_POR_PROMEDIO);
            case VistaController.ARBOL_B:
            default:
                throw new AssertionError();
        }
    }

}
