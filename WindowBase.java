package swordMaster.windows;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.File;
import swordMaster.data.GlobalVar;

public class WindowBase
{
    /*
    x, y : The coordinate of the top left corner of the window
    width : width of the window
    height : height of the window
    visible : if it is visible
    windowskin : the skin of the window
    */
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    public boolean visible;
    BufferedImage windowskin;
    public WindowBase (int x, int y, int width, int height) throws Exception
    {
	/* Window Skin */
	final String path = "SwordMaster\\Graphics\\001-Blue01.png";
	File f = new File(path);
	if (!f.exists ())
	    throw new Exception ();
	this.x = x;
	this.y = y;
	this.width = width;
	this.height = height;
	this.visible = false;
	/*
	 Use getImage to load the picture here. This is different from
	 createImage () because getImage can create a iamge object that
	 can be shared in many instances or subclassed. It is like a
	 static variable.
	*/
	windowskin = ImageIO.read(f);
    }


    /* 29 is the height of the status bar */
    public void refresh ()
    {
	/* Draw the background of the window */
	GlobalVar.offScreenG.drawImage (windowskin, x + 6, y + 23 + 3, x + width, y + height + 17, 0, 0, 127, 127, null);
	/* Four sides of margin */
	GlobalVar.offScreenG.drawImage (windowskin, x + 6, y + 23, x + width, y + 23 + 3, 131, 0, 189, 3, null);
	GlobalVar.offScreenG.drawImage (windowskin, x + 6, y + height + 17, x + width, y + height + 20, 131, 61, 189, 64, null);
	GlobalVar.offScreenG.drawImage (windowskin, x + 3, y + 23 + 3, x + 6, y + height + 23 - 3, 128, 3, 131, 61, null);
	GlobalVar.offScreenG.drawImage (windowskin, x + width, y + 23 + 3, x + width + 3, y + height + 23 - 3, 189, 3, 192, 61, null);
	/* Four corners of margin */
	GlobalVar.offScreenG.drawImage (windowskin, x + 3, y + 23, x + 6, y + 23 + 3, 128, 0, 131, 3, null);
	GlobalVar.offScreenG.drawImage (windowskin, x + width, y + 23, x + width + 3, y + 23 + 3, 189, 0, 192, 3, null);
	GlobalVar.offScreenG.drawImage (windowskin, x + width, y + height + 17, x + width + 3, y + height + 20, 189, 61, 192, 64, null);
	GlobalVar.offScreenG.drawImage (windowskin, x + 3, y + height + 17, x + 6, y + height + 20, 128, 61, 131, 64, null);
    }


    /* Method to draw text on the window */
    public void drawText (String str, int x, int y)
    {
	GlobalVar.offScreenG.setFont (new Font ("Arial", 0, GlobalVar.defaultFontSize));
	GlobalVar.offScreenG.drawString (str, this.x + x + 21, this.y + y + 57);
    }


    public BufferedImage getWindowskin ()
    {
	return windowskin;
    }


    public void setX (int x)
    {
	this.x = x;
    }


    public void setY (int y)
    {
	this.y = y;
    }


    public int getWidth ()
    {
	return width;
    }


    public int getHeight ()
    {
	return height;
    }
}
