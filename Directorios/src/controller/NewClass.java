package controller;

import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.SwingWorker;

/**
 *
 * @author nicol
 */
public class NewClass extends SwingWorker<String, String>

{

    @Override
    protected String doInBackground() throws Exception
    {
        setProgress(5);
        publish("Nicolás", "Eusebio", "Carlos", "Emmanuel");
        return "SonBear";
    }

    @Override
    protected void done()
    {
        try
        {
            super.done(); //To change body of generated methods, choose Tools | Templates.
            System.out.println(get());

        } catch (InterruptedException | ExecutionException ex)
        {
            System.out.println(ex.getMessage());

        }
    }

    @Override
    protected void process(List<String> chunks)
    {
        System.out.println(chunks);
        super.process(chunks); //To change body of generated methods, choose Tools | Templates.
    }

}

class Test
{

    public static void main(String[] args)
    {
        NewClass c = new NewClass();

        c.addPropertyChangeListener(e ->
        {
            if (e.getPropertyName().equals("progress"))
                System.out.println(e.getNewValue());
        });

        Thread t = new Thread(c);
        t.start();
    }
}
