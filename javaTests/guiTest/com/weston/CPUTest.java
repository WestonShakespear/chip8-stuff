package com.weston;

public class CPUTest {
    public static void main(String[] args) {
        CPU chip8 = new CPU();

       
       
        for (int i = 0; i< 1; i++) {
            chip8.clockPulse();
            System.out.println(chip8.getStateString());
            String[] state = chip8.getState();
            for (int a = 0; a < 35; a++) {
                System.out.print(state[a] + "  ");
            }
            
            System.out.println("\n");
        }
        
    }
}
