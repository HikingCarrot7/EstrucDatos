package com.sw.controller;

import java.awt.Toolkit;
import java.text.ParseException;
import javax.swing.JFormattedTextField;

/**
 *
 * @author Nicolás
 */
public class PromedioFormatter extends JFormattedTextField.AbstractFormatterFactory
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
                    double promedio = Double.parseDouble(text);

                    if (promedio < 0 || promedio > 100)
                        throw new ParseException(text, 0);

                    return promedio;

                } catch (NumberFormatException e)
                {
                    Toolkit.getDefaultToolkit().beep();
                    throw new ParseException(text, 0);
                }
            }

            @Override
            public String valueToString(Object value) throws ParseException
            {
                return String.valueOf(value == null ? "" : value.toString());
            }
        };
    }

}
