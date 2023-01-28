package com.weston;

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

    public CPU() {
        this.init();
    }

    public void clockPulse() {
        this.randomTest();
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

    }

    private void clearScreenMEM() {
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                screenMEM[x][y] = false;
            }
        }
    }

    private void randomTest() {
        Random rd = new Random();
        for (int x = 0; x < 64; x++) {
            for (int y = 0; y < 32; y++) {
                screenMEM[x][y] = rd.nextBoolean();
            }
        }

        for(int r = 0; r < 16; r++) {
            V[r] = (short)rd.nextInt(0, 0xFF);
        }

        for(int r = 0; r < 16; r++) {
            S[r] = (short)rd.nextInt(0, 0xFFF);
        }

        I = (short)rd.nextInt(0, 0xFFF);
        PC = (short)rd.nextInt(0, 0xFFF);
        SP = (short)rd.nextInt(0, 0xFFF);
    }





    public void setI(short inI) {
        this.I = inI;
    }
    
    public short getI() {
        return this.I;
    }

    public void setPC(short inPC) {
        this.PC = inPC;
    }

    public short getPC() {
        return this.PC;
    }

    public void setSP(short inSP) {
        this.SP = inSP;
    }

    public short getSP() {
        return this.SP;
    }


    public void setV(byte reg, byte val) {
        V[reg] = val;
    }

    public short getV(byte reg) {
        return V[reg];
    }

    public void setS(byte level, short val) {
        this.S[level] = val;
    }

    public short getS(byte level) {
        return S[level];
    }



    public String[] getState() {
        String[] ret = new String[35];

        int i = 0;
        for (int vI = 0; vI < 16; vI++) {
            ret[i] = padHex(Integer.toHexString(V[vI]));
            i++;
        }
        for (int sI = 0; sI < 16; sI++) {
            ret[i] = padHex(Integer.toHexString(S[sI]));
            i++;
        }
        ret[32] = padHex(Integer.toHexString(PC));
        ret[33] = padHex(Integer.toHexString(I));
        ret[34] = padHex(Integer.toHexString(SP));

        return ret;        
    }

    public String formatStateSingle(String name, short value) {
        String ret = name + ":  ";
        ret += padHex(Integer.toHexString(value));
        ret += "  " + value + "\n";

        return ret;
    }

    public String padHex(String hex) {
        String ret = "0x";
        ret += String.format("%4s", hex).toUpperCase();
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
            String hex = padHex(Integer.toHexString(V[a]));
            data += hex + "  ";
        }
        data += "\n\n";

        for (int a = 0; a < 16; a++) {
            data += "S" + Integer.toHexString(a).toUpperCase() + "      ";
        }
        data += "\n";
        for (int a = 0; a < 16; a++) {
            String hex = padHex(Integer.toHexString(S[a]));
            data += hex + "  ";
        }
        data += "\n";

        return data;
    }
}