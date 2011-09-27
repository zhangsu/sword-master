package swordMaster.windows;
import swordMaster.data.GlobalVar;

public class WindowMessage extends WindowBase
{
    /* A small window uses when player or NPC is talking */
    public WindowBase nameWindow;
    public WindowMessage () throws Exception
    {
	super (160, 320, 320, 144);
	nameWindow = new WindowBase (x, y - 60, GlobalVar.playerName.length () * 12 + 32, 60);
    }


    public void drawMessage (String msg, String name)
    {
	super.refresh ();
	GlobalVar.offScreenG.setColor (GlobalVar.systemColor);
	/* Split the message to 4 pieces by "\n" */
	String[] substring = msg.split ("\n");
	/* Auto adjust the height of the window */
	this.height = 28 * substring.length + 32;
	for (int row = 0 ; row < substring.length ; row++)
	{
	    drawText (substring [row], 0, row * 28);
	}
	if (!name.equals (""))
	    nameWindow.refresh ();
	if (name.equals (" "))
	{
	    nameWindow.width = GlobalVar.playerName.length () * 12 + 32;
	    nameWindow.drawText (GlobalVar.playerName, 0, 0);
	}
	else
	{
	    nameWindow.width = name.length () * 12 + 32;
	    nameWindow.drawText (name, 0, 0);
	}
    }
}
