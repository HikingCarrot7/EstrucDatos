package funcionmultiplicacion;

import java.util.Scanner;

/**
 *
 * @author HikingCarrot7
 */
public class PruebaDispersionHash
{

    public static void main(String[] args)
    {
        String clave;
        long valor;
        Scanner in = new Scanner(System.in);
        DispersionHash dispersionHash = new DispersionHash();

        for (int i = 0; i < 10; i++)
        {
            System.out.print("\nClave a dispersar: ");
            clave = in.nextLine();
            valor = dispersionHash.transformaClave(clave);
            valor = dispersionHash.dispersion(valor);
            System.out.println(String.format("Dispersión de la clave %s es: %d", clave, valor));
        }

    }

}
