package com.sw.model.exceptions;

import java.io.IOException;

/**
 *
 * @author HikingCarrot7
 */
public class InicializacionIncorrectaException extends IOException
{

    /**
     * Creates a new instance of <code>InicializacionIncorrectaException</code> without detail message.
     */
    public InicializacionIncorrectaException()
    {
        super("Error al iniciar.");
    }

    /**
     * Constructs an instance of <code>InicializacionIncorrectaException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public InicializacionIncorrectaException(String msg)
    {
        super(msg);
    }
}
