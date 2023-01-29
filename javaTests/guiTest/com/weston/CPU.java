package com.weston;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

class CPU {
    
    //Index Register
    private short I;
    //Program Counter
    private short PC;
    //Stack Pointer
    private short SP;

    //Registers V0-F
    private short V[] = new short[16];

    //16 level stack
    private short S[] = new short[16];

    private boolean[][] screenMEM = new boolean[64][32];

    private short[] memory = new short[4096];

    Random rd;

    public CPU(String romLocation) {
        this.init();
        this.readROM(romLocation);
    }

    public void clockPulse() {
        // this.randomTest();
    }

    private void init() {
        I = 0x0000;
        PC = 0x0200;
        SP = 0x0000;

        for(int r = 0; r < 16; r++) {
            V[r] = 0x00;
        }

        for(int r = 0; r < 16; r++) {
            S[r] = 0x0000;
        }

        this.clearScreenMEM();
        this.rd = new Random();

        this.initMemory();
    }

    private void initMemory() {
        for (int i = 0; i < 4096; i++) {
            memory[i] = 0;
        }
    }

    private void readROM(String filename) {
        try {
            File file = new File(filename);
            byte[] bytes = new byte[(int) file.length()];
            FileInputStream fis = new FileInputStream(file);
            try {
                fis.read(bytes);
                int location = 0x200;
                for (int i = 0; i < bytes.length; i++) {
                    short val = bytes[i];
                    if (val < 0) {
                        val = (short) (val & 0xFF);
                    }

                    memory[location] = val;
                    location++;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        
    }

    public String dumpMEMString() {
        String ret = "";

        for (int i = 0; i < 4096; i++) {
            if (i % 16 == 0) {
                ret += "\n";
                ret += padHex(Integer.toHexString(i), 4);
                ret += ":   ";

            }
            ret += padHex(Integer.toHexString(memory[i]), 2);
            ret += "  ";

        }
        return ret;
    }

    private void clearScreenMEM() {
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                this.screenMEM[x][y] = false;
            }
        }
    }

    





    public void setI(short inI) {
        this.I = inI;
    }

    public void setPC(short inPC) {
        this.PC = inPC;
    }

    public void setSP(short inSP) {
        this.SP = inSP;
    }

    public void setV(byte reg, byte val) {
        V[reg] = val;
    }

    public void setS(byte level, short val) {
        this.S[level] = val;
    }
    
    public short getI() {
        return this.I;
    }

    public short getPC() {
        return this.PC;
    }

    public short getSP() {
        return this.SP;
    }

    public short getV(byte reg) {
        return V[reg];
    }

    public short getS(byte level) {
        return S[level];
    }

    public boolean[][] getStateScreen() {
        return this.screenMEM;
    }


    private void randomTest() {
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                this.screenMEM[x][y] = this.rd.nextBoolean();
            }
        }

        for(int r = 0; r < 16; r++) {
            this.V[r] = (short)this.rd.nextInt(0, 0xFF);
        }

        for(int r = 0; r < 16; r++) {
            this.S[r] = (short)this.rd.nextInt(0, 0xFFF);
        }

        I = (short)this.rd.nextInt(0, 0xFFF);
        PC = (short)this.rd.nextInt(0, 0xFFF);
        SP = (short)this.rd.nextInt(0, 0xFFF);
    }


    public String[] getStateCPUView() {
        String[] ret = new String[35];

        int i = 0;
        for (int vI = 0; vI < 16; vI++) {
            ret[i] = padHex(Integer.toHexString(V[vI]), 4);
            i++;
        }
        for (int sI = 0; sI < 16; sI++) {
            ret[i] = padHex(Integer.toHexString(S[sI]), 4);
            i++;
        }
        ret[32] = padHex(Integer.toHexString(PC), 4);
        ret[33] = padHex(Integer.toHexString(I), 4);
        ret[34] = padHex(Integer.toHexString(SP), 4);

        return ret;        
    }

    public String formatStateSingle(String name, short value) {
        String ret = name + ":  ";
        ret += padHex(Integer.toHexString(value), 4);
        ret += "  " + value + "\n";

        return ret;
    }

    public String padHex(String hex, int level) {
        String ret = "0x";
        ret += String.format("%" + level + "s", hex).toUpperCase();
        ret = ret.replace(" ", "0");

        return ret;
    }

    public String getStateString() {
        String data = "";

        data += formatStateSingle("PC", PC);
        data += formatStateSingle(" I", I);
        data += formatStateSingle("SP", SP) + "\n";
        
        for (int a = 0; a < 16; a++) {
            data += "V" + Integer.toHexString(a).toUpperCase() + "      ";
        }
        data += "\n";
        for (int a = 0; a < 16; a++) {
            String hex = padHex(Integer.toHexString(V[a]), 2);
            data += hex + "  ";
        }
        data += "\n\n";

        for (int a = 0; a < 16; a++) {
            data += "S" + Integer.toHexString(a).toUpperCase() + "      ";
        }
        data += "\n";
        for (int a = 0; a < 16; a++) {
            String hex = padHex(Integer.toHexString(S[a]), 4);
            data += hex + "  ";
        }
        data += "\n";

        return data;
    }


    public void actionPlay() {

    }

    public void actionPause() {

    }

    public void actionStep() {

    }

    public void actionReset() {

    }

    public void actionMEM() {
        System.out.println(this.dumpMEMString());
    }

    public void actionSave() {

    }





    
}