package swordMaster.data;

public abstract class GameEnemy
{
    protected int strength;
    public int hp;
    public int maxHp;
    public int damage;
    public boolean alive = false;

    public static boolean isAggressive = false;

    private long time = 0;

    public abstract void initialize ();

    public void update ()
    {
	damage = strength + (int) (2 * Math.random ());
	/* death */
	if (hp <= 0)
	    alive = false;
	/* attack the player */
	if (
		(
		    (GlobalVar.scene.player.x == GlobalVar.scene.enemy.x && Math.abs (GlobalVar.scene.enemy.y - GlobalVar.scene.player.y) == 1)
		    ||
		    (GlobalVar.scene.player.y == GlobalVar.scene.enemy.y && Math.abs (GlobalVar.scene.enemy.x - GlobalVar.scene.player.x) == 1)
		    )
		&& (!isAggressive)
		)
	{
	    if (time == 0)
		time = System.currentTimeMillis ();
	    else if (System.currentTimeMillis () - time >= 1000)
	    {
		isAggressive = true;
		time = 0;
	    }
	}
    }
}


