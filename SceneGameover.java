package swordMaster.scenes;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.io.File;
import swordMaster.data.GlobalVar;

public class SceneGameover extends Scene
{
    BufferedImage gameover;

    public void main () throws Exception
    {
	String path = "swordMaster\\graphics\\CG\\001-Gameover01.jpg";
	File f = new File(path);
	if (!f.exists ())
	    throw new Exception ();
	gameover = ImageIO.read(f);
	do
	{
	    update (gameover);
	}
	while (GlobalVar.scene instanceof SceneGameover && !GlobalVar.gameOver);
	GlobalVar.offScreenG.clearRect (3, 23, 640, 480);
	System.gc ();
    }


    public void update (BufferedImage img)
    {
	super.update (img);
	GlobalVar.offScreenG.setColor (new Color (255, 255, 255));
	GlobalVar.offScreenG.drawImage (gameover, 3, (480 - gameover.getHeight (GlobalVar.main)) / 2 + 23, null);
	GlobalVar.offScreenG.drawString (
		(GlobalVar.win ? "You win!":
	"You lose!"), 275, 180);
	GlobalVar.mainG.drawImage (GlobalVar.offScreen, 0, 0, GlobalVar.main);
    }
}
