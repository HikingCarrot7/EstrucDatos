package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class UsuarioNoExistenteException extends RuntimeException
{

    /**
     * Creates a new instance of <code>UsuarioNoExistenteException</code> without detail message.
     */
    public UsuarioNoExistenteException()
    {
        super("El usuario no existe!");
    }

    /**
     * Constructs an instance of <code>UsuarioNoExistenteException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public UsuarioNoExistenteException(String msg)
    {
        super(msg);
    }
}
