package com.weston;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Window extends JFrame {

    private int resolution;

    public Window(String[] args) {
        resolution = Integer.parseInt(args[0]);
        buildWindow();
    }

    private void buildWindow() {
        Screen screen = new Screen(this, resolution);
        add(screen);



        setSize(128*resolution, 64*resolution);
        setTitle("GUI Test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(rootPane);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Window window = new Window(args);
                window.setVisible(true);
            }
        });
    }
}
