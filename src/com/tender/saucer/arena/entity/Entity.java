package com.tender.saucer.arena.entity;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Disposable;
import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.miscellaneous.ConvertUtils;
import com.tender.saucer.arena.update.IUpdate;

/**
 * This represents a body with an attached sprite.
 */
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
	
	@Override
	public boolean update()
	{
		float x = ConvertUtils.toPixels(body.getPosition().x);
		float y = ConvertUtils.toPixels(body.getPosition().y);
		sprite.setPosition(x, y);	
		
		sprite.setRotation(MathUtils.radiansToDegrees * body.getAngle());
		
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

	public Body getBody()
	{
		return body;
	}
}
