package WorldSymulation;

import javax.sound.sampled.Line;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.JTextComponent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.util.*;
import java.awt.*;


public class GUI extends JFrame implements ActionListener, KeyListener
{
    private static final int FIELDS_WIDTH = 40;
    private static final int FIELDS_HEIGHT = 40;
    private static final int SPACING = 5;
    public Board board;
    private Organizm[][] orgMap;
    private Swiat swiat;
    private static int licznikTur = 0;
    protected JTextArea raport;
    protected JScrollPane scrollbar;
    private Czlowiek player = null;

    public GUI(Swiat swiat, Organizm[][] newOrgMap)
    {
        this.swiat = swiat;
        this.orgMap = newOrgMap;
        this.setTitle("Symulacja Swiata");
        this.setSize(((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10), ((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.raport = new JTextArea();
        this.raport.setEnabled(false);
        this.scrollbar = new JScrollPane();
        addKeyListener(this);
        this.setFocusable(true);

        this.board = new Board(this.swiat, this.orgMap, this);
        this.setContentPane(this.board);
        this.setVisible(true);
    }

    public Organizm getPlayer() { return this.player; }
    public void setPlayer(Czlowiek player) { this.player = player; }

    public void putLog(String message)
    {
        this.board.putLog(message);
    }

    @Override
    public void keyPressed(KeyEvent evt) {

    }
    @Override
    public void keyReleased(KeyEvent evt) {
        if(this.player != null)
        {
            switch (evt.getKeyCode())
            {
                case KeyEvent.VK_UP:
                    this.player.setDirection("UP");
                    break;
                case KeyEvent.VK_DOWN:
                    this.player.setDirection("DOWN");
                    break;
                case KeyEvent.VK_LEFT:
                    this.player.setDirection("LEFT");
                    break;
                case KeyEvent.VK_RIGHT:
                    this.player.setDirection("RIGHT");
                    break;
                case KeyEvent.VK_R:
                    if(this.player.getMoc() == "DOSTEPNA")
                        this.player.setMoc("AKTYWNA");
                    break;
                default:
                    this.player.setDirection(null);
            }
        }
    }
    @Override
    public void keyTyped(KeyEvent evt) {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("Nowa Tura"))
        {
            //     this.raport.setText("");
            if(licznikTur ==  0)
                this.swiat.logger.log("REJESTR SWIATA\n");
            else
            {
                this.swiat.logger.log("%s%d%s", "\nTura ", licznikTur, "\n");
            }
            this.swiat.wykonajTure();
            this.swiat.objects = this.swiat.cleanObjects(this.swiat.objects);
            this.swiat.rysujSwiat();
            this.swiat.objects = this.swiat.sortObjects(this.swiat.objects);
            licznikTur++;
        }
        else if(e.getActionCommand().equals("Zapisz"))
        {
            try
            {
                this.swiat.Zapisz();
            }
            catch (FileNotFoundException x)
            {
                this.swiat.logger.log("Nie znaleziono pliku\n");
            }
        }
        else if(e.getActionCommand().equals("Wczytaj"))
        {
            try
            {
                this.swiat.Wczytaj();
            }
            catch (FileNotFoundException x)
            {
                this.swiat.logger.log("Nie znaleziono pliku\n");
            }
        }
        addKeyListener(this);
    }


    public class Board extends JPanel
    {
        private Organizm[][] orgMap;
        private Swiat swiat;
        private GUI gui;
        private JLabel[][] label;
        private JTextArea playerLabel;
        private ImageIcon EmptyImage, TrawaImage, GuaranaImage, MleczImage, JagodyImage, BarszczImage, WilkImage, OwcaImage, LisImage, AntylopaImage, ZolwImage, CyberOwcaImage, CzlowiekImage;
        private JButton nowaTura, zapisz, wczytaj;

        Board(Swiat swiat, Organizm[][] newOrgMap, GUI gui)
        {
            this.gui = gui;
            this.swiat = swiat;
            this.orgMap = newOrgMap;
            playerLabel = new JTextArea();
            playerLabel.setEnabled(false);
            this.label = new JLabel[this.swiat.getSize_Y()][this.swiat.getSize_X()];
            EmptyImage = new ImageIcon(getClass().getResource("Empty.png"));
            TrawaImage = new ImageIcon(getClass().getResource("Trawa.png"));
            GuaranaImage = new ImageIcon(getClass().getResource("Guarana.png"));
            MleczImage = new ImageIcon(getClass().getResource("Mlecz.png"));
            JagodyImage = new ImageIcon(getClass().getResource("Jagody.png"));
            BarszczImage = new ImageIcon(getClass().getResource("Barszcz.png"));
            WilkImage = new ImageIcon(getClass().getResource("Wilk.png"));
            OwcaImage = new ImageIcon(getClass().getResource("Owca.png"));
            LisImage = new ImageIcon(getClass().getResource("Lis.png"));
            AntylopaImage = new ImageIcon(getClass().getResource("Antylopa.png"));
            ZolwImage = new ImageIcon(getClass().getResource("Zolw.png"));
            CyberOwcaImage = new ImageIcon(getClass().getResource("CyberOwca.png"));
            CzlowiekImage = new ImageIcon(getClass().getResource("Czlowiek.png"));
        }

        public void putLog(String message)
        {
            this.gui.raport.append(message);
        }

        private void showPlayerPanel()
        {
            if(this.gui.player != null)
            {
                this.playerLabel.setText("");
                this.playerLabel.append("            PANEL CZLOWIEK\n\n" + "kierunek: " + player.getDirection() + "\n\n" + "supermoc: " + player.getMoc());
            }
        }

        public void paintComponent(Graphics g)
        {
                this.removeAll();
                this.nowaTura = new JButton("Nowa Tura");
                this.nowaTura.addActionListener(this.gui);
                this.nowaTura.setSize((((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 10, (((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5)) / 20);
                this.nowaTura.setLocation(25, FIELDS_HEIGHT*this.swiat.getSize_Y() + 25);
                this.add(this.nowaTura);
                this.zapisz = new JButton("Zapisz");
                this.zapisz.addActionListener(this.gui);
                this.zapisz.setSize((((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 10, (((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5)) / 20);
                this.zapisz.setLocation(((((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 10) + 2*25, FIELDS_HEIGHT*this.swiat.getSize_Y() + 25);
                this.add(this.zapisz);
                this.wczytaj = new JButton("Wczytaj");
                this.wczytaj.addActionListener(this.gui);
                this.wczytaj.setSize((((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 10, (((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5)) / 20);
                this.wczytaj.setLocation(2*((((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 10) + 3*25, FIELDS_HEIGHT*this.swiat.getSize_Y() + 25);
                this.add(this.wczytaj);
                this.gui.raport.setBounds(((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) - 25, 25, (((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 3, (((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5)) / 2);
                this.gui.raport.setBorder( new LineBorder(Color.BLACK));
                this.gui.raport.setBackground(Color.BLACK);
                this.gui.raport.setDisabledTextColor(Color.GREEN);
                this.add(this.gui.raport);
                this.gui.scrollbar.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
                this.gui.scrollbar.setBounds(((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) - 25, 25, (((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 3, (((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5)) / 2);
                this.gui.scrollbar.getViewport().setBackground(Color.BLACK);
                this.gui.scrollbar.getViewport().add(this.gui.raport);
                this.add(this.gui.scrollbar);
                this.playerLabel.setBounds(((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) - 25, FIELDS_HEIGHT*this.swiat.getSize_Y() + 25, (((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10)) / 6, (((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5)) / 8);
                this.playerLabel.setBorder( new LineBorder(Color.RED));
                this.playerLabel.setBackground(Color.BLACK);
                this.playerLabel.setDisabledTextColor(Color.GREEN);
                this.showPlayerPanel();

                this.add(this.playerLabel);

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, ((FIELDS_WIDTH + SPACING)*this.swiat.getSize_X()) + ((FIELDS_WIDTH + SPACING)*10), ((FIELDS_HEIGHT + SPACING)*this.swiat.getSize_Y()) + ((FIELDS_HEIGHT + SPACING)*5));

            for(int i = 0; i < this.swiat.getSize_Y(); i++)
            {
                for(int j = 0; j < this.swiat.getSize_X(); j++)
                {
                    label[i][j] = new JLabel(EmptyImage);

                    if(this.orgMap[i][j] instanceof Trawa)
                    {
                        label[i][j].setIcon(TrawaImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Guarana)
                    {
                        label[i][j].setIcon(GuaranaImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Mlecz)
                    {
                        label[i][j].setIcon(MleczImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Jagody)
                    {
                        label[i][j].setIcon(JagodyImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Barszcz)
                    {
                        label[i][j].setIcon(BarszczImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Wilk)
                    {
                        label[i][j].setIcon(WilkImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Owca)
                    {
                        label[i][j].setIcon(OwcaImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Lis)
                    {
                        label[i][j].setIcon(LisImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Antylopa)
                    {
                        label[i][j].setIcon(AntylopaImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Zolw)
                    {
                        label[i][j].setIcon(ZolwImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof CyberOwca)
                    {
                        label[i][j].setIcon(CyberOwcaImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else if(this.orgMap[i][j] instanceof Czlowiek)
                    {
                        label[i][j].setIcon(CzlowiekImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                    else
                    {
                        label[i][j] = new JLabel(EmptyImage);
                        label[i][j].setSize(FIELDS_WIDTH - 2 * SPACING, FIELDS_HEIGHT - 2 * SPACING);
                        label[i][j].setLocation(SPACING + j * FIELDS_WIDTH,SPACING + i * FIELDS_HEIGHT);
                        add(label[i][j]);
                    }
                }
            }
        }
    }

}
