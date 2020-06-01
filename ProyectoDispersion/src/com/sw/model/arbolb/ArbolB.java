package com.sw.model.arbolb;

import com.sw.model.exceptions.ArbolVacioException;
import com.sw.model.exceptions.ItemNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 * @param <T>
 */
public class ArbolB<T extends Comparable<? super T>> implements Serializable
{

    private static final long serialVersionUID = 1L;

    // Default to 2-3 Tree
    private int minKeySize = 1;
    private int minChildrenSize = minKeySize + 1; // 2
    private int maxKeySize = 2 * minKeySize; // 2
    private int maxChildrenSize = maxKeySize + 1; // 3

    private Node<T> root = null;
    private int size = 0;

    /**
     * Constructor for B-Tree which defaults to a 2-3 B-Tree.
     */
    public ArbolB()
    {

    }

    /**
     * Constructor for B-Tree of ordered parameter. Order here means minimum number of keys in a non-root node.
     *
     * @param order of the B-Tree.
     */
    public ArbolB(int order)
    {
        this.minKeySize = order / 2 - 1;
        this.minChildrenSize = 1;
        this.maxKeySize = order - 1;
        this.maxChildrenSize = order;
    }

    /**
     * {@inheritDoc}
     */
    public boolean add(T value)
    {
        if (root == null)
        {
            root = new Node<>(null, maxKeySize, maxChildrenSize);
            Key<T> key = new Key<>(value);
            root.addKey(key);

        } else
        {
            Node<T> node = root;
            while (node != null)
            {
                //Search for a repited key
                boolean repitKey = false;
                for (int i = 0; i < node.numberOfKeys(); i++)
                    if (node.getKey(i).getElemento().compareTo(value) == 0)
                    {
                        repitKey = true;
                        break;
                    }

                if (repitKey)
                    break;

                if (node.numberOfChildren() == 0)
                {

                    Key<T> key = new Key<>(value);
                    node.addKey(key);
                    if (node.numberOfKeys() <= maxKeySize)
                        // A-OK
                        break;
                    // Need to split up
                    split(node);
                    break;
                }

                // Navigate
                // Lesser o
                Key<T> lesser = node.getKey(0);
                //Si es igual se agrega un indice

                if (value.compareTo(lesser.getElemento()) < 0)
                {
                    node = node.getChild(0);
                    continue;
                }

                // Greater
                int numberOfKeys = node.numberOfKeys();
                int last = numberOfKeys - 1;
                Key<T> greater = node.getKey(last);
                //Si es igual se agrega un indice

                if (value.compareTo(greater.getElemento()) > 0)
                {
                    node = node.getChild(numberOfKeys);
                    continue;
                }

                // Search internal nodes
                for (int i = 1; i < node.numberOfKeys(); i++)
                {
                    Key<T> prev = node.getKey(i - 1);
                    Key<T> next = node.getKey(i);

                    if (value.compareTo(prev.getElemento()) > 0 && value.compareTo(next.getElemento()) <= 0)
                    {
                        node = node.getChild(i);
                        break;
                    }
                }
            }
        }

        size++;
        return true;
    }

    /**
     * The node's key size is greater than maxKeySize, split down the middle.
     *
     * @param nodeToSplit to split.
     */
    private void split(Node<T> nodeToSplit)
    {
        Node<T> node = nodeToSplit;
        int numberOfKeys = node.numberOfKeys();
        int medianIndex = numberOfKeys / 2;
        Key<T> medianValue = node.getKey(medianIndex);

        Node<T> left = new Node<>(null, maxKeySize, maxChildrenSize);

        for (int i = 0; i < medianIndex; i++)
            left.addKey(node.getKey(i));

        if (node.numberOfChildren() > 0)
            for (int j = 0; j <= medianIndex; j++)
            {
                Node<T> c = node.getChild(j);
                left.addChild(c);
            }

        Node<T> right = new Node<>(null, maxKeySize, maxChildrenSize);

        for (int i = medianIndex + 1; i < numberOfKeys; i++)
            right.addKey(node.getKey(i));

        if (node.numberOfChildren() > 0)
            for (int j = medianIndex + 1; j < node.numberOfChildren(); j++)
            {
                Node<T> c = node.getChild(j);
                right.addChild(c);
            }

        if (node.getParent() == null)
        {
            // new root, height of tree is increased
            Node<T> newRoot = new Node<>(null, maxKeySize, maxChildrenSize);
            newRoot.addKey(medianValue);
            node.setParent(newRoot);
            root = newRoot;
            node = root;
            node.addChild(left);
            node.addChild(right);
        } else
        {
            // Move the median value up to the parent
            Node<T> parent = node.getParent();
            parent.addKey(medianValue);
            parent.removeChild(node);
            parent.addChild(left);
            parent.addChild(right);

            if (parent.numberOfKeys() > maxKeySize)
                split(parent);
        }
    }

