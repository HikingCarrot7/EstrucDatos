package ada_3;

/**
 * @author A15001169
 */
public class PruebaLinkedList
{

    public static void main(String[] args)
    {
        LinkedList<Integer> lista = new LinkedList<>();

        System.out.println(lista);

        lista.insertarAlInicio(1);

        System.out.println(lista);

        lista.insertarAlFinal(2);

        System.out.println(lista);

        lista.insertarAlFinal(3);

        System.out.println(lista);

        lista.insertarAlInicio(0);

        System.out.println(lista);
    }

}
