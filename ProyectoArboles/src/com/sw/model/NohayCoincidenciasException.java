package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class NohayCoincidenciasException extends RuntimeException
{

    /**
     * Creates a new instance of <code>NohayCoincidenciasException</code> without detail message.
     */
    public NohayCoincidenciasException()
    {
        this("No hay coincidencias.");
    }

    /**
     * Constructs an instance of <code>NohayCoincidenciasException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NohayCoincidenciasException(String msg)
    {
        super(msg);
    }
}
