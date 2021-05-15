package dev.jairo.runner;

import dev.jairo.gui.MainWindow;

public class Runner {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        window.begin();
        window.setVisible( true );
    }
}
