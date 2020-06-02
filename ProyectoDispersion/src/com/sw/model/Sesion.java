package com.sw.model;

/**
 *
 * @author HikingCarrot7
 */
public class Sesion
{

    private static Sesion instance;

    public static synchronized Sesion getInstance()
    {
        if (instance == null)
            instance = new Sesion();

        return instance;
    }

    private Usuario usuarioActual;

    private Sesion()
    {

    }

    public Usuario getUsuarioActual()
    {
        return usuarioActual;
    }

    public String getCorreoUsuarioActual()
    {
        return usuarioActual.getCorreo();
    }

    public void setUsuarioActual(Usuario usuarioActual)
    {
        this.usuarioActual = usuarioActual;
    }

}
