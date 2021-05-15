package dev.jairo.gui;

import javax.swing.*;
import java.awt.*;

public class WindowSubscriptor extends JInternalFrame {
    private JDesktopPane desktop;
    private JTextField txtName;
    private JTextField txtEmail;

    public WindowSubscriptor(JDesktopPane desktop) {
        this.desktop = desktop;
        setTitle("Agregar Suscriptor");
        setSize( new Dimension( 600, 400 ));
        setDefaultCloseOperation( HIDE_ON_CLOSE );
        setResizable( true );
        setClosable( true );
        setMaximizable( true );
        setIconifiable( true );
        setLayout( new GridBagLayout());
        beginComponents();
        addComponents();
        //this.pack();
        desktop.add( this );
    }

    private void addComponents() {
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        add( new JLabel("Nombre Susciptor"),gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add( txtName,gbc );
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

        gbc.gridx = 0;
        gbc.gridy = 1;
        add( new JLabel("Correo Electrónico"),gbc);

        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add( txtEmail,gbc );
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;

    }

    private void beginComponents() {
        txtName = new JTextField();
        txtEmail = new JTextField();
    }
}
