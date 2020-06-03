package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class VistaDatosUsuario extends JDialog
{

    public VistaDatosUsuario(Window owner)
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
        GridBagConstraints gridBagConstraints;

        jPanel4 = new JPanel();
        jPanel6 = new JPanel();
        lblGenero = new JLabel();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        lblNombre = new JLabel();
        jLabel3 = new JLabel();
        lblEdad = new JLabel();
        jLabel5 = new JLabel();
        lblCorreo = new JLabel();
        jPanel1 = new JPanel();
        jPanel5 = new JPanel();
        panelBotones = new JPanel();
        btnAgregar = new JButton();
        btnAgregarContactos = new JButton();
        btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Datos encontrados del usuario");
        setMinimumSize(new Dimension(540, 300));
        setPreferredSize(new Dimension(540, 300));

        jPanel4.setBorder(BorderFactory.createTitledBorder("Datos de usuario"));
        jPanel4.setLayout(new GridBagLayout());

        jPanel6.setLayout(new BorderLayout());
        jPanel6.add(lblGenero, BorderLayout.LINE_END);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jPanel6, gridBagConstraints);

        jPanel2.setLayout(new GridBagLayout());

        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 35, 10, 5);
        jPanel2.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(20, 5, 10, 35);
        jPanel2.add(lblNombre, gridBagConstraints);

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Edad:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 35, 10, 5);
        jPanel2.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(10, 5, 10, 35);
        jPanel2.add(lblEdad, gridBagConstraints);

        jLabel5.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel5.setText("Correo electrónico:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(10, 35, 10, 5);
        jPanel2.add(jLabel5, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(10, 5, 10, 35);
        jPanel2.add(lblCorreo, gridBagConstraints);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        jPanel4.add(jPanel2, gridBagConstraints);

        getContentPane().add(jPanel4, BorderLayout.CENTER);

        jPanel1.setLayout(new GridBagLayout());
        jPanel1.add(jPanel5, new GridBagConstraints());

        panelBotones.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnAgregar.setText("Agregar");
        panelBotones.add(btnAgregar);

        btnAgregarContactos.setText("Agregar contactos");
        panelBotones.add(btnAgregarContactos);

        btnCancelar.setText("Cancelar");
        panelBotones.add(btnCancelar);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        jPanel1.add(panelBotones, gridBagConstraints);

        getContentPane().add(jPanel1, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnAgregar()
    {
        return btnAgregar;
    }

    public JButton getBtnAgregarContactos()
    {
        return btnAgregarContactos;
    }

    public JButton getBtnCancelar()
    {
        return btnCancelar;
    }

    public JLabel getLblCorreo()
    {
        return lblCorreo;
    }

    public JLabel getLblEdad()
    {
        return lblEdad;
    }

    public JLabel getLblNombre()
    {
        return lblNombre;
    }

    public JPanel getPanelBotones()
    {
        return panelBotones;
    }

    public JLabel getLblGenero()
    {
        return lblGenero;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnAgregar;
    private JButton btnAgregarContactos;
    private JButton btnCancelar;
    private JLabel jLabel1;
    private JLabel jLabel3;
    private JLabel jLabel5;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JLabel lblCorreo;
    private JLabel lblEdad;
    private JLabel lblGenero;
    private JLabel lblNombre;
    private JPanel panelBotones;
    // End of variables declaration//GEN-END:variables
}
