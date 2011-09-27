package swordMaster.scenes;
import java.awt.image.BufferedImage;
import swordMaster.data.GlobalVar;
import swordMaster.data.SpritePlayer;
import swordMaster.data.SpriteEnemy;
import swordMaster.data.GameEnemy;
import swordMaster.data.Animation;
import swordMaster.data.Counter;
import swordMaster.windows.*;

interface Proc
{
    void main () throws Exception;
    void update (BufferedImage img);
    void update (BufferedImage[] imgs);
}

abstract public class Scene implements Proc
{
    public boolean isLoading;
    public SpritePlayer player;

    public SpriteEnemy enemy;
    public SpriteEnemy tree;

    public Scene runningMap;
    public int runningMapIndex;
    public static WindowMenu menuWindow;
    public static WindowCommand commandWindow;
    public static WindowOperationInfo infoWindow;
    public static WindowStatus statusWindow;
    public static WindowMessage messageWindow;
    public static Animation an;
    public static GameEnemy activeEnemy;
    public static int masterDir = 2;
    public static Counter counter;
    
    public void drawMap ()
    {
    }


    public void drawSuperTile ()
    {
    }


    public void updateHpBar ()
    {
    }


    public void main () throws Exception
    {
    }


    public void update (BufferedImage im)
    {
	isLoading = true;
	while (!GlobalVar.main.prepareImage (im, null))
	{
	    GlobalVar.offScreenG.drawString ("Loading...", 10, 491);
	    GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
	}
	GlobalVar.offScreenG.clearRect (3, 23, 640, 480);
	isLoading = false;
    }


    public void update (BufferedImage[] ims)
    {
	isLoading = true;
	for (int checkImage = 0 ; checkImage < ims.length ; checkImage++)
	{
	    while (!GlobalVar.main.prepareImage (ims [checkImage], null))
	    {
		GlobalVar.offScreenG.drawString ("Loading...", 10, 491);
		GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
	    }
	}
	GlobalVar.offScreenG.clearRect (3, 23, 640, 480);
	isLoading = false;
    }
}
