package com.sw.model.exceptions;

/**
 *
 * @author Nicolás
 */
public class ArchivoExtranoException extends RuntimeException
{

    /**
     * Creates a new instance of <code>ArchivoExtranoException</code> without detail message.
     */
    public ArchivoExtranoException()
    {
        this("Revisa el archivo, seguramente no cumple con lo especificado en el proyecto :(");
    }

    /**
     * Constructs an instance of <code>ArchivoExtranoException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ArchivoExtranoException(String msg)
    {
        super(msg);
    }
}
