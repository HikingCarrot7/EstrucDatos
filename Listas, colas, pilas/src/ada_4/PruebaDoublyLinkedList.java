package ada_4;

import excepciones.ListaVaciaException;

public class PruebaDoublyLinkedList
{

    public static void main(String[] args)
    {
        DoublyLinkedList<Integer> lista = new DoublyLinkedList<>();

        try
        {
            System.out.println(lista);
            lista.addFirst(1);
            System.out.println(lista);
            lista.addLast(2);
            System.out.println(lista);
            lista.addLast(3);
            System.out.println(lista);
            lista.addFirst(0);
            System.out.println(lista);

        } catch (ListaVaciaException ex)
        {
            System.out.println(ex.getMessage());
        }

    }

}
