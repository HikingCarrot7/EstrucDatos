package arbolb;

/**
 *
 * @author Nicolás
 * @param <T>
 */
@SuppressWarnings("unchecked")
public class ArbolB<T extends Comparable<T>>
{

    private int maxClaves;
    private int minClaves;
    private int maxClavesRaiz;
    private int nClaves;
    private int nodosMinimos;

    private final Object[] lista;
    private final Object[] listaPunteros;

    private NodoB<T> raiz;

    public ArbolB(int nClaves)
    {
        this.nClaves = nClaves;
        lista = new Object[nClaves + 1];
        listaPunteros = new Object[nClaves + 2];
    }

    public T buscar(T clave)
    {
        NodoB<T> nodo = raiz;
        int i;

        while (nodo != null)
        {
            i = 0;

            while (i < nodo.getLlavesUsadas() && nodo.getClave(i).compareTo(clave) < 0)
                i++;

            if (nodo.getClave(i).equals(clave))
            {
                System.out.println(i);
                return nodo.getClave(i);

            } else
                nodo = nodo.getHijo(i);
        }

        return null;
    }

    public void insertar(T clave)
    {
        NodoB<T> nodo = raiz;
        NodoB<T> padre = raiz;
        int i;

        while (nodo != null)
        {
            i = 0;
            padre = nodo;

            while (i < nodo.getLlavesUsadas() && nodo.getClave(i).compareTo(clave) < 0)
                i++;

            if (i < nodo.getLlavesUsadas() && nodo.getClave(i).equals(clave))
            {
                System.out.println("Clave duplicada");
                return;

            } else
                nodo = nodo.getHijo(i);
        }

        nodo = padre;
        insertar(clave, nodo, null, null);
    }

    private void insertar(T clave, NodoB<T> nodo, NodoB<T> hijo1, NodoB<T> hijo2)
    {
        NodoB<T> padre, nuevo;
        int i;
        boolean salir = false;

        do
        {
            if (nodo == null)
            {
                nodo = new NodoB<>(nClaves);
                raiz = nodo;
            }

            padre = nodo.getPadre();

            if (nodo.getLlavesUsadas() == nClaves)
            {
                nuevo = new NodoB<>(nClaves);
                i = 0;

                while (nodo.getClave(i).compareTo(clave) < 0 && i < nClaves)
                {
                    lista[i] = nodo.getClave(i);
                    listaPunteros[i] = nodo.getHijo(i);
                    i++;
                }

                lista[i] = clave;
                listaPunteros[i] = hijo1;
                listaPunteros[i + 1] = hijo2;

                while (i < nClaves)
                {
                    lista[i + 1] = nodo.getClave(i);
                    lista[i + 2] = nodo.getHijo(i + 1);
                    i++;
                }

                nodo.setLlavesUsadas(nClaves / 2);

                for (int j = 0; j < nodo.getLlavesUsadas(); j++)
                {
                    nodo.setClave(j, (T) lista[j]);
                    nodo.setHijo(j, (NodoB<T>) listaPunteros[j]);
                }

                nodo.setHijo(nodo.getLlavesUsadas(), (NodoB<T>) listaPunteros[nodo.getLlavesUsadas()]);
                nuevo.setLlavesUsadas(nClaves - nodo.getLlavesUsadas());

                for (int j = 0; j < nuevo.getLlavesUsadas(); j++)
                {
                    nuevo.setClave(j, (T) lista[j + nClaves / 2 + 1]);
                    nuevo.setHijo(j, (NodoB<T>) listaPunteros[j + nClaves / 2 + 1]);
                }

                nodo.setHijo(nodo.getLlavesUsadas(), (NodoB<T>) listaPunteros[nClaves + 1]);

                for (int j = 0; j < nodo.getLlavesUsadas(); j++)
                    if (nodo.getHijo(j) != null)
                        nodo.getHijo(j).setPadre(nodo);

                for (int j = 0; j < nuevo.getLlavesUsadas(); j++)
                    if (nuevo.getHijo(j) != null)
                        nuevo.getHijo(j).setPadre(nuevo);

                clave = (T) lista[nClaves / 2];
                hijo1 = nodo;
                hijo2 = nuevo;
                nodo = padre;

            } else
            {
                i = 0;
                if (nodo.getLlavesUsadas() > 0)
                {
                    while (i < nodo.getLlavesUsadas() && nodo.getClave(i).compareTo(clave) < 0)
                        i++;

                    for (int j = nodo.getLlavesUsadas(); j > i; j--)
                        nodo.setClave(j, nodo.getClave(j - 1));

                    for (int j = nodo.getLlavesUsadas() + 1; j > i; j--)
                        nodo.setHijo(j, nodo.getHijo(j - 1));
                }

                nodo.setLlavesUsadas(nodo.getLlavesUsadas() + 1);
                nodo.setClave(i, clave);
                nodo.setHijo(i, hijo1);
                nodo.setHijo(i + 1, hijo2);

                if (hijo1 != null)
                    hijo1.setPadre(nodo);

                if (hijo2 != null)
                    hijo2.setPadre(nodo);

                salir = true;
            }

        } while (!salir);

    }

