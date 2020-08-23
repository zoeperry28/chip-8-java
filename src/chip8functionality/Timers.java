package chip8functionality;

public class Timers {

    static int DT = 60;
    static int ST = 60;

    void setDT (int value)
    {
        DT = value;
    }

    void setST (int value)
    {
        ST = value;
    }
    int getDT()
    {
        return DT;
    }

    int getST (int value)
    {
        return ST;
    }

    int delayTimer () throws InterruptedException
    {

        Thread.sleep(1);
        DT--;
        if (DT != 0)
        {
            DT = 0;
            return 1;
        }

        return -1;
    }

    int soundTimer () throws InterruptedException
    {
        if (ST != 0)
        {
            Thread.sleep(1);
            ST--;
        }
        else
        {
            ST = 0;
            return 1;
        }
        return -1;
    }
}
