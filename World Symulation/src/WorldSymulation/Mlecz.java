package WorldSymulation;

public class Mlecz extends Roslina
{
    Mlecz(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(0);
        this.setInicjatywa(0);
        this.setId("Mlecz");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }
    @Override
    public void akcja()
    {
        for (int i = 0; i < 3; i++)
            super.akcja();
    }
}