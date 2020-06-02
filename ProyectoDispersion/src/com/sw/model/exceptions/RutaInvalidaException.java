package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class RutaInvalidaException extends RuntimeException
{

    /**
     * Creates a new instance of <code>RutaInvalidaException</code> without detail message.
     */
    public RutaInvalidaException()
    {
        super("La ruta que insertó no es válida.");
    }

    /**
     * Constructs an instance of <code>RutaInvalidaException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public RutaInvalidaException(String msg)
    {
        super(msg);
    }
}
