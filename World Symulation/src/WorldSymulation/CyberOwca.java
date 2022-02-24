package WorldSymulation;

import java.util.Arrays;
import java.util.Random;

public class CyberOwca extends Zwierze
{
    CyberOwca(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(11);
        this.setInicjatywa(4);
        this.setId("CyberOwca");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }

    @Override
    public void akcja()
    {
        Organizm barszcz = null;
        int destinyRange = this.swiat.getSize_Y() + this.swiat.getSize_X();
        int tempRange;
        for (int i = 0; i < this.swiat.objects.size(); i++)
        {
            if(this.swiat.objects.get(i) instanceof Barszcz)
            {
                Organizm tempOrg = this.swiat.objects.get(i);
                tempRange = Math.abs(this.polozenie_Y - tempOrg.getPolozenie_Y()) + Math.abs(this.polozenie_X - tempOrg.getPolozenie_X());
                if(tempRange < destinyRange)
                {
                    destinyRange = tempRange;
                    barszcz = tempOrg;
                }
            }
        }
        if(barszcz == null)
            super.akcja();
        else
        {
            int old_Y = this.polozenie_Y;
            int old_X = this.polozenie_X;
            Boolean[] tab = new Boolean[4];
            Arrays.fill(tab, Boolean.TRUE);
            while (true) {
                Random random = new Random();
                int x = random.nextInt(4);
                if (x == 0 && tab[0] == true) {
                    if (Math.abs(this.polozenie_Y + 1 - barszcz.getPolozenie_Y()) + Math.abs(this.polozenie_X - barszcz.getPolozenie_X()) < destinyRange) {
                        this.setPolozenie_Y(this.polozenie_Y + 1);
                        logger.log("%s%s%d%s%d%s%d%s%d%s", this.getId(), ": ", old_Y, " ", old_X, " -> ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                        break;
                    } else {
                        tab[x] = false;
                    }
                } else if (x == 1 && tab[1] == true) {
                    if (Math.abs(this.polozenie_Y - 1 - barszcz.getPolozenie_Y()) + Math.abs(this.polozenie_X - barszcz.getPolozenie_X()) < destinyRange) {
                        this.setPolozenie_Y(this.polozenie_Y - 1);
                        logger.log("%s%s%d%s%d%s%d%s%d%s", this.getId(), ": ", old_Y, " ", old_X, " -> ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                        break;
                    } else {
                        tab[x] = false;
                    }
                } else if (x == 2 && tab[2] == true) {
                    if (Math.abs(this.polozenie_Y - barszcz.getPolozenie_Y()) + Math.abs(this.polozenie_X + 1 - barszcz.getPolozenie_X()) < destinyRange) {
                        this.setPolozenie_X(this.polozenie_X + 1);
                        logger.log("%s%s%d%s%d%s%d%s%d%s", this.getId(), ": ", old_Y, " ", old_X, " -> ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                        break;
                    } else {
                        tab[x] = false;
                    }
                } else if (x == 3 && tab[3] == true) {
                    if (Math.abs(this.polozenie_Y - barszcz.getPolozenie_Y()) + Math.abs(this.polozenie_X - 1 - barszcz.getPolozenie_X()) < destinyRange) {
                        this.setPolozenie_X(this.polozenie_X - 1);
                        logger.log("%s%s%d%s%d%s%d%s%d%s", this.getId(), ": ", old_Y, " ", old_X, " -> ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                        break;
                    } else {
                        tab[x] = false;
                    }
                }
            }
            if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
                this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X).kolizja(this, old_Y, old_X);
        }
    }

}