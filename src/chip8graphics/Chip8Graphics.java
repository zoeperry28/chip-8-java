package chip8graphics;

import chip8functionality.MemoryMap;

import javax.swing.*;
import java.awt.*;

class Graphics extends JFrame 
{

    private static final long serialVersionUID = 1L;

    public Graphics() 
    {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        add( new BoardPanel());          //add underlaying board
        getGlassPane().setVisible(true);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
       // new Graphics();
    }
}

class BoardPanel extends JPanel 
{

    private static final long serialVersionUID = 1L;
    public static final int LENGTH = 32;
    public static final int HEIGHT = 64;
    private final Color[] COLOR_ARRAY = {Color.decode("#000000"), Color.decode("#FFFFFF")};

    public BoardPanel()
    {
        MemoryMap m = new MemoryMap();
        int [] bin = m.getBin();
        //grid layout 6x6
        setLayout(new GridLayout(LENGTH, HEIGHT));
        int numView = 0;
        //tiles color determined by odd/even
        for (int i = 0; i < HEIGHT; i++)
        {
            for (int j = 0; j < LENGTH; j++)
            {
                if (bin[numView] == 1) {
                    add(new TileView(COLOR_ARRAY[1]));
                }
                else if (bin[numView] == 0) {
                    add(new TileView(COLOR_ARRAY[0]));
                }
                numView++;
            }
        }
    }
}

class TileView extends JLabel
 {
    private static final long serialVersionUID = 1L;
    public static final int SIZE = 100;
    TileView(Color color) {
        setPreferredSize(new Dimension(SIZE, SIZE));
        setOpaque(true);
        setBackground(color);
    }
}
