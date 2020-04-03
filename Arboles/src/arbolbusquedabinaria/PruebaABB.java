package arbolbusquedabinaria;

/**
 *
 * @author Nicolás
 */
public class PruebaABB
{

    public static void main(String[] args)
    {
        ArbolBB<String> arbol = new ArbolBB<>();

        arbol.insertar("Nicolás");
        arbol.insertar("Javier");
        arbol.insertar("Carlos");
        arbol.insertar("Emmanuel");
        arbol.insertar("Juan");
        arbol.insertar("Eusebio");
        arbol.insertar("Arturo");
        arbol.insertar("Edwin");
        arbol.insertar("Antonio");
        arbol.insertar("Rubén");
        arbol.insertar("Gerardo");

        System.out.println("RECORRIDO EN INORDEN");
        arbol.inorder();

        System.out.println("\n\nRECORRIDO EN PREORDEN");
        arbol.preorder();
        System.out.println("\n\nRECORRIDO EN POSTORDEN");
        arbol.postorder();
        System.out.println("\n\nBuscando datos...");

        try
        {
            arbol.buscar("Mario");

        } catch (ItemNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        String eliminado = "Nicolás";
        System.out.println("\nEliminando el elemento: " + eliminado);

        try
        {
            arbol.eliminar(eliminado);
            arbol.inorder();

        } catch (ItemNotFoundException e)
        {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n\n");

    }

}
