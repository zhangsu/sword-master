package swordMaster.scenes;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
import swordMaster.data.*;
import swordMaster.windows.*;

public class SceneMap extends Scene
{
    static BufferedImage[] map = new BufferedImage [3], superTile = new BufferedImage [3];
    static BufferedImage master;
    public void main () throws Exception
    {
	menuWindow = new WindowMenu ();
	infoWindow = new WindowOperationInfo ();
	statusWindow = new WindowStatus ();
	messageWindow = new WindowMessage ();
	an = new Animation ();
	counter = new Counter (31);
	String p[] = {
	    "swordMaster\\graphics\\Map\\001-Map01.jpg",
	    "swordMaster\\graphics\\Map\\002-Map02.jpg",
	    "swordMaster\\graphics\\Map\\003-Map03.jpg",
	    "swordMaster\\graphics\\Map\\004-Crown01.png",
	    "swordMaster\\graphics\\Map\\005-Crown02.png",
	    "swordMaster\\graphics\\Characters\\002-Enemy02.png"
	    };
    File[] f = new File[p.length];
	for (int i = 0 ; i < 6 ; i++)
	{
	    f[i] = new File(p[i]);
	    if (!f[i].exists())
    		throw new Exception ();
	    else
	    {
    		if (i < 3)
    		    map [i] = ImageIO.read(f [i]);
    		else if (i == 5)
    		    master = ImageIO.read(f [i]);
    		else
    		    superTile [i - 3] = ImageIO.read(f [i]);
	    }
	}
	/* Generate player sprite */
	player = new SpritePlayer ();
	player.direction = 3;
	enemy = new SpriteEnemy (8, 8, 0);
	tree = new SpriteEnemy (15, 10, 1);
	activeEnemy = new Cock ();
	/* Generate map object */
	runningMapIndex = 1;
	runningMap = new Map01 (6, 4);
	Event1.msgIndex = 0;
	do
	{
	    update (new BufferedImage[]
	    {
		player.sprite, enemy.getSprite (), map [0], map [1], map [2], superTile [0], superTile [1], master, menuWindow.getWindowskin (), an.attack
	    }
	    );
	    mapTranslation ();
	}
	while (GlobalVar.scene instanceof SceneMap && !GlobalVar.gameOver);
	GlobalVar.offScreenG.clearRect (3, 23, 640, 480);
	System.gc ();
    }


    public void update (BufferedImage[] imgs)
    {
	super.update (imgs);
	runningMap.drawMap ();
	if (activeEnemy.alive)
	{
	    if (activeEnemy instanceof Cock)
	    {
		enemy.update ();
	    }
	    GlobalVar.scene.activeEnemy.update ();
	}
	player.update ();
	/* Update the animation */
	if (!GlobalVar.event1 && !GlobalVar.event2 && !GlobalVar.event3 && !GlobalVar.event4)
	    an.update ();
	/* Refresh the window if there is any */
	if (GlobalVar.activeWindow instanceof WindowMenu)
	    menuWindow.refresh ();
	if (infoWindow.visible)
	    infoWindow.refresh ();
	if (statusWindow.visible)
	    statusWindow.refresh ();
	if (GamePlayer.attacking)
	    updateHpBar ();
	/* Update the events */
	if (GlobalVar.event1)
	    Event1.main ();
	if (GlobalVar.event2)
	    Event2.main ();
	if (GlobalVar.event3)
	    Event3.main ();
	if (GlobalVar.event4)
	    Event4.main ();
	/* Update the combat information */
	GamePlayer.update ();
	/* Update the counter */
	if (GlobalVar.beginCount)
	{
	    counter.update ();
	    if (counter.count <= 0)
	    {
		GlobalVar.beginCount = false;
		activeEnemy.alive = false;
		GlobalVar.lose = true;
	    }
	}
	GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
    }


    /* check for the translation of map */
    public void mapTranslation () throws Exception
    {
	/* Translate to Map02 */
	if (runningMapIndex == 1 && ((player.x == 7 || player.x == 8) && player.y == 10))
	{
	    runningMapIndex = 2;
	    runningMap = new Map02 (3, 5);
	}
	if (runningMapIndex == 2)
	{
	    /* Translate to Map01 */
	    if (player.x == 3 && player.y == 4)
	    {
		runningMapIndex = 1;
		runningMap = new Map01 (7, 9);
		activeEnemy.alive = false;
		GamePlayer.clearMsg ();
	    }
	    /* Translate to Map03 */
	    if ((player.x >= 10 && player.x <= 14) && player.y == 14)
	    {
		runningMapIndex = 3;
		runningMap = new Map03 (13, 1);
		GamePlayer.clearMsg ();
		if (activeEnemy instanceof Cock)
		{
		    activeEnemy.alive = false;
		}
	    }
	}
	/* Translate to Map02 */
	if (runningMapIndex == 3 && player.x <= 15 && player.x >= 11 && player.y == 0)
	{
	    runningMapIndex = 2;
	    runningMap = new Map02 (12, 13);
	}
    }


