package com.sw.model.exceptions;

/**
 *
 * @author Nicolás
 */
public class ArcoNoExistenteException extends RuntimeException
{

    /**
     * Creates a new instance of <code>ArcoNoExistente</code> without detail message.
     */
    public ArcoNoExistenteException()
    {
        this("El arco no existe!");
    }

    /**
     * Constructs an instance of <code>ArcoNoExistente</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ArcoNoExistenteException(String msg)
    {
        super(msg);
    }
}
