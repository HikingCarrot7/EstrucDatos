package com.sw.controller.util;

import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author HikingCarrot7
 */
public class DialogUtils
{

    public static void showDialogAndWait(Window relativeTo, JDialog dialog)
    {
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLocationRelativeTo(relativeTo);
        dialog.setVisible(true);
    }

    public static void showDialog(Window relativeTo, JDialog dialog)
    {
        dialog.setLocationRelativeTo(relativeTo);
        dialog.setVisible(true);
    }

    public static void quitarDialog(JDialog dialog)
    {
        if (dialog != null)
            dialog.dispose();
    }

}
