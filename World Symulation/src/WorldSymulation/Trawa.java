package WorldSymulation;

public class Trawa extends Roslina
{
    Trawa(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(0);
        this.setInicjatywa(0);
        this.setId("Trawa");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }
}
