package WorldSymulation;

public class GUILogger implements Logger
{
    private GUI gui;
    GUILogger(GUI gui)
    {
        this.gui = gui;
    }
    @Override
    public void log(String description) {
        this.gui.putLog(description);
    }

    @Override
    public void log(String format, Object... args) {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<args.length; i++) {
            sb.append(args[i]);;
        }
        log(sb.toString());
    }
}
