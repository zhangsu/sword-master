package swordMaster;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Frame;
import java.io.File;
import java.io.PipedReader;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import swordMaster.data.GlobalVar;
import swordMaster.data.MyKeyListener;

/* Main class, execution start here */
public final class Main extends Frame
{
    /* Main program window */
    public Main (String title)
    {
	super (title);
	setIconImage(Toolkit.getDefaultToolkit ().createImage ("swordMaster\\graphics\\002-Weapon02.png"));
	/* Game Resolution: 640 x 480, 6 and 26 are the width of the margin of the window */
	setSize (640 + 6, 480 + 26);
	/* Make the window center-justifed for the Monitor resolution of 800 x 600 */
	setLocation (77, 47);
	setBackground (new Color (0, 0, 0, 255));
	/* Add the function to close the window by clicking the cross at the top right coner (This is a inner class) */
	addWindowListener (new WindowAdapter ()
	{
	    public void windowClosing (WindowEvent we)
	    {
		System.gc ();
		System.exit (0);
	    }
	}
	);
	addKeyListener (new MyKeyListener ());
	setVisible (true);
    }


    public static void main (String[] args) throws Exception
    {
	BufferedReader r = new BufferedReader (new InputStreamReader (System.in));
	/* Get the player's name to display in game */
	System.out.println ("Enter you name: ");
	GlobalVar.playerName = r.readLine ();
	GlobalVar.playerName = GlobalVar.playerName.toUpperCase ();
	try
	{
	    /* Generate global variable object */
	    GlobalVar gv = new GlobalVar ();
	    gv.initialize ();
	    /* Start to execute */
	    while (!GlobalVar.gameOver)
	    {
		gv.scene.main ();
	    }
	}
	catch (Exception ex)
	{
	    System.out.println ("Program terminates due to the exception below:");
	    ex.printStackTrace ();
	}
	finally
	{
	    System.gc ();
	    System.exit (0);
	}
    }
}
