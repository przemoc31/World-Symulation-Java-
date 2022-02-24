package WorldSymulation;

import java.util.Arrays;
import java.util.Random;

public class Roslina extends Organizm
{
    @Override
    public void akcja()
    {
        if (this.wiek > 3)
        {
            Random random = new Random();
            int x = random.nextInt(100);
            if (x == 0)
            {
                Boolean[] tab = new Boolean[4];
                Arrays.fill(tab, Boolean.TRUE);
                while (true)
                {
                    x = random.nextInt(4);
                    if (x == 0 && tab[0] == true)
                    {
                        if ((this.polozenie_Y + 1 < this.swiat.getSize_Y()) && (this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null))
                        {
                            if (this instanceof Trawa)
                            {
                                Trawa temp = new Trawa(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Mlecz)
                            {
                                Mlecz temp = new Mlecz(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Guarana)
                            {
                                Guarana temp = new Guarana(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Jagody)
                            {
                                Jagody temp = new Jagody(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Barszcz)
                            {
                                Barszcz temp = new Barszcz(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                            }
                            break;
                        }
					    else
                        {
                            tab[x] = false;
                        }
                    }
                    else if (x == 1 && tab[1] == true)
                    {
                        if ((this.polozenie_Y - 1 >= 0) && (this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null))
                        {
                            if (this instanceof Trawa)
                            {
                                Trawa temp = new Trawa(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Mlecz)
                            {
                                Mlecz temp = new Mlecz(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Guarana)
                            {
                                Guarana temp = new Guarana(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Jagody)
                            {
                                Jagody temp = new Jagody(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                            }
                            else if (this instanceof Barszcz)
                            {
                                Barszcz temp = new Barszcz(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                            }
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
                            if (this instanceof Trawa)
                            {
                                Trawa temp = new Trawa(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                            }
                            else if (this instanceof Mlecz)
                            {
                                Mlecz temp = new Mlecz(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                            }
                            else if (this instanceof Guarana)
                            {
                                Guarana temp = new Guarana(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                            }
                            else if (this instanceof Jagody)
                            {
                                Jagody temp = new Jagody(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                            }
                            else if (this instanceof Barszcz)
                            {
                                Barszcz temp = new Barszcz(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                            }
                            break;
                        }
					else
                        {
                            tab[x] = false;
                        }
                    }
                    else if (x == 3 && tab[3] == true)
                    {
                        if ((this.polozenie_X - 1 >= 0) && (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null))
                        {
                            if (this instanceof Trawa)
                            {
                                Trawa temp = new Trawa(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                            }
                            else if (this instanceof Mlecz)
                            {
                                Mlecz temp = new Mlecz(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                            }
                            else if (this instanceof Guarana)
                            {
                                Guarana temp = new Guarana(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                            }
                            else if (this instanceof Jagody)
                            {
                                Jagody temp = new Jagody(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                            }
                            else if (this instanceof Barszcz)
                            {
                                Barszcz temp = new Barszcz(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                                this.swiat.stworzOrganizm(temp);
                                this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                                logger.log("%s%s%s%d%s%d%s", "Powstaje ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                            }
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
                            logger.log("%s%s%s", "Roslina ", this.id, " nie moze sie rozprzestrzenic ze wzgledu na brak miejsca\n");
                            break;
                        }

                    }
                }
            }
        }
    }
    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        logger.log("%s%s%s%s", attacker.getId(), " zjada ", this.id, "\n");
        int thisY = this.polozenie_Y;
        int thisX = this.polozenie_X;
        attacker.setPolozenie_Y(old_Y);
        attacker.setPolozenie_X(old_X);
        this.swiat.zabijOrganizm(thisY, thisX);
        attacker.setPolozenie_Y(thisY);
        attacker.setPolozenie_X(thisX);
    }
}
