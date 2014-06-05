package com.tender.saucer.arena.actor.body.player;

import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;

public class MeleePlayer extends Player
{
	public MeleePlayer(Level level)
	{
		super(level);
		
		damage = BASE_DAMAGE * 3;
	}

	@Override
	public void collide(ICollide other)
	{

	}

	@Override
	public void attack()
	{

	}
}
