package ada_2;

/**
 *
 * @author A15001169
 */
public class PruebaPila
{

    public static void main(String[] args)
    {
        Pila<String> pila = new Pila<>(10);

        pila.push("Javier");
        pila.push("Nicolás");
        pila.push("Nicolás");
        pila.push("Nicolás");
        pila.push("Nicolás");
        pila.push("Nicolás");
        pila.push("Nicolás");

        System.out.println(pila);

        pila.pop();
        pila.pop();

        System.out.println(pila);

    }

}
