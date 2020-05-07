package com.sw.model.exceptions;

/**
 *
 * @author Nicolás
 */
public class VerticeNoExistenteException extends RuntimeException
{

    /**
     * Creates a new instance of <code>NodoNoExistente</code> without detail message.
     */
    public VerticeNoExistenteException()
    {
        this("El vértice no existe!");
    }

    /**
     * Constructs an instance of <code>NodoNoExistente</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public VerticeNoExistenteException(String msg)
    {
        super(msg);
    }
}
