package com.weston;

class CPU {
    
    //Index Register
    private short I;
    //Program Counter
    private short PC;

    //Registers V0-F
    private byte V[] = new byte[16];

    //16 level stack
    private short S[] = new short[16];

    public CPU() {
        init();
    }

    private void init() {
        I = 0x0000;
        PC = 0x0200;

        for(int r = 0; r < 16; r++) {
            V[r] = 0x00;
        }

        for(int r = 0; r < 16; r++) {
            S[r] = 0x0000;
        }
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


    public void setV(byte reg, byte val) {
        V[reg] = val;
    }

    public byte getV(byte reg) {
        return V[reg];
    }

    public void setS(byte level, short val) {
        this.S[level] = val;
    }

    public short getS(byte level) {
        return S[level];
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

    public String getState() {
        String data = "";

        data += formatStateSingle("PC", PC);
        data += formatStateSingle(" I", I) + "\n";
        
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