    public void borrar(T dato)
    {
        NodoB<T> nodo = raiz;
        int i;

        while (nodo != null)
        {
            i = 0;
            while (i < nodo.getLlavesUsadas() && nodo.getClave(i).compareTo(dato) < 0)
                i++;

            if (i < nodo.getLlavesUsadas() && nodo.getClave(i).equals(dato))
                break;
            else
                nodo = nodo.getHijo(i);
        }

        if (nodo != null)
            borrarClave(dato, nodo);
    }

    private void borrarClave(T clave, NodoB<T> nodo)
    {
        NodoB<T> actual, derecha, izquierda = null, padre;
        int posClavePadre, pos;

        pos = 0;

        while (nodo.getClave(pos).compareTo(clave) < 0)
            pos++;

        if (nodo.getHijo(0) != null)
        {
            actual = nodo.getHijo(pos + 1);

            while (actual.getHijo(0) != null)
                actual = actual.getHijo(0);

            nodo.setClave(pos, actual.getClave(0));
            pos = 0;

        } else
            actual = nodo;

        for (int i = pos; i < actual.getLlavesUsadas(); i++)
            actual.setClave(i, actual.getClave(i + 1));

        actual.setLlavesUsadas(actual.getLlavesUsadas() - 1);

        if (actual == raiz && actual.getLlavesUsadas() == 0)
        {
            raiz = null;
            return;
        }

        if (actual == raiz || actual.getLlavesUsadas() >= nodosMinimos)
            return;

        do
        {
            padre = actual.getPadre();

            for (posClavePadre = 0; padre.getHijo(posClavePadre) != actual; posClavePadre++)
                if (posClavePadre > 0)
                    izquierda = padre.getHijo(posClavePadre - 1);
                else
                    izquierda = null;

            if (posClavePadre < padre.getLlavesUsadas())
                derecha = padre.getHijo(posClavePadre + 1);
            else
                derecha = null;

            if (derecha != null && derecha.getLlavesUsadas() > nodosMinimos)
                pasarClaveDerecha(derecha, padre, actual, posClavePadre);

            else if (izquierda != null && izquierda.getLlavesUsadas() > nodosMinimos)
                pasarClaveIzquierda(izquierda, padre, actual, posClavePadre - 1);

            else if (derecha != null)
                fundirNodo(actual, padre, derecha, posClavePadre);

            else
                fundirNodo(izquierda, padre, actual, posClavePadre - 1);

            actual = padre;

        } while (actual != null && actual != raiz && actual.getLlavesUsadas() < nodosMinimos);

    }

    private void pasarClaveDerecha(NodoB<T> derecha, NodoB<T> padre, NodoB<T> nodo, int posClavePadre)
    {
        nodo.setClave(nodo.getLlavesUsadas(), padre.getClave(posClavePadre));
        nodo.setLlavesUsadas(nodo.getLlavesUsadas() + 1);
        padre.setClave(posClavePadre, derecha.getClave(0));
        nodo.setHijo(nodo.getLlavesUsadas(), derecha.getHijo(0));

        if (derecha.getHijo(0) != null)
            derecha.getHijo(0).setPadre(nodo);

        for (int i = 0; i < derecha.getLlavesUsadas(); i++)
            derecha.setClave(i, derecha.getClave(i + 1));

        for (int i = 0; i <= derecha.getLlavesUsadas(); i++)
            derecha.setHijo(i, derecha.getHijo(i + 1));

        derecha.setLlavesUsadas(derecha.getLlavesUsadas() - 1);
    }

    private void pasarClaveIzquierda(NodoB<T> izquierda, NodoB<T> padre, NodoB<T> nodo, int posClavePadre)
    {
        for (int i = nodo.getLlavesUsadas(); i > 0; i--)
            nodo.setClave(i, nodo.getClave(i - 1));

        for (int i = nodo.getLlavesUsadas() + 1; i > 0; i--)
            nodo.setHijo(i, nodo.getHijo(i - 1));

        nodo.setLlavesUsadas(nodo.getLlavesUsadas() + 1);
        nodo.setClave(0, padre.getClave(posClavePadre));
        padre.setClave(posClavePadre, izquierda.getClave(izquierda.getLlavesUsadas() - 1));
        nodo.setHijo(0, izquierda.getHijo(izquierda.getLlavesUsadas()));

        if (nodo.getHijo(0) != null)
            nodo.getHijo(0).setPadre(nodo);

        izquierda.setLlavesUsadas(izquierda.getLlavesUsadas() - 1);
    }

