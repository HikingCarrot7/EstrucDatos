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

        grafo.nuevoArco("Antonio", "Carlos");
        grafo.nuevoArco("Nicolás", "Carlos");
        grafo.nuevoArco("Carlos", "Javier");
        grafo.nuevoArco("Carlos", "Antonio");
        grafo.nuevoArco("Antonio", "Emmanuel");
        grafo.nuevoArco("Antonio", "Lucas");
        grafo.nuevoArco("Javier", "Emmanuel");
        grafo.mostrarListaAdy();
    }

}
