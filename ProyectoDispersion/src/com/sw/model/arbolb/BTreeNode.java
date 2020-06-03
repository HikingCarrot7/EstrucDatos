package com.sw.model.arbolb;

import com.sw.model.Usuario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BTreeNode implements Serializable
{

    private int order;
    BTreeComparable[] key;
    BTreeNode[] child;
    BTreeNode parent;
    int filled;
    boolean isLeaf;
    public BTreeNode hidden;
    public BTreeNode hiddenPosition;

    public BTreeNode(int paramInt, boolean paramBoolean)
    {
        this.order = paramInt;
        int i = paramInt << 1;
        this.key = new BTreeComparable[i];
        this.child = new BTreeNode[i | 0x1];
        this.parent = null;
        this.filled = 0;
        this.isLeaf = paramBoolean;
    }

    public BTreeNode(int paramInt, BTreeNode paramBTreeNode, BTreeComparable[] paramArrayOfBTreeComparable, BTreeNode[] paramArrayOfBTreeNode, boolean paramBoolean)
    {
        this(paramInt, paramBoolean);

        if ((paramArrayOfBTreeComparable.length > paramInt << 1) || (paramArrayOfBTreeComparable.length != paramArrayOfBTreeNode.length - 1))
            throw new IllegalArgumentException("order: " + paramInt + "objects.length: " + paramArrayOfBTreeComparable.length + "theirSons.length: " + paramArrayOfBTreeNode.length);
        this.parent = paramBTreeNode;
        this.filled = paramArrayOfBTreeComparable.length;

        System.arraycopy(paramArrayOfBTreeComparable, 0, this.key, 0, this.filled);
        System.arraycopy(paramArrayOfBTreeNode, 0, this.child, 0, this.filled + 1);
    }

    public int findKeyPosition(BTreeComparable paramBTreeComparable)
    {
        int j;

        if (isEmpty())
            throw new IllegalStateException();

        int i;

        for (j = i = 0; i < this.filled; i++)
        {
            int k = paramBTreeComparable.compareTo(this.key[i]);
            if (k < 0)
                return j;

            j++;

            if (k == 0)
                return j;

            j++;
        }

        return j;
    }

    public int findChildPosition(BTreeNode paramBTreeNode)
    {
        if (isEmpty())
            throw new IllegalStateException("nodo vacio");

        for (int i = 0; i <= this.filled; i++)
            if (this.child[i] == paramBTreeNode)
                return i;

        throw new IllegalStateException("no se encuentra en nodo");
    }

    public List<Usuario> getItems()
    {
        List<Usuario> items = new ArrayList<>();

        for (int i = 0; i <= this.order << 1; i++)
        {
            if (this.child[i] != null)
                items.addAll(child[i].getItems());

            if (i < this.filled)
                items.add((Usuario) key[i]);
        }

        return items;
    }

    @Override
    public String toString()
    {
        String str = "<[";

        for (int i = 0; i <= this.order << 1; i++)
        {
            if (this.child[i] != null)
                str = str + "{" + this.child[i].toString() + "}";

            if (i < this.filled)
                str = str + " (" + this.key[i].toString() + ") ";

            else if (i < this.order << 1)
                str += " . ";
        }

        return str + "]>";
    }

    public void cleanNode()
    {
        int i = this.order << 1;

        for (int j = this.filled; j < i; j++)
        {
            this.key[j] = null;
            this.child[(j + 1)] = null;
        }
    }

    public int getOrder()
    {
        return this.order;
    }

    public int getFilled()
    {
        return this.filled;
    }

    public BTreeNode getParent()
    {
        return this.parent;
    }

    public BTreeNode getChild(int paramInt)
    {
        if ((paramInt < 0) || (paramInt > this.filled) || (isEmpty()))
            throw new IndexOutOfBoundsException("filled: " + this.filled + " position: " + paramInt);

        return this.child[paramInt];
    }

    public BTreeComparable getKey(int paramInt)
    {
        if ((paramInt < 0) || (paramInt >= this.filled) || (isEmpty()))
            throw new IndexOutOfBoundsException("filled: " + this.filled + " position: " + paramInt);

        return this.key[paramInt];
    }

    public boolean isLeaf()
    {
        return this.isLeaf;
    }

    public boolean isEmpty()
    {
        return getFilled() == 0;
    }

    public int getWidth()
    {
        return 20 * this.order * 2 + 5 + 5;
    }

    public int getFullWidth()
    {
        if (isLeaf())
            return getWidth();

        int i = 0;

        for (int j = 0; j <= this.filled; j++)
        {
            i += getChild(j).getFullWidth();

            if (j > 0)
                i += 10;
        }

        if (this.hidden != null)
            i += 10 + this.hidden.getFullWidth();

        return i;
    }

}
