package com.sw.controller;

import com.sw.model.ArbolBinario;
import com.sw.model.Egresado;
import com.sw.util.LinkedList;
import javax.swing.SwingWorker;

/**
 *
 * @author Nicolás
 */
public class Service extends SwingWorker<Long, Long>
{

    private final ArbolBinario<LinkedList<Integer>, Egresado> arbolNombres;
    private final ArbolBinario<LinkedList<Integer>, Egresado> arbolProfesiones;
    private final ArbolBinario<LinkedList<Integer>, Egresado> arbolPromedios;
    private final Egresado[] egresados;

    public Service(Egresado[] egresados,
            ArbolBinario<LinkedList<Integer>, Egresado> arbolNombres,
            ArbolBinario<LinkedList<Integer>, Egresado> arbolProfesiones,
            ArbolBinario<LinkedList<Integer>, Egresado> arbolPromedios)
    {
        this.arbolNombres = arbolNombres;
        this.arbolProfesiones = arbolProfesiones;
        this.arbolPromedios = arbolPromedios;
        this.egresados = egresados;
    }

    @Override
    protected Long doInBackground() throws Exception
    {
        long now = System.currentTimeMillis();

        for (int i = 0; i < egresados.length; i++)
        {
            arbolNombres.insertar(i, egresados[i]);
            arbolProfesiones.insertar(i, egresados[i]);
            arbolPromedios.insertar(i, egresados[i]);
        }

        return System.currentTimeMillis() - now;
    }

}
