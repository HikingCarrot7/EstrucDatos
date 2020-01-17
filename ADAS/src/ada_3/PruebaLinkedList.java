package ada_3;

/**
 *
 * @author A15001169
 */
public class PruebaLinkedList
{

    public static void main(String[] args)
    {

        LinkedList<Integer> lista = new LinkedList<>();

        System.out.println(lista);

        lista.insertarInicio(5);

        System.out.println(lista);

        lista.insertarInicio(10);

        System.out.println(lista);

        lista.insertarInicio(15);

        System.out.println(lista);

        lista.insertarAlFinal(20);

        System.out.println(lista);

    }

}
