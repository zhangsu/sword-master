package swordMaster.data;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import swordMaster.windows.WindowMenu;
import swordMaster.scenes.SceneTitle;
import swordMaster.scenes.SceneMap;
import swordMaster.scenes.SceneGameover;

public final class MyKeyListener implements KeyListener
{



    public void keyTyped (KeyEvent e)
    {
	/* Only enable input if the scene is not loading */
	if (!GlobalVar.scene.isLoading)
	{
	    /* Update input in the gameover Scene */
	    if (GlobalVar.scene instanceof SceneGameover)
	    {
		/* If press any key */
		if (Character.toString (e.getKeyChar ()) instanceof String)
		{
		    //GlobalVar.endLogo = true;
		    GlobalVar.scene = new SceneTitle ();
		    return;
		}
	    }
	}
    }


    public void keyPressed (KeyEvent e)
    {
	/* Only enable input if the scene is not loading */
	if (!GlobalVar.scene.isLoading)
	{
	    /* Update input in the title Scene */
	    if (GlobalVar.scene instanceof SceneTitle)
	    {
		if (e.getKeyCode () == e.VK_DOWN)
		{
		    GlobalVar.scene.commandWindow.index++;
		    return;
		}
		if (e.getKeyCode () == e.VK_UP)
		{
		    GlobalVar.scene.commandWindow.index--;
		    return;
		}
		if (e.getKeyCode () == 10 || e.getKeyChar () == ' ')
		{
		    /* Two choice of the title command window */
		    switch (GlobalVar.scene.commandWindow.index)
		    {
			case 0:
			    //GlobalVar.endTitle = true;
			    GlobalVar.scene = new SceneMap ();
			    break;
			case 1:
			    GlobalVar.gameOver = true;
		    }
		    return;
		}
	    }
	    if (GlobalVar.scene instanceof SceneMap && !GlobalVar.scene.player.isMoving)
	    {
		if (GlobalVar.activeWindow == null)
		{
		    switch (GlobalVar.scene.runningMapIndex)
		    {
			case 1: /* Map01 */
			    /* Event 1 : soliloquy */
			    if (GlobalVar.event1)
			    {
				if (e.getKeyCode () == 10 || e.getKeyChar () == ' ')
				{
				    GamePlayer.attacking = false;
				    Event1.msgIndex++;
				    return;
				}
			    }
			    /* Event 2 : potion */
			    if (!GlobalVar.event2 && GlobalVar.scene.player.direction == 3 && (e.getKeyCode () == 10 || e.getKeyChar () == ' ') &&
				    (
					(GlobalVar.scene.player.x == 9 || GlobalVar.scene.player.x == 10)
					&& GlobalVar.scene.player.y == 5))
			    {
				GlobalVar.event2 = true;
				GamePlayer.attacking = false;
				return;
			    }
			    if (GlobalVar.event2 && (e.getKeyCode () == 10 || e.getKeyChar () == ' '))
			    {
				if (Event2.rand >= 0.5 && !GamePlayer.potion)
				{
				    GamePlayer.noPotion = false;
				    GamePlayer.potion = true;
				}
				Event2.rand = Math.random ();
				GlobalVar.event2 = false;
				return;
			    }
			    if (!GlobalVar.event2 && !GlobalVar.event1)
			    {
				/* Update movement in Map01 */
				/* Restriction in unmovable tile */
				if (e.getKeyCode () == e.VK_RIGHT)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 2;
				    if (
					    GlobalVar.scene.player.x < 16 &&
					    (GlobalVar.scene.player.x != 15 || (GlobalVar.scene.player.y != 5 && GlobalVar.scene.player.y != 6)) &&
					    (GlobalVar.scene.player.x != 12 || GlobalVar.scene.player.y != 4) &&
					    (GlobalVar.scene.player.x != 11 || GlobalVar.scene.player.y != 9) &&
					    (GlobalVar.scene.player.x != 10 || GlobalVar.scene.player.y >= 8) &&
					    (GlobalVar.scene.player.x != 8 || GlobalVar.scene.player.y != 4)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.x++;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
				if (e.getKeyCode () == e.VK_LEFT)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 1;
				    if (
					    GlobalVar.scene.player.x > 4 &&
					    (GlobalVar.scene.player.x != 12 || GlobalVar.scene.player.y >= 8) &&
					    (GlobalVar.scene.player.x != 6 || (GlobalVar.scene.player.y != 7 && GlobalVar.scene.player.y != 8)) &&
					    (GlobalVar.scene.player.x != 6 || GlobalVar.scene.player.y != 4)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.x--;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
				if (e.getKeyCode () == e.VK_UP)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 3;
				    if (
					    (GlobalVar.scene.player.y > 5 || (GlobalVar.scene.player.x == 6 && GlobalVar.scene.player.y > 4) ||
						(GlobalVar.scene.player.x == 7 && GlobalVar.scene.player.y > 4) ||
						(GlobalVar.scene.player.x == 8 && GlobalVar.scene.player.y > 4) ||
						(GlobalVar.scene.player.x == 12 && GlobalVar.scene.player.y > 4)) &&
					    (GlobalVar.scene.player.x != 11 || GlobalVar.scene.player.y != 8) &&
					    (GlobalVar.scene.player.x != 4 || GlobalVar.scene.player.y != 9) &&
					    (GlobalVar.scene.player.x != 5 || GlobalVar.scene.player.y != 9) &&
					    (GlobalVar.scene.player.x != 16 || GlobalVar.scene.player.y != 7)
					    )

					{
					    GlobalVar.scene.player.time = System.currentTimeMillis ();
					    GlobalVar.scene.player.y--;
					    GlobalVar.scene.player.isMoving = true;
					}
				    return;
				}
				if (e.getKeyCode () == e.VK_DOWN)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 0;
				    if (
					    (
						GlobalVar.scene.player.y < 9 &&
						(GlobalVar.scene.player.y < 8 || GlobalVar.scene.player.x < 12) &&
						(GlobalVar.scene.player.y < 6 || GlobalVar.scene.player.x != 4) &&
						(GlobalVar.scene.player.y < 6 || GlobalVar.scene.player.x != 5)
						) ||
					    (GlobalVar.scene.player.y < 10 && GlobalVar.scene.player.x == 7) ||
					    (GlobalVar.scene.player.y < 10 && GlobalVar.scene.player.x == 8)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.y++;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
			    } // end of if (GlobalVar.event1)
			    break;
			case 2: /* Map02 */
			    /* Event 3: guidepost */
			    if (!GlobalVar.event3 && GlobalVar.scene.player.direction == 3 && (e.getKeyCode () == 10 || e.getKeyChar () == ' ') &&
				    (
					(GlobalVar.scene.player.x == 11 || GlobalVar.scene.player.x == 12)
					&& GlobalVar.scene.player.y == 12)
				    )
			    {
				GlobalVar.event3 = true;
				GamePlayer.attacking = false;
				GamePlayer.clearMsg ();
				return;
			    }
			    if (GlobalVar.event3 && e.getKeyCode () == 10 || e.getKeyChar () == ' ')
			    {
				GlobalVar.event3 = false;
				GamePlayer.clearMsg ();
				return;
			    }
			    if (!GlobalVar.event3)
			    {
				if (e.getKeyCode () == e.VK_RIGHT)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 2;
				    if (
					    GlobalVar.scene.player.x < 19 &&
					    (GlobalVar.scene.player.x != 16 || GlobalVar.scene.player.y != 3) &&
					    (GlobalVar.scene.player.x != 10 || GlobalVar.scene.player.y != 11) &&
					    (GlobalVar.scene.player.x != 0 || GlobalVar.scene.player.y != 13) &&
					    (GlobalVar.scene.player.x != 6 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != GlobalVar.scene.enemy.x - 1 || GlobalVar.scene.player.y != GlobalVar.scene.enemy.y || !GlobalVar.scene.activeEnemy.alive || GlobalVar.beginCount)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.x++;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
				if (e.getKeyCode () == e.VK_LEFT)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 1;
				    if (
					    GlobalVar.scene.player.x > 0 &&
					    (GlobalVar.scene.player.x != 2 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != 2 || GlobalVar.scene.player.y != 13) &&
					    (GlobalVar.scene.player.x != 13 || GlobalVar.scene.player.y != 11) &&
					    (GlobalVar.scene.player.x != 19 || GlobalVar.scene.player.y != 3) &&
					    (GlobalVar.scene.player.x != 9 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != 11 || GlobalVar.scene.player.y >= 5) &&
					    (GlobalVar.scene.player.x != GlobalVar.scene.enemy.x + 1 || GlobalVar.scene.player.y != GlobalVar.scene.enemy.y || !GlobalVar.scene.activeEnemy.alive || GlobalVar.beginCount)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.x--;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
				if (e.getKeyCode () == e.VK_UP)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 3;
				    if (
					    (
						GlobalVar.scene.player.y > 0 &&
						(GlobalVar.scene.player.x != 1 || GlobalVar.scene.player.y != 14) &&
						(GlobalVar.scene.player.x != 11 || GlobalVar.scene.player.y != 12) &&
						(GlobalVar.scene.player.x != 12 || GlobalVar.scene.player.y != 12) &&
						(GlobalVar.scene.player.x != 17 || GlobalVar.scene.player.y != 4) &&
						(GlobalVar.scene.player.x != 18 || GlobalVar.scene.player.y != 4) &&
						(GlobalVar.scene.player.x != 0 || GlobalVar.scene.player.y != 6) &&
						(GlobalVar.scene.player.x != 1 || GlobalVar.scene.player.y != 6) &&
						(GlobalVar.scene.player.x != 7 || GlobalVar.scene.player.y != 6) &&
						(GlobalVar.scene.player.x != 8 || GlobalVar.scene.player.y != 6) &&
						(GlobalVar.scene.player.x > 10 || GlobalVar.scene.player.y > 5) &&
						(GlobalVar.scene.player.x != GlobalVar.scene.enemy.x || GlobalVar.scene.player.y != GlobalVar.scene.enemy.y + 1 || !GlobalVar.scene.activeEnemy.alive || GlobalVar.beginCount)
						) ||
					    (GlobalVar.scene.player.x == 3 && GlobalVar.scene.player.y == 5)
					    )

					{
					    GlobalVar.scene.player.time = System.currentTimeMillis ();
					    GlobalVar.scene.player.y--;
					    GlobalVar.scene.player.isMoving = true;
					}
				    return;
				}
				if (e.getKeyCode () == e.VK_DOWN)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 0;
				    if (
					    GlobalVar.scene.player.y < 14 &&
					    (GlobalVar.scene.player.x != 11 || GlobalVar.scene.player.y != 10) &&
					    (GlobalVar.scene.player.x != 12 || GlobalVar.scene.player.y != 10) &&
					    (GlobalVar.scene.player.x != 1 || GlobalVar.scene.player.y != 12) &&
					    (GlobalVar.scene.player.x != 17 || GlobalVar.scene.player.y != 2) &&
					    (GlobalVar.scene.player.x != 18 || GlobalVar.scene.player.y != 2) &&
					    (GlobalVar.scene.player.x != GlobalVar.scene.enemy.x || GlobalVar.scene.player.y != GlobalVar.scene.enemy.y - 1 || !GlobalVar.scene.activeEnemy.alive || GlobalVar.beginCount)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.y++;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
			    } // end of if (!GlobalVar.event3)
			    break;
			case 3: /* Map03 */
			    if (!GlobalVar.event4 && (e.getKeyCode () == 10 || e.getKeyChar () == ' ') && (
					(GlobalVar.scene.player.direction == 3 && GlobalVar.scene.player.x == 6 && GlobalVar.scene.player.y == 7) ||
					(GlobalVar.scene.player.direction == 1 && GlobalVar.scene.player.x == 7 && GlobalVar.scene.player.y == 6) ||
					(GlobalVar.scene.player.direction == 2 && GlobalVar.scene.player.x == 5 && GlobalVar.scene.player.y == 6)
					)
				    )
			    {
				GlobalVar.event4 = true;
				return;
			    }
			    /* Event 4 */
			    if (GlobalVar.event4 && e.getKeyCode () == 10 || e.getKeyChar () == ' ')
			    {
				Event4.msgIndex++;
				if (GlobalVar.lose || GlobalVar.win)
				{
				    GlobalVar.event4 = false;
				    GlobalVar.scene = new SceneGameover ();
				}
				return;
			    }
			    if (!GlobalVar.event4)
			    {
				if (e.getKeyCode () == e.VK_RIGHT)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 2;
				    if (
					    GlobalVar.scene.player.x < 19 &&
					    (GlobalVar.scene.player.x != 4 || GlobalVar.scene.player.y != 4) &&
					    (GlobalVar.scene.player.x != 4 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != 14 || GlobalVar.scene.player.y != 4) &&
					    (GlobalVar.scene.player.x != 14 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != 13 || GlobalVar.scene.player.y != 10) &&
					    (GlobalVar.scene.player.x != 5 || GlobalVar.scene.player.y != 6)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.x++;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
				if (e.getKeyCode () == e.VK_LEFT)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 1;
				    if (
					    GlobalVar.scene.player.x > 0 &&
					    (GlobalVar.scene.player.x != 7 || GlobalVar.scene.player.y != 4) &&
					    (GlobalVar.scene.player.x != 7 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != 17 || GlobalVar.scene.player.y != 4) &&
					    (GlobalVar.scene.player.x != 17 || GlobalVar.scene.player.y != 5) &&
					    (GlobalVar.scene.player.x != 16 || GlobalVar.scene.player.y != 10) &&
					    (GlobalVar.scene.player.x != 7 || GlobalVar.scene.player.y != 6)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.x--;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
				if (e.getKeyCode () == e.VK_UP)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 3;
				    if (
					    GlobalVar.scene.player.y > 0 &&
					    (GlobalVar.scene.player.x != 5 || GlobalVar.scene.player.y != 6) &&
					    (GlobalVar.scene.player.x != 6 || GlobalVar.scene.player.y != 6) &&
					    (GlobalVar.scene.player.x != 14 || GlobalVar.scene.player.y != 11) &&
					    (GlobalVar.scene.player.x != 15 || GlobalVar.scene.player.y != 11) &&
					    (GlobalVar.scene.player.x != 15 || GlobalVar.scene.player.y != 6) &&
					    (GlobalVar.scene.player.x != 16 || GlobalVar.scene.player.y != 6) &&
					    (GlobalVar.scene.player.x != 6 || GlobalVar.scene.player.y != 7)
					    )

					{
					    GlobalVar.scene.player.time = System.currentTimeMillis ();
					    GlobalVar.scene.player.y--;
					    GlobalVar.scene.player.isMoving = true;
					}
				    return;
				}
				if (e.getKeyCode () == e.VK_DOWN)
				{
				    GamePlayer.attacking = false;
				    GamePlayer.clearMsg ();
				    GlobalVar.scene.player.direction = 0;
				    if (
					    GlobalVar.scene.player.y < 14 &&
					    (GlobalVar.scene.player.x != 5 || GlobalVar.scene.player.y != 3) &&
					    (GlobalVar.scene.player.x != 6 || GlobalVar.scene.player.y != 3) &&
					    (GlobalVar.scene.player.x != 14 || GlobalVar.scene.player.y != 9) &&
					    (GlobalVar.scene.player.x != 15 || GlobalVar.scene.player.y != 9) &&
					    (GlobalVar.scene.player.x != 15 || GlobalVar.scene.player.y != 3) &&
					    (GlobalVar.scene.player.x != 16 || GlobalVar.scene.player.y != 3)
					    )
				    {
					GlobalVar.scene.player.time = System.currentTimeMillis ();
					GlobalVar.scene.player.y++;
					GlobalVar.scene.player.isMoving = true;
				    }
				    return;
				}
			    } // end of if (!GlobalVar.event4)

			default:
		    } // end of switch statement
		    /* If press ESC, open the menu window */
		    if (e.getKeyCode () == 27 && !GlobalVar.event1 && !GlobalVar.event2 && !GlobalVar.event3 && !GlobalVar.event4)
		    {
			GamePlayer.attacking = false;
			GamePlayer.clearMsg ();
			GlobalVar.activeWindow = GlobalVar.scene.menuWindow;
			GlobalVar.scene.menuWindow.index = 0;
			return;
		    }
		} // end of if (GlobalVar.activeWindow == null)
		/* If menu window opens */
		if (GlobalVar.activeWindow instanceof WindowMenu && !GlobalVar.scene.infoWindow.visible && !GlobalVar.scene.statusWindow.visible)
		{
		    if (e.getKeyCode () == e.VK_DOWN)
		    {
			GlobalVar.activeWindow.index++;
			return;
		    }
		    if (e.getKeyCode () == e.VK_UP)
		    {
			GlobalVar.activeWindow.index--;
			return;
		    }
		    if (e.getKeyCode () == 10 || e.getKeyChar () == ' ')
		    {
			/* choice of the main menu window */
			switch (GlobalVar.activeWindow.index)
			{
			    case 0:
				GlobalVar.scene.statusWindow.visible = true;
				break;
			    case 1:
				GlobalVar.scene.infoWindow.visible = true;
				break;
			    case 2:
				if (GamePlayer.potion)
				{
				    GamePlayer.takingPotion = true;
				    GamePlayer.potion = false;
				    GamePlayer.hp += 10;
				    GlobalVar.activeWindow = null;
				}
				else
				{
				    GamePlayer.noPotion = true;
				    GlobalVar.activeWindow = null;
				}
				break;
			    case 3:
				GlobalVar.gameOver = true;
				break;
			    case 4:
				GlobalVar.activeWindow = null;
			}
			return;
		    }
		    if (e.getKeyCode () == 27)
		    {
			GlobalVar.activeWindow = null;
			return;
		    }
		} // end of if (GlobalVar.activeWindow instanceof WindowMenu)
		if (GlobalVar.scene.infoWindow.visible)
		{
		    if (e.getKeyCode () == 27)
		    {
			GlobalVar.scene.infoWindow.visible = false;
			return;
		    }
		}
		if (GlobalVar.scene.statusWindow.visible)
		{
		    if (e.getKeyCode () == 27)
		    {
			GlobalVar.scene.statusWindow.visible = false;
			return;
		    }
		}
	    } // end of if (GlobalVar.scene instanceof SceneMap && !GlobalVar.scene.player.isMoving)
	} //end of if (!GlobalVar.scene.isLoading)
    } // end of keyPressed method


    public void keyReleased (KeyEvent e)
    {
	int[] attackPoint = new int [2];
	if (GlobalVar.scene instanceof SceneMap) // && !GlobalVar.scene.player.isMoving)
	{
	    if (GlobalVar.activeWindow == null)
	    {
		if (e.getKeyCode () == 17)
		{
		    Animation.on = true;
		    GamePlayer.attacking = true;
		    switch (GlobalVar.scene.player.direction)
		    {
			case 0:
			    attackPoint = new int[]
			    {
				GlobalVar.scene.player.x, GlobalVar.scene.player.y + 1
			    }
			    ;
			    break;
			case 1:
			    attackPoint = new int[]
			    {
				GlobalVar.scene.player.x - 1, GlobalVar.scene.player.y
			    }
			    ;
			    break;
			case 2:
			    attackPoint = new int[]
			    {
				GlobalVar.scene.player.x + 1, GlobalVar.scene.player.y
			    }
			    ;
			    break;
			case 3:
			    attackPoint = new int[]
			    {
				GlobalVar.scene.player.x, GlobalVar.scene.player.y - 1
			    }
			    ;
		    }
		    if (GlobalVar.scene.activeEnemy instanceof Cock && GlobalVar.scene.runningMapIndex == 2 &&
			    attackPoint [0] == GlobalVar.scene.enemy.x && attackPoint [1] == GlobalVar.scene.enemy.y && GlobalVar.scene.activeEnemy.alive)
		    {
			GlobalVar.scene.activeEnemy.hp -= GamePlayer.damage;
			GamePlayer.strExp++;
			GamePlayer.maxHpExp++;
		    }
		    if (GlobalVar.scene.activeEnemy instanceof Tree && GlobalVar.scene.runningMapIndex == 3 &&
			    attackPoint [0] == GlobalVar.scene.tree.x && attackPoint [1] == GlobalVar.scene.tree.y && GlobalVar.scene.activeEnemy.alive)
		    {
			GlobalVar.beginCount = true;
			GlobalVar.scene.activeEnemy.hp -= GamePlayer.damage;
		    }
		}
	    }
	}
    } // end of keyReleased method
}


