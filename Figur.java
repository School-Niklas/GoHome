class Figur
{
    String farbe;
    int x;
    int y;

    Figur(String f, int startX, int startY)
    {
        farbe = f;
        x = startX;
        y = startY;
    }

    void druckePosition()
    {
        System.out.println("Figur mit farbe " + farbe + " bei " + x + " " + y);
    }

    boolean gewonnen()
    {
        if(x == 0 && y == 0)
            return true;
        else
            return false;
    }

    void gehe(int richtung)
    {
        if(richtung == 0)
            y++;
        if(richtung == 1)
            x++;
        if(richtung == 2)
            y--;
        if(richtung == 3)
            x--;
        // Springe falls notwendig an das andere Ende des Spielfeldes
        if(x < -2)
            x = 2;
        if(x > 2)
            x = -2;
        if(y < -2)
            y = 2;
        if(y > 2)
            y = -2;
    }
}