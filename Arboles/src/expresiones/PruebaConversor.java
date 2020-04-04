package expresiones;

/**
 *
 * @author Nicolás
 */
public class PruebaConversor
{

    public static void main(String[] args)
    {

        String ejemplo4 = "19 - 0 / 5 *10";
        System.out.println("Entrada: " + ejemplo4);
        ArbolExpresiones arbolExpresiones = new ArbolExpresiones(ejemplo4);

        System.out.println("\nRecorrido en PreOrden");
        arbolExpresiones.preorder();

        System.out.println("\nRecorrido en Inorden");
        arbolExpresiones.inorder();

        System.out.println("\nRecorrido en PostOrden");
        arbolExpresiones.postorder();

        System.out.println("\n");

    }

}
