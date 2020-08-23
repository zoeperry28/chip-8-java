package chip8functionality;

public class KeyPressed{

    int current_key = 0x0;

    public byte GET_Actual_Key_Press(char keyChar)
    {
        switch (keyChar)
        {
            case '1':
                return 0x01;
            case '2':
                return 0x02;
            case '3':
                return 0x03;
            case '4':
                return 0x0C;
            case 'q':
            case 'Q':
                return 0x04;
            case 'w':
            case 'W':
                return 0x05;
            case 'e':
            case 'E':
                return 0x06;
            case 'r':
            case 'R':
                return 0x0D;

            case 'a':
            case 'A':
                return 0x07;
            case 's':
            case 'S':
                return 0x08;
            case 'd':
            case 'D':
                return 0x09;
            case 'f':
            case 'F':
                return 0x0E;

            case 'z':
            case 'Z':
                return 0x0A;
            case 'x':
            case 'X':
                return 0x00;
            case 'c':
            case 'C':
                return 0x0B;
            case 'v':
            case 'V':
                return 0x0F;

        }
        return 0xF;
    }

}
