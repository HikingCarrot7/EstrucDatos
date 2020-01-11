package ada_1;

import java.util.ArrayList;

/**
 *
 * @author HikingCarrot7
 */
public class ADA_1
{

    public static void main(String[] args)
    {

        ArrayList<String> lista = new ArrayList<>();

        long antes = System.currentTimeMillis();

        for (int i = 0; i < 5000000; i++)
            lista.add("F");

        long ahora = System.currentTimeMillis();

        System.out.println(ahora - antes + " ms");

    }

}