    /**
     * {@inheritDoc}
     */
    public Key<T> remove(T value)
    {
        Key<T> removed = null;
        Node<T> node = this.getNode(value);

        removed = remove(value, node);

        return removed;
    }

    /**
     * Remove the value from the Node and check invariants
     *
     * @param value T to remove from the tree
     * @param node Node to remove value from
     * @return True if value was removed from the tree.
     */
    private Key<T> remove(T value, Node<T> node)
    {
        if (node == null)
            return null;

        Key<T> removed = null;
        int index = node.indexOf(value);
        removed = node.removeKey(value);

        if (node.numberOfChildren() == 0)
        {
            // leaf node
            if (node.getParent() != null && node.numberOfKeys() < minKeySize)
                this.combined(node);

            else if (node.getParent() == null && node.numberOfKeys() == 0)
                // Removing root node with no keys or children
                root = null;
        } else
        {
            // internal node
            Node<T> lesser = node.getChild(index);
            Node<T> greatest = this.getGreatestNode(lesser);
            Key<T> replaceValue = this.removeGreatestValue(greatest);
            node.addKey(replaceValue);

            if (greatest.getParent() != null && greatest.numberOfKeys() < minKeySize)
                this.combined(greatest);

            if (greatest.numberOfChildren() > maxChildrenSize)
                this.split(greatest);
        }

        size--;

        return removed;
    }

    /**
     * Remove greatest valued key from node.
     *
     * @param node to remove greatest value from.
     * @return value removed;
     */
    private Key<T> removeGreatestValue(Node<T> node)
    {
        Key<T> value = null;

        if (node.numberOfKeys() > 0)
            value = node.removeKey(node.numberOfKeys() - 1);

        return value;
    }

    /**
     * {@inheritDoc}
     */
    public void clear()
    {
        root = null;
        size = 0;
    }

    /**
     * {@inheritDoc}
     */
    public boolean contains(T value)
    {
        Node<T> node = getNode(value);
        return (node != null);
    }

