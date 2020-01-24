package ada_5;

/**
 *
 * @author A15001169
 */
public class PruebaLinkedStack
{

    public static void main(String[] args)
    {
        LinkedStack<Integer> pila = new LinkedStack<>();

        pila.push(5);
        pila.push(10);
        pila.push(15);
        pila.push(30);
        pila.push(40);
        pila.push(50);

        System.out.println(pila);
        System.out.println(pila.pop());
        System.out.println(pila);

    }

}
