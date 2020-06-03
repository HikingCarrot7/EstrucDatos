package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class DatosInvalidosException extends RuntimeException
{

    /**
     * Creates a new instance of <code>DatosInvalidosException</code> without detail message.
     */
    public DatosInvalidosException()
    {
        super("Algún dato es incorrecto");
    }

    /**
     * Constructs an instance of <code>DatosInvalidosException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public DatosInvalidosException(String msg)
    {
        super(msg);
    }
}
