package com.sw.model;

/**
 *
 * @author Nicolás
 */
public class ItemNotFoundException extends RuntimeException
{

    public ItemNotFoundException()
    {
        this("No se encontró el elemento.");
    }

    public ItemNotFoundException(String message)
    {
        super(message);
    }

}
