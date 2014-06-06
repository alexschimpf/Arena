package com.tender.saucer.arena.collision;

/**
 * This should be implemented by all objects that can collide.
 */
public interface ICollide
{
	public void collide(ICollide other);
}
