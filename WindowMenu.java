package swordMaster.windows;

public final class WindowMenu extends WindowCommand
{
    public WindowMenu () throws Exception
    {
	super (116, new String[]{"Status", "Operation", "Heal", "Exit", "Cancel"});
	this.index = 0;
	this.x = 0;
	this.y = 480 - this.height;
    }
}
