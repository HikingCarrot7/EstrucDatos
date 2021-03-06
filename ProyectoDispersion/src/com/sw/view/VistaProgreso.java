package com.sw.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class VistaProgreso extends JDialog
{

    public VistaProgreso(Window owner)
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

        jLabel1 = new JLabel();
        jProgressBar1 = new JProgressBar();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(300, 95));
        setUndecorated(true);
        setPreferredSize(new Dimension(300, 95));
        getContentPane().setLayout(new GridBagLayout());

        jLabel1.setFont(new Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel1.setText("Procesando...");
        jLabel1.setAlignmentX(0.5F);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        getContentPane().add(jLabel1, gridBagConstraints);

        jProgressBar1.setIndeterminate(true);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 20, 5, 20);
        getContentPane().add(jProgressBar1, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JLabel jLabel1;
    private JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables
}
