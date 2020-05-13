package com.sw.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Icon;

/**
 *
 * @author Nicolás
 */
public final class AccionEmergente extends AbstractAction
{

    private final ActionListener action;

    public AccionEmergente(String title, Icon icon, ActionListener action)
    {
        this.action = action;
        putValue(NAME, title);
        putValue(SMALL_ICON, icon);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        action.actionPerformed(e);
    }

}
