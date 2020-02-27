package chip8functionality;

import java.io.IOException;
import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {

    private static final long serialVersionUID = 1L;

    public static void main(String[] args) throws IOException {
        MemoryMap m = new MemoryMap();

        Draw g = new Draw();
        m.setDraw(g);

        JFrame app = new JFrame("CHIP8");
        app.add(g, BorderLayout.CENTER);
        app.setSize(1290, 640);
        app.setLocationRelativeTo(null);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.addKeyListener(g);
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

        while (chip8Running) {
            int temp = m.getPC();
            int x = m.getMemory(temp);
            i.processOpcode(x);
            System.out.println(x);

            g.repaint();
            m.setDraw(g);

        }
    }
}

