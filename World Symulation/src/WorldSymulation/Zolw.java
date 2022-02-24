package WorldSymulation;

import java.util.Random;

public class Zolw extends Zwierze
{
    Zolw(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(2);
        this.setInicjatywa(1);
        this.setId("Zolw");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }

    @Override
    public void akcja()
    {
        Random random = new Random();
        int x = random.nextInt(4);
        if (x == 3)
            super.akcja();
        else
            logger.log("%s%s%d%s%d%s%d%s%d%s", this.getId(), ": ", this.polozenie_Y, " ", this.polozenie_X, " -> ", this.polozenie_Y, " ", this.polozenie_X, "\n");
    }

    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        if (attacker.getSila() < 5 && (!(attacker instanceof Zolw)))
        {
            logger.log("%s%s%c%s", attacker.getId(), " zaatakowal ", this.id, "\n");
            attacker.setPolozenie_Y(old_Y);
            attacker.setPolozenie_X(old_X);
            logger.log("%s%s%s%s%d%s%d%s", this.id, " odepchnal ", attacker.getId(), " na jego stare pole ", old_Y, " ", old_X, "\n");
        }
        else
        {
            super.kolizja(attacker, old_Y, old_X);
        }
    }
}