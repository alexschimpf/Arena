package com.tender.saucer.arena.entity.player;

import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;

/**
 * This represents a player that does melee attacks.
 */
public class MeleePlayer extends Player
{
	public MeleePlayer(Level level, float x, float y)
	{
		super(level, x, y, "melee_player.png", 1, 8, 200);
		
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
