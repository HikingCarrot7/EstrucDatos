package ada_6;

public class PruebaArrayQueue
{

    public static void main(String[] args)
    {
        ArrayQueue<String> lista = new ArrayQueue<>();

        lista.enqueue("Nicolás");
        lista.enqueue("Javier");
        lista.enqueue("Guillermo");

        System.out.println(lista);

        lista.dequeue();
        lista.dequeue();

        System.out.println(lista);

        lista.enqueue("Eusebio");
        lista.enqueue("Emmanuel");
        lista.enqueue("Carlos");

        System.out.println(lista);

        lista.enqueue("Charly");
        lista.enqueue("Charly");
        lista.enqueue("Charly");
        lista.enqueue("Charly");

        System.out.println(lista);

        lista.enqueue("Cambranes");
        lista.enqueue("Edwin");

        System.out.println(lista);

        lista.dequeue();
        lista.dequeue();
        lista.dequeue();
        lista.dequeue();
        lista.dequeue();
        lista.dequeue();
        lista.dequeue();
        lista.dequeue();

        System.out.println(lista);

    }

}
