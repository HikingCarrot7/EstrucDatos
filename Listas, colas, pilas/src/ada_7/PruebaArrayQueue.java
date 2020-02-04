package ada_7;

import excepciones.ColaLlenaException;
import excepciones.ColaVaciaException;

public class PruebaArrayQueue
{

    public static void main(String[] args)
    {
        CircularArrayQueue<Integer> lista = new CircularArrayQueue<>();

        try
        {
            System.out.println(lista);
            System.out.println(lista.front());
            lista.enqueue(1);
            lista.dequeue();
            lista.enqueue(0);
            lista.enqueue(1);
            lista.enqueue(2);
            lista.enqueue(3);
            lista.enqueue(4);
            lista.enqueue(5);
            lista.enqueue(6);
            lista.enqueue(7);
            lista.enqueue(8);
            lista.enqueue(9);
            System.out.println(lista);
            System.out.println("El frente es: " + lista.front());
            lista.dequeue();
            System.out.println(lista);
            lista.enqueue(10);
            System.out.println(lista);
            lista.enqueue(11);

        } catch (ColaLlenaException | ColaVaciaException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
