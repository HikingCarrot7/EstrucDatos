package com.sw.model.arbolb;

import com.sw.model.Usuario;
import java.io.Serializable;
import java.util.List;

public class BTree implements Serializable
{

    protected BTreeNode root;
    private int order;

    public BTree(int order)
    {
        if (order < 1)
            order = 1;

        this.order = order;
        this.root = new BTreeNode(order, true);
    }

    public BTreeComparable find(BTreeComparable paramBTreeComparable)
    {
        if (this.root == null)
            throw new IllegalStateException();

        if (this.root.isEmpty())
            return null;

        BTreeNode localBTreeNode = findNode(paramBTreeComparable);

        if (localBTreeNode == null)
            throw new IllegalStateException();

        int i = localBTreeNode.findKeyPosition(paramBTreeComparable);

        if ((i & 0x1) != 1)
            return null;

        return localBTreeNode.getKey(i >> 1);
    }

    public void add(BTreeComparable paramBTreeComparable)
    {
        if (find(paramBTreeComparable) != null)
            return;

        BTreeNode localBTreeNode = findLeaf(paramBTreeComparable);
        addHere(localBTreeNode, paramBTreeComparable, null, null);
    }

    public void insert(BTreeComparable paramBTreeComparable)
    {
        add(paramBTreeComparable);
    }

    public void delete(BTreeComparable paramBTreeComparable)
    {
        if (find(paramBTreeComparable) == null)
            return;
        BTreeNode localBTreeNode = findNode(paramBTreeComparable);
        int i = localBTreeNode.findKeyPosition(paramBTreeComparable) >> 1;
        if (localBTreeNode.isLeaf() == false)
        {
            localBTreeNode = swapWithLeaf(localBTreeNode, i);
            i = 0;
        }

        removeOne(localBTreeNode, i);

        if (localBTreeNode == this.root)
            return;

        notEnoughKeys(localBTreeNode);
    }

    public void remove(BTreeComparable paramBTreeComparable)
    {
        delete(paramBTreeComparable);
    }

    private BTreeNode findNode(BTreeComparable paramBTreeComparable)
    {
        if ((this.root == null) || (this.root.isEmpty()))
            throw new IllegalStateException();

        BTreeNode localBTreeNode = this.root;

        while (localBTreeNode != null)
        {
            int i = localBTreeNode.findKeyPosition(paramBTreeComparable);

            if (((i & 0x1) == 1) || (localBTreeNode.isLeaf()))
                return localBTreeNode;

            localBTreeNode = localBTreeNode.getChild(i >> 1);
        }

        throw new IllegalStateException();
    }

    private BTreeNode findLeaf(BTreeComparable paramBTreeComparable)
    {
        BTreeNode localBTreeNode = this.root;

        while (localBTreeNode != null)
        {
            if (localBTreeNode.isLeaf())
                return localBTreeNode;

            int i = localBTreeNode.findKeyPosition(paramBTreeComparable);

            if ((i & 0x1) == 1)
                i--;

            localBTreeNode = localBTreeNode.getChild(i >> 1);
        }

        throw new IllegalStateException();
    }

