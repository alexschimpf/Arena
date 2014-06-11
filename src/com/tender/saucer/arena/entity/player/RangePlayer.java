package com.tender.saucer.arena.entity.player;

import com.tender.saucer.arena.level.Level;

/**
 * This represents a player that does range attacks.
 */
public class RangePlayer extends Player
{
	public RangePlayer(Level level, float x, float y)
	{
		super(level, x, y, "player_range.png", 1, 8, 0.1f);
	}

	@Override
	public void attack()
	{

	}
}
