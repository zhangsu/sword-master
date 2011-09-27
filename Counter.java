package swordMaster.data;
import java.awt.Color;
import java.awt.Font;

public class Counter
{
    public int count;
    private long time;
    public Counter (int startTimeSec)
    {
	count = startTimeSec;
    }


    public void update ()
    {
	if (System.currentTimeMillis () - time >= 1000)
	{
	    count--;
	    time = System.currentTimeMillis ();
	}
	GlobalVar.offScreenG.setFont (new Font ("Arial", 0, 30));
	GlobalVar.offScreenG.setColor (new Color (200, 0, 0));
	GlobalVar.offScreenG.drawString (Integer.toString (count), 580, 80);
    }
}