    protected void addHere(BTreeNode paramBTreeNode1, BTreeComparable paramBTreeComparable, BTreeNode paramBTreeNode2, BTreeNode paramBTreeNode3)
    {
        if (paramBTreeNode1.isEmpty())
        {
            addHereToEmpty(paramBTreeNode1, paramBTreeComparable, paramBTreeNode2, paramBTreeNode3);
            return;
        }

        int i = paramBTreeNode1.findKeyPosition(paramBTreeComparable);

        if ((i & 0x1) == 1)
            throw new IllegalStateException("filled: " + paramBTreeNode1.getFilled() + " position: " + i + " item: " + paramBTreeComparable.toString());

        i >>= 1;

        if (paramBTreeNode1.getChild(i) != paramBTreeNode2)
            throw new IllegalStateException();

        if (paramBTreeNode1.getFilled() < 2 * paramBTreeNode1.getOrder())
        {
            addHereNotFull(paramBTreeNode1, paramBTreeComparable, paramBTreeNode2, paramBTreeNode3, i);
            return;
        }

        BTreeComparable[] arrayOfBTreeComparable1 = new BTreeComparable[this.order * 2 + 1];
        BTreeNode[] arrayOfBTreeNode1 = new BTreeNode[this.order * 2 + 2];
        addHereMakeTmp(paramBTreeNode1, paramBTreeComparable, paramBTreeNode2, paramBTreeNode3, arrayOfBTreeComparable1, arrayOfBTreeNode1, i);
        BTreeNode localBTreeNode = addHereNewRight(paramBTreeNode1, arrayOfBTreeComparable1, arrayOfBTreeNode1, this.order);
        addHereSetLeft(paramBTreeNode1, arrayOfBTreeComparable1, arrayOfBTreeNode1);

        if (!localBTreeNode.isLeaf())
            for (int j = 0; j <= this.order; j++)
                localBTreeNode.getChild(j).parent = localBTreeNode;

        if (paramBTreeNode1 == this.root)
        {
            BTreeComparable[] arrayOfBTreeComparable2 =
            {
                arrayOfBTreeComparable1[this.order]
            };

            BTreeNode[] arrayOfBTreeNode2 =
            {
                paramBTreeNode1, localBTreeNode
            };

            this.root = new BTreeNode(this.order, null, arrayOfBTreeComparable2, arrayOfBTreeNode2, false);
            paramBTreeNode1.parent = this.root;
            localBTreeNode.parent = this.root;

        } else
            addHere(paramBTreeNode1.getParent(), arrayOfBTreeComparable1[this.order], paramBTreeNode1, localBTreeNode);
    }

    protected void addHereToEmpty(BTreeNode paramBTreeNode1, BTreeComparable paramBTreeComparable, BTreeNode paramBTreeNode2, BTreeNode paramBTreeNode3)
    {
        paramBTreeNode1.child[0] = paramBTreeNode2;
        paramBTreeNode1.child[1] = paramBTreeNode3;
        paramBTreeNode1.key[0] = paramBTreeComparable;
        paramBTreeNode1.filled = 1;
    }

    protected void addHereNotFull(BTreeNode paramBTreeNode1, BTreeComparable paramBTreeComparable, BTreeNode paramBTreeNode2, BTreeNode paramBTreeNode3, int paramInt)
    {
        int i = paramBTreeNode1.getFilled() - paramInt;
        int j = paramInt;
        System.arraycopy(paramBTreeNode1.key, j, paramBTreeNode1.key, j + 1, i);
        System.arraycopy(paramBTreeNode1.child, j + 1, paramBTreeNode1.child, j + 2, i);
        paramBTreeNode1.key[paramInt] = paramBTreeComparable;
        paramBTreeNode1.child[(paramInt + 1)] = paramBTreeNode3;
        paramBTreeNode1.filled += 1;
    }

    protected BTreeNode addHereNewRight(BTreeNode paramBTreeNode, BTreeComparable[] paramArrayOfBTreeComparable, BTreeNode[] paramArrayOfBTreeNode, int paramInt)
    {
        BTreeComparable[] arrayOfBTreeComparable = new BTreeComparable[paramInt];
        BTreeNode[] arrayOfBTreeNode = new BTreeNode[paramInt + 1];
        System.arraycopy(paramArrayOfBTreeComparable, paramInt + 1, arrayOfBTreeComparable, 0, paramInt);
        System.arraycopy(paramArrayOfBTreeNode, paramInt + 1, arrayOfBTreeNode, 0, paramInt + 1);
        return new BTreeNode(paramInt, paramBTreeNode.getParent(), arrayOfBTreeComparable, arrayOfBTreeNode, paramBTreeNode.isLeaf());
    }

    protected void addHereMakeTmp(BTreeNode paramBTreeNode1, BTreeComparable paramBTreeComparable, BTreeNode paramBTreeNode2, BTreeNode paramBTreeNode3, BTreeComparable[] paramArrayOfBTreeComparable, BTreeNode[] paramArrayOfBTreeNode, int paramInt)
    {
        int i = paramBTreeNode1.getFilled() - paramInt;
        System.arraycopy(paramBTreeNode1.key, 0, paramArrayOfBTreeComparable, 0, paramInt);
        System.arraycopy(paramBTreeNode1.child, 0, paramArrayOfBTreeNode, 0, paramInt);
        System.arraycopy(paramBTreeNode1.key, paramInt, paramArrayOfBTreeComparable, paramInt + 1, i);
        System.arraycopy(paramBTreeNode1.child, paramInt + 1, paramArrayOfBTreeNode, paramInt + 2, i);
        paramArrayOfBTreeComparable[paramInt] = paramBTreeComparable;
        paramArrayOfBTreeNode[paramInt] = paramBTreeNode2;
        paramArrayOfBTreeNode[(paramInt + 1)] = paramBTreeNode3;
    }

