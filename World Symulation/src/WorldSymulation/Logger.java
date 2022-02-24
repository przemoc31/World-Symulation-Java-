package WorldSymulation;

public interface Logger
{
    public void log(String description);
    public void log(String format, Object ... args);
}
