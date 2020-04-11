/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avl;

/**
 *
 * @author jorge.reyes
 */
public class ArbolAVL
{

    protected NodoAVL raiz;

    public ArbolAVL()
    {
        raiz = null;
    }

    public ArbolAVL(Object o)
    {
        raiz = new NodoAVL(o);
    }

    public void inOrden()
    {
        if (raiz != null)
            raiz.inOrden();
    }

    public void preOrden()
    {
        if (raiz != null)
            raiz.preOrden();
    }

    public void posOrden()
    {
        if (raiz != null)
            raiz.posOrden();
    }

    public void insertar(Object o)
    {
        insertarOrdenado(raiz, o);
    }

    private void insertarOrdenado(NodoAVL n, Object o)
    {
        if ((int) o < (int) n.getDato())
            if (n.getIzq() == null)
            {
                n.setIzq(new NodoAVL(o, null, null, n));
                recalcularFE(n);
            } else
                insertarOrdenado((NodoAVL) n.getIzq(), o);
        else if ((int) o > (int) n.getDato())
            if (n.getDer() == null)
            {
                n.setDer(new NodoAVL(o, null, null, n));
                recalcularFE(n);
            } else
                insertarOrdenado((NodoAVL) n.getDer(), o);
    }

    public void recalcularFE(NodoAVL nodo)
    {
        if (nodo != null)
        {
            nodo.setFE(NodoAVL.altura((NodoAVL) nodo.getDer()) - NodoAVL.altura((NodoAVL) nodo.getIzq()));

            if (nodo.getFE() == 2 || nodo.getFE() == -2)
                balancear(nodo);
            else
                recalcularFE(nodo.getPadre());
        }
    }

    public void balancear(NodoAVL nodo)
    {
        int feActual = nodo.getFE();
        if (feActual == 2)
            switch (((NodoAVL) nodo.getDer()).getFE())
            {
                case 0:
                case 1:
                    rotacionDD(nodo);
                    System.out.println("Aplicando rotación DD..");
                    break;
                case -1:
                    rotacionDI(nodo);
                    System.out.println("Aplicando rotación DI..");
                    break;
            }
        else
            switch (((NodoAVL) nodo.getIzq()).getFE())
            {
                case 0:
                case -1:
                    rotacionII(nodo);
                    System.out.println("Aplicando rotación II..");
                    break;
                case 1:
                    rotacionID(nodo);
                    System.out.println("Aplicando rotación ID..");
                    break;
            }
    }

    public void rotacionII(NodoAVL nodo)
    {
        //Establecer los apuntadores..
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getIzq();
        NodoAVL B = (NodoAVL) Q.getDer();

        //Ajustar hijos
        if (Padre != null)
            if (Padre.getDer() == P)
                Padre.setDer(Q);
            else
                Padre.setIzq(Q);
        else
            raiz = Q;
        //Reconstruir el arbol
        P.setIzq(B);
        Q.setDer(P);

        //Reasignar Padres
        P.setPadre(Q);
        if (B != null)
            B.setPadre(P);
        Q.setPadre(Padre);

        //Ajustar los valores de los FE
        P.setFE(0);
        Q.setFE(0);
    }

    public void rotacionDD(NodoAVL nodo)
    {
        //Establecer los apuntadores..
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getDer();
        NodoAVL B = (NodoAVL) Q.getIzq();

        //Ajustar hijos
        if (Padre != null)
            if (Padre.getIzq() == P)
                Padre.setIzq(Q);
            else
                Padre.setDer(Q);
        else
            raiz = Q;
        //Reconstruir el arbol
        P.setDer(B);
        Q.setIzq(P);

        //Reasignar Padres
        P.setPadre(Q);
        if (B != null)
            B.setPadre(P);
        Q.setPadre(Padre);

        //Ajustar los valores de los FE
        P.setFE(0);
        Q.setFE(0);
    }

    public void rotacionID(NodoAVL nodo)
    {
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getIzq();
        NodoAVL R = (NodoAVL) Q.getDer();
        NodoAVL B = (NodoAVL) R.getIzq();
        NodoAVL C = (NodoAVL) R.getDer();

        if (Padre != null)
            if (Padre.getDer() == nodo)
                Padre.setDer(R);
            else
                Padre.setIzq(R);
        else
            raiz = R;
        //Reconstrucción del árbol
        Q.setDer(B);
        P.setIzq(C);
        R.setIzq(Q);
        R.setDer(P);
        //Reasignación de padres
        R.setPadre(Padre);
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

    public void rotacionDI(NodoAVL nodo)
    {
        NodoAVL Padre = nodo.getPadre();
        NodoAVL P = nodo;
        NodoAVL Q = (NodoAVL) P.getDer();
        NodoAVL R = (NodoAVL) Q.getIzq();
        NodoAVL B = (NodoAVL) R.getDer();
        NodoAVL C = (NodoAVL) R.getIzq();

        if (Padre != null)
            if (Padre.getIzq() == nodo)
                Padre.setIzq(R);
            else
                Padre.setDer(R);
        else
            raiz = R;
        //Reconstrucción del árbol
        Q.setIzq(B);
        P.setDer(C);
        R.setDer(Q);
        R.setIzq(P);
        //Reasignación de padres
        R.setPadre(Padre);
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

    public static void main(String[] args)
    {
        ArbolAVL arbol = new ArbolAVL(55);
        arbol.insertar(12);
        arbol.insertar(5);
        arbol.insertar(678);
        arbol.insertar(90);
        arbol.insertar(780);
        arbol.insertar(389);
        arbol.insertar(4545);
        arbol.insertar(9967);
        arbol.inOrden();

    }

}
