package swordMaster.data;
import swordMaster.windows.WindowMessage;

/* soliloquy event */
public class Event1
{
    public static int msgIndex = 0;
    public static void main ()
    {
	switch (msgIndex)
	{
	    case 0:
		GlobalVar.scene.messageWindow.drawMessage
		    (
			"The grey-eyed morn smiles on the\n".concat (
			    "frowning night, chequering the\n").concat (
			    "eastern clouds with streaks of\n").concat (
			    "light..."), " "
			);
		break;
	    case 1:
		GlobalVar.scene.messageWindow.drawMessage
		    (
			"Morning comes again! Such scene \n".concat (
			    "is repeated everyday. Sigh...When\n").concat (
			    "can I go outside of this remote\n").concat (
			    "village? I'll never know."), " "
			);
		break;
	    case 2:
		GlobalVar.scene.messageWindow.drawMessage
		    (
			"But anyway.. Well, I'd better go to\n".concat (
			    "see master."), " "
			);
		break;
	    default: /* Terminate event when reach the end of the index */
		GlobalVar.event1 = false;
	}
    }
}
