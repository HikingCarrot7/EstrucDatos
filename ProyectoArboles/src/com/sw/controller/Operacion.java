package com.sw.controller;

import com.sw.model.Egresado;
import com.sw.utils.LinkedList;

/**
 *
 * @author Nicolás
 */
@FunctionalInterface
public interface Operacion
{

    public int operar(LinkedList<Integer> lista, Egresado egresado);
}
