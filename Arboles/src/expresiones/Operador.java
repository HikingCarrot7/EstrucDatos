package expresiones;

/**
 *
 * @author Nicolás
 */
public class Operador
{

    public static final String APERTURA_PARENTESIS = "(";
    public static final String CIERRE_PARENTESIS = ")";
    public static final String SUMA = "+";
    public static final String RESTA = "-";
    public static final String MULTIPLICACION = "*";
    public static final String DIVISION = "/";
    public static final String EXPONENTE = "^";
    public static final String ESPACIO = " ";

    public static boolean esOperador(String caracter)
    {
        switch (caracter)
        {
            case SUMA:
            case RESTA:
            case MULTIPLICACION:
            case DIVISION:
            case EXPONENTE:
                return true;
        }

        return false;
    }

    public static boolean esParentesis(String caracter)
    {
        return esParentesisIzquierdo(caracter) || esParentesisDerecho(caracter);
    }

    public static boolean esParentesisIzquierdo(String caracter)
    {
        return caracter.equals(APERTURA_PARENTESIS);
    }

    public static boolean esParentesisDerecho(String caracter)
    {
        return caracter.equals(CIERRE_PARENTESIS);
    }

    public static boolean esEspacio(String caracter)
    {
        return caracter.equals(ESPACIO);
    }

}
