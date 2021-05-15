package dev.jairo.gui;

import dev.jairo.controll.Controller;

import javax.swing.*;

public class MainMenu extends JMenuBar {
    private JMenu menuSubscriptor;
    private JMenuItem optAddSubscriptor;
    private JMenuItem exit;
    private MainWindow mainWindow;
    private Controller controller;

    private WindowSubscriptor winSubscriptor;

    public MainMenu(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        beginComponents();
        addComponents();
    }

    private void addComponents() {
        menuSubscriptor.add(optAddSubscriptor);
        menuSubscriptor.addSeparator();
        menuSubscriptor.add(exit);

        add(menuSubscriptor);
    }

    private void beginComponents() {
        menuSubscriptor = new JMenu("Suscriptor");

        optAddSubscriptor = new JMenuItem("Adcionar");
        optAddSubscriptor.setActionCommand(HandlingEvents.ADD_SUBSCRIPTOR);
        optAddSubscriptor.addActionListener( new HandlingEvents( mainWindow));

        exit = new JMenuItem("Salir");
        exit.setActionCommand( HandlingEvents.EXIT );
        exit.addActionListener( new HandlingEvents( mainWindow ) );
    }
}
