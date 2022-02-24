package WorldSymulation;

import java.util.Random;

public class Czlowiek extends Zwierze
{
    Czlowiek(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(5);
        this.setInicjatywa(4);
        this.setId("Czlowiek");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
        this.direction = null;
        this.moc = "DOSTEPNA";
        this.cooldown = 0;
    }

    public String getMoc() { return this.moc; }
    public void setMoc( String newMoc ) { this.moc = newMoc; }
    public int getCooldown() { return this.cooldown; }
    public void setCooldown( int newCooldown ) { this.cooldown = newCooldown; }

    public void niesmiertelnosc(Organizm defender, int old_Y, int old_X)
    {
        if (this.sila > defender.getSila())
        {
            defender.kolizja(this, old_Y, old_X);
        }
    else
        {
            int Y = this.getPolozenie_Y();
            int X = this.getPolozenie_X();
            this.logger.log("%s%s%d%s%d", this.id, " ucieka z ", this.getPolozenie_Y(), " ", this.getPolozenie_X());

            Random random = new Random();
            int x = random.nextInt(4);
            if (x == 0)
            {
                if (this.polozenie_Y + 1 == this.swiat.getSize_Y())
                this.setPolozenie_Y(this.polozenie_Y - 1);
            else
                this.setPolozenie_Y(this.polozenie_Y + 1);
            }
            else if (x == 1)
            {
                if (this.polozenie_Y - 1 < 0)
                this.setPolozenie_Y(this.polozenie_Y + 1);
            else
                this.setPolozenie_Y(this.polozenie_Y - 1);
            }
            else if (x == 2)
            {
                if (this.polozenie_X + 1 == this.swiat.getSize_X())
                this.setPolozenie_X(this.polozenie_X - 1);
            else
                this.setPolozenie_X(this.polozenie_X + 1);
            }
            else
            {
                if (this.polozenie_X - 1 < 0)
                this.setPolozenie_X(this.polozenie_X + 1);
            else
                this.setPolozenie_X(this.polozenie_X - 1);
            }
            this.logger.log("%s%d%s%d%s", " na ", this.getPolozenie_Y(), " ", this.getPolozenie_X(), "\n");

            if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
            {
                niesmiertelnosc(this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X), Y, X);
            }
        }
    }

    @Override
    public void akcja()
    {
        int old_Y = this.polozenie_Y;
        int old_X = this.polozenie_X;
        logger.log("%s%s%d%s%d%s", this.getId(), ": ", this.polozenie_Y, " ", this.polozenie_X, " -> ");

        if(direction == "UP" && ((this.polozenie_Y - 1) >= 0))
            this.polozenie_Y--;
        else if(direction == "DOWN" && ((this.polozenie_Y + 1) < this.swiat.getSize_Y()))
            this.polozenie_Y++;
        else if(direction == "LEFT" && ((this.polozenie_X - 1) >= 0))
            this.polozenie_X--;
        else if(direction == "RIGHT" && ((this.polozenie_X + 1) < this.swiat.getSize_X()))
            this.polozenie_X++;

        logger.log("%d%s%d%s", this.polozenie_Y, " ", this.polozenie_X, "\n");

        if (moc != "AKTYWNA")
        {
            if((this.polozenie_Y != old_Y) || (this.polozenie_X != old_X))
            {
                if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
                    this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X).kolizja(this, old_Y, old_X);
            }
        }
        else
        {
            this.swiat.usunOrganizm(old_Y, old_X);
            if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
            niesmiertelnosc(this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X), old_Y, old_X);
        }

        if (moc == "AKTYWNA")
            cooldown++;
        else if (moc == "NIEDOSTEPNA")
            cooldown--;

        if (cooldown == 5)
            moc = "NIEDOSTEPNA";
        else if (cooldown == 0)
            moc = "DOSTEPNA";
    }

    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        if(moc == "AKTYWNA")
        {
            if (this.sila > attacker.getSila())
            {
                this.logger.log("%s%s%s%s", this.id, " zabija ", attacker.getId(), "\n");
                attacker.setPolozenie_Y(old_Y);
                attacker.setPolozenie_X(old_X);
                this.swiat.zabijOrganizm(old_Y, old_X);
            }
        else
            {
                int Y = this.getPolozenie_Y();
                int X = this.getPolozenie_X();
                this.logger.log("%s%s%d%s%d", this.id, " ucieka z ", this.getPolozenie_Y(), " ", this.getPolozenie_X());

                Random random = new Random();
                int x = random.nextInt(4);
                if (x == 0)
                {
                    if (this.polozenie_Y + 1 == this.swiat.getSize_Y())
                    this.setPolozenie_Y(this.polozenie_Y - 1);
                else
                    this.setPolozenie_Y(this.polozenie_Y + 1);
                }
                else if (x == 1)
                {
                    if (this.polozenie_Y - 1 < 0)
                    this.setPolozenie_Y(this.polozenie_Y + 1);
                else
                    this.setPolozenie_Y(this.polozenie_Y - 1);
                }
                else if (x == 2)
                {
                    if (this.polozenie_X + 1 == this.swiat.getSize_X())
                    this.setPolozenie_X(this.polozenie_X - 1);
                else
                    this.setPolozenie_X(this.polozenie_X + 1);
                }
                else
                {
                    if (this.polozenie_X - 1 < 0)
                    this.setPolozenie_X(this.polozenie_X + 1);
                else
                    this.setPolozenie_X(this.polozenie_X - 1);
                }
                this.logger.log("%s%d%s%d%s", " na ", this.getPolozenie_Y(), " ", this.getPolozenie_X(), "\n");

                if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
                niesmiertelnosc(this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X), Y, X);

                this.swiat.ustawOrganizm(this, this.polozenie_Y, this.polozenie_X);
            }
        }
        else
            super.kolizja(attacker, old_Y, old_X);

    }

    private String moc;
    private int cooldown;
}
