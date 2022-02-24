package WorldSymulation;
import java.util.*;

public class Lis extends Zwierze {
    Lis(Swiat newSwiat, int y, int x, int newWiek) {
        this.setSila(3);
        this.setInicjatywa(7);
        this.setId("Lis");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }

    @Override
    public void akcja() {
        int old_Y = this.polozenie_Y;
        int old_X = this.polozenie_X;
        logger.log("%s%s%d%s%d%s", this.getId(), ": ", this.polozenie_Y, " ", this.polozenie_X, " -> ");
        Boolean[] tab = new Boolean[4];
        Arrays.fill(tab, Boolean.TRUE);
        while (true) {
            Random random = new Random();
            int x = random.nextInt(4);
            if (x == 0 && tab[0] == true) {
                if ((this.polozenie_Y + 1 < this.swiat.getSize_Y()) && (((this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null) || this.getSila() >= this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X).getSila()))) {
                    this.setPolozenie_Y(this.polozenie_Y + 1);
                    break;
                } else {
                    tab[x] = false;
                }
            } else if (x == 1 && tab[1] == true) {
                if ((this.polozenie_Y - 1 >= 0) && (((this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null) || this.getSila() >= this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X).getSila()))) {
                    this.setPolozenie_Y(this.polozenie_Y - 1);
                    break;
                } else {
                    tab[x] = false;
                }
            } else if (x == 2 && tab[2] == true) {
                if ((this.polozenie_X + 1 < this.swiat.getSize_X()) && (((this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null) || this.getSila() >= this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1).getSila()))) {
                    this.setPolozenie_X(this.polozenie_X + 1);
                    break;
                } else {
                    tab[x] = false;
                }
            } else if (x == 3 && tab[3] == true) {
                if ((this.polozenie_X - 1 >= 0) && (((this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null) || this.getSila() >= this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1).getSila()))) {
                    this.setPolozenie_X(this.polozenie_X - 1);
                    break;
                } else {
                    tab[x] = false;
                }
            } else {
                if (!tab[0] && !tab[1] && !tab[2] && !tab[3])
                    break;
            }
        }
        logger.log("%d%s%d%s", this.polozenie_Y, " ", this.polozenie_X, "\n");
        if (this.getPolozenie_Y() != old_Y || this.getPolozenie_Y() != old_X)
        {
            if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
                this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X).kolizja(this, old_Y, old_X);
        }
    }
}
