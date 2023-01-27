package com.weston;

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import java.util.Random;


public class Screen extends JPanel
    implements ActionListener {

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
        timer = new Timer(1, this);
        timer.start();
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
    public void actionPerformed(ActionEvent e) {
        System.out.println("tick");
        populateScreenMEM();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
    
}
