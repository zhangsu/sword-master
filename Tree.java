package swordMaster.data;

public class Tree extends GameEnemy
{
    public void initialize ()
    {
	hp = 10000;
	maxHp = 10000;
    }


    public void update ()
    {
	/* death */
	if (hp <= 0)
	{
	    alive = false;
	    GlobalVar.beginCount = false;
	    GlobalVar.win = true;
	}
    }
}
