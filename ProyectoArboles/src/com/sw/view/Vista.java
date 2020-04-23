package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Nicolás
 */
public class Vista extends JFrame
{

    public Vista()
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
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {
        GridBagConstraints gridBagConstraints;

        grupoRadioButtons = new ButtonGroup();
        jPanel1 = new JPanel();
        jLabel1 = new JLabel();
        txtDireccion = new JTextField();
        btnBuscarDirectorio = new JButton();
        panelSuperior = new JPanel();
        rbArbolBB = new JRadioButton();
        filler2 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        rbArbolAVL = new JRadioButton();
        filler3 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        rbArbolB = new JRadioButton();
        filler1 = new Box.Filler(new Dimension(20, 0), new Dimension(20, 0), new Dimension(20, 32767));
        btnGenerar = new JButton();
        jSplitPane1 = new JSplitPane();
        panelLateralIzq = new JPanel();
        jPanel3 = new JPanel();
        chbNombre = new JCheckBox();
        txtNombre = new JTextField();
        chbPromedio = new JCheckBox();
        chbProfesion = new JCheckBox();
        cmbProfesiones = new JComboBox<>();
        txtPromedio = new JFormattedTextField();
        jPanel5 = new JPanel();
        btnBuscar = new JButton();
        jPanel6 = new JPanel();
        jLabel2 = new JLabel();
        jLabel3 = new JLabel();
        jLabel4 = new JLabel();
        tiempoTranscurrido = new JLabel();
        panelLateralDer = new JPanel();
        progressBar = new JProgressBar();
        jPanel2 = new JPanel();
        jScrollPane1 = new JScrollPane();
        tablaEgresados = new JTable();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(600, 450));
        setPreferredSize(new Dimension(600, 450));

