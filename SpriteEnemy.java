package swordMaster.data;
import java.awt.Image;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public final class SpriteEnemy
{
    public int x;
    public int y;
    /* Direction: 0 South 1 West 2 East 3 North */
    public int direction;
    public int width;
    public int height;
    public int screenX;
    public int screenY;
    public long time = 0;

    public BufferedImage sprite;
    /* Index : 0 cock 1 tree 2 master */
    public SpriteEnemy (int x, int y, int index) throws Exception
    {
	String path = "SwordMaster\\Graphics\\Characters\\003-Enemy01.png";
	if (index != 1)
	{
	    File f = new File(path);
	    if (!f.exists ())
		    throw new Exception ();
	    this.sprite = ImageIO.read(f);
	}
	this.x = x;
	this.y = y;
	this.direction = (int) (4 * Math.random ());
    }


    public void update ()
    {
	if (time == 0)
	    time = System.currentTimeMillis ();
	width = sprite.getWidth (GlobalVar.main) / 4;
	height = sprite.getHeight (GlobalVar.main) / 4;
	if (System.currentTimeMillis () - time >= 500)
	{
	    /* Origin of the sprite */
	    screenX = (x * 32 + 32 - width + x * 32) / 2 + 3;
	    screenY = (y * 32 - (height - 32)) + 23;
	    time = 0;
	}
	updateAggression ();
	/* 32 is the length of one tile */
	GlobalVar.offScreenG.drawImage (sprite, screenX, screenY,
		screenX + width, screenY + height,
		0, this.direction * 32, width, this.direction * 32 + height, GlobalVar.main);
    }


    public void updateAggression ()
    {
	if (GameEnemy.isAggressive)
	{
	    GamePlayer.attacking = true;
	    GameEnemy.isAggressive = false;
	    /* face to various direction */;
	    if (GlobalVar.scene.player.y < y)
	    {
		direction = 3;
		screenY -= 8;
	    }
	    else if (GlobalVar.scene.player.x > x)
	    {
		direction = 2;
		screenX += 8;
	    }
	    else if (GlobalVar.scene.player.x < x)
	    {
		direction = 1;
		screenX -= 8;
	    }
	    else if (GlobalVar.scene.player.y > y)
	    {
		direction = 0;
		screenY += 8;
	    }
	    GamePlayer.hp -= GlobalVar.scene.activeEnemy.damage;
	    GlobalVar.scene.updateHpBar ();
	}
    }


    public BufferedImage getSprite ()
    {
	return sprite;
    }
}
