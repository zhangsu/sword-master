package swordMaster.windows;
import java.awt.Color;
import swordMaster.data.GlobalVar;
import swordMaster.data.GamePlayer;

public final class WindowStatus extends WindowBase
{
    public WindowStatus () throws Exception
    {
	super (160, 340, 192, 88);
    }


    public void refresh ()
    {
	super.refresh ();
	GlobalVar.offScreenG.setColor (new Color (192, 224, 255));
	drawText ("Strength", 0, 0);
	drawText ("Hp", 0, 28);
	GlobalVar.offScreenG.setColor (GlobalVar.systemColor);
	drawText (Integer.toString (GamePlayer.strength), 80, 0);
	drawText (Integer.toString (GamePlayer.hp) + "/" + Integer.toString (GamePlayer.maxHp), 80, 28);
    }
}

