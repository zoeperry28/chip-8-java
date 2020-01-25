package chip8functionality;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

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

        int array[] = new int [2048];
        try {
            array = randomizeArray();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        int x = 0;
        int y = 0;

        for(int i = 0; i < array.length; i++){

            if (i % 64 == 1 && i != 1) {
                y +=20;
                x = 0; 
            }

            if (array[i] == 1){
                g.setColor(Color.BLACK);
                g.fillRect(x, y, 20, 20);
            }
            else{
                g.setColor(Color.WHITE);
                g.fillRect(x, y, 20, 20);   
            }
            
            x+=20;

            
        }
        
    }

    int[] randomizeArray() throws IOException
    {
        /*
         * Temporary random number gen to make sure that 
         * the graphics work as expected
         */
        int temp [] = new int [2048];

        Random rand = new Random();
        
        for (int i = 0; i < 2048; i++)
        {
            temp[i] = 0 + rand.nextInt((1 - 0) + 1);
        }

        FileWriter writer = new FileWriter("newgraphics.bin");
        int len = temp.length;
        for (int i = 0; i < len; i++) {
           writer.write(temp[i] + "\t"+ "");
        }
        writer.close();

        // Write the bytes to the array 
        return temp; 
    }


}


  