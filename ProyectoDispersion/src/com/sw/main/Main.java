package com.sw.main;

import com.sw.controller.LoginController;
import com.sw.view.Login;
import java.awt.EventQueue;

/**
 *
 * @author HikingCarrot7
 */
public class Main
{

    public static void main(String[] args)
    {
        EventQueue.invokeLater(() ->
        {
            Login login = new Login();
            login.setVisible(true);
            login.setLocationRelativeTo(null);
            LoginController loginController = new LoginController(login);
        });
    }
}
