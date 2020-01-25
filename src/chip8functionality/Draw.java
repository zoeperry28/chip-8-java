package chip8functionality;

import java.awt.*;

import javax.swing.*;

public class Draw extends JPanel 
{
    int x = 0;
    int y = 0;
    private static final long serialVersionUID = 1L;


    MemoryMap m = new MemoryMap();

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        g.setColor(Color.black);

        int[][] array = new int[64][32];
        
        array = m.randomBin();
        int x = 0;
        int y = 0;

        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){

            if (j == 31) {
                y +=20;
                x = 0; 
            }

            if (array[i][j] == 1){
                g.setColor(Color.BLACK);
                g.fillRect(y, x, 20, 20);
            }
            else{
                g.setColor(Color.WHITE);
                g.fillRect(y, x, 20, 20);   
            }
            
            x+=20;

        }

        }
        
    }

    void drawPixel(int x, int y)
    {

    } 


}


  