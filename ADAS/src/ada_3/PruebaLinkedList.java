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

        lista.insertarAlInicio(5);

        System.out.println(lista);

        lista.insertarAlInicio(10);

        System.out.println(lista);

        lista.insertarAlInicio(15);

        System.out.println(lista);

        lista.insertarAlFinal(20);

        System.out.println(lista);

        lista.insertarAlInicio(30);

        System.out.println(lista);

        lista.removeLast();

        System.out.println(lista);

        lista.removeFirst();

        System.out.println(lista);

        lista.removeLast();

        System.out.println(lista);
        System.out.println(lista.size());

    }

}
