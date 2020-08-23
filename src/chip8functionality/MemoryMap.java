package chip8functionality;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Random;

public class MemoryMap {

    static int memory[] = new int[4096];
    static int Vx[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    static int stack[] = new int[16];
    public int I = 0;
    public int start_of_ram = 0x0000;
    public int start_of_rom = 0x0200;

    Path fileLocation = Paths.get("fontset.bin");
    byte[] chip8_fontset;
    Draw g;
    public static int[][] bin = new int[64][32];

    static int pc = 0x200;
    int sp = 0x0;

    public void initArray() {
        for (int i = 0; i < bin.length; i++) {
            for (int j = 0; j < bin[i].length; j++) {
                if (i % 2 == 1 || i == 0) {
                    bin[i][j] = 0;
                } else {
                    bin[i][j] = 0;
                }
            }
        }
    }

    Draw getDraw() {
        return this.g;
    }

    void setDraw(Draw draw) {
        this.g = draw;
    }

    public int[][] ADDBIN(byte[] n, int x, int y) {
        int[][] temp = new int[64][32];
        int inc = 0;
        String [] bindump = new String[n.length];

        System.out.println(Arrays.toString(n) + " " + x + " " + y);

        for(int k = 0 ; k < n.length; k++) {
            bindump[k] = String.format("%8s", Integer.toBinaryString(n[k] & 0xFF)).replace(' ', '0');
        }

        int index = 0;
        for (int new_x = 0 ; new_x < temp[0].length; new_x++){
            for (int new_y = 0 ; new_y < temp.length; new_y++){

                if((new_x == x) && (new_y == y)) {
                    int tempx = new_x;
                    int tempy = new_y;

                        for (int i = 0; i < bindump.length; i++)
                        {
                            System.out.println(bindump[i]);
                            for (int j = 0; j < 8; j++)
                            {
                                if (bindump[i].charAt(j) == '1')
                                {
                                    temp[tempx+i][tempy+j] = 1;
                                }
                            }
                            System.out.print("\n");
                        }

                }


            }
        }


        Draw.setVisibleGraphics(temp);
        // Write the bytes to the array
        return temp;
    }

    // DEBUG ONLY
    public int[][] randomBin() {
        int[][] temp = new int[64][32];

        Random rand = new Random();

        for (int row = 0; row < temp.length; row++) {
            for (int col = 0; col < temp[row].length; col++) {
                temp[row][col] = 0 + rand.nextInt((1 - 0) + 1);
            }
        }
        // Write the bytes to the array
        return temp;
    }

    public void setfont(byte[] font) {
        chip8_fontset = font;
    }

    public int[][] getBin() {
        return bin;
    }

    void setBin(int[][] new_bin) {

        for (int i = 0; i < bin.length - 1; i++) {
            for (int j = 0; j < bin[i].length - 1; j++) {
                bin[i][j] = new_bin[i][j];
            }
        }
    }

    void setBinItem(int item, int locx, int locy) {
        bin[locx][locy] = item;
    }

    int getBinItem(int locx, int locy) {
        return bin[locx][locy];
    }

    int[] getStack() {
        return stack;
    }

    int getStackItem(int i) {
        return stack[i];
    }

    void setStackItem(int location, int value) {
        stack[location] = value;
    }

    byte[] getFontSet() {
        return chip8_fontset;
    }

    int getI() {
        return this.I;
    }

    void setI(int newI) {
        this.I = newI;
    }

    int getSP() {
        return this.sp;
    }

    void setSP(int newSP) {
        this.sp = newSP;
    }

    int getPC() throws IOException {
        return pc;
    }

    void setPC(int newPC) throws IOException {
        pc = newPC;
    }

    int getVx(int loc) {
        return Vx[loc];
    }

    void setVx(int newVx, int value) {
        Vx[newVx] = value;
         
    }

    int getMemory (int loc)
    {
        int opcode = memory[loc] << 8 | memory[loc + 1];
        return opcode;
    }

    int [] getMemoryArr ()
    {
        return memory;
    }

    void setMemoryArr (int[] new_memory)
    {
        memory = new_memory;
    }

    void setMemory (byte[] bytes, int location)
    {
        int temp = location;
       for (int i = 0; i < bytes.length; i++ )
        {
           memory[temp] = bytes[i];
           temp++;
       }
    }

    void setMemoryIndividual (byte bytes, int location)
    {
        int temp = location;

        memory[temp] = bytes;
        temp++;

    }

    int getMemoryIndividual(int location)
    {
        return memory[location];
    }

    int getStartOfRam()
    {
        return start_of_ram;
    }

    int getStartOfRom()
    {
        return start_of_rom;
    }

}
