package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HikingCarrot7
 */
public class VistaListadoUsuarios extends JDialog
{

    public VistaListadoUsuarios(Window owner)
    {
        super(owner);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new JPanel();
        jPanel4 = new JPanel();
        jPanel3 = new JPanel();
        btnAceptar = new JButton();
        panelTitulo = new JPanel();
        jScrollPane1 = new JScrollPane();
        tablaListaUsuarios = new JTable();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Listado de usuarios");
        setMinimumSize(new Dimension(450, 325));
        setPreferredSize(new Dimension(450, 325));

        jPanel1.setLayout(new GridLayout(1, 0));

        jPanel4.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(jPanel4);

        jPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnAceptar.setText("Aceptar");
        jPanel3.add(btnAceptar);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, BorderLayout.SOUTH);

        panelTitulo.setBorder(BorderFactory.createTitledBorder("Usuarios registrados"));
        panelTitulo.setLayout(new BorderLayout());

        tablaListaUsuarios.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Nombre", "Edad", "Correo electrónico"
            }
        ));
        jScrollPane1.setViewportView(tablaListaUsuarios);

        panelTitulo.add(jScrollPane1, BorderLayout.CENTER);

        getContentPane().add(panelTitulo, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnAceptar()
    {
        return btnAceptar;
    }

    public JPanel getPanelTitulo()
    {
        return panelTitulo;
    }

    public JTable getTablaListaUsuarios()
    {
        return tablaListaUsuarios;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnAceptar;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JScrollPane jScrollPane1;
    private JPanel panelTitulo;
    private JTable tablaListaUsuarios;
    // End of variables declaration//GEN-END:variables
}
