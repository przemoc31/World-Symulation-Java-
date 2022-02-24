package WorldSymulation;

public class Owca extends Zwierze
{
    Owca(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(4);
        this.setInicjatywa(4);
        this.setId("Owca");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }
}