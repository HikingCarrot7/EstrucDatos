package doublycircularlinkedlist;

/**
 *
 * @author HikingCarrot7
 */
public class PruebaDoublyCircularLinkedList
{

    public static void main(String[] args)
    {
        DoublyCircularLinkedList<String> lista = new DoublyCircularLinkedList<>();
        lista.addLast("Nicolás");
        lista.addLast("Javier");
        lista.addLast("Guillermo");
        lista.addFirst("Edwin");
        lista.addFirst("Edwin");
        lista.addFirst("Edwin");
        lista.addLast("Rafa");

        System.out.println(lista);

        lista.removeLast();

        System.out.println(lista);

    }

}
