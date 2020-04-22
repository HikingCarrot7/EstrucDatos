package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class Egresado
{

    private String nombre;
    private String profesion;
    private double promedio;

    public Egresado(String nombre, String profesion, double promedio)
    {
        this.nombre = nombre;
        this.profesion = profesion;
        this.promedio = promedio;
    }

    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getProfesion()
    {
        return profesion;
    }

    public void setProfesion(String profesion)
    {
        this.profesion = profesion;
    }

    public Double getPromedio()
    {
        return promedio;
    }

    public void setPromedio(double promedio)
    {
        this.promedio = promedio;
    }

}
