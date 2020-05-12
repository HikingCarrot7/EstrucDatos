package com.sw.model.arbolavl;

import com.sw.model.Comparador;
import com.sw.model.arbolbb.ArbolBinario;
import com.sw.model.arbolbb.NodoBinario;
import com.sw.model.exceptions.ItemNotFoundException;
import com.sw.model.util.LinkedList;

/**
 *
 * @author jorge.reyes
 * @param <L>
 * @param <I>
 */
@SuppressWarnings("unchecked")
public class ArbolAVL<L extends LinkedList<Integer>, I extends Comparable<I>> extends ArbolBinario<L, I>
{

    private final Comparador<L, I> comparador;

    public ArbolAVL(Comparador<L, I> comparador)
    {
        this.comparador = comparador;
    }

    @Override
    public void insertar(int idx, I dato)
    {
        insertarOrdenado(getRaiz(), idx, dato);
    }

    private void insertarOrdenado(NodoAVL<L> nodo, int idx, I item)
    {
        if (isEmpty())
        {
            raiz = new NodoAVL<>((L) new LinkedList<Integer>());
            raiz.getDato().addFirst(idx);

        } else if (comparador.comparar(nodo.getDato(), item) > 0)
            if (nodo.getIzq() == null)
            {
                nodo.setIzq(new NodoAVL<>((L) new LinkedList<Integer>(), null, null, nodo));
                nodo.getIzq().getDato().addLast(idx);
                recalcularFE(nodo);

            } else
                insertarOrdenado(nodo.getIzq(), idx, item);

        else if (comparador.comparar(nodo.getDato(), item) < 0)
            if (nodo.getDer() == null)
            {
                nodo.setDer(new NodoAVL<>((L) new LinkedList<Integer>(), null, null, nodo));
                nodo.getDer().getDato().addLast(idx);
                recalcularFE(nodo);

            } else
                insertarOrdenado(nodo.getDer(), idx, item);

        else
            nodo.getDato().addLast(idx);
    }

    @Override
    public L buscar(I item) throws ItemNotFoundException
    {
        return buscar(raiz, item);
    }

    private L buscar(NodoBinario<L> nodo, I item)
    {
        if (comparador.comparar(nodo.getDato(), item) > 0)
            if (nodo.getIzq() == null)
                throw new ItemNotFoundException();
            else
                return buscar(nodo.getIzq(), item);

        else if (comparador.comparar(nodo.getDato(), item) < 0)
            if (nodo.getDer() == null)
                throw new ItemNotFoundException();
            else
                return buscar(nodo.getDer(), item);

        else
            return nodo.getDato();
    }

    public void recalcularFE(NodoAVL<L> nodo)
    {
        if (nodo != null)
        {
            nodo.setFE(NodoAVL.altura(nodo.getDer()) - NodoAVL.altura(nodo.getIzq()));

            if (nodo.getFE() == 2 || nodo.getFE() == -2)
                balancear(nodo);

            else
                recalcularFE(nodo.getPadre());
        }
    }

    public void balancear(NodoAVL<L> nodo)
    {
        int feActual = nodo.getFE();

        if (feActual == 2)
            switch (((NodoAVL<?>) nodo.getDer()).getFE())
            {
                case 0:
                case 1:
                    rotacionDD(nodo);
                    break;
                case -1:
                    rotacionDI(nodo);
                    break;
            }

        else
            switch (((NodoAVL<?>) nodo.getIzq()).getFE())
            {
                case 0:
                case -1:
                    rotacionII(nodo);
                    break;
                case 1:
                    rotacionID(nodo);
                    break;
            }
    }

