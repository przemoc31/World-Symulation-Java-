package WorldSymulation;

import java.util.*;
import java.io.*;
public class Swiat
{

    private Organizm aNull;

    Swiat(int wysokosc, int szerokosc)
    {
        this.logger = logger;
        this.orgMap = new Organizm[wysokosc][szerokosc];
        this.size_X = szerokosc;
        this.size_Y = wysokosc;
        for (int i = 0; i < wysokosc; i++)
        {
            for (int j = 0; j < szerokosc; j++)
            {
                orgMap[i][j] = null;
            }
        }
    }
    public int getSize_X() { return size_X; }
    public int getSize_Y() { return size_Y; }
    public void setSize_X(int newSizeX) { this.size_X = newSizeX; }
    public void setSize_Y(int newSizeY) { this.size_Y = newSizeY; }
    public Organizm getOrgMap(int y, int x) { return this.orgMap[y][x]; }

    public void setOrgMap(int y, int x, Organizm newOrg)
    {
        this.orgMap[y][x] = newOrg;
    }
    public void setLogger(Logger guilogger) { this.logger = guilogger; };

    public Vector<Organizm> sortObjects(Vector<Organizm> objects)
    {
        for (int i = 0; i < objects.size(); i++)
        {
            for (int j = 0; j < objects.size(); j++)
            {
                if (objects.get(i).getInicjatywa() > objects.get(j).getInicjatywa())
                    Collections.swap(objects, i, j);
			    else if ((objects.get(i).getInicjatywa() == objects.get(j).getInicjatywa()) && (objects.get(i).getWiek() > objects.get(j).getWiek()))
                    Collections.swap(objects, i, j);
            }
        }
        return objects;
    }

    public void stworzOrganizm(Organizm nowy)
    {
        this.objects.addElement(nowy);
    }

    public void ustawOrganizm(Organizm organizm, int y, int x)
    {
        this.setOrgMap(y, x, organizm);
    }

    public void usunOrganizm(int y, int x)
    {
        this.setOrgMap(y, x, null);
    }

    public void zabijOrganizm(int y, int x)
    {
        this.usunOrganizm(y, x);
        for (int i = 0; i < objects.size(); i++)
        {
            if (objects.get(i) != null)
            {
                if (objects.get(i).getPolozenie_Y() == y && objects.get(i).getPolozenie_X() == x)
                {
                    objects.set(i, null);
                    break;
                }
            }
        }
    }

    public Vector<Organizm> cleanObjects(Vector<Organizm> objects)
    {
        for (int i = 0; i < objects.size(); i++)
        {
            if (objects.get(i) == null)
            {
                objects.removeElementAt(i);
                i--;
            }
        }
        return objects;
    }

    public void Zapisz() throws FileNotFoundException
    {
        PrintWriter zapis = new PrintWriter("zapis.txt");
        zapis.printf("%d%s%d%s", this.size_Y, " ", this.size_X, "\n");
        for (int i = 0; i < objects.size(); i++)
        {
            if (objects.get(i) != null)
            {
                if (objects.get(i) instanceof Czlowiek)
                    zapis.printf("%s%s%d%s%d%s%d%s%s%s%s%s%d%s", objects.get(i).getId(), " ", objects.get(i).getPolozenie_Y(), " ", objects.get(i).getPolozenie_X(), " ", objects.get(i).getWiek(), " ", objects.get(i).getDirection(), " ", ((Czlowiek) objects.get(i)).getMoc(), " ", ((Czlowiek) objects.get(i)).getCooldown(), "\n");
                else
                    zapis.printf("%s%s%d%s%d%s%d%s", objects.get(i).getId(), " ", objects.get(i).getPolozenie_Y(), " ", objects.get(i).getPolozenie_X(), " ", objects.get(i).getWiek(), "\n");
            }
        }
        zapis.close();
        this.logger.log("Zapisuje stan świata\n");
    }

