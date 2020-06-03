package com.sw.controller;

import java.awt.Toolkit;
import java.text.ParseException;
import javax.swing.JFormattedTextField;

/**
 *
 * @author HikingCarrot7
 */
public class IntegerFormatter extends JFormattedTextField.AbstractFormatterFactory
{

    @Override
    public JFormattedTextField.AbstractFormatter getFormatter(JFormattedTextField tf)
    {
        return new JFormattedTextField.AbstractFormatter()
        {
            @Override
            public Object stringToValue(String text) throws ParseException
            {
                try
                {
                    double edad = Integer.parseInt(text);

                    if (edad < 0 || edad > 200)
                        throw new ParseException(text, 0);

                    return edad;

                } catch (NumberFormatException e)
                {
                    Toolkit.getDefaultToolkit().beep();
                    throw new ParseException(text, 0);
                }
            }

            @Override
            public String valueToString(Object value) throws ParseException
            {
                return String.valueOf(value == null ? "" : (int) Double.parseDouble(value.toString()));
            }
        };
    }
}