    protected void addHereSetLeft(BTreeNode paramBTreeNode, BTreeComparable[] paramArrayOfBTreeComparable, BTreeNode[] paramArrayOfBTreeNode)
    {
        paramBTreeNode.filled = paramBTreeNode.getOrder();
        paramBTreeNode.cleanNode();
        System.arraycopy(paramArrayOfBTreeComparable, 0, paramBTreeNode.key, 0, paramBTreeNode.getFilled());
        System.arraycopy(paramArrayOfBTreeNode, 0, paramBTreeNode.child, 0, paramBTreeNode.getFilled() + 1);
    }

    protected BTreeNode findLogicalNext(BTreeNode paramBTreeNode, int paramInt)
    {
        paramBTreeNode = paramBTreeNode.getChild(paramInt + 1);

        while (paramBTreeNode.isLeaf() == false)
            paramBTreeNode = paramBTreeNode.getChild(0);

        return paramBTreeNode;
    }

    protected BTreeNode swapWithLeaf(BTreeNode paramBTreeNode, int paramInt)
    {
        BTreeNode localBTreeNode = findLogicalNext(paramBTreeNode, paramInt);
        BTreeComparable localBTreeComparable = paramBTreeNode.key[paramInt];
        paramBTreeNode.key[paramInt] = localBTreeNode.key[0];
        localBTreeNode.key[0] = localBTreeComparable;
        return localBTreeNode;
    }

    protected void removeOne(BTreeNode paramBTreeNode, int paramInt)
    {
        System.arraycopy(paramBTreeNode.key, paramInt + 1, paramBTreeNode.key, paramInt, paramBTreeNode.getFilled() - paramInt - 1);
        paramBTreeNode.filled -= 1;
        paramBTreeNode.cleanNode();
    }

    protected void notEnoughKeys(BTreeNode paramBTreeNode)
    {
        if ((paramBTreeNode.getFilled() >= this.order) || (paramBTreeNode == this.root))
            return;

        BTreeNode localBTreeNode1 = paramBTreeNode.getParent();
        int i = localBTreeNode1.findChildPosition(paramBTreeNode);

        if ((i > 0) && (localBTreeNode1.getChild(i - 1).getFilled() > this.order))
            deleteBorrowLeft(paramBTreeNode, localBTreeNode1, i);
        else if ((i < localBTreeNode1.getFilled()) && (localBTreeNode1.getChild(i + 1).getFilled() > this.order))
            deleteBorrowRight(paramBTreeNode, localBTreeNode1, i);
        else
        {
            BTreeNode localBTreeNode2;
            BTreeNode localBTreeNode3;
            if (i > 0)
            {
                localBTreeNode2 = localBTreeNode1.getChild(--i);
                localBTreeNode3 = paramBTreeNode;
            } else
            {
                localBTreeNode2 = paramBTreeNode;
                localBTreeNode3 = localBTreeNode1.getChild(i + 1);
            }

            deleteJoinNode(localBTreeNode2, localBTreeNode3, localBTreeNode1, i);

            if ((localBTreeNode1 == this.root) && (localBTreeNode1.getFilled() == 0))
            {
                this.root = localBTreeNode2;
                return;
            }
        }

        notEnoughKeys(localBTreeNode1);
    }

