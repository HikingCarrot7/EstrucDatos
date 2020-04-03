package expresiones;

import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Nicolás
 */
public class PruebaConversor
{

    public static void main(String[] args)
    {
        String test = "(6+4)*8*(7+4)";
        System.out.println(Conversor.deInfijoAPrefijo(test));

        HashMap<String, String> map = new HashMap<>();
        JFrame j = new JFrame();
        JLabel b = new JLabel("hola ");
        j.getContentPane().add(b);
        j.pack();

        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setVisible(true);

    }
}
