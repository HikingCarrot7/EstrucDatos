package aeropuerto;

import deque.DequeList;
import dequestack.DequeStack;
import excepciones.DequeEmptyException;

public class Aeropuerto
{

    private final DequeList<Vuelo> VUELOS;

    public Aeropuerto()
    {
        VUELOS = new DequeList<>();
    }

    public void generarVuelos(int numeroVuelos)
    {
        int clave = 0;

        for (int i = 0; i < numeroVuelos; i++)
        {
            do
                clave = (int) (Math.random() * numeroVuelos * 100);
            while (existeClaveVuelo(clave));

            VUELOS.insertLast(new Vuelo(clave));
        }

    }

    public void eliminarVueloAt(int index) throws DequeEmptyException
    {
        DequeStack<Vuelo> pilaTemporal = new DequeStack<>();
        int numeroVuelos = VUELOS.size();

        if (existenVuelosEnCola() && esVueloValido(index))
        {
            for (int i = 0; i < numeroVuelos; i++)
                if (i == index)
                    VUELOS.removeFirst();
                else
                    pilaTemporal.push(VUELOS.removeFirst());

            for (int i = 0; i < numeroVuelos - 1; i++)
                VUELOS.insertFirst(pilaTemporal.pop());

        } else
            throw new DequeEmptyException("El vuelo no es válido o no hay vuelos disponibles.");

    }

    public Vuelo eliminarSiguienteVuelo() throws DequeEmptyException
    {
        Vuelo siguienteVuelo = null;

        if (existenVuelosEnCola())
            siguienteVuelo = VUELOS.removeFirst();
        else
            throw new DequeEmptyException("No hay más vuelos.");

        return siguienteVuelo;
    }

    public boolean existeClaveVuelo(int clave)
    {
        DequeList<Vuelo> vuelosTemporales = obtenerCopiaVuelos();

        for (int i = 0; i < VUELOS.size(); i++)
            if (vuelosTemporales.removeFirst().getClave() == clave)
                return true;

        return false;
    }

    public DequeList<Vuelo> obtenerCopiaVuelos()
    {
        int numeroVuelos = VUELOS.size();
        DequeStack<Vuelo> pilaTemporal = new DequeStack<>();
        DequeList<Vuelo> copiaDeLosVuelos = new DequeList<>();

        for (int i = 0; i < numeroVuelos; i++)
            pilaTemporal.push(VUELOS.removeFirst());

        for (int i = 0; i < numeroVuelos; i++)
        {
            Vuelo vuelo = pilaTemporal.pop();
            VUELOS.insertFirst(new Vuelo(vuelo.getClave()));
            copiaDeLosVuelos.insertFirst(new Vuelo(vuelo.getClave()));
        }

        return copiaDeLosVuelos;
    }

    public boolean existenVuelosEnCola()
    {
        return VUELOS.size() > 0;
    }

    public boolean esVueloValido(int index)
    {
        return index >= 0 && index < VUELOS.size();
    }

    public int vuelosDisponibles()
    {
        return VUELOS.size();
    }

}
