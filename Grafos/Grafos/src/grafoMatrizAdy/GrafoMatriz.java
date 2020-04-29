package grafoMatrizAdy;

/**
 *
 * @author Nicolás
 */
@SuppressWarnings("unchecked")
public class GrafoMatriz<E>
{

    private int numeroVertices;
    private final Vertice<E>[] vertices;
    private final int[][] matrizAdy;

    public GrafoMatriz(int maxVertices)
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
            throw new RuntimeException("Algún vertice no existe.");

        matrizAdy[verticeInicio][verticeFinal] = 1;
        matrizAdy[verticeFinal][verticeInicio] = 1;
    }

    public boolean sonAdyacentes(E inicio, E destino) throws RuntimeException
    {
        int verticeInicio = numVertice(inicio);
        int verticeFinal = numVertice(destino);

        if (verticeInicio < 0 || verticeFinal < 0)
            throw new RuntimeException("Algún vertice no existe.");

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
