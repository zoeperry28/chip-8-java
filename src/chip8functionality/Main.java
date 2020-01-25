package chip8functionality;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.ThreadFactory;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;

public class Main extends JPanel {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException {
        MemoryMap m = new MemoryMap();

        Draw g = new Draw();

        JFrame app = new JFrame("CHIP8");
        app.add(g, BorderLayout.CENTER);
        app.setSize(1290, 640);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);

        boolean chip8Running = true;
        InstructionSet i = new InstructionSet();
        RomRead r = new RomRead();

        try {
            r.initBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (chip8Running) {
            int x = m.getMemory(m.getPC());
            i.processOpcode(x);
            System.out.println(x);
            g.randomizeArray();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            g.repaint();

            
        }
    }
}

