package chip8functionality;

import java.io.*;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.file.Files;
import java.nio.file.Paths;

public class RomRead
{
    MemoryMap m = new MemoryMap();
    public byte rom[] = new byte[255];
    public byte nextbyte [] = new byte[2];
    int inc = 0;


    void initBytes () throws IOException
    {
        rom = Files.readAllBytes(Paths.get("pong.rom"));
        this.setBytes();
    }

    void setBytes () throws IOException
    {
        m.setMemory(rom, m.getStartOfRom());
        m.setMemory(m.getFontSet(), 0x050);

    }
}
