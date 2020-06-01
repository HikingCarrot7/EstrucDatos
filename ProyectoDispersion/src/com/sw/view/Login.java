package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class Login extends JFrame
{

    public Login()
    {
        initLookAndFeel();
        initComponents();
    }

    private void initLookAndFeel()
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try
        {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
                if ("Windows".equals(info.getName()))
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex)
        {
            System.out.println(ex.getMessage());
        }
        //</editor-fold>
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
        jLabel3 = new JLabel();
        jPanel1 = new JPanel();
        jPanel6 = new JPanel();
        jLabel4 = new JLabel();
        jPanel5 = new JPanel();
        btnEntrar = new JButton();
        btnNuevo = new JButton();
        jPanel2 = new JPanel();
        jLabel1 = new JLabel();
        jTextField1 = new JTextField();
        jLabel2 = new JLabel();
        jPasswordField1 = new JPasswordField();
        jPanel3 = new JPanel();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Iniciar sesión");
        setMinimumSize(new Dimension(410, 210));
        setPreferredSize(new Dimension(410, 210));

        jPanel4.setLayout(new BorderLayout());

        jLabel3.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel3.setIcon(new ImageIcon(getClass().getResource("/com/sw/img/logo.png"))); // NOI18N
        jPanel4.add(jLabel3, BorderLayout.CENTER);

        getContentPane().add(jPanel4, BorderLayout.NORTH);

        jPanel1.setLayout(new GridLayout(1, 0));

        jPanel6.setLayout(new FlowLayout(FlowLayout.LEFT));

        jLabel4.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel4.setIcon(new ImageIcon(getClass().getResource("/com/sw/img/logo_fmat.png"))); // NOI18N
        jLabel4.setText("Powered by:");
        jLabel4.setToolTipText("¿A quién se le ocurre esto?");
        jLabel4.setHorizontalTextPosition(SwingConstants.LEFT);
        jPanel6.add(jLabel4);

        jPanel1.add(jPanel6);

        jPanel5.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnEntrar.setText("Entrar");
        jPanel5.add(btnEntrar);

        btnNuevo.setText("Nuevo");
        jPanel5.add(btnNuevo);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        jPanel2.setLayout(new GridBagLayout());

        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 35, 5, 3);
        jPanel2.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(20, 0, 5, 35);
        jPanel2.add(jTextField1, gridBagConstraints);

        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Contraseña:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 3);
        jPanel2.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 0, 5, 35);
        jPanel2.add(jPasswordField1, gridBagConstraints);

        GroupLayout jPanel3Layout = new GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(jPanel3Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        jPanel2.add(jPanel3, gridBagConstraints);

        getContentPane().add(jPanel2, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnEntrar()
    {
        return btnEntrar;
    }

    public JButton getBtnNuevo()
    {
        return btnNuevo;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnEntrar;
    private JButton btnNuevo;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPasswordField jPasswordField1;
    private JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