    private void fundirNodo(NodoB<T> izquierda, NodoB<T> padre, NodoB<T> derecha, int posClavePadre)
    {
        izquierda.setClave(izquierda.getLlavesUsadas(), padre.getClave(posClavePadre));
        padre.setLlavesUsadas(padre.getLlavesUsadas() - 1);

        for (int i = posClavePadre; i < padre.getLlavesUsadas(); i++)
        {
            padre.setClave(i, padre.getClave(i + 1));
            padre.setHijo(i + 1, padre.getHijo(i + 2));
        }

        izquierda.setLlavesUsadas(izquierda.getLlavesUsadas() + 1);

        for (int i = 0; i < derecha.getLlavesUsadas(); i++)
            izquierda.setClave(izquierda.getLlavesUsadas() + i, derecha.getClave(i));

        for (int i = 0; i < derecha.getLlavesUsadas(); i++)
        {
            izquierda.setHijo(izquierda.getLlavesUsadas() + i, derecha.getHijo(i));

            if (derecha.getHijo(i) != null)
                derecha.getHijo(i).setPadre(izquierda);

        }

        izquierda.setLlavesUsadas(izquierda.getLlavesUsadas() + derecha.getLlavesUsadas());

        if (padre == raiz && padre.getLlavesUsadas() == 0)
        {
            raiz = izquierda;
            raiz.setPadre(null);
            padre = null;
        }

        derecha = null;
    }

    public T encontrar(T dato)
    {
        NodoB<T> nodo = raiz;
        int i;

        while (nodo != null)
        {
            i = 0;

            while (i < nodo.getLlavesUsadas() && nodo.getClave(i).compareTo(dato) < 0)
                i++;

            if (i < nodo.getLlavesUsadas() && nodo.getClave(i) == dato)
                return nodo.getClave(i);
            else
                nodo = nodo.getHijo(i);
        }

        return null;
    }

    public void preorder()
    {
        preorder(true, null);
    }

    private void preorder(boolean r, NodoB<T> nodo)
    {
        if (r)
            nodo = raiz;

        if (nodo == null)
            return;

        mostrar(nodo);

        for (int i = 0; i < nodo.getLlavesUsadas(); i++)
            preorder(false, nodo.getHijo(i));
    }

    private void mostrar(NodoB<T> nodo)
    {
        for (int i = 0; i < nodo.getLlavesUsadas(); i++)
            System.out.println(nodo.getClave(i).toString());
    }

    public void eliminar(T dato)
    {
        NodoB<T> nodo = raiz;
        NodoB<T> hoja;
        NodoB<T> tmp;
        NodoB<T> nodo1;
        NodoB<T> nodo2;

        int i, h = 0;

        while (nodo != null)
        {
            i = 0;

            while (i < nodo.getLlavesUsadas() && nodo.getClave(i).compareTo(dato) < 0)
            {
                i++;

                if (i < nodo.getLlavesUsadas() && nodo.getClave(i).equals(dato))
                {
                    if (nodo.getHijo(0) == null)
                    {
                        hoja = nodo;

                        while (i < nodo.getLlavesUsadas() - 1)
                        {
                            nodo.setClave(i, nodo.getClave(i + 1));
                            i++;
                        }

                        nodo.setLlavesUsadas(nodo.getLlavesUsadas() - 1);
                        return;
                    }

                    if (nodo.getHijo(0) != null)//si el nodo a eliminar no es hoja pero no tiene descendientes
                    {
                        int j = 0;
                        nodo1 = nodo.getHijo(i);
                        nodo2 = nodo.getHijo(i + 1);
                        if (nodo1.getLlavesUsadas() == minClaves - 1 && nodo2.getLlavesUsadas() == minClaves - 1)
                        {
                            nodo1.setClave(minClaves - 1, nodo2.getClave(0));

                            if (nodo2.getHijo(0) != null)
                            {
                                int a1 = 0;
                                for (int a = minClaves - 1; a <= 2; a++)
                                {
                                    a1++;
                                    nodo1.setHijo(a + 1, nodo2.getHijo(a1));
                                }
                            }

                            nodo.setLlavesUsadas(nodo.getLlavesUsadas() - 1);
                            System.out.println(nodo1.getClave(1));
                            insertar(nodo1.getClave(1));

                            return;
                        }
                        if (nodo2.getLlavesUsadas() > nodo1.getLlavesUsadas())
                        {
                            if (nodo2.getLlavesUsadas() != minClaves)
                            {
                                h = 1;
                                nodo.setClave(i, nodo2.getClave(0));
                                while (j < nodo2.getLlavesUsadas() - 1)
                                {
                                    nodo2.setClave(j, nodo2.getClave(j + 1));
                                    j++;
                                }
                                nodo2.setLlavesUsadas(nodo2.getLlavesUsadas() - 1);
                            }

                            if (h == 1)
                                return;
                        } else
                        {
                            if (nodo1.getLlavesUsadas() != minClaves)
                            {
                                h = 1;
                                nodo.setClave(i, nodo1.getClave(nodo.getLlavesUsadas() - 1));
                                nodo2.setLlavesUsadas(nodo2.getLlavesUsadas() - 1);
                            }

                            if (h == 1)
                                return;
                        }

                    }

                }

            }

            tmp = nodo;
            nodo = nodo.getHijo(i);
        }

    }

}
