package WorldSymulation;

public class Jagody extends Roslina
{
    Jagody(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(99);
        this.setInicjatywa(0);
        this.setId("Jagody");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }
    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        logger.log("%s%s%s%s", attacker.getId(), " zjada ", this.id, "\n");
        logger.log("%s%s%s%s", this.id, " zabija ", attacker.getId(), "\n");
        int thisY = this.polozenie_Y;
        int thisX = this.polozenie_X;
        attacker.setPolozenie_Y(old_Y);
        attacker.setPolozenie_X(old_X);
        this.swiat.zabijOrganizm(thisY, thisX);
        this.swiat.zabijOrganizm(old_Y, old_X);
    }
}
