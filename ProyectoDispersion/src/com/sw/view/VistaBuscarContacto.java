package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class VistaBuscarContacto extends javax.swing.JFrame
{

    public VistaBuscarContacto()
    {
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
        jTextField1 = new JTextField();
        jPanel6 = new JPanel();
        jPanel2 = new JPanel();
        jPanel5 = new JPanel();
        jLabel1 = new JLabel();
        jPanel4 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Realizar búsqueda de un usuario");
        setMinimumSize(new Dimension(400, 150));
        setPreferredSize(new Dimension(400, 150));

        jPanel3.setBorder(BorderFactory.createTitledBorder("Búsqueda"));
        jPanel3.setLayout(new GridBagLayout());

        jLabel2.setText("Correo electrónico:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        jPanel3.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        jPanel3.add(jTextField1, gridBagConstraints);

        GroupLayout jPanel6Layout = new GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 378, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(jPanel6Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 54, Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel3.add(jPanel6, gridBagConstraints);

        getContentPane().add(jPanel3, BorderLayout.CENTER);

        jPanel2.setLayout(new GridLayout());

        jPanel5.setLayout(new FlowLayout(FlowLayout.LEFT));

        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/com/sw/img/logo_fmat.png"))); // NOI18N
        jLabel1.setText("Powered by:");
        jLabel1.setHorizontalTextPosition(SwingConstants.LEFT);
        jPanel5.add(jLabel1);

        jPanel2.add(jPanel5);

        jPanel4.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jButton1.setText("Aceptar");
        jPanel4.add(jButton1);

        jButton2.setText("Cancelar");
        jPanel4.add(jButton2);

        jPanel2.add(jPanel4);

        getContentPane().add(jPanel2, BorderLayout.SOUTH);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
