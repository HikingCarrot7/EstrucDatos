package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class NoTengoContactosException extends RuntimeException
{

    /**
     * Creates a new instance of <code>NoTengoContactosException</code> without detail message.
     */
    public NoTengoContactosException()
    {
        super("No tienes contactos añadidos");
    }

    /**
     * Constructs an instance of <code>NoTengoContactosException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public NoTengoContactosException(String msg)
    {
        super(msg);
    }
}
