package com.sw.model.util;

/**
 *
 * @author A15001169
 */
public class PilaVaciaException extends RuntimeException
{

    public PilaVaciaException()
    {
        super("La pila está vacía.");
    }

}
