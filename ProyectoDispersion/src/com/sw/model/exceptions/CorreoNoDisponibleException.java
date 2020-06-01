package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class CorreoNoDisponibleException extends RuntimeException
{

    /**
     * Creates a new instance of <code>CorreoNoDisponibleException</code> without detail message.
     */
    public CorreoNoDisponibleException()
    {
        super("El correo no está disponible!");
    }

    /**
     * Constructs an instance of <code>CorreoNoDisponibleException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public CorreoNoDisponibleException(String msg)
    {
        super(msg);
    }
}
