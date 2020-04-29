package grafoMatrizAdy;

/**
 *
 * @author Nicolás
 */
public class PruebaGrafo
{

    public static void main(String[] args)
    {
        GrafoMatriz<String> grafo = new GrafoMatriz<>(5);

        grafo.nuevoVertice("Nicolás");
        grafo.nuevoVertice("Antonio");
        grafo.nuevoVertice("Javier");
        grafo.nuevoVertice("Carlos");

        grafo.nuevoArco("Nicolás", "Antonio");
        grafo.nuevoArco("Javier", "Carlos");
        grafo.nuevoArco("Carlos", "Nicolás");

        grafo.mostrarMatrizAdy();
    }

}
