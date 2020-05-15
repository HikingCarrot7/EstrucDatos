package com.sw.controller;

import java.awt.Toolkit;
import java.text.ParseException;
import javafx.util.converter.NumberStringConverter;

/**
 *
 * @author Nicolás
 */
public class FormatterPromedio extends NumberStringConverter
{

    @Override
    public Number fromString(String value)
    {
        if (value.equals(""))
            return null;

        try
        {
            double number = Double.parseDouble(value);

            if (number < 0 || number > 100)
                throw new ParseException(value, 0);

            return number;

        } catch (NumberFormatException | ParseException e)
        {
            Toolkit.getDefaultToolkit().beep();
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString(Number value)
    {
        return value == null ? "" : String.valueOf(Double.parseDouble(String.valueOf(value.doubleValue())));
    }
}
