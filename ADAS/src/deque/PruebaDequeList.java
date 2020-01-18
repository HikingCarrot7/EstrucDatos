package deque;

public class PruebaDequeList
{
    public static void main(String[] args)
    {
        DequeList<String> lista = new DequeList<>();

        lista.insertLast("Nicolás");
        lista.insertLast("Eusebio");
        lista.insertFirst("Javier");

        System.out.println(lista);

        System.out.println(lista.reversed());

        lista.removeFirst();
        lista.removeLast();

        System.out.println(lista.reversed());

    }

}
