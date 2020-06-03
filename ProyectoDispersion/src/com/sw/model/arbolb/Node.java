package com.sw.model.arbolb;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author HikingCarrot7
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class Node<T extends Comparable<? super T>> implements Serializable
{

    private static final long serialVersionUID = 1L;

    private Key<T>[] keys = null;
    private int keysSize = 0;
    private Node<T>[] children = null;
    private int childrenSize = 0;
    private Comparator<Node<T>> comparator = new Comparador();

    private Node<T> parent = null;

    public Node(Node<T> parent, int maxKeySize, int maxChildrenSize)
    {
        this.parent = parent;
        this.keys = new Key[maxKeySize + 1];
        this.keysSize = 0;
        this.children = new Node[maxChildrenSize + 1];
        this.childrenSize = 0;
    }

    public Node<T> getParent()
    {
        return parent;
    }

    public void setKeys(Key<T>[] keys)
    {
        this.keys = keys;
    }

    public void setKeysSize(int keysSize)
    {
        this.keysSize = keysSize;
    }

    public void setChildren(Node<T>[] children)
    {
        this.children = children;
    }

    public void setChildrenSize(int childrenSize)
    {
        this.childrenSize = childrenSize;
    }

    public void setComparator(Comparator<Node<T>> comparator)
    {
        this.comparator = comparator;
    }

    public void setParent(Node<T> parent)
    {
        this.parent = parent;
    }

    public Key<T>[] getKeys()
    {
        return keys;
    }

    public int getKeysSize()
    {
        return keysSize;
    }

    public Node<T>[] getChildren()
    {
        return children;
    }

    public int getChildrenSize()
    {
        return childrenSize;
    }

    public Comparator<Node<T>> getComparator()
    {
        return comparator;
    }

    public Key<T> getKey(int index)
    {
        return keys[index];
    }

    public int indexOf(T value)
    {
        for (int i = 0; i < keysSize; i++)
            if (keys[i].equals(value))
                return i;

        return -1;
    }

    public void addKey(Key<T> value)
    {
        keys[keysSize++] = value;
        Arrays.sort(keys, 0, keysSize);
    }

    public Key<T> removeKey(T value)
    {
        Key<T> removed = null;
        boolean found = false;

        if (keysSize == 0)
            return null;

        for (int i = 0; i < keysSize; i++)
            if (keys[i].equals(value))
            {
                found = true;
                removed = keys[i];

            } else if (found)
                keys[i - 1] = keys[i];

        if (found)
        {
            keysSize--;
            keys[keysSize] = null;
        }

        return removed;
    }

    public Key<T> removeKey(int index)
    {
        if (index >= keysSize)
            return null;

        Key<T> value = keys[index];
        for (int i = index + 1; i < keysSize; i++)
            keys[i - 1] = keys[i];

        keysSize--;
        keys[keysSize] = null;
        return value;
    }

    public int numberOfKeys()
    {
        return keysSize;
    }

    public Node<T> getChild(int index)
    {
        if (index >= childrenSize)
            return null;

        return children[index];
    }

    public int indexOf(Node<T> child)
    {
        for (int i = 0; i < childrenSize; i++)
            if (children[i].equals(child))
                return i;

        return -1;
    }

    public boolean addChild(Node<T> child)
    {
        child.parent = this;
        children[childrenSize++] = child;
        Arrays.sort(children, 0, childrenSize, comparator);
        return true;
    }

    public boolean removeChild(Node<T> child)
    {
        boolean found = false;

        if (childrenSize == 0)
            return found;

        for (int i = 0; i < childrenSize; i++)
            if (children[i].equals(child))
                found = true;

            else if (found)
                children[i - 1] = children[i];

        if (found)
        {
            childrenSize--;
            children[childrenSize] = null;
        }

        return found;
    }

    public Node<T> removeChild(int index)
    {
        if (index >= childrenSize)
            return null;

        Node<T> value = children[index];
        children[index] = null;

        for (int i = index + 1; i < childrenSize; i++)
            children[i - 1] = children[i];

        childrenSize--;
        children[childrenSize] = null;
        return value;
    }

    public int numberOfChildren()
    {
        return childrenSize;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();

        builder.append("keys=[");

        for (int i = 0; i < numberOfKeys(); i++)
        {
            Key value = getKey(i);
            builder.append(value);

            if (i < numberOfKeys() - 1)
                builder.append(", ");
        }

        builder.append("]\n");

        if (parent != null)
        {
            builder.append("parent=[");

            for (int i = 0; i < parent.numberOfKeys(); i++)
            {
                Key<T> value = parent.getKey(i);
                builder.append(value);

                if (i < parent.numberOfKeys() - 1)
                    builder.append(", ");
            }

            builder.append("]\n");
        }

        if (children != null)
            builder.append("keySize=").append(numberOfKeys()).append(" children=").append(numberOfChildren()).append("\n");

        return builder.toString();
    }

    private class Comparador implements Comparator<Node<T>>, Serializable
    {

        private static final long serialVersionUID = 1L;

        @Override public int compare(Node<T> nodo1, Node<T> nodo2)
        {
            return nodo1.getKey(0).getElemento().compareTo(nodo2.getKey(0).getElemento());
        }
    }

}
