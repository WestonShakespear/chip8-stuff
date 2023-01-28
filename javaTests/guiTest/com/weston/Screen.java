package com.weston;

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Color;

import java.util.Random;


public class Screen extends JPanel {

    private int resolution;
    private int screenWidth;
    private int screenHeight;

    private Color bgColor;
    private Color fgColor;

    private Timer timer;

    private boolean[][] screenMEM;

    public Screen(Window parent, int argsResolution) {
        resolution = argsResolution;
        screenWidth = 64 * resolution;
        screenHeight = 32 * resolution;

        bgColor = new Color(31, 21, 1);
        fgColor = new Color(125, 167, 116);

        screenMEM = new boolean[64][32];
        populateScreenMEM();

        buildScreen(parent);
    }

    public void refresh() {
        this.populateScreenMEM();
        this.repaint();
    }

    private void populateScreenMEM() {
        Random rd = new Random();
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                screenMEM[x][y] = rd.nextBoolean();
            }
        }
    }

    private void buildScreen(Window parent) {
        setFocusable(true);
        setSize(64*resolution, 32*resolution);

        addKeyListener(new TAdapter());

              

    }

    

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        for (int x = 0; x < 64 * resolution; x += resolution) {
            for (int y = 0; y < 32 * resolution; y += resolution) {
                if (screenMEM[x / resolution][y / resolution]) {
                    g2d.setColor(fgColor);
                } else {
                    g2d.setColor(bgColor);
                }
                g2d.fillRect(x, y, resolution, resolution);
            }
        }
        
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();

            switch (keycode) {
                case KeyEvent.VK_Q -> System.out.println("1");
                case KeyEvent.VK_W -> System.out.println("2");
                case KeyEvent.VK_E -> System.out.println("3");
                case KeyEvent.VK_R -> System.out.println("4");
                case KeyEvent.VK_T -> System.out.println("5");
                case KeyEvent.VK_A -> System.out.println("6");
                case KeyEvent.VK_S -> System.out.println("7");
                case KeyEvent.VK_D -> System.out.println("8");
                case KeyEvent.VK_F -> System.out.println("9");
                case KeyEvent.VK_G -> System.out.println("0");

                case KeyEvent.VK_Z -> System.out.println("A");
                case KeyEvent.VK_X -> System.out.println("B");
                case KeyEvent.VK_C -> System.out.println("C");
                case KeyEvent.VK_V -> System.out.println("D");
                case KeyEvent.VK_B -> System.out.println("E");
                case KeyEvent.VK_N -> System.out.println("F");
            }
        }
    }
    
}
