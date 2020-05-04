package com.sw.model;

import com.sw.util.DequeEmptyException;
import com.sw.util.DequeList;
import com.sw.util.DequeStack;

public class Aeropuerto
{

    private final DequeList<Vuelo> vuelos;

    public Aeropuerto()
    {
        vuelos = new DequeList<>();
    }

    public void generarVuelos(int numeroVuelos)
    {
        int clave = 0;

        for (int i = 0; i < numeroVuelos; i++)
        {
            do
                clave = (int) (Math.random() * numeroVuelos * 100);
            while (existeClaveVuelo(clave));

            vuelos.insertLast(new Vuelo(clave));
        }
    }

    public void eliminarVueloAt(int index) throws DequeEmptyException
    {
        DequeStack<Vuelo> pilaTemporal = new DequeStack<>();
        int numeroVuelos = vuelos.size();

        if (existenVuelosEnCola() && esVueloValido(index))
        {
            for (int i = 0; i < numeroVuelos; i++)
                if (i == index)
                    vuelos.removeFirst();
                else
                    pilaTemporal.push(vuelos.removeFirst());

            for (int i = 0; i < numeroVuelos - 1; i++)
                vuelos.insertFirst(pilaTemporal.pop());

        } else
            throw new DequeEmptyException("El vuelo no es válido o no hay vuelos disponibles.");

    }

    public Vuelo eliminarSiguienteVuelo() throws DequeEmptyException
    {
        return vuelos.removeFirst();
    }

    public boolean existeClaveVuelo(int clave)
    {
        DequeList<Vuelo> vuelosTemporales = obtenerCopiaVuelos();

        for (int i = 0; i < vuelos.size(); i++)
            if (vuelosTemporales.removeFirst().getClave() == clave)
                return true;

        return false;
    }

    private DequeList<Vuelo> obtenerCopiaVuelos()
    {
        int numeroVuelos = vuelos.size();
        DequeStack<Vuelo> pilaTemporal = new DequeStack<>();
        DequeList<Vuelo> copiaDeLosVuelos = new DequeList<>();

        for (int i = 0; i < numeroVuelos; i++)
            pilaTemporal.push(vuelos.removeFirst());

        for (int i = 0; i < numeroVuelos; i++)
        {
            Vuelo vuelo = pilaTemporal.pop();
            vuelos.insertFirst(new Vuelo(vuelo.getClave()));
            copiaDeLosVuelos.insertFirst(new Vuelo(vuelo.getClave()));
        }

        return copiaDeLosVuelos;
    }

    public boolean existenVuelosEnCola()
    {
        return vuelos.size() > 0;
    }

    public boolean esVueloValido(int index)
    {
        return index >= 0 && index < vuelos.size();
    }

    public int vuelosDisponibles()
    {
        return vuelos.size();
    }

    public DequeList<Vuelo> obtenerVuelos()
    {
        return obtenerCopiaVuelos();
    }

}
