package chip8functionality;

import java.io.IOException;
import java.util.Random;

public class InstructionSet {

    // write out array of all the instructions available by number

    int temp1, temp2, temp3 = 0;

    public void processOpcode(int opcode) throws IOException {
        int new_op = opcode & 0xf000;
        int ins ;
        MemoryMap m = new MemoryMap();
        Timers time = new Timers() ;
        /*
        This will be put in a while loop, it goes through each of the opcodes and
        then does the associated operand
        */

        switch (new_op)
        {
            case 0x0000:
                if (opcode != 0x00E0 && opcode != 0x0EE)
                {
                    ins = opcode & 0x0FFF;
                    break;
                }
                else if (opcode == 0x00E0)
                {
                    System.out.println("Screen Cleared!");
                    break;
                }
                else {
                    return;
                }
            case 0x1000:
                ins = opcode & 0x0FFF;
                m.setPC(ins);
                break;

            case 0x2000:
                ins = opcode & 0x0FFF;
                m.setSP(m.getSP()+1);
                m.setStackItem(m.getSP(), m.getPC());
                m.setPC(ins);
                break;

            case 0x3000:
                temp1 = opcode & 0x0F00;
                temp2 = opcode & 0x00FF;
                if ((m.getVx(temp1) != (temp2)))
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0x4000:
                temp1 = opcode & 0x0F00;
                temp2 = opcode & 0x00FF;
                if ((m.getVx(temp1) == (temp2)))
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0x5000:
                temp1  = opcode & 0x0F00;
                temp2  = opcode & 0x00F0;
                if (temp1 == temp2)
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0x6000:
                temp1 = opcode & 0x0F00;
                temp2 = opcode & 0x00FF;
               // m.setVx(temp1, temp2);
                m.setPC(m.getPC()+2);
                break;

            case 0x7000:
                temp1 = opcode & 0x00FF;
                temp2 = opcode & 0x0F00;
                m.setVx(m.getVx(temp2), m.getVx(temp2) + temp1);
                m.setPC(m.getPC()+2);
                break;

            case 0x8000:
                temp1 = opcode & 0x000F;
                switch (temp1)
                {
                    case 0x0000:
                        m.setVx(opcode & 0x0F00, opcode & 0x00F0);
                        break;

                    case 0x0001:
                        m.setVx(opcode & 0x0F00, ((opcode & 0x0F00) | (opcode & 0x00F0)));
                        break;

                    case 0x0002:
                        m.setVx(opcode & 0x0F00, ((opcode & 0x0F00) & (opcode & 0x00F0)));
                        break;

                    case 0x0003:
                        m.setVx(opcode & 0x0F00, ((opcode & 0x0F00) ^ (opcode & 0x00F0)));
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0004:
                        temp2 = (opcode & 0x0F00) + (opcode & 0x00F0);
                            if (temp2 > 255) {
                                m.setVx(0xF, 1);
                            }
                            else {
                                m.setVx(0xF, 0);
                            }
                        temp2 = temp2 & 0x00FF;
                        m.setVx(opcode & 0x0F00, temp2);
                        break;

                    case 0x0005:
                        temp1 = (opcode & 0x0F00) - (opcode & 0x00F0);
                        if ((opcode & 0x0F00) > (opcode & 0x00F0)) {
                            m.setVx(0xF, 1);
                        }
                        else {
                            m.setVx(0xF, 0);
                        }
                        temp2 = temp1 & 0x00FF;
                        m.setVx(opcode & 0x0F00, temp2);
                        break;

                    case 0x0006:
                        System.out.println(new_op);
                        break;

                    case 0x0007:
                        System.out.println(new_op);
                        break;

                    case 0x000E:
                        System.out.println(new_op);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value:");
                }
            case 0x9000:
                if (m.getVx(opcode & 0x0F00) != m.getVx(opcode & 0x00F0))
                {
                    m.setPC(m.getPC()+2);
                }
                break;

            case 0xA000:
                m.setI(opcode & 0x0FFF, 1);
                System.out.println(new_op);
                break;

            case 0xB000:
                m.setPC(opcode & 0x0FFF + m.getVx(0));
                System.out.println(new_op);
                break;

            case 0xC000:
                System.out.println(new_op);
                Random rand = new Random();
                temp1 = rand.nextInt(0xFF);
                temp2 = temp1 & (opcode & 0x00FF);
                m.setVx(opcode & 0x0F00, temp2);
                break;

            case 0xD000:
                System.out.println("Image produced here");
                break;

            case 0xE000:
                System.out.println(new_op);
                //TODO: key presses
                break;

            case 0xF000:
                System.out.println(new_op + "well....");
                switch (opcode & 0x00FF)
                {
                    case 0x0007:
                        m.setVx(opcode & 0x0F00, time.getDT());
                        break;

                    case 0x000A:
                        System.out.println("Store a keypress");
                        break;

                    case 0x0015:
                        time.setDT(opcode & 0x0F00);
                        break;

                    case 0x0018:
                        time.setST(opcode & 0x0F00);
                        break;

                    case 0x001E:
                        m.setI(m.getI(1) + (m.getVx(opcode & 0x0F00)), 1);
                        break;

                    case 0x0029:
                        System.out.println("location of sprite");
                        break;

                    case 0x0033:
                        temp1 = m.getVx(opcode & 0x0F00);
                        m.setI(temp1 % 1000,0);
                        m.setI(temp1 % 100,1);
                        m.setI(temp1 % 10,2);
                        break;

                    case 0x0055:
                        break;
                }

                m.setPC(m.getPC()+2);
                break;
        }
    }
}
