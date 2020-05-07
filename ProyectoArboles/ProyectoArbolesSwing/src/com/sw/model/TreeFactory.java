package com.sw.model;

import com.sw.controller.VistaController;
import com.sw.model.arbolavl.ArbolAVL;
import com.sw.model.arbolb.ArbolB;
import com.sw.model.arbolbb.ArbolBB;
import com.sw.model.util.LinkedList;

/**
 *
 * @author Nicolás
 */
public class TreeFactory implements Factory
{

    @Override
    public Arbol<LinkedList<Integer>, String> crearArbolNombres(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_NOMBRE);
            case VistaController.ARBOL_AVL:
                return new ArbolAVL<>(VistaController.COMPARADOR_POR_NOMBRE);
            case VistaController.ARBOL_B:
                return new ArbolB<>();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public Arbol<LinkedList<Integer>, String> crearArbolProfesiones(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_PROFESION);
            case VistaController.ARBOL_AVL:
                return new ArbolAVL<>(VistaController.COMPARADOR_POR_PROFESION);
            case VistaController.ARBOL_B:
                return new ArbolB<>();
            default:
                throw new AssertionError();
        }
    }

    @Override
    public Arbol<LinkedList<Integer>, Double> crearArbolPromedios(String tipoArbol)
    {
        switch (tipoArbol)
        {
            case VistaController.ARBOL_BB:
                return new ArbolBB<>(VistaController.COMPARADOR_POR_PROMEDIO);
            case VistaController.ARBOL_AVL:
                return new ArbolAVL<>(VistaController.COMPARADOR_POR_PROMEDIO);
            case VistaController.ARBOL_B:
                return new ArbolB<>();
            default:
                throw new AssertionError();
        }
    }

}
