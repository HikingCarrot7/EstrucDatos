package aeropuerto;

import deque.DequeList;
import java.awt.*;
import java.awt.geom.GeneralPath;
import javax.swing.*;

/**
 * @author HikingCarrot7
 */
public final class Esquema extends JPanel
{

    private final int ESQUEMA_WIDTH = 550;
    private final int ESQUEMA_HEIGHT = 550;

    private final int OFFSET_X = 40;
    private final int OFFSET_Y = 25;

    private final int FLY_TO_FLY = 30;
    private final int LADO_TRIANGULO = 7;
    private final int RECT_WIDTH = 40;
    private final int RECT_HEIGHT = 20;

    private final int MAX_VUELOS_POR_LINEA = (ESQUEMA_WIDTH - 100) / (FLY_TO_FLY + RECT_WIDTH);
    private final int SEPARACION_POR_LINEA = 30;

    private Aeropuerto aeropuerto;

    public Esquema(Aeropuerto aeropuerto)
    {
        this.aeropuerto = aeropuerto;
        setBackground(Color.WHITE);
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        g.setColor(Color.BLACK);
        Graphics2D g2d = (Graphics2D) g;
        dibujarVuelosDisponibles(g2d, g2d.getFontMetrics());

        g.dispose();
        g2d.dispose();
    }

    private void dibujarVuelosDisponibles(Graphics2D g, FontMetrics fm)
    {

        DequeList<Vuelo> vuelos = aeropuerto.obtenerVuelos();
        int y = OFFSET_Y;

        for (int i = 0, x = OFFSET_X; i < aeropuerto.vuelosDisponibles(); i++, x += FLY_TO_FLY + RECT_WIDTH)
        {
            String claveVuelo = String.valueOf(vuelos.removeFirst().getClave());
            drawVuelo(g, fm, x, y, i, claveVuelo);

            if (aeropuerto.vuelosDisponibles() - (i + 1) > 0)
                if ((i + 1) % MAX_VUELOS_POR_LINEA == 0)
                {
                    drawLargeLine(g, x + RECT_WIDTH, y + RECT_HEIGHT / 2);
                    y += SEPARACION_POR_LINEA + RECT_HEIGHT;
                    x = OFFSET_X - (FLY_TO_FLY + RECT_WIDTH);

                } else
                {
                    g.drawLine(x + RECT_WIDTH, y + RECT_HEIGHT / 2, x + RECT_WIDTH + FLY_TO_FLY, y + RECT_HEIGHT / 2);
                    drawTriangle(g, x + RECT_WIDTH + FLY_TO_FLY, y + RECT_HEIGHT / 2, LADO_TRIANGULO);
                }

        }

    }

    private void drawVuelo(Graphics2D g, FontMetrics fm, int x, int y, int indexVuelo, String claveVuelo)
    {
        Rectangle textBounds = fm.getStringBounds(claveVuelo, g).getBounds();
        Rectangle posicionBounds = fm.getStringBounds(String.valueOf((indexVuelo + 1)), g).getBounds();

        g.drawString(String.valueOf(indexVuelo + 1), x + (RECT_WIDTH - posicionBounds.width) / 2, y - 1);
        g.drawString(claveVuelo, x + (RECT_WIDTH - textBounds.width) / 2, y + (RECT_HEIGHT + fm.getAscent()) / 2);

        g.drawRect(x, y, RECT_WIDTH, RECT_HEIGHT);
    }

    private void drawTriangle(Graphics2D g, int x, int y, int lenght)
    {
        int offset = (int) Math.sqrt(Math.pow(lenght, 2) - Math.pow((lenght / 2), 2));

        int[] puntosX =
        {
            x - offset, x, x - offset
        };

        int[] puntosY =
        {
            y - offset, y, y + offset
        };

        GeneralPath triangle = new GeneralPath();

        triangle.moveTo(puntosX[0], puntosY[0]);

        for (int i = 0; i < puntosX.length; i++)
            triangle.lineTo(puntosX[i], puntosY[i]);

        triangle.closePath();
        g.fill(triangle);
    }

    private void drawLargeLine(Graphics2D g, int x, int y)
    {
        g.drawLine(x, y, x + FLY_TO_FLY, y);
        g.drawLine(x + FLY_TO_FLY, y, x + FLY_TO_FLY, y + RECT_HEIGHT);
        g.drawLine(x + FLY_TO_FLY, y + RECT_HEIGHT, OFFSET_X - OFFSET_X / 2, y + RECT_HEIGHT);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + RECT_HEIGHT, OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + RECT_HEIGHT);
        g.drawLine(OFFSET_X - OFFSET_X / 2, y + SEPARACION_POR_LINEA + RECT_HEIGHT, OFFSET_X, y + SEPARACION_POR_LINEA + RECT_HEIGHT);
        drawTriangle(g, OFFSET_X, y + SEPARACION_POR_LINEA + RECT_HEIGHT, LADO_TRIANGULO);
    }

}
