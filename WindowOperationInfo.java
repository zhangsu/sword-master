package swordMaster.windows;
import java.awt.Color;
import swordMaster.data.GlobalVar;

public final class WindowOperationInfo extends WindowBase
{
    public WindowOperationInfo () throws Exception
    {
	super (120, 80, 480, 320);
    }


    public void refresh ()
    {
	super.refresh ();
	GlobalVar.offScreenG.setColor (new Color (192, 224, 255));
	drawText ("Ctrl", 0, 0);
	drawText ("Enter/Space", 0, 28);
	drawText ("ESC", 0, 54);
	drawText ("Arrow Keys", 0, 84);
	GlobalVar.offScreenG.setColor (GlobalVar.systemColor);
	drawText ("Attack (show the hp bar)", 140, 0);
	drawText ("Talk/Select Command", 140, 28);
	drawText ("Cancel/Main menu", 140, 54);
	drawText ("Moving/move command cursor", 140, 84);
    }
}