    public void updateHpBar ()
    {
	int barWidth = (int) (GamePlayer.hp / (double) (GamePlayer.maxHp) * 50);
	for (int hue = 255 ; hue > 255 - barWidth ; hue--)
	{
	    GlobalVar.offScreenG.setColor (new Color ((255 + 255 - 50 - hue), 0, 0));
	    GlobalVar.offScreenG.fillRect (player.x * 32 - (50 - 32) / 2 + (255 - hue) + 3, player.y * 32 + 56, 1, 3);
	}
	GlobalVar.offScreenG.setColor (new Color (0, 0, 0));
	GlobalVar.offScreenG.drawRect (player.x * 32 - (50 - 32) / 2 + 3 - 1, player.y * 32 + 55, 51, 4);

	if (activeEnemy.alive)
	{
	    barWidth = (int) (activeEnemy.hp / (double) (activeEnemy.maxHp) * 50);
	    if (activeEnemy instanceof Cock)
	    {
		for (int hue = 255 ; hue > 255 - barWidth ; hue--)
		{
		    GlobalVar.offScreenG.setColor (new Color ((255 + 255 - 50 - hue), 0, 0));
		    GlobalVar.offScreenG.fillRect (enemy.x * 32 - (50 - 32) / 2 + (255 - hue) + 3, enemy.y * 32 + 56, 1, 3);
		}
		GlobalVar.offScreenG.setColor (new Color (0, 0, 0));
		GlobalVar.offScreenG.drawRect (enemy.x * 32 - (50 - 32) / 2 + 3 - 1, enemy.y * 32 + 55, 51, 4);
	    }
	    else if (activeEnemy instanceof Tree && runningMapIndex == 3)
	    {
		for (int hue = 255 ; hue > 255 - barWidth ; hue--)
		{
		    GlobalVar.offScreenG.setColor (new Color ((255 + 255 - 50 - hue), 0, 0));
		    GlobalVar.offScreenG.fillRect (tree.x * 32 - (50 - 32) / 2 + (255 - hue) + 3, tree.y * 32 + 56, 1, 3);
		}
		GlobalVar.offScreenG.setColor (new Color (0, 0, 0));
		GlobalVar.offScreenG.drawRect (tree.x * 32 - (50 - 32) / 2 + 3 - 1, tree.y * 32 + 55, 51, 4);
	    }
	}
    }


    private final class Map01 extends SceneMap
    {
	Map01 (int x, int y) throws Exception
	{
	    SceneMap.this.player.x = x;
	    SceneMap.this.player.y = y;
	}


	public void drawMap ()
	{
	    GlobalVar.offScreenG.drawImage (SceneMap.this.map [0], 3, 23, null);
	}

    }


    private final class Map02 extends SceneMap
    {
	Map02 (int x, int y) throws Exception
	{
	    SceneMap.this.player.x = x;
	    SceneMap.this.player.y = y;
	    if (!GlobalVar.beginCount && !GlobalVar.win && !GlobalVar.lose)
	    {
		/* Generate enemies */
		SceneMap.this.enemy.direction = (int) (4 * Math.random ());
		SceneMap.this.enemy.width = SceneMap.this.enemy.sprite.getWidth (GlobalVar.main) / 4;
		SceneMap.this.enemy.height = SceneMap.this.enemy.sprite.getHeight (GlobalVar.main) / 4;
		SceneMap.this.enemy.screenX = (SceneMap.this.enemy.x * 32 + 32 - SceneMap.this.enemy.width + SceneMap.this.enemy.x * 32) / 2 + 3;
		SceneMap.this.enemy.screenY = (SceneMap.this.enemy.y * 32 - (SceneMap.this.enemy.height - 32)) + 23;
		SceneMap.this.activeEnemy = new Cock ();
		SceneMap.this.activeEnemy.initialize ();
		SceneMap.this.activeEnemy.alive = true;
	    }
	}


	public void drawMap ()
	{
	    GlobalVar.offScreenG.drawImage (SceneMap.this.map [1], 3, 23, null);
	}


	public void drawSuperTile ()
	{
	    GlobalVar.offScreenG.drawImage (SceneMap.this.superTile [0], 512 + 3, -32 + 23, null);
	    GlobalVar.offScreenG.drawImage (SceneMap.this.superTile [0], 480 + 3, 352 + 23, null);
	    GlobalVar.offScreenG.drawImage (SceneMap.this.superTile [1], -32 + 3, 320 + 23, null);
	}
    }


    private final class Map03 extends SceneMap
    {
	Map03 (int x, int y) throws Exception
	{
	    SceneMap.this.player.x = x;
	    SceneMap.this.player.y = y;
	    if (!GlobalVar.beginCount && GlobalVar.readyToCut && !GlobalVar.win && !GlobalVar.lose)
	    {
		SceneMap.this.activeEnemy = new Tree ();
		SceneMap.this.activeEnemy.initialize ();
		SceneMap.this.activeEnemy.alive = true;
	    }
	}


	public void drawMap ()
	{
	    GlobalVar.offScreenG.drawImage (SceneMap.this.map [2], 3, 23, null);
	    /* draw the master */
	    GlobalVar.offScreenG.drawImage (SceneMap.this.master, 32 * 6 + 3, 32 * 6 + 23 - 16, 32 * 6 + 3 + 32, 32 * 6 + 23 + 32, 0, masterDir * 48, 32, (masterDir + 1) * 48, null);
	}


	public void drawSuperTile ()
	{
	    GlobalVar.offScreenG.drawImage (SceneMap.this.superTile [0], 416 + 3, 192 + 23, null);
	}
    }
}
