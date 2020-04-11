package arbolesavl;

import arbolbusquedabinaria.ArbolBinario;

/**
 *
 * @author Nicolás
 * @param <E>
 */
public class ArbolAVL<E extends Comparable<E>> extends ArbolBinario<E>
{

    private NodoAVL<E> raiz;

    public void insertar(E dato)
    {
        insertarOrdenado(raiz, dato);
    }

    private void insertarOrdenado(NodoAVL<E> nodo, E dato)
    {
        if (raiz == null)
            raiz = new NodoAVL<>(dato);

        else if (dato.compareTo(nodo.getDato()) < 0)
            if (nodo.getIzq() == null)
            {
                nodo.setIzq(new NodoAVL<>(nodo, dato, null, null));
                recalcularFE(nodo);

            } else
                insertarOrdenado((NodoAVL<E>) nodo.getIzq(), dato);

        else if (dato.compareTo(nodo.getDato()) > 0)
            if (nodo.getDer() == null)
            {
                nodo.setDer(new NodoAVL<>(nodo, dato, null, null));
                recalcularFE(nodo);

            } else
                insertarOrdenado((NodoAVL<E>) nodo.getDer(), dato);

    }

    public void recalcularFE(NodoAVL<E> nodo)
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

    public void balancear(NodoAVL<E> nodo)
    {
        int feActual = nodo.getFE();

        if (feActual == 2)
            switch (((NodoAVL<?>) nodo.getDer()).getFE())
            {
                case 0:
                case 1:
                    rotacionDD(nodo);
                    System.out.println("Aplicando rotación DD...");
                    break;
                case -1:
                    rotacionDI(nodo);
                    System.out.println("Aplicando rotación DI...");
                    break;
            }
        else
            switch (((NodoAVL<?>) nodo.getIzq()).getFE())
            {
                case 0:
                case -1:
                    rotacionII(nodo);
                    System.out.println("Aplicando rotación II...");
                    break;
                case 1:
                    rotacionID(nodo);
                    System.out.println("Aplicando rotación ID...");
                    break;
            }
    }

    public void rotacionII(NodoAVL<E> nodo)
    {
        //Establecer los apuntadores.
        NodoAVL<E> padre = nodo.getPadre();
        NodoAVL<E> P = nodo;
        NodoAVL<E> Q = (NodoAVL<E>) P.getIzq();
        NodoAVL<E> B = (NodoAVL<E>) Q.getDer();

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

    public void rotacionDD(NodoAVL<E> nodo)
    {
        //Establecer los apuntadores.
        NodoAVL<E> padre = nodo.getPadre();
        NodoAVL<E> P = nodo;
        NodoAVL<E> Q = (NodoAVL<E>) P.getDer();
        NodoAVL<E> B = (NodoAVL<E>) Q.getIzq();

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

    public void rotacionID(NodoAVL<E> nodo)
    {
        NodoAVL<E> padre = nodo.getPadre();
        NodoAVL<E> P = nodo;
        NodoAVL<E> Q = (NodoAVL<E>) P.getDer();
        NodoAVL<E> R = (NodoAVL<E>) Q.getIzq();
        NodoAVL<E> B = (NodoAVL<E>) R.getDer();
        NodoAVL<E> C = (NodoAVL<E>) R.getIzq();

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

    public void rotacionDI(NodoAVL<E> nodo)
    {
        NodoAVL<E> padre = nodo.getPadre();
        NodoAVL<E> P = nodo;
        NodoAVL<E> Q = (NodoAVL<E>) P.getDer();
        NodoAVL<E> R = (NodoAVL<E>) Q.getIzq();
        NodoAVL<E> B = (NodoAVL<E>) R.getDer();
        NodoAVL<E> C = (NodoAVL<E>) R.getIzq();

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

}
