package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class VistaBuscarUsuario extends JDialog
{

    public VistaBuscarUsuario(Window owner)
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

        jPanel3 = new JPanel();
        jLabel2 = new JLabel();
        txtCorreo = new JTextField();
        jPanel6 = new JPanel();
        btnBuscar = new JButton();
        jPanel2 = new JPanel();
        jPanel5 = new JPanel();
        jPanel4 = new JPanel();
        btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Realizar búsqueda de un usuario");
        setMinimumSize(new Dimension(400, 150));

        jPanel3.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        jPanel3.setLayout(new GridBagLayout());

        jLabel2.setText("Correo electrónico:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 20, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(txtCorreo, gridBagConstraints);

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 51, Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(jPanel6, gridBagConstraints);

        btnBuscar.setText("Buscar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 20);
        jPanel3.add(btnBuscar, gridBagConstraints);

        getContentPane().add(jPanel3, BorderLayout.CENTER);

        jPanel2.setLayout(new GridLayout(1, 0));

        jPanel5.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel2.add(jPanel5);

        jPanel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnCancelar.setText("Cancelar");
        jPanel4.add(btnCancelar);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnBuscar()
    {
        return btnBuscar;
    }

    public JButton getBtnCancelar()
    {
        return btnCancelar;
    }

    public JTextField getTxtCorreo()
    {
        return txtCorreo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnBuscar;
    private JButton btnCancelar;
    private JLabel jLabel2;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}
