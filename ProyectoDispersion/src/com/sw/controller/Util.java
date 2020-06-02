package com.sw.controller;

import java.awt.Dialog;
import java.awt.Window;
import javax.swing.JDialog;

/**
 *
 * @author HikingCarrot7
 */
public class Util
{

    public static void showDialogAndWait(Window relativeTo, JDialog dialog)
    {
        dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        dialog.setLocationRelativeTo(relativeTo);
        dialog.setVisible(true);
    }

}
