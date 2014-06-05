package com.tender.saucer.arena.actor.body;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;

public abstract class ActorBody extends Actor implements Disposable, ICollide
{
	protected TextureRegion region;
	protected Body body;
	protected World world;

	public ActorBody(Level level)
	{
		world = level.getWorld();
	}

	@Override
	public void dispose()
	{
		world.destroyBody(body);
	}
	
	public TextureRegion getTextureRegion()
	{
		return region;
	}

	public Body getBody()
	{
		return body;
	}
}
