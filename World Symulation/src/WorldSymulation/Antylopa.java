package WorldSymulation;

import java.util.Arrays;
import java.util.Random;

public class Antylopa extends Zwierze
{
    Antylopa(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(4);
        this.setInicjatywa(4);
        this.setId("Antylopa");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }

    @Override
    public void akcja()
    {
        int old_Y = this.polozenie_Y;
        int old_X = this.polozenie_X;

        while (true)
        {
            Random random = new Random();
            int x = random.nextInt(4);
            if (x == 0)
            {
                if (this.polozenie_Y + 2 < this.swiat.getSize_Y())
                {
                    this.setPolozenie_Y(this.polozenie_Y + 2);
                    break;
                }
            }
            else if (x == 1)
            {
                if (this.polozenie_Y - 2 >= 0)
                {
                    this.setPolozenie_Y(this.polozenie_Y - 2);
                    break;
                }
            }
            else if (x == 2)
            {
                if (this.polozenie_X + 2 < this.swiat.getSize_X())
                {
                    this.setPolozenie_X(this.polozenie_X + 2);
                    break;
                }
            }
            else
            {
                if (this.polozenie_X - 2 >= 0)
                {
                    this.setPolozenie_X(this.polozenie_X - 2);
                    break;
                }
            }
        }
        logger.log("%s%sd%s%d%s%d%s%d%s", this.id, ": ", old_Y, " ", old_X," -> ", this.polozenie_Y, " ", this.polozenie_X, "\n");

        if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
        this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X).kolizja(this, old_Y, old_X);
    }

    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        if (attacker instanceof Antylopa)
        {
            attacker.setPolozenie_Y(old_Y);
            attacker.setPolozenie_X(old_X);
            logger.log("%s%s%d%s%d%s", attacker.getId(), " wraca na pole ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), "\n");
            if (attacker.getWiek() > 3 && this.wiek > 3)
            {
                if (this.polozenie_Y == attacker.getPolozenie_Y())
                {
                    if (this.polozenie_X > attacker.getPolozenie_X())
                    {
                        if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null)
                        {
                            Antylopa temp = new Antylopa(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                            this.swiat.stworzOrganizm(temp);
                            this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                            logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                        }
                        else
                            logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");

                    }
                    else
                    {
                        if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null)
                        {
                            Antylopa temp = new Antylopa(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                            this.swiat.stworzOrganizm(temp);
                            this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                            logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                        }
					    else
                            logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                    }

                }
                else
                {
                    if (this.polozenie_Y > attacker.getPolozenie_Y())
                    {
                        if (this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null)
                        {
                            Antylopa temp = new Antylopa(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                            this.swiat.stworzOrganizm(temp);
                            this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                            logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                        }
					    else
                            logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                    }
				    else
                    {
                        if (this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null)
                        {
                            Antylopa temp = new Antylopa(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                            this.swiat.stworzOrganizm(temp);
                            this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                            logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                        }
					    else
                        logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                    }
                }

            }
        }
        else
        {
            Random random = new Random();
            int x = random.nextInt(2);
            if (x == 1)
                super.kolizja(attacker, old_Y, old_X);
            else
            {
                logger.log("%s%s%s%s", attacker.getId(), " zaatakowal ", this.id, "\n");
                Boolean[] tab = new Boolean[4];
                Arrays.fill(tab, Boolean.TRUE);
                while (true)
                {
                    x = random.nextInt(4);
                    if (x == 0 && tab[0] == true)
                    {
                        if ((this.polozenie_Y + 1 < this.swiat.getSize_Y()) && (this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null))
                        {
                            this.setPolozenie_Y(this.polozenie_Y + 1);
                            logger.log("%s%s%d%s%d%s", this.id, " ucieka na pole ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                            this.swiat.ustawOrganizm(this, this.polozenie_Y, this.polozenie_X);
                            break;
                        }
					    else
                        {
                            tab[x] = false;
                        }
                    }
                    else if (x == 1 && tab[1] == true)
                    {
                        if ((this.polozenie_Y - 1 < this.swiat.getSize_Y()) && (this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null))
                        {
                            this.setPolozenie_Y(this.polozenie_Y - 1);
                            logger.log("%s%s%d%s%d%s", this.id, " ucieka na pole ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                            this.swiat.ustawOrganizm(this, this.polozenie_Y, this.polozenie_X);
                            break;
                        }
                        else
                        {
                            tab[x] = false;
                        }
                    }
                    else if (x == 2 && tab[2] == true)
                    {
                        if ((this.polozenie_X + 1 < this.swiat.getSize_X()) && (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null))
                        {
                            this.setPolozenie_X(this.polozenie_X + 1);
                            logger.log("%s%s%d%s%d%s", this.id, " ucieka na pole ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                            this.swiat.ustawOrganizm(this, this.polozenie_Y, this.polozenie_X);
                            break;
                        }
					    else
                        {
                            tab[x] = false;
                        }
                    }
                    else if (x == 3 && tab[3] == true)
                    {
                        if ((this.polozenie_X - 1 < this.swiat.getSize_X()) && (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null))
                        {
                            this.setPolozenie_X(this.polozenie_X - 1);
                            logger.log("%s%s%d%s%d%s", this.id, " ucieka na pole ", this.polozenie_Y, " ", this.polozenie_X, "\n");
                            this.swiat.ustawOrganizm(this, this.polozenie_Y, this.polozenie_X);
                            break;
                        }
                        else
                        {
                            tab[x] = false;
                        }
                    }
                    else
                    {
                        if (!tab[0] && !tab[1] && !tab[2] && !tab[3])
                        {
                            super.kolizja(attacker, old_Y, old_X);
                            break;
                        }

                    }
                }
            }
        }
    }

}