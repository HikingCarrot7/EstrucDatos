package grafoListaAdy;

/**
 *
 * @author Nicolás
 */
public class PruebaGrafoListaAdy
{

    public static void main(String[] args)
    {
        GrafoListaAdy<String> grafo = new GrafoListaAdy<>(10);

        grafo.nuevoVertice("Nicolás");
        grafo.nuevoVertice("Carlos");
        grafo.nuevoVertice("Javier");
        grafo.nuevoVertice("Antonio");
        grafo.nuevoVertice("Lucas");
        grafo.nuevoVertice("Emmanuel");

        grafo.nuevoArco("Nicolás", "Carlos");
        grafo.nuevoArco("Nicolás", "Emmanuel");
        grafo.nuevoArco("Carlos", "Lucas");
        grafo.recorridoAnchura();
    }

}
