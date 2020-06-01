package com.sw.model.exceptions;

/**
 *
 * @author HikingCarrot7
 */
public class ItemNotFoundException extends RuntimeException
{

    /**
     * Creates a new instance of <code>ItemNotFoundException</code> without detail message.
     */
    public ItemNotFoundException()
    {
    }

    /**
     * Constructs an instance of <code>ItemNotFoundException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public ItemNotFoundException(String msg)
    {
        super(msg);
    }
}
