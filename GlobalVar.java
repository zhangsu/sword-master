package swordMaster.data;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import swordMaster.Main;
import swordMaster.windows.WindowCommand;
import swordMaster.scenes.Scene;
import swordMaster.scenes.SceneTitle;

public final class GlobalVar
{
    /* The runing time, can be used to calculate the playing time */
    public static long time = System.currentTimeMillis ();

    public static final Main main = new Main ("Sword Master");
    public static final Graphics mainG = main.getGraphics ();

    /* offScreen and offScreenG are used for double buffer */
    public static Image offScreen = main.createImage (640 + 3, 480 + 23);
    public static Graphics offScreenG = offScreen.getGraphics ();

    /* The running Scene */
    public static Scene scene;

    /* The active window */
    public static WindowCommand activeWindow;

    /* Default font attribute */
    public static Font systemFont;
    public static Color systemColor;
    public static int defaultFontSize;

    public static String playerName;

    public static int attackCount = 0;

    public static boolean event1;
    public static boolean event2;
    public static boolean event3;
    public static boolean event4;
    public static boolean event5;
    
    public static boolean readyToCut;
    public static boolean beginCount;

    public static boolean win;
    public static boolean lose;
    public static boolean gameOver;

    public static void initialize () throws Exception
    {
	defaultFontSize = 18;
	systemFont = new Font ("Arial", 0, defaultFontSize);
	systemColor = new Color (255, 255, 255);
	scene = new SceneTitle ();
	offScreenG.setFont (systemFont);
	offScreenG.setColor (systemColor);

	event1 = true;
	event2 = false;
	event3 = false;
	event4 = false;
	event5 = false;
	readyToCut = false;
	win = false;
	lose = false;
	gameOver = false;
	GamePlayer.initialize ();
    }
}
