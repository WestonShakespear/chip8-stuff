package com.weston;

import javax.swing.JPanel;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;


public class Screen extends JPanel {

    private int resolution;
    private boolean[][] screenMEM;

    private Color bgColor;
    private Color fgColor;


    public Screen(Window parent, int argsResolution) {
        resolution = argsResolution;
        screenMEM = new boolean[64][32];

        bgColor = new Color(31, 21, 1);
        fgColor = new Color(125, 167, 116);

        initScreenMEM();
        buildScreen(parent);
    }

    public void refresh(boolean[][] inputMEM) {
        this.populateScreenMEM(inputMEM);
        this.repaint();
    }

    public void populateScreenMEM(boolean[][] inputMEM) {
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                screenMEM[x][y] = inputMEM[x][y];
            }
        }
    }

    private void initScreenMEM() {
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                screenMEM[x][y] = false;
            }
        }
    }

    private void buildScreen(Window parent) {
        setFocusable(true);
        setSize(64*resolution, 32*resolution);
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
}
