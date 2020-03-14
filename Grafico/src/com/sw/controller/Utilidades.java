package com.sw.controller;

import com.sun.javafx.tk.Toolkit;
import javafx.scene.text.Font;

/**
 *
 * @author HikingCarrot7
 */
public class Utilidades
{

    public static double getFontWidth(String text, Font font)
    {
        return Toolkit.getToolkit().getFontLoader().computeStringWidth(String.valueOf(text), font);
    }

    public static double getFontHeight(Font font)
    {
        return Toolkit.getToolkit().getFontLoader().getFontMetrics(font).getLineHeight();
    }

}
