package com.weston;

import javax.swing.Timer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
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
import java.awt.GridLayout;

import java.util.Random;


public class CPUView extends JPanel
    implements ActionListener {

    private Timer timer;


    public CPUView(Window parent) {
        buildCPUView(parent);
    }

    private void buildCPUView(Window parent) {
        JPanel registerPanel = new JPanel();
        JPanel stackPanel = new JPanel();
        JPanel otherPanel = new JPanel();

        GridLayout gridLayout = new GridLayout(1,3);
        this.setLayout(gridLayout);


        GridLayout gridRegLayout = new GridLayout(16,2);
        registerPanel.setLayout(gridRegLayout);

        GridLayout gridStackLayout = new GridLayout(16,2);
        stackPanel.setLayout(gridStackLayout);

        GridLayout gridOtherLayout = new GridLayout(3,2);
        otherPanel.setLayout(gridOtherLayout);

        

        JLabel registers[][] = new JLabel[16][2];

        for (int row = 0; row < 16; row++) {
            registers[row][0] = new JLabel("V" + Integer.toHexString(row).toUpperCase());
            registers[row][1] = new JLabel("0x0000");
            registers[row][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            registerPanel.add(registers[row][0]);
            registerPanel.add(registers[row][1]);

        }

        JLabel stack[][] = new JLabel[16][2];

        for (int row = 0; row < 16; row++) {
            stack[row][0] = new JLabel("ST" + Integer.toHexString(row).toUpperCase());
            stack[row][1] = new JLabel("0x0000");
            stack[row][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            stackPanel.add(stack[row][0]);
            stackPanel.add(stack[row][1]);

        }

        JLabel other[][] = {
            {
                new JLabel("Program Counter"),
                new JLabel("0x0000")
            },
            {
                new JLabel("Index Register"),
                new JLabel("0x0000")
            },
            {
                new JLabel("Stack Pointer"),
                new JLabel("0x00")
            }};

        for (int row = 0; row < 3; row++) {
            other[row][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            otherPanel.add(other[row][0]);
            otherPanel.add(other[row][1]);

        }
        
        
        

        

        this.add(registerPanel);
        this.add(stackPanel);
        this.add(otherPanel);



        setFocusable(true);
        timer = new Timer(100, this);
        timer.start();
        
        

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // System.out.println("tick");
        

        String s = e.getActionCommand();

        // if (s == null) {
        //     populateScreenMEM();
        //     repaint();
        // } else {
        //     switch (s) {
        //         case "submit":
        //             System.out.println("But");
        //             break;
        //     }
        // }
        

    }
    
}
