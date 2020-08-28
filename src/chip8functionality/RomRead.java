package chip8functionality;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class RomRead
{
    MemoryMap m = new MemoryMap();
    String romName = "";
    public byte rom[] = new byte[255];
    public byte nextbyte [] = new byte[2];
    int inc = 0;
    static int init = 0;

    void initBytes () throws IOException
    {
        m.initArray();
        Path fileLocation = Paths.get("fontset.bin");
        final byte [] chip8_fontset = Files.readAllBytes(fileLocation);
        m.setfont(chip8_fontset);

        rom = Files.readAllBytes(Paths.get("IBM.ch8"));
        this.setBytes();
    }

    void setBytes () throws IOException
    {
        m.setMemory(rom, m.getStartOfRom());
        m.setMemory(m.getFontSet(), 0x00);
    }
}
