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
        pila.push("Guillermo");
        pila.push("Guillermo");
        pila.push("Guillermo");

        System.out.println(pila);

        System.out.println(pila);

    }

}
