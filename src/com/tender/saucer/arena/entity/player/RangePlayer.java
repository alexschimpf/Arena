package com.tender.saucer.arena.entity.player;

import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;

/**
 * This represents a player that does range attacks.
 */
public class RangePlayer extends Player
{
	public RangePlayer(Level level, float x, float y)
	{
		super(level, x, y, "range_player.png", 1, 8, 200);
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
