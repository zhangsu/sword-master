package swordMaster.data;
import swordMaster.scenes.SceneGameover;

public class Event4
{
    public static int msgIndex = 0;
    public static void main ()
    {
	if (GlobalVar.scene.player.direction == 1)
	    GlobalVar.scene.masterDir = 2;
	if (GlobalVar.scene.player.direction == 2)
	    GlobalVar.scene.masterDir = 1;
	if (GlobalVar.scene.player.direction == 3)
	    GlobalVar.scene.masterDir = 0;
	if (!GlobalVar.win && !GlobalVar.lose)
	{
	    switch (msgIndex)
	    {
		case 0:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Good morning, master.", " "
			    );
		    break;
		case 1:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Morning.", "Dalian Yi"
			    );
		    break;
		case 2:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Do we have any training today?", " "
			    );
		    break;
		case 3:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Actually, um, I have an important\n".concat (
				"thing to say... Wait a second. How\n").concat (
				"long have you been here?\n"), "Dalian Yi"
			    );
		    break;
		case 4:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "I guess it's about 5 years, I'm\n".concat (
				"not sure."), " "
			    );
		    break;
		case 5:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Oh yeah. I think it's the time to\n".concat (
				"let you go outside of here. Your\n").concat (
				"sword skill must be good enough\n").concat (
				"for the world out there.\n"), "Dalian Yi"
			    );
		    break;
		case 6:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "....\n", " "
			    );
		    break;
		case 7:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Even though, I have to test you,\n".concat (
				"see if you're able to go. I\n").concat (
				"found a good material for you.\n").concat (
				"It's a tree east of here."), "Dalian Yi"
			    );
		    break;
		case 8:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Your job is to cut the tree down\n".concat (
				"in half of a minute. If you success,\n").concat (
				"congratulation! I don't have any-\n").concat (
				"thing left to teach you."), "Dalian Yi"
			    );
		    break;
		case 9:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "But if you fail..You probably would\n".concat (
				"want to stay here and train a,\n").concat (
				"couple of years more, because if\n").concat (
				"you can't cut the tree down in 30"), "Dalian Yi"
			    );
		    break;
		case 10:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "seconds, you can't defeat anyone\n".concat (
				"out there neither. Tree is dead,\n").concat (
				"but a human is active! Remember.\n"), "Dalian Yi"
			    );
		    break;
		case 11:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "So you can start once you're\n".concat (
				"prepared, you can go back to your\n").concat (
				"house and kill the cock to train\n").concat (
				"your strength, don't died though."), "Dalian Yi"
			    );
		    break;
		case 12:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "There're some potions in your\n".concat (
				"house, you can pick them up.\n").concat (
				"They're good for healing.\n"), "Dalian Yi"
			    );
		    break;
		case 13:
		    GlobalVar.scene.messageWindow.drawMessage
			(
			    "Come and talk to me whether you\n".concat (
				"fail or not. I'm waiting for your\n").concat (
				"good news.\n"), "Dalian Yi"
			    );
		    break;
		default:
		    GlobalVar.event4 = false;
		    msgIndex = 13;
		    GlobalVar.readyToCut = true;
		    GlobalVar.scene.activeEnemy = new Tree ();
		    GlobalVar.scene.activeEnemy.initialize ();
		    GlobalVar.scene.activeEnemy.alive = true;
	    }
	}

	if (GlobalVar.win)
	{

	    GlobalVar.scene.messageWindow.drawMessage
		(
		    "Very well done! You made it! Nice!\n".concat (
			"I can't think of a reason for you\n").concat (
			"to not leave! Go! my prentice, now!\n").concat (
			"Take a look in the fantastic world!"), "Dalian Yi"
		    );


	}
	if (GlobalVar.lose)
	{

	    GlobalVar.scene.messageWindow.drawMessage
		(
		    "O my poor prentice, I don't think\n".concat (
			"it's a good idea for you to leave.\n").concat (
			"Ok..No problem, stay here, and\n").concat (
			"make your self stronger!"), "Dalian Yi"
		    );

	}
    }
}
