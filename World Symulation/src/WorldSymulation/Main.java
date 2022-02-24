package WorldSymulation;
import java.util.*;

public class Main
{

    public static void main(String[] args)
    {
        int n, m;
        Scanner Input = new Scanner( System.in );
        System.out.println("Type the width of the map (recommended 10-20");
        n = Input.nextInt();
        System.out.println("Type the height of the map (recommended 10-20");
        m = Input.nextInt();
        Swiat swiat = new Swiat(n, m);
        swiat.start();
    }

}
