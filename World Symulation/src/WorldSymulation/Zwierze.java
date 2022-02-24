package WorldSymulation;

import java.util.Random;

public class Zwierze extends Organizm
{
    @Override
    public void akcja()
    {
        int old_Y = this.polozenie_Y;
        int old_X = this.polozenie_X;
        logger.log("%s%s%d%s%d%s", this.getId(), ": ", this.polozenie_Y, " ", this.polozenie_X, " -> ");
        Random random = new Random();
        int x = random.nextInt(4);
        if (x == 0)
        {
            if(this.polozenie_Y + 1 == this.swiat.getSize_Y())
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
        logger.log("%d%s%d%s", this.polozenie_Y, " ", this.polozenie_X, "\n");

        if (this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X) != null)
            this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X).kolizja(this, old_Y, old_X);
    }
    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        if ((attacker instanceof Wilk) && (this instanceof Wilk))
        {
            attacker.setPolozenie_Y(old_Y);
            attacker.setPolozenie_X(old_X);
            logger.log("%s%s%d%s%d%s", attacker.getId(), " wraca na pole ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), "\n");
            if (attacker.getWiek() > 3 && this.wiek > 3)
            {
                if (this.polozenie_Y + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null)
                {
                    Wilk temp = new Wilk(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                }
			else if (this.polozenie_Y - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null)
                {
                    Wilk temp = new Wilk(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                }
			else if (this.polozenie_X + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null)
                {
                    Wilk temp = new Wilk(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                }
			else if (this.polozenie_X - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null)
                {
                    Wilk temp = new Wilk(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                }
			else if (attacker.getPolozenie_Y() + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X()) == null)
                {
                    Wilk temp = new Wilk(this.swiat, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() + 1, " ", attacker.getPolozenie_X(), "\n");
                }
			else if (attacker.getPolozenie_Y() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X()) == null)
                {
                    Wilk temp = new Wilk(this.swiat, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() - 1, " ", attacker.getPolozenie_X(), "\n");
                }
			else if (attacker.getPolozenie_X() + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1) == null)
                {
                    Wilk temp = new Wilk(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() + 1, "\n");
                }
			else if (attacker.getPolozenie_X() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1) == null)
                {
                    Wilk temp = new Wilk(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() - 1, "\n");
                }
			else
                {
                    logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                }
            }
        }
        else if ((attacker instanceof Owca) && (this instanceof Owca))
        {
            attacker.setPolozenie_Y(old_Y);
            attacker.setPolozenie_X(old_X);
            logger.log("%s%s%d%s%d%s", attacker.getId(), " wraca na pole ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), "\n");
            if (attacker.getWiek() > 3 && this.wiek > 3)
            {
                if (this.polozenie_Y + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null)
                {
                    Owca temp = new Owca(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                }
                else if (this.polozenie_Y - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null)
                {
                    Owca temp = new Owca(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                }
                else if (this.polozenie_X + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null)
                {
                    Owca temp = new Owca(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                }
                else if (this.polozenie_X - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null)
                {
                    Owca temp = new Owca(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                }
                else if (attacker.getPolozenie_Y() + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X()) == null)
                {
                    Owca temp = new Owca(this.swiat, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() + 1, " ", attacker.getPolozenie_X(), "\n");
                }
                else if (attacker.getPolozenie_Y() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X()) == null)
                {
                    Owca temp = new Owca(this.swiat, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() - 1, " ", attacker.getPolozenie_X(), "\n");
                }
                else if (attacker.getPolozenie_X() + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1) == null)
                {
                    Owca temp = new Owca(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() + 1, "\n");
                }
                else if (attacker.getPolozenie_X() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1) == null)
                {
                    Owca temp = new Owca(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() - 1, "\n");
                }
                else
                {
                    logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                }
            }
        }
        else if ((attacker instanceof Zolw) && (this instanceof Zolw))
        {
            attacker.setPolozenie_Y(old_Y);
            attacker.setPolozenie_X(old_X);
            logger.log("%s%s%d%s%d%s", attacker.getId(), " wraca na pole ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), "\n");
            if (attacker.getWiek() > 3 && this.wiek > 3)
            {
                if (this.polozenie_Y + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null)
                {
                    Zolw temp = new Zolw(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                }
                else if (this.polozenie_Y - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null)
                {
                    Zolw temp = new Zolw(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                }
                else if (this.polozenie_X + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null)
                {
                    Zolw temp = new Zolw(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                }
                else if (this.polozenie_X - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null)
                {
                    Zolw temp = new Zolw(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                }
                else if (attacker.getPolozenie_Y() + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X()) == null)
                {
                    Zolw temp = new Zolw(this.swiat, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() + 1, " ", attacker.getPolozenie_X(), "\n");
                }
                else if (attacker.getPolozenie_Y() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X()) == null)
                {
                    Zolw temp = new Zolw(this.swiat, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() - 1, " ", attacker.getPolozenie_X(), "\n");
                }
                else if (attacker.getPolozenie_X() + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1) == null)
                {
                    Zolw temp = new Zolw(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() + 1, "\n");
                }
                else if (attacker.getPolozenie_X() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1) == null)
                {
                    Zolw temp = new Zolw(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() - 1, "\n");
                }
                else
                {
                    logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                }
            }
        }
        else if ((attacker instanceof Lis) && (this instanceof Lis))
        {
            attacker.setPolozenie_Y(old_Y);
            attacker.setPolozenie_X(old_X);
            logger.log("%s%s%d%s%d%s", attacker.getId(), " wraca na pole ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), "\n");
            if (attacker.getWiek() > 3 && this.wiek > 3)
            {
                if (this.polozenie_Y + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(this.polozenie_Y + 1, this.polozenie_X) == null)
                {
                    Lis temp = new Lis(this.swiat, this.polozenie_Y + 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y + 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y + 1, " ", this.polozenie_X, "\n");
                }
                else if (this.polozenie_Y - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y - 1, this.polozenie_X) == null)
                {
                    Lis temp = new Lis(this.swiat, this.polozenie_Y - 1, this.polozenie_X, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y - 1, this.polozenie_X);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y - 1, " ", this.polozenie_X, "\n");
                }
                else if (this.polozenie_X + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X + 1) == null)
                {
                    Lis temp = new Lis(this.swiat, this.polozenie_Y, this.polozenie_X + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X + 1, "\n");
                }
                else if (this.polozenie_X - 1 >= 0 && this.swiat.getOrgMap(this.polozenie_Y, this.polozenie_X - 1) == null)
                {
                    Lis temp = new Lis(this.swiat, this.polozenie_Y, this.polozenie_X - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, this.polozenie_Y, this.polozenie_X - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", this.polozenie_Y, " ", this.polozenie_X - 1, "\n");
                }
                else if (attacker.getPolozenie_Y() + 1 < this.swiat.getSize_Y() && this.swiat.getOrgMap(attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X()) == null)
                {
                    Lis temp = new Lis(this.swiat, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() + 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() + 1, " ", attacker.getPolozenie_X(), "\n");
                }
                else if (attacker.getPolozenie_Y() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X()) == null)
                {
                    Lis temp = new Lis(this.swiat, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X(), 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y() - 1, attacker.getPolozenie_X());
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y() - 1, " ", attacker.getPolozenie_X(), "\n");
                }
                else if (attacker.getPolozenie_X() + 1 < this.swiat.getSize_X() && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1) == null)
                {
                    Lis temp = new Lis(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() + 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() + 1, "\n");
                }
                else if (attacker.getPolozenie_X() - 1 >= 0 && this.swiat.getOrgMap(attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1) == null)
                {
                    Lis temp = new Lis(this.swiat, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1, 0);
                    this.swiat.stworzOrganizm(temp);
                    this.swiat.ustawOrganizm(temp, attacker.getPolozenie_Y(), attacker.getPolozenie_X() - 1);
                    logger.log("%s%s%s%d%s%d%s", "Rodzi sie ", this.id, " na polu ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X() - 1, "\n");
                }
                else
                {
                    logger.log("%s%s%d%s%d%s%s%s%d%s%d%s", this.id, " ", this.polozenie_Y, " ", this.polozenie_X, " i ", attacker.getId(), " ", attacker.getPolozenie_Y(), " ", attacker.getPolozenie_X(), " probawaly sie rozmnozyc ale brak miejsca\n");
                }
            }
        }
        else
        {
            if (this.sila > attacker.getSila())
            {
                logger.log("%s%s%s%s", this.id, " zabija ", attacker.getId(), "\n");
                attacker.setPolozenie_Y(old_Y);
                attacker.setPolozenie_X(old_X);
                this.swiat.zabijOrganizm(old_Y, old_X);
            }
            else
            {
                logger.log("%s%s%s%s", attacker.getId(), " zabija ", this.id, "\n");
                int thisY = this.polozenie_Y;
                int thisX = this.polozenie_X;
                attacker.setPolozenie_Y(old_Y);
                attacker.setPolozenie_X(old_X);
                this.swiat.zabijOrganizm(thisY, thisX);
                attacker.setPolozenie_Y(thisY);
                attacker.setPolozenie_X(thisX);
            }
        }

    }
}