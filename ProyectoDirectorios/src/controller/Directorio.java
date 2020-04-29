package controller;

import test.FunnyStuff;

/**
 *
 * @author HikingCarrot7
 */
@FunnyStuff(descripcion = "Funny stuff :)")
public class Directorio implements Comparable<Directorio>
{

    private String nombre;
    private String ruta;
    private String fecha;

    public Directorio(String nombre, String ruta, String fecha)
    {
        this.nombre = nombre;
        this.ruta = ruta;
        this.fecha = fecha;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getRuta()
    {
        return ruta;
    }

    public void setRuta(String ruta)
    {
        this.ruta = ruta;
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    @Override
    public int compareTo(Directorio o)
    {
        return getNombre().toLowerCase().compareTo(o.getNombre().toLowerCase());
    }

}
