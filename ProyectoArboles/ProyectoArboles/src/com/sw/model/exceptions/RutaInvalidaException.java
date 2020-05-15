package com.sw.model.exceptions;

/**
 *
 * @author Nicolás
 */
public class RutaInvalidaException extends RuntimeException
{

    /**
     * Creates a new instance of <code>NohayCoincidenciasException</code> without detail message.
     */
    public RutaInvalidaException()
    {
        this("La ruta no es válida.");
    }

    /**
     * Constructs an instance of <code>NohayCoincidenciasException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public RutaInvalidaException(String msg)
    {
        super(msg);
    }
}
