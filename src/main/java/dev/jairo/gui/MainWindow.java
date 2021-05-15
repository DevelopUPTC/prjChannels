package dev.jairo.gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    private JDesktopPane desktop;
    private MainMenu mainMenu;
    private WindowSubscriptor windowSubscriptor;

    public MainWindow()  {
        setTitle("Gestión de Canales / Susciptores");
        setSize( new Dimension( 840, 680 ) );
        setDefaultCloseOperation( DO_NOTHING_ON_CLOSE );
        setResizable( true );
        setLayout( new BorderLayout());
    }

    public void begin(){
        beginComponents();
        addComponents();
    }

    private void addComponents() {
        this.setJMenuBar( mainMenu );

        add( desktop, BorderLayout.CENTER);
    }

    private void beginComponents() {
        mainMenu = new MainMenu( this );
        desktop = new JDesktopPane();

        windowSubscriptor = new WindowSubscriptor( desktop );
        addWindowListener( new HandlingEvents( this) );
    }

    public void exit() {
        int exit = JOptionPane.showConfirmDialog(null,"Está Segur@?","Confirmar",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if( exit == JOptionPane.YES_OPTION ){
            System.exit(0);
        }
    }

    public void addSubsceriptor() {
        windowSubscriptor.setVisible( true );
    }
}
