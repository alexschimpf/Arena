package com.tender.saucer.arena.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.update.IUpdate;

public abstract class Entity implements Disposable, ICollide, IUpdate
{
	protected Sprite sprite;
	protected Body body;
	protected World world;

	public Entity(Level level)
	{
		world = level.getWorld();
	}

	@Override
	public void dispose()
	{
		world.destroyBody(body);
	}
	
	public Sprite getSprite()
	{
		return sprite;
	}

	public Body getBody()
	{
		return body;
	}
}
