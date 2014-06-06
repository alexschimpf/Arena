package com.tender.saucer.arena.collision;

import com.tender.saucer.arena.entity.Entity;

/**
 * Used in {@link CollisionListener} for body context (the entity owner).
 */
public class BodyData
{
	public Entity owner;

	public BodyData(Entity owner)
	{
		this.owner = owner;
	}
}
