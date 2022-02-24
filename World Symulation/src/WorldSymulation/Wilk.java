package WorldSymulation;

public class Wilk extends Zwierze
{
    Wilk(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(9);
        this.setInicjatywa(5);
        this.setId("Wilk");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }
}