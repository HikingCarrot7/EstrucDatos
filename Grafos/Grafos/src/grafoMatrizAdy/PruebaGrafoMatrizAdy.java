package grafoMatrizAdy;

/**
 *
 * @author Nicolás
 */
public class PruebaGrafoMatrizAdy
{

    public static void main(String[] args)
    {
        GrafoMatrizAdy<String> grafo = new GrafoMatrizAdy<>(10);

        grafo.nuevoVertice("Nicolás");
        grafo.nuevoVertice("Antonio");
        grafo.nuevoVertice("Javier");
        grafo.nuevoVertice("Carlos");
        grafo.nuevoVertice("Juan");
        grafo.nuevoVertice("Eusebio");
        grafo.nuevoVertice("Emmanuel");

        grafo.nuevoArco("Nicolás", "Antonio");
        grafo.nuevoArco("Nicolás", "Carlos");
        grafo.nuevoArco("Carlos", "Antonio");
        grafo.nuevoArco("Carlos", "Javier");
        grafo.nuevoArco("Nicolás", "Juan");
        grafo.nuevoArco("Juan", "Emmanuel");
        grafo.nuevoArco("Eusebio", "Emmanuel");

        System.out.println("Recorrido en anchura");
        grafo.recorridoAnchura();

        System.out.println("\n\nRecorrido en profundidad");
        grafo.recorridoProfundidad();

        System.out.println("\nMatriz de adyacencia");
        grafo.mostrarMatrizAdy();
    }

}
