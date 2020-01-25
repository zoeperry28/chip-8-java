package chip8functionality;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

public class MemoryMap {

    static int memory[] = new int[4096];
    public int Vx[] = new int[16];
    static int stack[] = new int[16];
    public int I[] = new int[16];
    public int start_of_ram = 0x0000;
    public int start_of_rom = 0x0200;

    Path fileLocation = Paths.get("fontset.bin");
    byte[] chip8_fontset;
    
    public int bin[] = randomBin();
    static int pc = 0x200;
    int sp = 0x0;

    /*
     * This could get messy....
    */
    public int[] randomBin () 
    {
        int temp [] = new int [2048];

        Random rand = new Random();
        
        for (int i = 0; i < 2048; i++)
        {
            temp[i] = 0 + rand.nextInt((1 - 0) + 1);
        }
        // Write the bytes to the array 
        return temp; 
    }
    
    public void setfont (byte [] font)
    {
        chip8_fontset = font;
    }
    public int[] getBin() {
        return bin;
    }

    void setBin(int[] new_bin) {
        for (int p = 0; p < bin.length; p++)
        {
            bin[p] = new_bin[p];
        }
    }

    void setBinItem(int item, int loc) {
        bin[loc] = item;
    }

    int getBinItem(int loc)
    {
        return bin[loc];
    }

    int[] getStack() {
        return stack;
    }

    void setStackItem(int location, int value) {
        stack[location] = value;
    }

    byte[] getFontSet() {
        return chip8_fontset;
    }

    int getI(int loc) {
        return this.I[loc];
    }

    void setI(int newI, int loc) {
        this.I[loc] = newI;
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

    int getVx(int loc)
    {
        return Vx[loc];
    }

    void setVx (int newVx, int value)
    {
        this.Vx[newVx] = value;   
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

    void setMemory (byte [] bytes, int location)
    {
        int temp = location;
       for (int i = 0; i < bytes.length; i++ )
       {
           memory[temp] = bytes[i];
           temp++;
       }
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
