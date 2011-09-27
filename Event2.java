package swordMaster.data;

public class Event2
{
    /* Random chance to get the potion */
    public static double rand = Math.random ();
    public static void main ()
    {
	if (GamePlayer.potion)
	{
	    GlobalVar.scene.messageWindow.drawMessage ("You can't hold more than 1 bottle", "");
	}
	else
	{
		GlobalVar.scene.messageWindow.drawMessage ("You " + (rand >= 0.5 ? "gain a bottle of potion!" : "found nothing here..."), "");
	}
    }
}
