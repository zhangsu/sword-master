package swordMaster.data;
//import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public final class SpritePlayer
{
    public long time;
    public int x;
    public int y;
    /* Direction: 0 South 1 West 2 East 3 North */
    public int direction;
    public boolean isMoving;
    int width;
    int height;
    int screenX;
    int screenY;
    public static BufferedImage sprite;
    public static final String path = "SwordMaster\\Graphics\\Characters\\001-Player01.png";
    public SpritePlayer () throws Exception
    {
    File f = new File(path);
	if (!f.exists ())
	    throw new Exception ();
	this.sprite = ImageIO.read(f);
	this.direction = 3;
	this.isMoving = false;
    }


    public void update ()
    {
	width = sprite.getWidth (GlobalVar.main) / 4;
	height = sprite.getHeight (GlobalVar.main) / 4;
	/* Origin of the sprite */
	screenX = (x * 32 + 32 - width + x * 32) / 2 + 3;
	screenY = (y * 32 - (height - 32)) + 23;
	if (!this.isMoving)
	{
	    /* 32 is the length of one tile */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY,
		    screenX + width, screenY + height,
		    0, this.direction * 48, width, this.direction * 48 + height, GlobalVar.main);
	    /* Draw the tile(s) that is(are) prior */
	    GlobalVar.scene.runningMap.drawSuperTile ();
	}
	/* Action of moving */
	else
	{
	    GlobalVar.scene.runningMap.drawMap ();
	    if (GlobalVar.scene.activeEnemy.alive && GlobalVar.scene.activeEnemy instanceof Cock)
		GlobalVar.scene.enemy.update ();
	    switch (this.direction)
	    {
		case 0:
		    moveDown ();
		    break;
		case 1:
		    moveLeft ();
		    break;
		case 2:
		    moveRight ();
		    break;
		case 3:
		    moveUp ();
	    }
	}
    }


    public void updateFrames ()
    {
	GlobalVar.scene.runningMap.drawSuperTile ();
	/* Update the counter */
	if (GlobalVar.beginCount)
	    GlobalVar.scene.counter.update ();
	GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
    }


    public void moveRight ()
    {
	if (System.currentTimeMillis () - time < 50)
	{
	    /* First frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX - 32 + (int) (System.currentTimeMillis () - time) / 7, screenY,
		    screenX + width - 32 + (int) (System.currentTimeMillis () - time) / 7, screenY + height,
		    0, this.direction * 48, width, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 50 && System.currentTimeMillis () - time < 100)
	{
	    /* second frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX - 24 + (int) (System.currentTimeMillis () - time) / 13, screenY,
		    screenX + width - 24 + (int) (System.currentTimeMillis () - time) / 13, screenY + height,
		    width, this.direction * 48, width * 2, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 100 && System.currentTimeMillis () - time < 150)
	{
	    /* third frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX - 16 + (int) (System.currentTimeMillis () - time) / 19, screenY,
		    screenX + width - 16 + (int) (System.currentTimeMillis () - time) / 19, screenY + height,
		    width * 2, this.direction * 48, width * 3, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 150)
	{
	    /* fourth frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX - 8 + (int) (System.currentTimeMillis () - time) / 25, screenY,
		    screenX + width - 8 + (int) (System.currentTimeMillis () - time) / 25, screenY + height,
		    width * 3, this.direction * 48, width * 4, this.direction * 48 + height, null);
	    updateFrames ();
	    this.isMoving = false;
	}
    }


    public void moveLeft ()
    {
	if (System.currentTimeMillis () - time < 50)
	{
	    /* First frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX + 32 - (int) (System.currentTimeMillis () - time) / 7, screenY,
		    screenX + width + 32 - (int) (System.currentTimeMillis () - time) / 7, screenY + height,
		    0, this.direction * 48, width, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 50 && System.currentTimeMillis () - time < 100)
	{
	    /* second frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX + 24 - (int) (System.currentTimeMillis () - time) / 13, screenY,
		    screenX + width + 24 - (int) (System.currentTimeMillis () - time) / 13, screenY + height,
		    width, this.direction * 48, width * 2, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 100 && System.currentTimeMillis () - time < 150)
	{
	    /* third frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX + 16 - (int) (System.currentTimeMillis () - time) / 19, screenY,
		    screenX + width + 16 - (int) (System.currentTimeMillis () - time) / 19, screenY + height,
		    width * 2, this.direction * 48, width * 3, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 150)
	{
	    /* fourth frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX + 8 - (int) (System.currentTimeMillis () - time) / 25, screenY,
		    screenX + width + 8 - (int) (System.currentTimeMillis () - time) / 25, screenY + height,
		    width * 3, this.direction * 48, width * 4, this.direction * 48 + height, null);
	    updateFrames ();
	    this.isMoving = false;
	}
    }


    public void moveUp ()
    {
	if (System.currentTimeMillis () - time < 50)
	{
	    /* First frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY + 32 - (int) (System.currentTimeMillis () - time) / 7,
		    screenX + width, screenY + height + 32 - (int) (System.currentTimeMillis () - time) / 7,
		    0, this.direction * 48, width, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 50 && System.currentTimeMillis () - time < 100)
	{
	    /* second frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY + 24 - (int) (System.currentTimeMillis () - time) / 13,
		    screenX + width, screenY + height + 24 - (int) (System.currentTimeMillis () - time) / 13,
		    width, this.direction * 48, width * 2, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 100 && System.currentTimeMillis () - time < 150)
	{

	    /* third frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY + 16 - (int) (System.currentTimeMillis () - time) / 19,
		    screenX + width, screenY + height + 16 - (int) (System.currentTimeMillis () - time) / 19,
		    width * 2, this.direction * 48, width * 3, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 150)
	{
	    /* fourth frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY + 8 - (int) (System.currentTimeMillis () - time) / 25,
		    screenX + width, screenY + height + 8 - (int) (System.currentTimeMillis () - time) / 25,
		    width * 3, this.direction * 48, width * 4, this.direction * 48 + height, null);
	    updateFrames ();
	    this.isMoving = false;
	}
    }


    public void moveDown ()
    {
	if (System.currentTimeMillis () - time < 50)
	{
	    /* First frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY - 32 + (int) (System.currentTimeMillis () - time) / 7,
		    screenX + width, screenY + height - 32 + (int) (System.currentTimeMillis () - time) / 7,
		    0, this.direction * 48, width, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 50 && System.currentTimeMillis () - time < 100)
	{

	    /* second frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY - 24 + (int) (System.currentTimeMillis () - time) / 13,
		    screenX + width, screenY + height - 24 + (int) (System.currentTimeMillis () - time) / 13,
		    width, this.direction * 48, width * 2, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 100 && System.currentTimeMillis () - time < 150)
	{

	    /* third frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY - 16 + (int) (System.currentTimeMillis () - time) / 19,
		    screenX + width, screenY + height - 16 + (int) (System.currentTimeMillis () - time) / 19,
		    width * 2, this.direction * 48, width * 3, this.direction * 48 + height, null);
	    updateFrames ();
	    return;
	}

	else if (System.currentTimeMillis () - time >= 150)
	{
	    /* fourth frame */
	    GlobalVar.offScreenG.drawImage (sprite, screenX, screenY - 8 + (int) (System.currentTimeMillis () - time) / 25,
		    screenX + width, screenY + height - 8 + (int) (System.currentTimeMillis () - time) / 25,
		    width * 3, this.direction * 48, width * 4, this.direction * 48 + height, null);
	    updateFrames ();
	    this.isMoving = false;
	}
    }
}


