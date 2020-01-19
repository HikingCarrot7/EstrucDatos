package deque;

public class PruebaDequeList
{
    public static void main(String[] args)
    {
        DequeList<String> lista = new DequeList<>();

        lista.insertFirst("Javier");
        lista.removeLast();

        System.out.println(lista.reversed());

    }

}
