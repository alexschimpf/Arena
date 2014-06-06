package com.tender.saucer.arena.entity.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tender.saucer.arena.collision.BodyData;
import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.stuff.ConvertUtils;

/**
 * This represents a square block the player can walk on.
 */
public class Platform extends Entity
{
	public Platform(Level level, float x, float y)
	{
		super(level);
		
		int choice = MathUtils.random(level.getTextures().size - 1);
		Texture texture = level.getTextures().get(choice);
		sprite = new Sprite(texture);
		sprite.setPosition(x, y);
			
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.StaticBody;
		bDef.position.set(ConvertUtils.toMeters(x), ConvertUtils.toMeters(y));
		body = world.createBody(bDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(ConvertUtils.toMeters(Level.CELL_SIZE / 2.0f), ConvertUtils.toMeters(Level.CELL_SIZE / 2.0f));
		
		FixtureDef fDef = new FixtureDef();
		fDef.density = 0;
		fDef.friction = 0.4f;
		fDef.restitution = 0.4f;
		fDef.shape = shape;
		body.createFixture(shape, 0);
		
		body.setUserData(new BodyData(this));
	}

	@Override
	public void collide(ICollide other)
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
	}
}
