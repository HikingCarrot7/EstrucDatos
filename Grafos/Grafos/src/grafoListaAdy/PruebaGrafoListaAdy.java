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
        grafo.nuevoVertice("Jimmy");
        grafo.nuevoVertice("Rafael");
        grafo.nuevoVertice("Charly");
        grafo.nuevoVertice("René");
        grafo.nuevoVertice("Axel");
        grafo.nuevoVertice("Alejandra");

        grafo.nuevoArco("Nicolás", "Jimmy");
        grafo.nuevoArco("Jimmy", "Alejandra");
        grafo.nuevoArco("Jimmy", "Axel");
        grafo.nuevoArco("Jimmy", "René");
        grafo.nuevoArco("Axel", "Nicolás");
        grafo.nuevoArco("Axel", "Charly");
        grafo.nuevoArco("Axel", "Rafael");

        grafo.recorridoAnchura();
        System.out.println("\n\n");
        grafo.recorridoProfundidad();

    }

}
