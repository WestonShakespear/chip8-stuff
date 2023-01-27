package com.weston;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;
public class Window extends JFrame {

    private int resolution;

    public Window(String[] args) {
        resolution = Integer.parseInt(args[0]);
        buildWindow();
    }

    private void buildWindow() {
        Screen screen = new Screen(this, resolution);

        GridLayout gLayout = new GridLayout(2,2);
        this.setLayout(gLayout);
        this.add(screen);

        
        CPUView cpuView = new CPUView(this);
        this.add(cpuView);  
        this.add(new JButton("hello"));
        this.add(new JButton("hello"));



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
