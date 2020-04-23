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

    private final Egresado[] egresados;
    private final ArbolBinario<LinkedList<Integer>, String> arbolNombres;
    private final ArbolBinario<LinkedList<Integer>, String> arbolProfesiones;
    private final ArbolBinario<LinkedList<Integer>, Double> arbolPromedios;

    public Service(Egresado[] egresados,
            ArbolBinario<LinkedList<Integer>, String> arbolNombres,
            ArbolBinario<LinkedList<Integer>, String> arbolProfesiones,
            ArbolBinario<LinkedList<Integer>, Double> arbolPromedios)
    {
        this.egresados = egresados;
        this.arbolNombres = arbolNombres;
        this.arbolProfesiones = arbolProfesiones;
        this.arbolPromedios = arbolPromedios;
    }

    @Override
    protected Long doInBackground() throws Exception
    {
        long now = System.currentTimeMillis();

        for (int i = 0; i < egresados.length; i++)
        {
            arbolNombres.insertar(i, egresados[i].getNombre());
            arbolProfesiones.insertar(i, egresados[i].getProfesion());
            arbolPromedios.insertar(i, egresados[i].getPromedio());
        }

        return System.currentTimeMillis() - now;
    }

}
