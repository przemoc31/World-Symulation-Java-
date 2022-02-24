package WorldSymulation;

abstract public class Organizm
{
    public int getPolozenie_X() { return this.polozenie_X; }
    public int getPolozenie_Y() { return this.polozenie_Y; }
    public int getSila() { return this.sila; }
    public int getInicjatywa() { return this.inicjatywa; }
    public String getId() { return this.id; }
    public int getWiek() { return this.wiek; }
    public String getDirection() { return this.direction; }
    public void setDirection(String newDirection) { this.direction = newDirection; }
    public void setPolozenie_X(int newX) { this.polozenie_X = newX; }
    public void setPolozenie_Y(int newY) { this.polozenie_Y = newY; }
    public void setSila(int newSila) { this.sila = newSila; }
    public void setInicjatywa(int newInicjatywa) { this.inicjatywa = newInicjatywa; }
    public void setId(String newId) { this.id = newId; }
    public void setWiek(int newWiek) { this.wiek = newWiek; }
    public void setLogger(Logger newLogger) { this.logger = newLogger; }
    abstract void akcja();
    abstract void kolizja(Organizm attacker, int old_Y, int old_X);
    protected int sila;
    protected int inicjatywa;
    protected String id;
    protected int polozenie_X;
    protected int polozenie_Y;
    protected int wiek;
    protected String direction;
    Swiat swiat;
    Logger logger;

}
