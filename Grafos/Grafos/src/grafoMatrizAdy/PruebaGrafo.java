package grafoMatrizAdy;

/**
 *
 * @author Nicolás
 */
public class PruebaGrafo
{

    public static void main(String[] args)
    {
        GrafoMatrizAdy<String> grafo = new GrafoMatrizAdy<>(10);

        grafo.nuevoVertice("Nicolás");
        grafo.nuevoVertice("Antonio");
        grafo.nuevoVertice("Javier");
        grafo.nuevoVertice("Carlos");

        grafo.nuevoArco("Nicolás", "Antonio");
        grafo.nuevoArco("Nicolás", "Carlos");
        grafo.nuevoArco("Carlos", "Antonio");
        grafo.nuevoArco("Carlos", "Javier");

        grafo.mostrarMatrizAdy();

        grafo.recorridoAnchura();
    }

}
