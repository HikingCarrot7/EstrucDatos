package deque;

public class PruebaDequeList
{

    public static void main(String[] args)
    {
        DequeList<String> lista = new DequeList<>();

        lista.insertFirst("Javier");
        lista.insertLast("Nicolás");
        lista.removeFirst();

        System.out.println(lista.reversed());

    }

}
