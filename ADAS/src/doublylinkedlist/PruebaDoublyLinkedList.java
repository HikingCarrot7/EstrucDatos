package doublylinkedlist;

public class PruebaDoublyLinkedList
{

    public static void main(String[] args)
    {
        DoublyLinkedList<String> lista = new DoublyLinkedList<>();

        System.out.println(lista);

        lista.addLast("Javier");
        lista.addLast("Nicolás");
        lista.addLast("Nicolás");
        lista.addLast("Nicolás");
        lista.addLast("Nicolás");
        lista.addLast("Guillermo");

        System.out.println(lista);

        lista.removeLast();

        System.out.println(lista);

        System.out.println(lista.reversed());

    }

}
