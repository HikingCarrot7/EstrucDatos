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
        lista.addFirst("Eusebio");
        lista.addFirst("Xavier");
        lista.addLast("Javier");
        lista.addLast("Guillermo");

        System.out.println(lista);
        lista.removeLast();
        System.out.println(lista);
        lista.removeFirst();
        System.out.println(lista);
        lista.removeFirst();
        System.out.println(lista);
    }

}
