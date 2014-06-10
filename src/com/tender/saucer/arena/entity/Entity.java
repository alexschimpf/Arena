package com.tender.saucer.arena.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.update.IUpdate;

/**
 * This represents a body with an attached sprite.
 */
public abstract class Entity implements Disposable, IUpdate
{
	protected Sprite sprite;

	public Entity(Level level)
	{
	}

	@Override
	public void dispose()
	{
		
	}
	
	@Override
	public boolean update()
	{
		return false;
	}
	
	@Override
	public void onDone()
	{
		dispose();	
	}
	
	public void draw(SpriteBatch batch)
	{
		sprite.draw(batch);
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}
}
