package com.tender.saucer.arena.entity.player;

import com.tender.saucer.arena.level.Level;

/**
 * This represents a player that does melee attacks.
 */
public class MeleePlayer extends Player
{
	public MeleePlayer(Level level, float x, float y)
	{
		super(level, x, y, "player_melee.png", 1, 8, 0.1f);
		
		damage = BASE_DAMAGE * 3;
	}

	@Override
	public void attack()
	{

	}
}
