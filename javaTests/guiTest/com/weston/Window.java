package com.weston;

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import java.awt.GridLayout;

public class Window extends JFrame
    implements ActionListener {

    private int resolution;
    private Timer timer;

    private CPU chip8;
    private Screen screen;
    private CPUView cpuView;
    private Control control;

    public Window(String[] args) {

        chip8 = new CPU(args[1]);

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
        
        control = new Control(this, chip8);
        this.add(control);
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
            String[] stateCPU = chip8.getStateCPUView();
            boolean[][] stateScreen = chip8.getStateScreen();
            
            screen.refresh(stateScreen);
            cpuView.refresh(stateCPU);

            // System.out.println(chip8.getStateString());
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
