package ada_2;

/**
 *
 * @author A15001169
 */
public class PruebaPila
{

    public static void main(String[] args)
    {

        try
        {

            System.out.println(new PruebaPila().estaBalanceado(""));

        } catch (ExpresionNoEvaluableException ex)
        {
            System.out.println(ex.getMessage());;
        }

    }

    public boolean estaBalanceado(String cadena)
    {

        int contador = 0;
        String[] parentesis = cadena.split("");

        if (parentesis.length > 10)
            throw new ExpresionNoEvaluableException();

        Pila<String> pila = new Pila(cadena.length());

        while (contador < parentesis.length)
        {

            try
            {

                if (parentesis[contador].equals("("))
                    pila.push("(");
                else if (parentesis[contador].equals(")"))
                    pila.pop();

            } catch (PilaVaciaException ex)
            {
                return false;
            }

            contador++;

        }

        return pila.isEmpty();

    }

}
