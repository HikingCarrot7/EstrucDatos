package aeropuerto;

import deque.DequeList;
import dequestack.DequeStack;
import excepciones.DequeEmptyException;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Aeropuerto
{

    private final DequeList<Vuelo> VUELOS;
    private PropertyChangeSupport propertyChangeSupport;

    public Aeropuerto()
    {
        VUELOS = new DequeList<>();
        propertyChangeSupport = new PropertyChangeSupport(this);
    }

    public void generarVuelos(int numeroVuelos)
    {
        int clave = 0;

        for (int i = 0; i < numeroVuelos; i++)
        {
            do
                clave = (int) (Math.random() * numeroVuelos * 100);
            while (existeClaveVuelo(clave));

            propertyChangeSupport.firePropertyChange("progress", 0, (VUELOS.size() + 1) * 100 / numeroVuelos);
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

    private DequeList<Vuelo> obtenerCopiaVuelos()
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

    public void addPropertyChangeListener(PropertyChangeListener p)
    {
        propertyChangeSupport.addPropertyChangeListener(p);
    }

    public void removePropertyChangeListener(PropertyChangeListener p)
    {
        propertyChangeSupport.removePropertyChangeListener(p);
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

    public DequeList<Vuelo> obtenerVuelos()
    {
        return obtenerCopiaVuelos();
    }

}
