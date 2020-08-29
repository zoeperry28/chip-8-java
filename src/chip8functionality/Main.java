package chip8functionality;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException, InterruptedException {

        char KEY_PRESSED = '-';
        MemoryMap m = new MemoryMap();

        Draw g = new Draw(64,32);
        m.setDraw(g);

        JFrame app = new JFrame("CHIP8");
        app.add(g, BorderLayout.CENTER);
        app.setSize(1290, 640);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        KeyPressed k = new KeyPressed();
        app.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getKeyChar() == 'f'){
                    System.out.print("YES");

                    k.GET_Actual_Key_Press(e.getKeyChar());
                }
            }

            @Override
            public void keyPressed(KeyEvent e) { }

            @Override
            public void keyReleased(KeyEvent e) { }

            //more code
        });
        //more code


        app.setFocusable(true);
        app.setVisible(true);

        boolean chip8Running = true;
        InstructionSet i = new InstructionSet();
        RomRead r = new RomRead();

        try {
            r.initBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int inc = 0 ;
        while (chip8Running) {
            int temp = m.getPC();
            int x = m.getMemory(temp) & 0x00FF;
            int second_byte = m.getMemory(temp+1) & 0xFF;
            int OPCODE = second_byte | ( x << 8);

            g = i.processOpcode(OPCODE, k, g);

            g.repaint();
            //inc++;
            if(inc == 30)
            {
                chip8Running = false;
            }

        }
    }



}