        jPanel1.setLayout(new GridBagLayout());

        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setText("Dirección:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(txtDireccion, gridBagConstraints);

        btnBuscarDirectorio.setText("Buscar directorio");
        btnBuscarDirectorio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(btnBuscarDirectorio, gridBagConstraints);

        panelSuperior.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 5));

        grupoRadioButtons.add(rbArbolBB);
        rbArbolBB.setSelected(true);
        rbArbolBB.setText("Árbol BB");
        rbArbolBB.setActionCommand("Arbol BB");
        rbArbolBB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSuperior.add(rbArbolBB);
        panelSuperior.add(filler2);

        grupoRadioButtons.add(rbArbolAVL);
        rbArbolAVL.setText("Árbol AVL");
        rbArbolAVL.setActionCommand("Arbol AVL");
        rbArbolAVL.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSuperior.add(rbArbolAVL);
        panelSuperior.add(filler3);

        grupoRadioButtons.add(rbArbolB);
        rbArbolB.setText("Árbol B");
        rbArbolB.setActionCommand("Arbol B");
        rbArbolB.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSuperior.add(rbArbolB);
        panelSuperior.add(filler1);

        btnGenerar.setText("Generar");
        btnGenerar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panelSuperior.add(btnGenerar);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.weighty = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        jPanel1.add(panelSuperior, gridBagConstraints);

        getContentPane().add(jPanel1, BorderLayout.PAGE_START);

        jSplitPane1.setDividerLocation(230);
        jSplitPane1.setDividerSize(6);
        jSplitPane1.setContinuousLayout(true);
        jSplitPane1.setOneTouchExpandable(true);

        panelLateralIzq.setEnabled(false);
        panelLateralIzq.setLayout(new BorderLayout());

        jPanel3.setLayout(new GridBagLayout());

        chbNombre.setText("Nombre:");
        chbNombre.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chbNombre.setHorizontalAlignment(SwingConstants.LEFT);
        chbNombre.setHorizontalTextPosition(SwingConstants.RIGHT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel3.add(chbNombre, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel3.add(txtNombre, gridBagConstraints);

        chbPromedio.setText("Promedio:");
        chbPromedio.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chbPromedio.setHorizontalAlignment(SwingConstants.LEFT);
        chbPromedio.setHorizontalTextPosition(SwingConstants.RIGHT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel3.add(chbPromedio, gridBagConstraints);

        chbProfesion.setText("Profesión:");
        chbProfesion.setCursor(new Cursor(Cursor.HAND_CURSOR));
        chbProfesion.setHorizontalAlignment(SwingConstants.LEFT);
        chbProfesion.setHorizontalTextPosition(SwingConstants.RIGHT);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel3.add(chbProfesion, gridBagConstraints);

        cmbProfesiones.setCursor(new Cursor(Cursor.HAND_CURSOR));
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel3.add(cmbProfesiones, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel3.add(txtPromedio, gridBagConstraints);

        panelLateralIzq.add(jPanel3, BorderLayout.NORTH);

        jPanel5.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 15));

        btnBuscar.setText("Buscar");
        btnBuscar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jPanel5.add(btnBuscar);

        panelLateralIzq.add(jPanel5, BorderLayout.CENTER);

        jPanel6.setLayout(new GridBagLayout());

        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Comparaciones:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel6.add(jLabel2, gridBagConstraints);

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Tiempo transcurrido:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel6.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel6.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(3, 3, 3, 3);
        jPanel6.add(tiempoTranscurrido, gridBagConstraints);

        panelLateralIzq.add(jPanel6, BorderLayout.SOUTH);

        jSplitPane1.setLeftComponent(panelLateralIzq);

        panelLateralDer.setLayout(new BorderLayout());

        progressBar.setIndeterminate(true);
        panelLateralDer.add(progressBar, BorderLayout.SOUTH);

        jPanel2.setBorder(BorderFactory.createTitledBorder("Resultados"));
        jPanel2.setLayout(new BorderLayout());

        tablaEgresados.setModel(new DefaultTableModel(
            new Object [][]
            {

            },
            new String []
            {
                "Nombre", "Profesión", "Promedio"
            }
        )
        {
            @Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        });
        jScrollPane1.setViewportView(tablaEgresados);

        jPanel2.add(jScrollPane1, BorderLayout.CENTER);

        panelLateralDer.add(jPanel2, BorderLayout.CENTER);

        jSplitPane1.setRightComponent(panelLateralDer);

        getContentPane().add(jSplitPane1, BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnBuscar()
    {
        return btnBuscar;
    }

    public JButton getBtnBuscarDirectorio()
    {
        return btnBuscarDirectorio;
    }

    public JButton getBtnGenerar()
    {
        return btnGenerar;
    }

    public JCheckBox getChbNombre()
    {
        return chbNombre;
    }

    public JCheckBox getChbProfesion()
    {
        return chbProfesion;
    }

    public JCheckBox getChbPromedio()
    {
        return chbPromedio;
    }

    public JComboBox<String> getCmbProfesiones()
    {
        return cmbProfesiones;
    }

    public JProgressBar getProgressBar()
    {
        return progressBar;
    }

    public JRadioButton getRbArbolAVL()
    {
        return rbArbolAVL;
    }

    public JRadioButton getRbArbolB()
    {
        return rbArbolB;
    }

    public JRadioButton getRbArbolBB()
    {
        return rbArbolBB;
    }

    public JTable getTablaEgresados()
    {
        return tablaEgresados;
    }

    public JTextField getTxtDireccion()
    {
        return txtDireccion;
    }

    public JTextField getTxtNombre()
    {
        return txtNombre;
    }

    public JFormattedTextField getTxtPromedio()
    {
        return txtPromedio;
    }

    public ButtonGroup getGrupoRadioButtons()
    {
        return grupoRadioButtons;
    }

    public JPanel getPanelLateralIzq()
    {
        return panelLateralIzq;
    }

    public JPanel getPanelLateralDer()
    {
        return panelLateralDer;
    }

    public JPanel getPanelSuperior()
    {
        return panelSuperior;
    }

    public JLabel getTiempoTranscurrido()
    {
        return tiempoTranscurrido;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnBuscar;
    private JButton btnBuscarDirectorio;
    private JButton btnGenerar;
    private JCheckBox chbNombre;
    private JCheckBox chbProfesion;
    private JCheckBox chbPromedio;
    private JComboBox<String> cmbProfesiones;
    private Box.Filler filler1;
    private Box.Filler filler2;
    private Box.Filler filler3;
    private ButtonGroup grupoRadioButtons;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JPanel jPanel1;
    private JPanel jPanel2;
    private JPanel jPanel3;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JScrollPane jScrollPane1;
    private JSplitPane jSplitPane1;
    private JPanel panelLateralDer;
    private JPanel panelLateralIzq;
    private JPanel panelSuperior;
    private JProgressBar progressBar;
    private JRadioButton rbArbolAVL;
    private JRadioButton rbArbolB;
    private JRadioButton rbArbolBB;
    private JTable tablaEgresados;
    private JLabel tiempoTranscurrido;
    private JTextField txtDireccion;
    private JTextField txtNombre;
    private JFormattedTextField txtPromedio;
    // End of variables declaration//GEN-END:variables
}
