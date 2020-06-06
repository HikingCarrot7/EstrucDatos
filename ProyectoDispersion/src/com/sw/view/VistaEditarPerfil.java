package com.sw.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Window;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 *
 * @author HikingCarrot7
 */
public class VistaEditarPerfil extends JDialog
{

    public VistaEditarPerfil(Window owner)
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

        generoGrupo = new ButtonGroup();
        jPanel3 = new JPanel();
        jPanel7 = new JPanel();
        jLabel5 = new JLabel();
        jPanel9 = new JPanel();
        panelDatosPersonales = new JPanel();
        jLabel1 = new JLabel();
        txtNombre = new JTextField();
        jLabel2 = new JLabel();
        txtEdad = new JFormattedTextField();
        jLabel3 = new JLabel();
        txtCorreo = new JTextField();
        jLabel4 = new JLabel();
        txtPassword = new JPasswordField();
        jPanel4 = new JPanel();
        jPanel8 = new JPanel();
        rdBtnHombre = new JRadioButton();
        filler1 = new Box.Filler(new Dimension(10, 0), new Dimension(10, 0), new Dimension(10, 32767));
        rdBtnMujer = new JRadioButton();
        jLabel6 = new JLabel();
        panelUbicacion = new JPanel();
        jLabel7 = new JLabel();
        txtRuta = new JTextField();
        btnCambiarRuta = new JButton();
        jPanel1 = new JPanel();
        jPanel6 = new JPanel();
        jPanel5 = new JPanel();
        btnGuardar = new JButton();
        btnCancelar = new JButton();

        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Editar mi perfil");
        setMinimumSize(new Dimension(530, 450));
        setPreferredSize(new Dimension(530, 450));

        jPanel3.setLayout(new BorderLayout());

        jPanel7.setBorder(BorderFactory.createEmptyBorder(5, 1, 5, 1));

        jLabel5.setFont(new Font("Tahoma", 0, 36)); // NOI18N
        jLabel5.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel5.setText("Editar perfil");
        jPanel7.add(jLabel5);

        jPanel3.add(jPanel7, BorderLayout.PAGE_START);

        getContentPane().add(jPanel3, BorderLayout.NORTH);

        jPanel9.setLayout(new BorderLayout());

        panelDatosPersonales.setBorder(BorderFactory.createTitledBorder("Datos personales"));
        panelDatosPersonales.setLayout(new GridBagLayout());

        jLabel1.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel1.setText("Nombre:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(15, 35, 5, 5);
        panelDatosPersonales.add(jLabel1, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(15, 5, 5, 35);
        panelDatosPersonales.add(txtNombre, gridBagConstraints);

        jLabel2.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel2.setText("Edad:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        panelDatosPersonales.add(jLabel2, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        panelDatosPersonales.add(txtEdad, gridBagConstraints);

        jLabel3.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel3.setText("Correo electrónico:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        panelDatosPersonales.add(jLabel3, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        panelDatosPersonales.add(txtCorreo, gridBagConstraints);

        jLabel4.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel4.setText("Contraseña:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        panelDatosPersonales.add(jLabel4, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        panelDatosPersonales.add(txtPassword, gridBagConstraints);

        jPanel4.setLayout(new BorderLayout());
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.weighty = 0.1;
        panelDatosPersonales.add(jPanel4, gridBagConstraints);

        FlowLayout flowLayout1 = new FlowLayout(FlowLayout.LEFT, 0, 0);
        flowLayout1.setAlignOnBaseline(true);
        jPanel8.setLayout(flowLayout1);

        generoGrupo.add(rdBtnHombre);
        rdBtnHombre.setSelected(true);
        rdBtnHombre.setText("Hombre");
        jPanel8.add(rdBtnHombre);
        jPanel8.add(filler1);

        generoGrupo.add(rdBtnMujer);
        rdBtnMujer.setText("Mujer");
        jPanel8.add(rdBtnMujer);

        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        panelDatosPersonales.add(jPanel8, gridBagConstraints);

        jLabel6.setHorizontalAlignment(SwingConstants.RIGHT);
        jLabel6.setText("Género:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        panelDatosPersonales.add(jLabel6, gridBagConstraints);

        jPanel9.add(panelDatosPersonales, BorderLayout.CENTER);

        panelUbicacion.setBorder(BorderFactory.createTitledBorder("Ubicación de mis contactos"));
        panelUbicacion.setLayout(new GridBagLayout());

        jLabel7.setText("Ubicación:");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(5, 35, 5, 5);
        panelUbicacion.add(jLabel7, gridBagConstraints);
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 0.1;
        gridBagConstraints.insets = new Insets(5, 5, 5, 5);
        panelUbicacion.add(txtRuta, gridBagConstraints);

        btnCambiarRuta.setText("Cambiar");
        gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.insets = new Insets(5, 5, 5, 35);
        panelUbicacion.add(btnCambiarRuta, gridBagConstraints);

        jPanel9.add(panelUbicacion, BorderLayout.SOUTH);

        getContentPane().add(jPanel9, BorderLayout.CENTER);

        jPanel1.setLayout(new GridLayout(1, 0));

        jPanel6.setLayout(new FlowLayout(FlowLayout.LEFT));
        jPanel1.add(jPanel6);

        jPanel5.setLayout(new FlowLayout(FlowLayout.RIGHT));

        btnGuardar.setText("Guardar");
        jPanel5.add(btnGuardar);

        btnCancelar.setText("Cancelar");
        jPanel5.add(btnCancelar);

        jPanel1.add(jPanel5);

        getContentPane().add(jPanel1, BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public JButton getBtnCambiarRuta()
    {
        return btnCambiarRuta;
    }

    public JButton getBtnCancelar()
    {
        return btnCancelar;
    }

    public JButton getBtnGuardar()
    {
        return btnGuardar;
    }

    public JTextField getTxtCorreo()
    {
        return txtCorreo;
    }

    public JFormattedTextField getTxtEdad()
    {
        return txtEdad;
    }

    public JTextField getTxtNombre()
    {
        return txtNombre;
    }

    public JPasswordField getTxtPassword()
    {
        return txtPassword;
    }

    public JTextField getTxtRuta()
    {
        return txtRuta;
    }

    public ButtonGroup getGeneroGrupo()
    {
        return generoGrupo;
    }

    public JRadioButton getRdBtnHombre()
    {
        return rdBtnHombre;
    }

    public JRadioButton getRdBtnMujer()
    {
        return rdBtnMujer;
    }

    public JPanel getPanelDatosPersonales()
    {
        return panelDatosPersonales;
    }

    public JPanel getPanelUbicacion()
    {
        return panelUbicacion;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JButton btnCambiarRuta;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private Box.Filler filler1;
    private ButtonGroup generoGrupo;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JLabel jLabel3;
    private JLabel jLabel4;
    private JLabel jLabel5;
    private JLabel jLabel6;
    private JLabel jLabel7;
    private JPanel jPanel1;
    private JPanel jPanel3;
    private JPanel jPanel4;
    private JPanel jPanel5;
    private JPanel jPanel6;
    private JPanel jPanel7;
    private JPanel jPanel8;
    private JPanel jPanel9;
    private JPanel panelDatosPersonales;
    private JPanel panelUbicacion;
    private JRadioButton rdBtnHombre;
    private JRadioButton rdBtnMujer;
    private JTextField txtCorreo;
    private JFormattedTextField txtEdad;
    private JTextField txtNombre;
    private JPasswordField txtPassword;
    private JTextField txtRuta;
    // End of variables declaration//GEN-END:variables
}
