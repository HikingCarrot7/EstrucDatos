package com.sw.model;

import com.sw.model.arbolb.BTreeComparable;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author HikingCarrot7
 */
public class Usuario implements Serializable, BTreeComparable
{

    private static final long serialVersionUID = 1L;

    public static final boolean HOMBRE = true;
    public static final boolean MUJER = false;

    private String nombreCompleto;
    private String correo;
    private String password;
    private int edad;
    private boolean genero;

    public Usuario()
    {

    }

    public Usuario(String nombreCompleto, String correo, String contrasena, int edad, boolean genero)
    {
        this.nombreCompleto = nombreCompleto;
        this.correo = correo;
        this.password = contrasena;
        this.edad = edad;
        this.genero = genero;
    }

    public String getNombreCompleto()
    {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto)
    {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getEdad()
    {
        return edad;
    }

    public void setEdad(int edad)
    {
        this.edad = edad;
    }

    public boolean getGenero()
    {
        return genero;
    }

    public void setGenero(boolean genero)
    {
        this.genero = genero;
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.nombreCompleto);
        hash = 29 * hash + Objects.hashCode(this.correo);
        hash = 29 * hash + Objects.hashCode(this.password);
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

        return this.correo.equals(other.correo);
    }

    @Override
    public String toString()
    {
        return "Usuario{" + "nombreCompleto=" + nombreCompleto + ", correo=" + correo + ", contrasena=" + password + ", edad=" + edad + '}';
    }

    @Override
    public int compareTo(BTreeComparable other)
    {
        return getCorreo().compareTo(((Usuario) other).getCorreo());
    }

}
