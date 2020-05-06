package grafoMatrizAdy;

import java.util.ArrayList;
import java.util.List;
import util.Deque;
import util.DequeList;

/**
 *
 * @author Nicolás
 * @param <E>
 */
@SuppressWarnings("unchecked")
public class GrafoMatrizAdy<E>
{

    private int numeroVertices;
    private final Vertice<E>[] vertices;
    private final int[][] matrizAdy;

    public GrafoMatrizAdy(int maxVertices)
    {
        matrizAdy = new int[maxVertices][maxVertices];
        vertices = (Vertice<E>[]) new Vertice<?>[maxVertices];
    }

    public void nuevoVertice(E dato)
    {
        boolean esta = numVertice(dato) >= 0;

        if (!esta)
        {
            Vertice<E> v = new Vertice<>(dato);
            v.setNumVertice(numeroVertices);
            vertices[numeroVertices] = v;
            numeroVertices++;
        }
    }

    public void nuevoArco(E inicio, E destino) throws RuntimeException
    {
        int verticeInicio = numVertice(inicio);
        int verticeFinal = numVertice(destino);

        if (verticeInicio < 0 || verticeFinal < 0)
            throw new RuntimeException("Algún vértice no existe.");

        matrizAdy[verticeInicio][verticeFinal] = 1;
        matrizAdy[verticeFinal][verticeInicio] = 1;
    }

    public boolean sonAdyacentes(E inicio, E destino) throws RuntimeException
    {
        int verticeInicio = numVertice(inicio);
        int verticeFinal = numVertice(destino);

        if (verticeInicio < 0 || verticeFinal < 0)
            throw new RuntimeException("Algún vértice no existe.");

        return matrizAdy[verticeInicio][verticeFinal] == 1;
    }

    public int numVertice(E nombre)
    {
        Vertice<E> vertice = new Vertice<>(nombre);

        for (int i = 0; i < numeroVertices; i++)
            if (vertices[i].equals(vertice))
                return i;

        return -1;
    }

    public void recorridoAnchura()
    {
        recorridoAnchura(0);
    }

    public void recorridoAnchura(int numeroVertice)
    {
        StringBuilder recorrido = new StringBuilder();

        if (!isEmpty())
        {
            Deque<Vertice<E>> colaRecorrido = new DequeList<>();
            ArrayList<Vertice<E>> verticesRecorridos = new ArrayList<>();

            colaRecorrido.insertFirst(vertices[numeroVertice]);
            verticesRecorridos.add(vertices[numeroVertice]);
            System.out.println("Se ha recorrido el vértice: " + vertices[numeroVertice].getDato());

            while (!colaRecorrido.isEmpty())
            {
                Vertice<E> verticeActual = colaRecorrido.removeFirst();
                recorrido.append(verticeActual.getDato()).append("->");

                for (int i = 0; i < matrizAdy[verticeActual.getNumVertice()].length; i++)
                    if (matrizAdy[verticeActual.getNumVertice()][i] == 1 && !verticesRecorridos.contains(vertices[i]))
                    {
                        verticesRecorridos.add(vertices[i]);
                        colaRecorrido.insertLast(vertices[i]);
                        System.out.println("Se ha recorrido el vértice: " + vertices[i].getDato());
                    }

            }

        }

        System.out.println("\nRecorrido en anchura");
        System.out.println(recorrido.substring(0, recorrido.length() - "->".length()));
    }

    public void recorridoProfundidad()
    {
        recorridoProfundidad(0);
    }

    public void recorridoProfundidad(int numeroVertice)
    {
        Deque<Vertice<E>> pila = new DequeList<>();
        List<Vertice<E>> verticesRecorridos = new ArrayList<>();
        pila.insertLast(vertices[numeroVertice]);
        verticesRecorridos.add(vertices[numeroVertice]);
        recorridoProfundidad(pila, verticesRecorridos);
    }

    private void recorridoProfundidad(Deque<Vertice<E>> pila, List<Vertice<E>> verticesRecorridos)
    {
        if (!pila.isEmpty())
        {
            Vertice<E> verticeActual = pila.removeLast();

            for (int i = 0; i < matrizAdy[verticeActual.getNumVertice()].length; i++)
                if (matrizAdy[verticeActual.getNumVertice()][i] == 1 && !verticesRecorridos.contains(vertices[i]))
                {
                    pila.insertLast(vertices[i]);
                    verticesRecorridos.add(vertices[i]);
                }

            System.out.println("Se ha recorrido el vértice: " + verticeActual.getDato());
            recorridoProfundidad(pila, verticesRecorridos);
        }
    }

    public void mostrarMatrizAdy()
    {
        for (int i = 0; i < numeroVertices; i++)
        {
            System.out.print(String.format("%15s:", vertices[i].getDato()));

            for (int j = 0; j < numeroVertices; j++)
                System.out.print(String.format("[%d]", matrizAdy[i][j]));

            System.out.println("");
        }
    }

    public boolean isEmpty()
    {
        return numeroVertices == 0;
    }

    public int getNumeroVertices()
    {
        return numeroVertices;
    }

    public void setNumeroVertices(int numeroVertices)
    {
        this.numeroVertices = numeroVertices;
    }

    public Vertice<E>[] getVertices()
    {
        return vertices;
    }

    public int[][] getMatrizAdy()
    {
        return matrizAdy;
    }

}
