package arbolb;

/**
 *
 * @author Nicolás
 */
public class Test
{

    public static void main(String[] args)
    {
        ArbolB<String> arbol = new ArbolB<>(15);
        arbol.insertar("Nicolás");
        arbol.insertar("Javier");
        arbol.insertar("Xavier");
        arbol.insertar("Emmanuel");
        arbol.insertar("Eusebio");
        arbol.insertar("Carlos");
        arbol.preorder(true, null);

        System.out.println("\n\n");
        arbol.eliminar("Emmanuel");
        arbol.preorder(true, null);
    }
}
