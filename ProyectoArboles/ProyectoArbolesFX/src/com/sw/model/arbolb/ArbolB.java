package com.sw.model.arbolb;

import com.sw.model.Arbol;
import com.sw.model.exceptions.ItemNotFoundException;
import com.sw.util.LinkedList;

/**
 *
 * @author Internet
 * @param <L>
 * @param <I>
 */
@SuppressWarnings("unchecked")
public class ArbolB<L extends LinkedList<Integer>, I extends Comparable<I>> implements Arbol<L, I>
{

    private NodoB<I> root = null;

    private int minKeySize = 1;
    private int minChildrenSize = minKeySize + 1;
    private int maxKeySize = 2 * minKeySize;
    private int maxChildrenSize = maxKeySize + 1;
    private int size = 0;

    public ArbolB()
    {

    }

    public ArbolB(int order)
    {
        this.minKeySize = order / 2 - 1;
        this.minChildrenSize = 1;
        this.maxKeySize = order - 1;
        this.maxChildrenSize = order;
    }

    @Override
    public void insertar(int idx, I item)
    {
        if (root == null)
        {
            root = new NodoB<>(null, maxKeySize, maxChildrenSize);
            Key<I> key = new Key<>(item);
            key.getIndiceEgresados().addLast(idx);
            root.addKey(key);

        } else
        {
            NodoB<I> node = root;

            Loop:
            while (node != null)
            {
                for (int i = 0; i < node.numberOfKeys(); i++)
                    if (node.getKey(i).getElemento().compareTo(item) == 0)
                    {
                        node.getKey(i).getIndiceEgresados().addLast(idx);
                        break Loop;
                    }

                if (node.numberOfChildren() == 0)
                {
                    Key<I> key = new Key<>(item);
                    key.getIndiceEgresados().addLast(idx);
                    node.addKey(key);

                    if (node.numberOfKeys() <= maxKeySize)
                        break;
                    // Need to split up
                    split(node);
                    break;
                }

                // Navigate
                // Lesser o
                Key<I> lesser = node.getKey(0);
                //Si es igual se agrega un indice

                if (item.compareTo(lesser.getElemento()) < 0)
                {
                    node = node.getChild(0);
                    continue;
                }

                // Greater
                int numberOfKeys = node.numberOfKeys();
                int last = numberOfKeys - 1;
                Key<I> greater = node.getKey(last);
                //Si es igual se agrega un indice

                if (item.compareTo(greater.getElemento()) > 0)
                {
                    node = node.getChild(numberOfKeys);
                    continue;
                }

                // Search internal nodes
                for (int i = 1; i < node.numberOfKeys(); i++)
                {
                    Key<I> prev = node.getKey(i - 1);
                    Key<I> next = node.getKey(i);

                    if (item.compareTo(prev.getElemento()) > 0 && item.compareTo(next.getElemento()) <= 0)
                    {
                        node = node.getChild(i);
                        break;
                    }
                }
            }
        }

        size++;
    }

    private void split(NodoB<I> nodeToSplit)
    {
        NodoB<I> node = nodeToSplit;
        int numberOfKeys = node.numberOfKeys();
        int medianIndex = numberOfKeys / 2;
        Key<I> medianValue = node.getKey(medianIndex);

        NodoB<I> left = new NodoB<>(null, maxKeySize, maxChildrenSize);

        for (int i = 0; i < medianIndex; i++)
            left.addKey(node.getKey(i));

        if (node.numberOfChildren() > 0)
            for (int j = 0; j <= medianIndex; j++)
            {
                NodoB<I> c = node.getChild(j);
                left.addChild(c);
            }

        NodoB<I> right = new NodoB<>(null, maxKeySize, maxChildrenSize);

        for (int i = medianIndex + 1; i < numberOfKeys; i++)
            right.addKey(node.getKey(i));

        if (node.numberOfChildren() > 0)
            for (int j = medianIndex + 1; j < node.numberOfChildren(); j++)
            {
                NodoB<I> c = node.getChild(j);
                right.addChild(c);
            }

        if (node.getParent() == null)
        {
            // new root, height of tree is increased
            NodoB<I> newRoot = new NodoB<>(null, maxKeySize, maxChildrenSize);
            newRoot.addKey(medianValue);
            node.setParent(newRoot);
            root = newRoot;
            node = root;
            node.addChild(left);
            node.addChild(right);

        } else
        {
            // Move the median value up to the parent
            NodoB<I> parent = node.getParent();
            parent.addKey(medianValue);
            parent.removeChild(node);
            parent.addChild(left);
            parent.addChild(right);

            if (parent.numberOfKeys() > maxKeySize)
                split(parent);
        }
    }

    private NodoB<I> getNode(I value)
    {
        NodoB<I> node = root;

        while (node != null)
        {
            Key<I> lesser = node.getKey(0);

            if (value.compareTo(lesser.getElemento()) < 0)
            {
                if (node.numberOfChildren() > 0)
                    node = node.getChild(0);
                else
                    node = null;

                continue;
            }

            int numberOfKeys = node.numberOfKeys();
            int last = numberOfKeys - 1;
            Key<I> greater = node.getKey(last);

            if (value.compareTo(greater.getElemento()) > 0)
            {
                if (node.numberOfChildren() > numberOfKeys)
                    node = node.getChild(numberOfKeys);
                else
                    node = null;

                continue;
            }

            for (int i = 0; i < numberOfKeys; i++)
            {
                Key<I> currentValue = node.getKey(i);

                if (currentValue.getElemento().compareTo(value) == 0)
                    return node;

                int next = i + 1;

                if (next <= last)
                {
                    Key<I> nextValue = node.getKey(next);

                    if (currentValue.getElemento().compareTo(value) < 0 && nextValue.getElemento().compareTo(value) > 0)
                    {
                        if (next < node.numberOfChildren())
                        {
                            node = node.getChild(next);
                            break;
                        }

                        return null;
                    }
                }
            }
        }

        return null;
    }

    @Override
    public L buscar(I item) throws ItemNotFoundException
    {
        NodoB<I> node = getNode(item);

        if (node == null)
            throw new ItemNotFoundException();

        for (int i = 0; i < node.getKeysSize(); i++)
            if (node.getKey(i).getElemento().compareTo(item) == 0)
                return (L) node.getKey(i).getIndiceEgresados();

        throw new ItemNotFoundException();
    }

    private void recorrerArbol(NodoB<I> node, LinkedList<LinkedList<Integer>> indices)
    {
        int x = 0;

        for (int i = 0; i < node.numberOfChildren(); i++)
        {
            if (node.getChild(i) != null)
                recorrerArbol(node.getChild(i), indices);

            if (x <= node.numberOfKeys() && node.getKey(x) != null)
            {
                Key<I> actKey = node.getKey(x++);
                indices.addLast(actKey.getIndiceEgresados());
            }
        }

        if (isLeaf(node))
            for (int i = 0; i < node.numberOfKeys(); i++)
            {
                Key<I> actKey = node.getKey(i);
                indices.addLast(actKey.getIndiceEgresados());
            }

    }

    @Override
    public LinkedList<L> preorder()
    {
        return inorder();
    }

    @Override
    public LinkedList<L> inorder()
    {
        LinkedList<LinkedList<Integer>> lista = new LinkedList<>();
        recorrerArbol(root, lista);
        return (LinkedList<L>) lista;
    }

    @Override
    public LinkedList<L> postorder()
    {
        return inorder();
    }

    private boolean isLeaf(NodoB<I> n)
    {
        return n.numberOfChildren() == 0;
    }

    public int getSize()
    {
        return size;
    }

}
