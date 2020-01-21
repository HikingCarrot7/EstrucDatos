package dequestack;

public class PruebaDequeStack
{

    public static void main(String[] args)
    {
        DequeStack<String> pila = new DequeStack<>();

        pila.push("Javier");
        pila.push("Nicolás");
        pila.push("Guillermo");

        System.out.println(pila.pop());
        System.out.println(pila.peek());

        System.out.println(pila);

        pila.pop();
        pila.pop();

        System.out.println(pila);

    }

}