    /**
     * Get the node with value.
     *
     * @param value to find in the tree.
     * @return Node<T> with value.
     */
    private Node<T> getNode(T value)
    {
        Node<T> node = root;
        while (node != null)
        {
            Key<T> lesser = node.getKey(0);
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
            Key<T> greater = node.getKey(last);

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
                Key<T> currentValue = node.getKey(i);

                if (currentValue.getElemento().compareTo(value) == 0)
                    return node;

                int next = i + 1;
                if (next <= last)
                {
                    Key<T> nextValue = node.getKey(next);

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

    /**
     * Get the greatest valued child from node.
     *
     * @param nodeToGet child with the greatest value.
     * @return Node<T> child with greatest value.
     */
    private Node<T> getGreatestNode(Node<T> nodeToGet)
    {
        Node<T> node = nodeToGet;

        while (node.numberOfChildren() > 0)
            node = node.getChild(node.numberOfChildren() - 1);

        return node;
    }

    /**
     * Combined children keys with parent when size is less than minKeySize.
     *
     * @param node with children to combined.
     * @return True if combined successfully.
     */
    private boolean combined(Node<T> node)
    {
        Node<T> parent = node.getParent();
        int index = parent.indexOf(node);
        int indexOfLeftNeighbor = index - 1;
        int indexOfRightNeighbor = index + 1;

        Node<T> rightNeighbor = null;
        int rightNeighborSize = -minChildrenSize;

        if (indexOfRightNeighbor < parent.numberOfChildren())
        {
            rightNeighbor = parent.getChild(indexOfRightNeighbor);
            rightNeighborSize = rightNeighbor.numberOfKeys();
        }

        // Try to borrow neighbor
        if (rightNeighbor != null && rightNeighborSize > minKeySize)
        {
            // Try to borrow from right neighbor
            Key<T> removeValue = rightNeighbor.getKey(0);
            int prev = getIndexOfPreviousValue(parent, removeValue.getElemento());
            Key<T> parentValue = parent.removeKey(prev);
            Key<T> neighborValue = rightNeighbor.removeKey(0);
            node.addKey(parentValue);
            parent.addKey(neighborValue);

            if (rightNeighbor.numberOfChildren() > 0)
                node.addChild(rightNeighbor.removeChild(0));

        } else
        {
            Node<T> leftNeighbor = null;
            int leftNeighborSize = -minChildrenSize;

            if (indexOfLeftNeighbor >= 0)
            {
                leftNeighbor = parent.getChild(indexOfLeftNeighbor);
                leftNeighborSize = leftNeighbor.numberOfKeys();
            }

            if (leftNeighbor != null && leftNeighborSize > minKeySize)
            {
                // Try to borrow from left neighbor
                Key<T> removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
                int prev = getIndexOfNextValue(parent, removeValue.getElemento());
                Key<T> parentValue = parent.removeKey(prev);
                Key<T> neighborValue = leftNeighbor.removeKey(leftNeighbor.numberOfKeys() - 1);
                node.addKey(parentValue);
                parent.addKey(neighborValue);

                if (leftNeighbor.numberOfChildren() > 0)
                    node.addChild(leftNeighbor.removeChild(leftNeighbor.numberOfChildren() - 1));

            } else if (rightNeighbor != null && parent.numberOfKeys() > 0)
            {
                // Can't borrow from neighbors, try to combined with right neighbor
                Key<T> removeValue = rightNeighbor.getKey(0);
                int prev = getIndexOfPreviousValue(parent, removeValue.getElemento());
                Key<T> parentValue = parent.removeKey(prev);
                parent.removeChild(rightNeighbor);
                node.addKey(parentValue);

                for (int i = 0; i < rightNeighbor.getKeysSize(); i++)
                {
                    Key<T> v = rightNeighbor.getKey(i);
                    node.addKey(v);
                }

                for (int i = 0; i < rightNeighbor.getChildrenSize(); i++)
                {
                    Node<T> c = rightNeighbor.getChild(i);
                    node.addChild(c);
                }

                if (parent.getParent() != null && parent.numberOfKeys() < minKeySize)
                    combined(parent);

                else if (parent.numberOfKeys() == 0)
                {
                    node.setParent(null);
                    root = node;
                }

            } else if (leftNeighbor != null && parent.numberOfKeys() > 0)
            {
                // Can't borrow from neighbors, try to combined with left neighbor
                Key<T> removeValue = leftNeighbor.getKey(leftNeighbor.numberOfKeys() - 1);
                int prev = getIndexOfNextValue(parent, removeValue.getElemento());
                Key<T> parentValue = parent.removeKey(prev);
                parent.removeChild(leftNeighbor);
                node.addKey(parentValue);

                for (int i = 0; i < leftNeighbor.getKeysSize(); i++)
                {
                    Key<T> v = leftNeighbor.getKey(i);
                    node.addKey(v);
                }

                for (int i = 0; i < leftNeighbor.getKeysSize(); i++)
                {
                    Node<T> c = leftNeighbor.getChild(i);
                    node.addChild(c);
                }

                if (parent.getParent() != null && parent.numberOfKeys() < minKeySize)
                    combined(parent);

                else if (parent.numberOfKeys() == 0)
                {
                    node.setParent(null);
                    root = node;
                }
            }
        }

        return true;
    }

    /**
     * Get the index of previous key in node.
     *
     * @param node to find the previous key in.
     * @param value to find a previous value for.
     * @return index of previous key or -1 if not found.
     */
    private int getIndexOfPreviousValue(Node<T> node, T value)
    {
        for (int i = 1; i < node.numberOfKeys(); i++)
        {
            Key<T> t = node.getKey(i);

            if (t.getElemento().compareTo(value) >= 0)
                return i - 1;
        }

        return node.numberOfKeys() - 1;
    }

    /**
     * Get the index of next key in node.
     *
     * @param node to find the next key in.
     * @param value to find a next value for.
     * @return index of next key or -1 if not found.
     */
    private int getIndexOfNextValue(Node<T> node, T value)
    {
        for (int i = 0; i < node.numberOfKeys(); i++)
        {
            Key<T> t = node.getKey(i);

            if (t.getElemento().compareTo(value) >= 0)
                return i;
        }

        return node.numberOfKeys() - 1;
    }

    /**
     * {@inheritDoc}
     */
    public int size()
    {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    public boolean validate()
    {
        if (root == null)
            return true;

        return validateNode(root);
    }

    /**
     * Validate the node according to the B-Tree invariants.
     *
     * @param node to validate.
     * @return True if valid.
     */
    private boolean validateNode(Node<T> node)
    {
        int keySize = node.numberOfKeys();

        if (keySize > 1)
            // Make sure the keys are sorted
            for (int i = 1; i < keySize; i++)
            {
                Key<T> p = node.getKey(i - 1);
                Key<T> n = node.getKey(i);

                if (p.getElemento().compareTo(n.getElemento()) > 0)
                    return false;
            }

        int childrenSize = node.numberOfChildren();

        if (node.getParent() == null)
        {
            if (keySize > maxKeySize)
                return false;

            else if (childrenSize == 0)
                return true;

            else if (childrenSize < 2)
                return false;

            else if (childrenSize > maxChildrenSize)
                return false;

        } else if (keySize < minKeySize)
            return false;

        else if (keySize > maxKeySize)
            return false;

        else if (childrenSize == 0)
            return true;

        else if (keySize != (childrenSize - 1))
            return false;

        else if (childrenSize < minChildrenSize)
            return false;

        else if (childrenSize > maxChildrenSize)
            return false;

        Node<T> first = node.getChild(0);

        // The first child's last key should be less than the node's first key
        if (first.getKey(first.numberOfKeys() - 1).getElemento().compareTo(node.getKey(0).getElemento()) > 0)
            return false;

        Node<T> last = node.getChild(node.numberOfChildren() - 1);
        // The last child's first key should be greater than the node's last key
        if (last.getKey(0).getElemento().compareTo(node.getKey(node.numberOfKeys() - 1).getElemento()) < 0)
            return false;

        // Check that each node's first and last key holds it's invariance
        for (int i = 1; i < node.numberOfKeys(); i++)
        {
            Key<T> p = node.getKey(i - 1);
            Key<T> n = node.getKey(i);
            Node<T> c = node.getChild(i);

            if (p.getElemento().compareTo(c.getKey(0).getElemento()) > 0)
                return false;

            if (n.getElemento().compareTo(c.getKey(c.numberOfKeys() - 1).getElemento()) < 0)
                return false;
        }

        for (int i = 0; i < node.getChildrenSize(); i++)
        {
            Node<T> c = node.getChild(i);
            boolean valid = this.validateNode(c);

            if (!valid)
                return false;
        }

        return true;
    }

    public T buscar(T elemento) throws ItemNotFoundException
    {
        Node<T> node = getNode(elemento);

        if (node == null)
            throw new ItemNotFoundException("Elemento no encontrado");

        for (int i = 0; i < node.getKeysSize(); i++)
            if (node.getKey(i).getElemento().compareTo(elemento) == 0)
                return node.getKey(i).getElemento();

        throw new ItemNotFoundException("Elemento no encontrado");
    }

    public void recorrerArbol()
    {
        recorrerArbol(root);
    }

    public void recorrerArbol(Node<T> node)
    {
        int x = 0;

        for (int i = 0; i < node.numberOfChildren(); i++)
        {
            if (node.getChild(i) != null)
                recorrerArbol(node.getChild(i));

            if (x <= node.numberOfKeys() && node.getKey(x) != null)
                System.out.println(node.getKey(x++));
        }

        if (isLeaf(node))
            for (int i = 0; i < node.numberOfKeys(); i++)
                System.out.println(node.getKey(i));

    }

    public void recorrerArbolElementos(Node<T> node, ArrayList<T> elementos)
    {
        int x = 0;

        for (int i = 0; i < node.numberOfChildren(); i++)
        {
            if (node.getChild(i) != null)
                recorrerArbolElementos(node.getChild(i), elementos);

            if (x <= node.numberOfKeys() && node.getKey(x) != null)
                elementos.add(node.getKey(x++).getElemento());
        }

        if (isLeaf(node))
            for (int i = 0; i < node.numberOfKeys(); i++)
                elementos.add(node.getKey(i).getElemento());

    }

    private boolean isLeaf(Node<T> n)
    {
        return n.numberOfChildren() == 0;
    }

    public ArrayList<T> enlistarElementos() throws ArbolVacioException
    {
        if (root == null)
            throw new ArbolVacioException("No se encontraron datos");

        ArrayList<T> elementos = new ArrayList<>();

        recorrerArbolElementos(root, elementos);

        if (size() == 0)
            throw new ArbolVacioException("No se encontraron datos");

        return elementos;
    }

}
