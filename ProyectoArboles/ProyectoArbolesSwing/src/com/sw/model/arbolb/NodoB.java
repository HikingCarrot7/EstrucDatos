package com.sw.model.arbolb;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Nicolás
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class NodoB<T extends Comparable<T>>
{

    private Key<T>[] keys = null;
    private NodoB<T> parent = null;
    private NodoB<T>[] children = null;
    private int keysSize = 0;
    private int childrenSize = 0;
    private Comparator<NodoB<T>> comparator = (hijo1, hijo2) -> hijo1.getKey(0).getElemento().compareTo(hijo2.getKey(0).getElemento());

    public NodoB(NodoB<T> parent, int maxKeySize, int maxChildrenSize)
    {
        this.parent = parent;
        this.keys = new Key[maxKeySize + 1];
        this.children = new NodoB[maxChildrenSize + 1];
        this.keysSize = 0;
        this.childrenSize = 0;
    }

    public void setParent(NodoB<T> parent)
    {
        this.parent = parent;
    }

    public NodoB<T> getParent()
    {
        return parent;
    }

    public void setKeys(Key<T>[] keys)
    {
        this.keys = keys;
    }

    public Key<T>[] getKeys()
    {
        return keys;
    }

    public void setKeysSize(int keysSize)
    {
        this.keysSize = keysSize;
    }

    public int getKeysSize()
    {
        return keysSize;
    }

    public void setChildren(NodoB<T>[] children)
    {
        this.children = children;
    }

    public NodoB<T>[] getChildren()
    {
        return children;
    }

    public void setChildrenSize(int childrenSize)
    {
        this.childrenSize = childrenSize;
    }

    public int getChildrenSize()
    {
        return childrenSize;
    }

    public void setComparator(Comparator<NodoB<T>> comparator)
    {
        this.comparator = comparator;
    }

    public Comparator<NodoB<T>> getComparator()
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

    public NodoB<T> getChild(int index)
    {
        if (index >= childrenSize)
            return null;

        return children[index];
    }

    public int indexOf(NodoB<T> child)
    {
        for (int i = 0; i < childrenSize; i++)
            if (children[i].equals(child))
                return i;

        return -1;
    }

    public boolean addChild(NodoB<T> child)
    {
        child.setParent(this);
        children[childrenSize++] = child;
        Arrays.sort(children, 0, childrenSize, comparator);
        return true;
    }

    public boolean removeChild(NodoB<T> child)
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

    public NodoB<T> removeChild(int index)
    {
        if (index >= childrenSize)
            return null;

        NodoB<T> value = children[index];
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

    @Override
    public String toString()
    {
        StringBuilder builder = new StringBuilder();
        builder.append("keys=[");

        for (int i = 0; i < numberOfKeys(); i++)
        {
            Key<T> value = getKey(i);
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
}
