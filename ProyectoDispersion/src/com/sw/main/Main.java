package com.sw.main;

import com.sw.controller.util.Alerta;
import com.sw.controller.LauncherManager;
import com.sw.controller.LoginController;
import com.sw.model.exceptions.InicializacionIncorrectaException;
import com.sw.view.Login;
import java.awt.EventQueue;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author HikingCarrot7
 */
public class Main
{

    public static void main(String[] args)
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Revisar archivos del sistema. ">
        try
        {
            LauncherManager.revisarArchivosSistema();

        } catch (InicializacionIncorrectaException e)
        {
            Alerta.mostrarError(null,
                    "Hubieron errores al tratar de leer los archivos principales del sistema. Todos los datos fueron restaurados.");
        }
        //</editor-fold>

        EventQueue.invokeLater(() ->
        {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            LoginController loginController = new LoginController(login);
        });
    }
}
