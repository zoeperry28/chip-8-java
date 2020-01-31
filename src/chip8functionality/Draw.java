package chip8functionality;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel 
{
    int x = 0;
    int y = 0;
    private static final long serialVersionUID = 1L;

   
    static MemoryMap m = new MemoryMap();
    static int[][] array = m.getBin();

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int x = 0;
        int y = 0;

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {

                if (j == 31) {
                    y += 20;
                    x = 0;
                }

                if (array[i][j] == 0) {
                    g.setColor(Color.BLACK);
                    g.fillRect(y, x, 20, 20);
                } else {
                    g.setColor(Color.WHITE);
                    g.fillRect(y, x, 20, 20);
                }

                x += 20;
            }
        }
    }

    static void setVisibleGraphics(int myarray[][]) {
        array = myarray;
    }
}


  