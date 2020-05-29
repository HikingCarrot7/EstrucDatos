package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class Contacto
{

    private String nombreCompleto;
    private String correoElectronico;
    private String contrasena;
    private int edad;

    public Contacto(String nombreCompleto, String correoElectronico, String contrasena, int edad)
    {
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.edad = edad;
    }

    public String getNombreCompleto()
    {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto)
    {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico()
    {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico)
    {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena()
    {
        return contrasena;
    }

    public void setContrasena(String contrasena)
    {
        this.contrasena = contrasena;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

}
