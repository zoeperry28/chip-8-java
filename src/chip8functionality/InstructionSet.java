package chip8functionality;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;


public class InstructionSet {

    // write out array of all the instructions available by number

    int file_count = 0 ; 
    Main m = new Main();
 
    int temp1, temp2;
    int temp3 = 0;
    boolean drawFlag = false;
  
    public void processOpcode(int opcode) throws IOException 
    {
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
                    m.initArray();
                    break;
                }
                else if (opcode == 0x00E0 || opcode == 0x00EE)
                {
                    m.setPC(m.getStackItem(m.getSP()));
                    m.setSP(m.getSP()-1);
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
                temp1 = (opcode & 0x0F00 >> 8);
                temp2 = opcode & 0x00FF;
                if ((m.getVx(temp1) == (temp2)))
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0x4000:
                temp1 = (opcode & 0x0F00 >> 8);
                temp2 = opcode & 0x00FF;
                if ((m.getVx(temp1) != (temp2)))
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0x5000:
                temp1  = opcode & 0x0F00 >> 8;
                temp2  = opcode & 0x00F0 >> 4;
                if (temp1 == temp2)
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0x6000:
                temp1 = opcode & 0x0F00 >> 8;
                temp2 = opcode & 0x00FF;
               // m.setVx(temp1, temp2);
                m.setPC(m.getPC()+2);
                break;

            case 0x7000:
                temp1 = opcode & 0x00FF;
                temp2 = (opcode & 0x0F00 >> 8);
                m.setVx(m.getVx(temp2), m.getVx(temp2) + temp1);
                m.setPC(m.getPC()+2);
                break;

            case 0x8000:
                temp1 = opcode & 0x000F;
                switch (temp1)
                {
                    case 0x0000:
                        m.setVx((opcode & 0x0F00 >> 8), (opcode & 0x00F0 >> 4));
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0001:
                        m.setVx((opcode & 0x0F00 >> 8), ((opcode & 0x0F00 >> 8) | (opcode & 0x00F0 >> 4)));
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0002:
                        temp1 = (opcode & 0x0F00 >> 8 ) ;
                        m.setVx(temp1, (temp1 & (opcode & 0x00F0 >> 4)));
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0003:
                        m.setVx((opcode & 0x0F00 >> 8), ((opcode & 0x0F00 >> 8) ^ (opcode & 0x00F0 >> 4)));
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0004:
                        temp2 = (opcode & 0x0F00 >> 8) + (opcode & 0x00F0 >> 4);
                            if (temp2 > 255) {
                                m.setVx(0xF, 1);
                                m.setPC(m.getPC()+2);
                            }
                            else {
                                m.setVx(0xF, 0);
                                m.setPC(m.getPC()+2);
                            }
                        temp2 = temp2 & 0x00FF;
                        m.setVx((opcode & 0x0F00 >> 8), temp2);
                        break;

                    case 0x0005:
                        temp1 = (opcode & 0x0F00 >> 8) - (opcode & 0x00F0 >> 4);
                        if ((opcode & 0x0F00 >> 8) > (opcode & 0x00F0 >> 4)) {
                            m.setVx(0xF, 1);
                        }
                        else {
                            m.setVx(0xF, 0);
                        }
                        temp2 = temp1 & 0x00FF;
                        m.setVx((opcode & 0x0F00 >> 8), temp2);
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0006:
                        System.out.println(new_op);
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0007:
                        System.out.println(new_op);
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x000E:
                        System.out.println(new_op);
                        m.setPC(m.getPC()+2);
                        break;

                    default:
                        throw new IllegalStateException("Unexpected value:");
                }
            case 0x9000:
                if (m.getVx((opcode & 0x0F00 >> 8)) != m.getVx((opcode & 0x00F0 >> 4)))
                {
                    m.setPC(m.getPC()+2);
                }
                m.setPC(m.getPC()+2);
                break;

            case 0xA000:
                m.setI(opcode & 0x0FFF);
                System.out.println(new_op);
                m.setPC(m.getPC()+2);
                break;

            case 0xB000:
                m.setPC(opcode & 0x0FFF + m.getVx(0));
                System.out.println(new_op);
                m.setPC(m.getPC()+2);
                break;

                //void setVx (int newVx, int value)

            case 0xC000:
            // Sets VX to the result of a bitwise and operation on a 
            // random number (Typically: 0 to 255) and NN.
            
                System.out.println(new_op);
                Random rand = new Random();
                temp1 = rand.nextInt(0xFF);

                //get a number 0 - 16 
                temp3 = (opcode & 0x0F00 >> 8);
                
                temp2 = temp1 & (opcode & 0x00FF);

                m.setVx(temp3, temp2);
                m.setPC(m.getPC()+2);
                break;

            case 0xD000:		   

                int wtf = (m.getI() - (opcode & 0x000F)) * -1;  
                byte bytes [] = new byte [wtf] ;

                for (int i = m.getI(); i < (opcode & 0x000F); i++)
                {
                    bytes[i] = (byte) m.getMemoryIndividual(i);
                    m.setMemoryIndividual(bytes[i], m.getI());
                }
              
                int XOR_val = m.getBinItem((opcode & 0x0F00 >> 8), (opcode & 0x00F0 >> 4)) ^ (opcode & 0x000F);

                String x = String.format("%8s", Integer.toBinaryString(XOR_val)).replace(' ', '0');

                int test[] = new int[8];

                for(int i = 0; i < 7; i++) {
                    test[i] = Integer.parseInt(x.substring(i));
                }

                temp1 = opcode & 0x0F00 >> 8;
                temp2 = opcode & 0x00F0 >> 4;

                m.setBin(m.ADDBIN(test, temp1, temp2));    
                m.setPC(m.getPC()+2);
            
                break;

            case 0xE000:
                System.out.println(new_op);
                switch (opcode & 0x00FF)
                {
                    case 0x009E:
                    // Ex9E - SKP Vx
                    // Skip next instruction if key with the value of Vx is pressed.
                    // Checks the keyboard, and if the key corresponding to the value of Vx is currently in the down position, PC is increased by 2.
                    
                        break;

                   
                    case 0x00A1:
                    // ExA1 - SKNP Vx
                    // Skip next instruction if key with the value of Vx is not pressed.
                    // Checks the keyboard, and if the key corresponding to the value of Vx is currently in the up position, PC is increased by 2.
                        break; 
                }
                m.setPC(m.getPC()+2);
                break;

            case 0xF000:
                
                m.setPC(m.getPC()+2);
                switch (opcode & 0x00FF)
                {
                    case 0x0007:
                        m.setVx((opcode & 0x0F00 >> 8), time.getDT());
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x000A:
                        System.out.println("Store a keypress");
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0015:
                        time.setDT(opcode & 0x0F00 >> 8);
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0018:
                        time.setST(opcode & 0x0F00 >> 8);
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x001E:
                        m.setI(m.getI() + m.getVx((opcode & 0x0F00 >> 8)));
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0029:

                    // Set I = location of sprite for digit Vx.

                    // The value of I is set to the location for the 
                    // hexadecimal sprite corresponding to the value of Vx.
                        System.out.println("location of sprite");
                        m.setMemoryIndividual((byte) m.getVx((opcode & 0x0F00)), m.getI());
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0033:
                        temp1 = m.getVx(opcode & 0x0F00);
                        
                        m.setMemoryIndividual((byte) (temp1 % 1000),m.getI());
                        m.setMemoryIndividual((byte) (temp1 % 100),m.getI()+1);
                        m.setMemoryIndividual((byte) (temp1 % 10) ,m.getI()+2);
                        
                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0055:
                        byte memory_loc1 [] = new byte [(opcode & 0x0f00 >> 8)];
                        for (int i = 0 ; i < memory_loc1.length; i++)
                        {
                            memory_loc1[i] = (byte) m.getVx(i);
                        }
                       
                        m.setMemory(memory_loc1, m.getI());

                        m.setPC(m.getPC()+2);
                        break;

                    case 0x0065:
                        int temp1 = 0 ; 
                        // while (temp1 != (opcode & 0x0F00))
                        // {
                        //     System.out.println("Not sure LMAO");
                        // }
                        m.setPC(m.getPC()+2);
                        break;
                }
                
                m.setPC(m.getPC()+2);
                break;

                
        }
    }
}
