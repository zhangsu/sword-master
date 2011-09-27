package swordMaster.data;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import swordMaster.data.GlobalVar;

/* The class uses to show the animation */
public class Animation
{
    static String path = "SwordMaster\\Graphics\\001-Weapon01.png";
    public static BufferedImage attack;
    public static boolean on = false;
    boolean reverse = false;

    private long time = 0;
    private int frames = 0;

    public Animation () throws Exception
    {
    File f = new File(path);
	if (!f.exists ())
	    throw new Exception ();
	attack = ImageIO.read(f);
    }


    public void update ()
    {
	int x[] = new int [4], y[] = new int [4];
	if (on)
	{
	    /* Direction: 0 South 1 West 2 East 3 North */
	    switch (GlobalVar.scene.player.direction)
	    {
		case 0:
		    x [0] = GlobalVar.scene.player.x * 32 - (192 - 32) / 2 + 3;
		    y [0] = GlobalVar.scene.player.y * 32 + 23 - 48;
		    for (int coor = 1 ; coor < 4 ; coor++)
		    {
			x [coor] = x [0];
			y [coor] = y [coor - 1] + 8;
		    }
		    reverse = true;
		    break;
		case 1:
		    x [0] = GlobalVar.scene.player.x * 32 + 3 - 80;
		    y [0] = GlobalVar.scene.player.y * 32 - (192 - 32) / 2 + 23;
		    for (int coor = 1 ; coor < 4 ; coor++)
		    {
			x [coor] = x [coor - 1] - 8;
			y [coor] = y [0];
		    }
		    reverse = false;
		    break;
		case 2:
		    x [0] = GlobalVar.scene.player.x * 32 + 3 - 48;
		    y [0] = GlobalVar.scene.player.y * 32 - (192 - 32) / 2 + 23;
		    for (int coor = 1 ; coor < 4 ; coor++)
		    {
			x [coor] = x [coor - 1] + 8;
			y [coor] = y [0];
		    }
		    reverse = true;
		    break;
		case 3:
		    x [0] = GlobalVar.scene.player.x * 32 - (192 - 32) / 2 + 3;
		    y [0] = GlobalVar.scene.player.y * 32 + 23 - 80;
		    for (int coor = 1 ; coor < 4 ; coor++)
		    {
			x [coor] = x [0];
			y [coor] = y [coor - 1] - 8;
		    }
		    reverse = false;

	    }
	    /* Four Frames */
	    if (time == 0)
		time = System.currentTimeMillis ();
	    if (System.currentTimeMillis () - time >= 75 && System.currentTimeMillis () - time < 150)
		frames = 1;
	    else if (System.currentTimeMillis () - time >= 150 && System.currentTimeMillis () - time < 225)
		frames = 2;
	    else if (System.currentTimeMillis () - time >= 225)
	    {
		frames = 3;
		time = 0;
	    }
	    //for (int frames = 0 ; frames < 4 ; frames++)
	    //{
	    GlobalVar.scene.runningMap.drawMap ();
	    GlobalVar.scene.player.update ();
	    if (GlobalVar.scene.activeEnemy.alive && GlobalVar.scene.activeEnemy instanceof Cock)
		GlobalVar.scene.enemy.update ();
	    if (reverse)
		GlobalVar.offScreenG.drawImage (attack, x [frames], y [frames], x [frames] + 192, y [frames] + 192, 192, 192, 0, 0, null);
	    else
		GlobalVar.offScreenG.drawImage (attack, x [frames], y [frames], null);
	    GlobalVar.scene.runningMap.drawSuperTile ();
	    GlobalVar.scene.updateHpBar ();
	    GamePlayer.showCombatMessage ();
	    /* Update the counter */
	    if (GlobalVar.beginCount)
		GlobalVar.scene.counter.update ();
	    GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
	    //for (int wait = 0 ; wait < 2000000 ; wait++)
	    //  ;
	    //}
	    if (frames == 3)
	    {
		frames = 0;
		on = false;
	    }
	}
    } // end of update method
}
