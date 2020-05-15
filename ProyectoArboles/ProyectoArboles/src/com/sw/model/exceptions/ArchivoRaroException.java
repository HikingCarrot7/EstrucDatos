package com.sw.model.exceptions;

/**
 *
 * @author Nicolás
 */
public class ArchivoRaroException extends RuntimeException
{

    /**
     * Creates a new instance of <code>ArchivoRaroException</code> without detail message.
     */
    public ArchivoRaroException()
    {
        this("Revisa el archivo, seguramente no cumple con lo especificado en el proyecto :(");
    }

    /**
     * Constructs an instance of <code>ArchivoRaroException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ArchivoRaroException(String msg)
    {
        super(msg);
    }
}
