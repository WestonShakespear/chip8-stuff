package com.weston;

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;

public class Window extends JFrame
    implements ActionListener {

    private int resolution;
    private Timer timer;

    private CPU chip8;
    private Screen screen;
    private CPUView cpuView;

    public Window(String[] args) {

        chip8 = new CPU();

        resolution = Integer.parseInt(args[0]);
        buildWindow();
    }

    private void buildWindow() {
        screen = new Screen(this, resolution);

        GridLayout gLayout = new GridLayout(2,2);
        this.setLayout(gLayout);
        this.add(screen);

        
        cpuView = new CPUView(this);
        this.add(cpuView);  
        this.add(new JButton("click"));
        this.add(new JButton("me"));

        setSize(128*resolution, 64*resolution);
        setTitle("GUI Test");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(rootPane);

        timer = new Timer(100, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("tick");
        

        String s = e.getActionCommand();

        if (s == null) {
            chip8.clockPulse();
            String[] state = chip8.getState();
            
            screen.refresh();
            cpuView.refresh(state);

            System.out.println(chip8.getStateString());
        } else {
            
        }
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