    public void rotacionII(NodoAVL<L> nodo)
    {
        //Establecer los apuntadores.
        NodoAVL<L> padre = nodo.getPadre();
        NodoAVL<L> P = nodo;
        NodoAVL<L> Q = P.getIzq();
        NodoAVL<L> B = Q.getDer();

        //Ajustar hijos
        if (padre != null)
            if (padre.getDer() == P)
                padre.setDer(Q);
            else
                padre.setIzq(Q);
        else
            raiz = Q;
        //Reconstruir el arbol
        P.setIzq(B);
        Q.setDer(P);

        //Reasignar Padres
        P.setPadre(Q);

        if (B != null)
            B.setPadre(P);

        Q.setPadre(padre);

        //Ajustar los valores de los FE
        P.setFE(0);
        Q.setFE(0);
    }

    public void rotacionDD(NodoAVL<L> nodo)
    {
        //Establecer los apuntadores.
        NodoAVL<L> padre = nodo.getPadre();
        NodoAVL<L> P = nodo;
        NodoAVL<L> Q = P.getDer();
        NodoAVL<L> B = Q.getIzq();

        //Ajustar hijos
        if (padre != null)
            if (padre.getIzq() == P)
                padre.setIzq(Q);
            else
                padre.setDer(Q);
        else
            raiz = Q;
        //Reconstruir el arbol
        P.setDer(B);
        Q.setIzq(P);

        //Reasignar Padres
        P.setPadre(Q);

        if (B != null)
            B.setPadre(P);

        Q.setPadre(padre);

        //Ajustar los valores de los FE
        P.setFE(0);
        Q.setFE(0);
    }

    public void rotacionID(NodoAVL<L> nodo)
    {
        NodoAVL<L> padre = nodo.getPadre();
        NodoAVL<L> P = nodo;
        NodoAVL<L> Q = P.getIzq();
        NodoAVL<L> R = Q.getDer();
        NodoAVL<L> B = R.getIzq();
        NodoAVL<L> C = R.getDer();

        if (padre != null)
            if (padre.getDer() == nodo)
                padre.setDer(R);
            else
                padre.setIzq(R);
        else
            raiz = R;
        //Reconstrucción del árbol
        Q.setDer(B);
        P.setIzq(C);
        R.setIzq(Q);
        R.setDer(P);
        //Reasignación de padres
        R.setPadre(padre);
        P.setPadre(R);
        Q.setPadre(R);

        if (B != null)
            B.setPadre(Q);

        if (C != null)
            C.setPadre(P);
        //Ajusta los valores de los factores de equilibrio
        switch (R.getFE())
        {
            case -1:
                Q.setFE(0);
                P.setFE(1);
                break;
            case 0:
                Q.setFE(0);
                P.setFE(0);
                break;
            case 1:
                Q.setFE(-1);
                P.setFE(0);
                break;
        }

        R.setFE(0);
    }

    public void rotacionDI(NodoAVL<L> nodo)
    {
        NodoAVL<L> padre = nodo.getPadre();
        NodoAVL<L> P = nodo;
        NodoAVL<L> Q = P.getDer();
        NodoAVL<L> R = Q.getIzq();
        NodoAVL<L> B = R.getDer();
        NodoAVL<L> C = R.getIzq();

        if (padre != null)
            if (padre.getIzq() == nodo)
                padre.setIzq(R);
            else
                padre.setDer(R);
        else
            raiz = R;
        //Reconstrucción del árbol
        Q.setIzq(B);
        P.setDer(C);
        R.setDer(Q);
        R.setIzq(P);
        //Reasignación de padres
        R.setPadre(padre);
        P.setPadre(R);
        Q.setPadre(R);

        if (B != null)
            B.setPadre(Q);
        if (C != null)
            C.setPadre(P);

        //Ajusta los valores de los factores de equilibrio
        switch (R.getFE())
        {
            case -1:
                Q.setFE(0);
                P.setFE(1);
                break;
            case 0:
                Q.setFE(0);
                P.setFE(0);
                break;
            case 1:
                Q.setFE(-1);
                P.setFE(0);
                break;
        }

        R.setFE(0);
    }

    @Override
    public NodoAVL<L> getRaiz()
    {
        return (NodoAVL<L>) raiz;
    }

}
