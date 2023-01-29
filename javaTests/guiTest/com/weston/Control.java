package com.weston;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class Control extends JPanel {

    private JButton[] keys = new JButton[16];

    private JButton play;
    private JButton pause;
    private JButton step;
    private JButton reset;
    private JButton mem;
    private JButton save;

    private CPU cpu;

    public Control(Window parent, CPU inCPU) {
        this.cpu = inCPU;
        buildControl(parent);
    }

    private void buildControl(Window parent) {
        GridLayout gridLayout = new GridLayout(1,2);
        this.setLayout(gridLayout);

        JPanel keyPanel = new JPanel();
        JPanel controlPanel = new JPanel();
        // JPanel otherPanel = new JPanel();

        GridLayout gridKeyLayout = new GridLayout(4,4);
        keyPanel.setLayout(gridKeyLayout);

        GridLayout gridControlLayout = new GridLayout(3,2);
        controlPanel.setLayout(gridControlLayout);

        // GridLayout gridOtherLayout = new GridLayout(3,2);
        // otherPanel.setLayout(gridOtherLayout);

        int[] keyLayout = {1, 2, 3, 0xC,
                        4, 5, 6, 0xD,
                        7, 8, 9, 0xE,
                        0xA, 0, 0xB, 0xF};

        for (int i = 0; i < 16; i++) {
            int val = keyLayout[i];
            keys[val] = new JButton(Integer.toHexString(val).toUpperCase());
            keyPanel.add(keys[val]);
        }


        play = new JButton("Play");
        play.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cpu.actionPlay();
            } 
          } );

        pause = new JButton("Pause");
        pause.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cpu.actionPause();
            } 
          } );

        step = new JButton("Step");
        step.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cpu.actionStep();
            } 
          } );

        reset = new JButton("Reset");
        reset.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cpu.actionReset();
            } 
          } );

        mem = new JButton("MEM");
        mem.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cpu.actionMEM();
            } 
          } );

        save = new JButton("Save");
        save.addActionListener(new ActionListener() { 
            public void actionPerformed(ActionEvent e) { 
                cpu.actionSave();
            } 
          } );


        controlPanel.add(play);
        controlPanel.add(pause);
        controlPanel.add(step);
        controlPanel.add(reset);
        controlPanel.add(mem);
        controlPanel.add(save);
        
        this.add(keyPanel);
        this.add(controlPanel);
        // this.add(otherPanel);

        setFocusable(true);

    }

    public void refresh(String[] data) {

    }
    
}