    public void Wczytaj() throws FileNotFoundException
    {
        File file = new File("zapis.txt");
        Scanner in = new Scanner(file);
        for (int i = 0; i < this.size_Y; i++)
        {
            for (int j = 0; j < this.size_X; j++)
            {
                this.zabijOrganizm(i, j);
            }
        }
        objects.removeAllElements();
        int newSize_X, newSize_Y, y, x, wiek;
        String id;
        this.size_Y = in.nextInt();
        this.size_X = in.nextInt();
        this.orgMap = new Organizm[this.size_Y][this.size_X];

        this.gui.dispose();
        this.gui = new GUI(this, this.orgMap);
        this.logger = new GUILogger(this.gui);

        while (in.hasNext())
        {
            id = in.next();
            y = in.nextInt();
            x = in.nextInt();
            wiek = in.nextInt();

            switch (id)
            {
                case "Trawa":
                    objects.addElement(new Trawa(this, y, x, wiek));
                    break;
                case "Guarana":
                    objects.addElement(new Guarana(this, y, x, wiek));
                    break;
                case "Mlecz":
                    objects.addElement(new Mlecz(this, y, x, wiek));
                    break;
                case "Jagody":
                    objects.addElement(new Jagody(this, y, x, wiek));
                    break;
                case "Barszcz":
                    objects.addElement(new Barszcz(this, y, x, wiek));
                    break;
                case "Wilk":
                    objects.addElement(new Wilk(this, y, x, wiek));
                    break;
                case "Antylopa":
                    objects.addElement(new Antylopa(this, y, x, wiek));
                    break;
                case "Zolw":
                    objects.addElement(new Zolw(this, y, x, wiek));
                    break;
                case "Owca":
                    objects.addElement(new Owca(this, y, x, wiek));
                    break;
                case "Lis":
                    objects.addElement(new Lis(this, y, x, wiek));
                    break;
                case "CyberOwca":
                    objects.addElement(new CyberOwca(this, y, x, wiek));
                    break;
                case "Czlowiek":
                    Czlowiek gracz = new Czlowiek(this, y, x, wiek);
                    gracz.setDirection(in.next());
                    gracz.setMoc(in.next());
                    gracz.setCooldown(in.nextInt());
                    objects.addElement(gracz);
                    this.gui.setPlayer(gracz);
                    break;
            }
        }
        in.close();

        for (int i = 0; i < objects.size(); i++)
            ustawOrganizm(objects.get(i), objects.get(i).getPolozenie_Y(), objects.get(i).getPolozenie_X());

        this.gui.board.updateUI();
    }

    public void rysujSwiat()
    {
        this.gui.board.repaint();
    }

    public void wykonajTure()
    {
        for(int i = 0; i < objects.size(); i++)
        {
                if(objects.get(i) != null)
                {
                    int old_Y = objects.get(i).getPolozenie_Y();
                    int old_X = objects.get(i).getPolozenie_X();
                    if(objects.get(i).getWiek() > 0)
                        this.objects.get(i).akcja();
                    if(objects.get(i) != null)
                    {
                        if((objects.get(i).getPolozenie_Y() != old_Y) || (objects.get(i).getPolozenie_X() != old_X))
                        {
                            this.ustawOrganizm(objects.get(i), objects.get(i).getPolozenie_Y(), objects.get(i).getPolozenie_X());
                            this.usunOrganizm(old_Y, old_X);
                        }
                        this.objects.get(i).setWiek(this.objects.get(i).getWiek() + 1);
                    }
            }
        }
    }

    private void stworzSwiat()
    {
        Random random = new Random();
        int iloscOrganizmow = 0;
        if((size_X * size_Y) >= 25 && (size_X * size_Y) < 125)
            iloscOrganizmow = 12;
        else if((size_X * size_Y) >= 125 && (size_X * size_Y) < 225)
            iloscOrganizmow = 22;
        else if((size_X * size_Y) >= 225 && (size_X * size_Y) < 325)
            iloscOrganizmow = 32;
        else if((size_X * size_Y) >= 325 && (size_X * size_Y) < 425)
            iloscOrganizmow = 42;
        else if((size_X * size_Y) >= 425 && (size_X * size_Y) < 525)
            iloscOrganizmow = 52;

        for(int i = 0; i < iloscOrganizmow; i++)
        {
            while (true)
            {
                int PosY = random.nextInt(this.size_Y);
                int PosX = random.nextInt(this.size_X);
                if(orgMap[PosY][PosX] == null)
                {
                    if(i < ((iloscOrganizmow-2)/10))
                        objects.addElement(new Trawa(this, PosY, PosX, 0));
                    else if(i < (2*(iloscOrganizmow-2)/10))
                        objects.addElement(new Guarana(this, PosY, PosX, 0));
                    else if(i < (3*(iloscOrganizmow-2)/10))
                        objects.addElement(new Mlecz(this, PosY, PosX, 0));
                    else if(i < (4*(iloscOrganizmow-2)/10))
                        objects.addElement(new Jagody(this, PosY, PosX, 0));
                    else if(i < (5*(iloscOrganizmow-2)/10))
                        objects.addElement(new Barszcz(this, PosY, PosX, 0));
                    else if(i < (6*(iloscOrganizmow-2)/10))
                        objects.addElement(new Wilk(this, PosY, PosX, 0));
                    else if(i < (7*(iloscOrganizmow-2)/10))
                        objects.addElement(new Antylopa(this, PosY, PosX, 0));
                    else if(i < (8*(iloscOrganizmow-2)/10))
                        objects.addElement(new Zolw(this, PosY, PosX, 0));
                    else if(i < (9*(iloscOrganizmow-2)/10))
                        objects.addElement(new Owca(this, PosY, PosX, 0));
                    else if(i < (iloscOrganizmow-2))
                        objects.addElement(new Lis(this, PosY, PosX, 0));
                    else if(i < (iloscOrganizmow-1))
                        objects.addElement(new CyberOwca(this, PosY, PosX, 0));
                    else
                    {
                        Czlowiek gracz = new Czlowiek(this, PosY, PosX, 0);
                        objects.addElement(gracz);
                        this.gui.setPlayer(gracz);
                    }


                    this.ustawOrganizm(objects.get(i), objects.get(i).getPolozenie_Y(), objects.get(i).getPolozenie_X());
                    break;
                }
            }
        }

    }