    protected void deleteBorrowLeft(BTreeNode paramBTreeNode1, BTreeNode paramBTreeNode2, int paramInt)
    {
        System.arraycopy(paramBTreeNode1.key, 0, paramBTreeNode1.key, 1, paramBTreeNode1.filled);
        System.arraycopy(paramBTreeNode1.child, 0, paramBTreeNode1.child, 1, ++paramBTreeNode1.filled);
        paramBTreeNode1.key[0] = paramBTreeNode2.key[(paramInt - 1)];
        BTreeNode localBTreeNode = paramBTreeNode2.getChild(paramInt - 1);
        paramBTreeNode1.child[0] = localBTreeNode.child[localBTreeNode.filled];
        if (paramBTreeNode1.isLeaf() == false)
            paramBTreeNode1.child[0].parent = paramBTreeNode1;
        paramBTreeNode2.key[(paramInt - 1)] = localBTreeNode.key[(--localBTreeNode.filled)];
        localBTreeNode.cleanNode();
    }

    protected void deleteBorrowRight(BTreeNode paramBTreeNode1, BTreeNode paramBTreeNode2, int paramInt)
    {
        BTreeNode localBTreeNode = paramBTreeNode2.getChild(paramInt + 1);
        paramBTreeNode1.key[(paramBTreeNode1.filled++)] = paramBTreeNode2.key[paramInt];
        paramBTreeNode2.key[paramInt] = localBTreeNode.key[0];
        paramBTreeNode1.child[paramBTreeNode1.filled] = localBTreeNode.child[0];
        if (paramBTreeNode1.isLeaf() == false)
            paramBTreeNode1.child[paramBTreeNode1.filled].parent = paramBTreeNode1;
        localBTreeNode.filled -= 1;
        System.arraycopy(localBTreeNode.key, 1, localBTreeNode.key, 0, localBTreeNode.filled);
        System.arraycopy(localBTreeNode.child, 1, localBTreeNode.child, 0, localBTreeNode.filled + 1);
        localBTreeNode.cleanNode();
    }

    protected void deleteJoinNode(BTreeNode paramBTreeNode1, BTreeNode paramBTreeNode2, BTreeNode paramBTreeNode3, int paramInt)
    {
        BTreeComparable[] arrayOfBTreeComparable = new BTreeComparable[paramBTreeNode1.getFilled() + paramBTreeNode2.getFilled() + 1];
        BTreeNode[] arrayOfBTreeNode = new BTreeNode[paramBTreeNode1.getFilled() + paramBTreeNode2.getFilled() + 2];

        System.arraycopy(paramBTreeNode1.key, 0, arrayOfBTreeComparable, 0, paramBTreeNode1.getFilled());
        System.arraycopy(paramBTreeNode2.key, 0, arrayOfBTreeComparable, paramBTreeNode1.getFilled() + 1, paramBTreeNode2.getFilled());
        arrayOfBTreeComparable[paramBTreeNode1.getFilled()] = paramBTreeNode3.key[paramInt];
        System.arraycopy(paramBTreeNode1.child, 0, arrayOfBTreeNode, 0, paramBTreeNode1.getFilled() + 1);
        System.arraycopy(paramBTreeNode2.child, 0, arrayOfBTreeNode, paramBTreeNode1.getFilled() + 1, paramBTreeNode2.getFilled() + 1);

        System.arraycopy(arrayOfBTreeComparable, 0, paramBTreeNode1.key, 0, arrayOfBTreeComparable.length);
        System.arraycopy(arrayOfBTreeNode, 0, paramBTreeNode1.child, 0, arrayOfBTreeNode.length);
        paramBTreeNode1.filled = arrayOfBTreeComparable.length;

        if (paramBTreeNode1.isLeaf() == false)
            for (int i = 0; i < arrayOfBTreeNode.length; i++)
                paramBTreeNode1.child[i].parent = paramBTreeNode1;
        System.arraycopy(paramBTreeNode3.key, paramInt + 1, paramBTreeNode3.key, paramInt, paramBTreeNode3.getFilled() - paramInt - 1);
        System.arraycopy(paramBTreeNode3.child, paramInt + 2, paramBTreeNode3.child, paramInt + 1, paramBTreeNode3.getFilled() - paramInt - 1);
        paramBTreeNode3.filled -= 1;

        paramBTreeNode2.filled = 0;

        paramBTreeNode1.cleanNode();
        paramBTreeNode2.cleanNode();
        paramBTreeNode3.cleanNode();
    }

    public List<Usuario> getItems()
    {
        return root.getItems();
    }

    @Override
    public String toString()
    {
        return this.root.toString();
    }
}
