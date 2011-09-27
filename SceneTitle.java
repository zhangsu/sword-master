package swordMaster.scenes;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import swordMaster.data.GlobalVar;
import swordMaster.windows.WindowCommand;

public final class SceneTitle extends Scene
{
    static BufferedImage titleBG;
    public void main () throws Exception
    {
	GlobalVar.initialize ();
	String path = "SwordMaster\\Graphics\\CG\\001-Title01.jpg";
	File f = new File(path);
	if (!f.exists ())
	    throw new Exception ();
	titleBG = ImageIO.read(f);
	String s1 = "Start";
	String s2 = "Exit";
	commandWindow = new WindowCommand (76, new String[]
	{
	    s1, s2
	}
	);
	commandWindow.setX ((640 + 3 - commandWindow.getWidth ()) / 2);
	commandWindow.setY (288);
	do
	{
	    update (new BufferedImage[]
	    {
		commandWindow.getWindowskin (), titleBG
	    }
	    );
	}
	while (GlobalVar.scene instanceof SceneTitle && !GlobalVar.gameOver);
	GlobalVar.offScreenG.clearRect (3, 23, 640, 480);
	System.gc ();
    }


    public void update (BufferedImage[] imgs)
    {
	super.update (imgs);
	GlobalVar.offScreenG.drawImage (titleBG, 3, 23, null);
	commandWindow.refresh ();
	GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
    }
}