    public void start()
    {
        this.gui = new GUI(this, this.orgMap);
        this.logger = new GUILogger(this.gui);

        objects = new Vector<Organizm>();
        stworzSwiat();

/*        objects.addElement(new Trawa(this, 1, 1, 0));
        objects.addElement(new Trawa(this, 12, 14, 0));
        objects.addElement(new Trawa(this, 7, 18, 0));
        objects.addElement(new Guarana(this, 17, 19, 0));
        objects.addElement(new Guarana(this, 5, 0, 0));
        objects.addElement(new Guarana(this, 9, 3, 0));
        objects.addElement(new Mlecz(this, 2, 7, 0));
        objects.addElement(new Mlecz(this, 4, 19, 0));
        objects.addElement(new Mlecz(this, 9, 9, 0));
        objects.addElement(new Jagody(this, 3, 2, 0));
        objects.addElement(new Jagody(this, 5, 11, 0));
        objects.addElement(new Jagody(this, 13, 9, 0));
        objects.addElement(new Barszcz(this, 3, 3, 0));
        objects.addElement(new Barszcz(this, 15, 15, 0));
        objects.addElement(new Barszcz(this, 12, 2, 0));
        objects.addElement(new Wilk(this, 0, 7, 0));
        objects.addElement(new Wilk(this, 7, 13, 0));
        objects.addElement(new Wilk(this, 14, 18, 0));
        objects.addElement(new Wilk(this, 19, 11, 0));
        objects.addElement(new Antylopa(this, 16, 12, 0));
        objects.addElement(new Antylopa(this, 1, 16, 0));
        objects.addElement(new Antylopa(this, 4, 7, 0));
        objects.addElement(new Antylopa(this, 11, 4, 0));
        objects.addElement(new Zolw(this, 0, 18, 0));
        objects.addElement(new Zolw(this, 3, 9, 0));
        objects.addElement(new Zolw(this, 2, 6, 0));
        objects.addElement(new Zolw(this, 18, 2, 0));
        objects.addElement(new Owca(this, 1, 8, 0));
        objects.addElement(new Owca(this, 13, 3, 0));
        objects.addElement(new Owca(this, 19, 4, 0));
        objects.addElement(new Owca(this, 6, 7, 0));
        objects.addElement(new Lis(this, 2, 18, 0));
        objects.addElement(new Lis(this, 7, 7, 0));
        objects.addElement(new Lis(this, 14, 15, 0));
        objects.addElement(new Lis(this, 10, 19, 0));
        objects.addElement(new CyberOwca(this, 19, 19, 0));
        objects.addElement(new Czlowiek(this, 0, 19, 0));

        for(int i = 0; i < objects.size(); i++)
            this.ustawOrganizm(objects.get(i), objects.get(i).getPolozenie_Y(), objects.get(i).getPolozenie_X());*/

        this.objects = this.sortObjects(this.objects);
        this.gui.board.updateUI();
    }

    private int size_X;
    private int size_Y;
    private Organizm[][] orgMap;
    public Vector<Organizm> objects;
    private GUI gui;
    public Logger logger;

}
