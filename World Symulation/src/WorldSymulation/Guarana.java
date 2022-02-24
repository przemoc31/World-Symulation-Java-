package WorldSymulation;

public class Guarana extends Roslina
{
    Guarana(Swiat newSwiat, int y, int x, int newWiek)
    {
        this.setSila(0);
        this.setInicjatywa(0);
        this.setId("Guarana");
        this.swiat = newSwiat;
        this.setPolozenie_Y(y);
        this.setPolozenie_X(x);
        this.wiek = newWiek;
        this.setLogger(this.swiat.logger);
    }
    @Override
    public void kolizja(Organizm attacker, int old_Y, int old_X)
    {
        attacker.setSila(attacker.getSila() + 3);
        super.kolizja(attacker, old_Y, old_X);
        logger.log("%s%s%s%s%d%s", this.id, " dodaje 3 sily ", attacker.getId(), " dzieki czemu ma ", attacker.getSila(), " sily\n");
    }
}
