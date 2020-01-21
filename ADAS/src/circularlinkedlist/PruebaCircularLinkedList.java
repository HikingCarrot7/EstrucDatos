package circularlinkedlist;

/**
 *
 * @author HikingC7
 */
public class PruebaCircularLinkedList
{

    public static void main(String[] args)
    {
        CircularLinkedList<String> lista = new CircularLinkedList<>();

        lista.addFirst("Nicolás");
        lista.addLast("Javier");
        lista.addFirst("Eusebio");
        lista.addLast("Guillermo");

        System.out.println(lista);

        lista.removeLast();

        System.out.println(lista);
        lista.removeFirst();

        System.out.println(lista);

        lista.removeFirst();
        lista.removeLast();

        System.out.println(lista);
    }

}
