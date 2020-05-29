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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class VistaSelecRuta extends JFrame
{

    public VistaSelecRuta()
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

        jPanel1 = new JPanel();
        jPanel5 = new JPanel();
        jLabel2 = new JLabel();
        jPanel3 = new JPanel();
        jButton1 = new JButton();
        jButton2 = new JButton();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jButton3 = new JButton();
        jPanel4 = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Seleccionar la ruta para almacenar el contacto");
        setMinimumSize(new Dimension(450, 160));
        setPreferredSize(new Dimension(450, 160));

        jPanel1.setLayout(new GridLayout(1, 0));

        jPanel5.setLayout(new FlowLayout(FlowLayout.LEFT));

        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2.setIcon(new ImageIcon(getClass().getResource("/com/sw/img/logo_fmat.png"))); // NOI18N
        jLabel2.setText("Powered by:");
        jLabel2.setToolTipText("alt+f4");
        jLabel2.setHorizontalTextPosition(SwingConstants.LEFT);
        jPanel5.add(jLabel2);

        jPanel1.add(jPanel5);

        jPanel3.setLayout(new FlowLayout(FlowLayout.RIGHT));

        jButton1.setText("Aceptar");
        jPanel3.add(jButton1);

        jButton2.setText("Cancelar");
        jPanel3.add(jButton2);

        jPanel1.add(jPanel3);

        getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        jPanel2.setBorder(BorderFactory.createTitledBorder("Seleccionar ruta..."));
        jPanel2.setLayout(new GridBagLayout());

        jLabel1.setText("Ruta:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        jPanel2.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel2.add(jTextField1, gridBagConstraints);

        jButton3.setText("Seleccionar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        jPanel2.add(jButton3, gridBagConstraints);

        GroupLayout jPanel4Layout = new GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jPanel4, gridBagConstraints);

        getContentPane().add(jPanel2, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton jButton1;
    private JButton jButton2;
    private JButton jButton3;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
