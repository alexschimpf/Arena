package com.tender.saucer.arena.entity.player;

import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;

/**
 * This represents a player that does melee attacks.
 */
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
