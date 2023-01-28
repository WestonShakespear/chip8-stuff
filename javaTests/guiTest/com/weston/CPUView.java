package com.weston;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;


public class CPUView extends JPanel {

    private JLabel registers[][] = new JLabel[16][2];
    private JLabel stack[][] = new JLabel[16][2];
    private JLabel other[][] = {
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

    public CPUView(Window parent) {
        buildCPUView(parent);
    }

    private void buildCPUView(Window parent) {
        GridLayout gridLayout = new GridLayout(1,3);
        this.setLayout(gridLayout);

        JPanel registerPanel = new JPanel();
        JPanel stackPanel = new JPanel();
        JPanel otherPanel = new JPanel();

        GridLayout gridRegLayout = new GridLayout(16,2);
        registerPanel.setLayout(gridRegLayout);

        GridLayout gridStackLayout = new GridLayout(16,2);
        stackPanel.setLayout(gridStackLayout);

        GridLayout gridOtherLayout = new GridLayout(3,2);
        otherPanel.setLayout(gridOtherLayout);

    
        for (int row = 0; row < 16; row++) {
            registers[row][0] = new JLabel("V" + Integer.toHexString(row).toUpperCase());
            registers[row][1] = new JLabel("0x0000");
            registers[row][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            registerPanel.add(registers[row][0]);
            registerPanel.add(registers[row][1]);

        }

        for (int row = 0; row < 16; row++) {
            stack[row][0] = new JLabel("ST" + Integer.toHexString(row).toUpperCase());
            stack[row][1] = new JLabel("0x0000");
            stack[row][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            stackPanel.add(stack[row][0]);
            stackPanel.add(stack[row][1]);

        }

        for (int row = 0; row < 3; row++) {
            other[row][1].setBorder(BorderFactory.createLineBorder(Color.BLACK));

            otherPanel.add(other[row][0]);
            otherPanel.add(other[row][1]);

        }
        
        this.add(registerPanel);
        this.add(stackPanel);
        this.add(otherPanel);

        setFocusable(true);

    }

    public void refresh(String[] data) {
        int i = 0;

        for (int row = 0; row < 16; row++) {
            registers[row][1].setText(data[i]);
            i++;
        }

        for (int row = 0; row < 16; row++) {
            stack[row][1].setText(data[i]);
            i++;
        }

        for (int row = 0; row < 3; row++) {
            other[row][1].setText(data[i]);
            i++;
        }

        this.repaint();
    }
    
}
