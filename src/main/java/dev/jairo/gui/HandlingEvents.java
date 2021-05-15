package dev.jairo.gui;

import dev.jairo.model.Management;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class HandlingEvents implements ActionListener, WindowListener {
    private MainWindow mainWindow;
    public static final String EXIT = "Salir del Sistema";
    public static final String ADD_SUBSCRIPTOR = "Agregar Suscriptor";

    public HandlingEvents(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch ( e.getActionCommand()){
            case EXIT : mainWindow.exit();
            break;

            case ADD_SUBSCRIPTOR: mainWindow.addSubsceriptor();
            break;
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
        mainWindow.exit();
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
