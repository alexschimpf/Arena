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
		bDef.position.set(x, y); // TODO: NEED TO CONVERT VIEW COORDS TO WORLD COORDS!
		body = world.createBody(bDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(Level.CELL_SIZE, Level.CELL_SIZE);
		
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
