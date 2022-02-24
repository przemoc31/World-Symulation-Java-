package WorldSymulation;

public class Barszcz extends Roslina
{
    Barszcz(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(10);
        this.setInicjatywa(0);
        this.setId("Barszcz");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }

    @Override
    public void akcja()
    {
        super.akcja();
        if (this.getPolozenie_Y() + 1 < this.swiat.getSize_Y())
        {
            if ((this.swiat.getOrgMap(this.getPolozenie_Y() + 1, this.getPolozenie_X()) instanceof Zwierze) && (!(this.swiat.getOrgMap(this.getPolozenie_Y() + 1, this.getPolozenie_X()) instanceof CyberOwca)))
            {
                logger.log("%s%s%s%s%d%s%d%s", this.id, " zabija ", this.swiat.getOrgMap(this.getPolozenie_Y() + 1, this.getPolozenie_X()).getId(), " na polu ", this.getPolozenie_Y() + 1, " ", this.getPolozenie_X(), "\n");
                this.swiat.zabijOrganizm(this.getPolozenie_Y() + 1, this.getPolozenie_X());
            }
        }
        if (this.getPolozenie_Y() - 1 >= 0)
                {
            if ((this.swiat.getOrgMap(this.getPolozenie_Y() - 1, this.getPolozenie_X()) instanceof Zwierze) && (!(this.swiat.getOrgMap(this.getPolozenie_Y() - 1, this.getPolozenie_X()) instanceof CyberOwca)))
            {
                logger.log("%s%s%s%s%d%s%d%s", this.id, " zabija ", this.swiat.getOrgMap(this.getPolozenie_Y() - 1, this.getPolozenie_X()).getId(), " na polu ", this.getPolozenie_Y() - 1, " ", this.getPolozenie_X(), "\n");
                this.swiat.zabijOrganizm(this.getPolozenie_Y() - 1, this.getPolozenie_X());
            }
        }
        if (this.getPolozenie_X() + 1 < this.swiat.getSize_X())
        {
            if ((this.swiat.getOrgMap(this.getPolozenie_Y(), this.getPolozenie_X() + 1) instanceof Zwierze) && (!(this.swiat.getOrgMap(this.getPolozenie_Y(), this.getPolozenie_X() + 1) instanceof CyberOwca)))
            {
                logger.log("%s%s%s%s%d%s%d%s", this.id, " zabija ", this.swiat.getOrgMap(this.getPolozenie_Y(), this.getPolozenie_X() + 1).getId(), " na polu ", this.getPolozenie_Y(), " ", this.getPolozenie_X() + 1, "\n");
                this.swiat.zabijOrganizm(this.getPolozenie_Y(), this.getPolozenie_X() + 1);
            }
        }
        if (this.getPolozenie_X() - 1 >= 0)
        {
            if ((this.swiat.getOrgMap(this.getPolozenie_Y(), this.getPolozenie_X() - 1) instanceof Zwierze) && (!(this.swiat.getOrgMap(this.getPolozenie_Y(), this.getPolozenie_X() - 1) instanceof CyberOwca)))
            {
                logger.log("%s%s%s%s%d%s%d%s", this.id, " zabija ", this.swiat.getOrgMap(this.getPolozenie_Y(), this.getPolozenie_X() - 1).getId(), " na polu ", this.getPolozenie_Y(), " ", this.getPolozenie_X() - 1, "\n");
                this.swiat.zabijOrganizm(this.getPolozenie_Y(), this.getPolozenie_X() - 1);
            }
        }
    }

    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        if(attacker instanceof CyberOwca)
            super.kolizja(attacker, old_Y, old_X);
        else
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
}