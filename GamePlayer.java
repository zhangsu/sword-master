package swordMaster.data;
import java.awt.Color;
import java.awt.Font;
import swordMaster.scenes.SceneGameover;

public class GamePlayer
{
    public static int strength = 10;
    public static int hp = 20;
    public static int maxHp = 20;
    public static boolean alive = true;
    /* The display of attacking animation */
    public static boolean attacking = false;
    public static int damage;
    /* The lastest combat message displays at the bottom right */
    static boolean msgChanges = false;

    public static int strExp = 0;
    public static int maxHpExp = 0;

    /* Whether holding a bottle of potion */
    public static boolean potion = false;
    /* Whether Takes the potion */
    public static boolean takingPotion = false;
    /* Whether the player doesn't have the potion */
    public static boolean noPotion = false;

    public static String[] combatMsg = new String[]{"", "", ""};
    public static String lastMsg = "";
    
    public static void initialize ()
    {
	strength = 10;
	hp = 20;
	maxHp = 20;
	alive = true;
	attacking = false;
	msgChanges = false;

	strExp = 0;
	maxHpExp = 0;
	potion = false;
	takingPotion = false;
	noPotion = false;

	combatMsg = new String[]{"", "", ""};
	lastMsg = "";
    }

    public static void update ()
    {
	hp = Math.min (maxHp, (Math.max (0, hp)));
	damage = strength + (int) (2 * Math.random ());
	if (hp <= 0)
	    GlobalVar.scene = new SceneGameover ();
	/* Evaluate the message that is supposed to be displayed on the screen */
	if (strExp >= strength * 5)
	{
	    strExp = 0;
	    msgChanges = true;
	    lastMsg = "You gain ".concat (Integer.toString ((int) (strength * 0.1))).concat (" strength");
	    strength += (int) (strength * 0.1);
	}
	if (maxHpExp > maxHp * 5)
	{
	    maxHpExp = 0;
	    msgChanges = true;
	    lastMsg = "You gain ".concat (Integer.toString ((int) (strength * 0.1))).concat (" max hp");
	    maxHp += (int) (maxHp * 0.1);
	}
	if (takingPotion)
	{
	    takingPotion = false;
	    msgChanges = true;
	    lastMsg = "You recover ".concat ("10").concat (" hp");
	}
	if (noPotion)
	{
	    noPotion = false;
	    msgChanges = true;
	    lastMsg = "You don't have potion";
	}
	/* display the message */
	GamePlayer.showCombatMessage ();
    }


    public static void showCombatMessage ()
    {
	GlobalVar.offScreenG.setFont (new Font ("Arial", 0, GlobalVar.defaultFontSize));
	GlobalVar.offScreenG.setColor (GlobalVar.systemColor);
	/* Pretend the message is scrolling up */
	if (msgChanges)
	{
	    combatMsg [2] = combatMsg [1];
	    combatMsg [1] = combatMsg [0];
	    combatMsg [0] = lastMsg;
	    msgChanges = false;
	}
	/* The newest message is displayed in white color */
	GlobalVar.offScreenG.setColor (new Color (192, 224, 200));
	GlobalVar.offScreenG.drawString (combatMsg [2], 460, 430);
	GlobalVar.offScreenG.drawString (combatMsg [1], 460, 458);
	/* The old message(s) is(are) displayed in other color */
	GlobalVar.offScreenG.setColor (GlobalVar.systemColor);
	GlobalVar.offScreenG.drawString (combatMsg [0], 460, 486);
    }


    public static void clearMsg ()
    {
	combatMsg [0] = "";
	combatMsg [1] = "";
	combatMsg [2] = "";
    }
}
