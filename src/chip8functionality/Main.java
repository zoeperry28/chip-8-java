package chip8functionality;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        boolean chip8Running = true;
        InstructionSet i = new InstructionSet();
        MemoryMap m = new MemoryMap();

        RomRead r = new RomRead();
        try
        {
            r.initBytes();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        /*
        This loop will get the opcode pointed to by the program counter
        */
       // Graphics g = new Graphics();
        while(chip8Running)
        {
            int x = m.getMemory(m.getPC());
            i.processOpcode(x);
            System.out.println(x);
        }
    }

}
