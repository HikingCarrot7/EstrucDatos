package com.sw.model.util;

public interface Stack<E>
{

    public E push(E element);

    public E pop() throws PilaVaciaException;

    public E peek() throws PilaVaciaException;

    public boolean isEmpty();

    public int size();
}
