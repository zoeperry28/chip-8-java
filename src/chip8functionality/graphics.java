package chip8functionality;

import java.awt.*;
import javax.swing.*;

public class graphics extends JPanel 
{
int x = 0; 
int y = 0; 
private static final long serialVersionUID = 1L;
MemoryMap m = new MemoryMap () ; 
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g);  
        
       int [] bin = m.getBin();

        for (int i = 0; i < bin.length; i++)
        {
            if (i % 64 == 1 && i != 1) {
                y +=20;
                x = 0; 
            }
            if (bin[i] == 0)
            {
                g.setColor(Color.BLACK);
                g.fillRect(x, y, 20, 20);   
            }
            else if (bin[i] == 1)
            {
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 20, 20);   
            }    

            x+=20;
        }
    }
}