package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class ArbolVacioException extends RuntimeException
{

    /**
     * Creates a new instance of <code>NoDatosException</code> without detail message.
     */
    public ArbolVacioException()
    {
    }

    /**
     * Constructs an instance of <code>NoDatosException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ArbolVacioException(String msg)
    {
        super(msg);
    }
}
