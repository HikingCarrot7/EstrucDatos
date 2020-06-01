package com.sw.model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author HikingCarrot7
 */
public class Usuario implements Serializable, Comparable<Usuario>
{

    private static final long serialVersionUID = 1L;

    private String nombreCompleto;
    private String correoElectronico;
    private String contrasena;
    private int edad;

    public Usuario(String nombreCompleto, String correoElectronico, String contrasena, int edad)
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

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombreCompleto);
        hash = 29 * hash + Objects.hashCode(this.correoElectronico);
        hash = 29 * hash + Objects.hashCode(this.contrasena);
        hash = 29 * hash + this.edad;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;

        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;

        final Usuario other = (Usuario) obj;

        if (this.edad != other.edad)
            return false;

        if (!Objects.equals(this.nombreCompleto, other.nombreCompleto))
            return false;

        if (!Objects.equals(this.correoElectronico, other.correoElectronico))
            return false;

        return Objects.equals(this.contrasena, other.contrasena);
    }

    @Override
    public int compareTo(Usuario o)
    {
        return getNombreCompleto().compareTo(o.getNombreCompleto());
    }

    @Override
    public String toString()
    {
        return "Usuario{" + "nombreCompleto=" + nombreCompleto + ", correoElectronico=" + correoElectronico + ", contrasena=" + contrasena + ", edad=" + edad + '}';
    }

}
