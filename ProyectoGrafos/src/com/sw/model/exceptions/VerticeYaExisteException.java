package com.sw.model.exceptions;

/**
 *
 * @author Nicolás
 */
public class VerticeYaExisteException extends RuntimeException
{

    /**
     * Creates a new instance of <code>NodoYaExiste</code> without detail message.
     */
    public VerticeYaExisteException()
    {
        this("El nodo ya existe!");
    }

    /**
     * Constructs an instance of <code>NodoYaExiste</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public VerticeYaExisteException(String msg)
    {
        super(msg);
    }
}
