package swordMaster.windows;
import swordMaster.data.GlobalVar;

public class WindowCommand extends WindowBase
{
    public String[] commands;
    public int itemMax;
    public int index;
    public WindowCommand (int width, String[] commands) throws Exception
    {
	super (0, 0, width, commands.length * 28 + 28);
	this.commands = commands;
	this.itemMax = commands.length;
	this.index = 0;
    }


    public void refresh ()
    {
	super.refresh ();
	GlobalVar.offScreenG.setColor (GlobalVar.systemColor);        
	for (int i = 0 ; i < this.itemMax ; i++)
	{
	    /* 28 pixels between every command */
	    this.drawText (commands [i], 0, 28 * i);
	}
	updateCursor ();
    }


    private void updateCursor ()
    {
	if (this.index > this.itemMax - 1)
	    this.index = 0;
	if (this.index < 0)
	    this.index = this.itemMax - 1;
	GlobalVar.offScreenG.drawImage (windowskin, this.x + 16, this.index * 28 + (this.y + 36),
		this.x + this.width - 10, this.index * 28 + (this.y + 64), 128, 64, 160, 96, null);
    }
}
