package ada_3;

/**
 * @author A15001169
 */
public class PruebaLinkedList
{

    public static void main(String[] args)
    {
        LinkedList<String> lista1 = new LinkedList<>();
        LinkedList<String> lista2 = new LinkedList<>();
        lista1.addFirst("Javier");
        lista1.addFirst("Rene");
        lista1.addFirst("Xavier");

        lista2.addFirst("Eusebio");
        lista2.addFirst("Guillermo");
        lista2.addFirst("Nicolás");

        LinkedList<String> lista3 = LinkedList.mergeLists(lista1, lista2);

        System.out.println(lista3);

    }

}
