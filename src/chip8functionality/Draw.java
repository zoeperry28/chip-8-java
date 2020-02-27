package chip8functionality;
import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Draw extends JPanel implements KeyListener
{
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

    static void setVisibleGraphics(int myarray[][]) 
    {
        array = myarray;
    }

    /*
     * When a key is pressed, set the value of the key
     * in the memory map, as the key value is required
     * by two of the opcodes. 
     * 
     * May need a boolean value in order to denote if the
     * key value has changed or not. 
     */ 
    
    @Override
    public void keyTyped(KeyEvent e) 
    {
        System.out.println("keyTyped: "+e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        System.out.println("STUB");
    }

    @Override
    public void keyReleased(KeyEvent e) {
        System.out.println("STUB");
    }
}


  