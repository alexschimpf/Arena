package com.tender.saucer.arena.actor.body.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.tender.saucer.arena.actor.body.ActorBody;
import com.tender.saucer.arena.collision.BodyData;
import com.tender.saucer.arena.collision.ICollide;
import com.tender.saucer.arena.level.Level;

public class Platform extends ActorBody
{
	public Platform(Level level, float x, float y)
	{
		super(level);
		
		int choice = MathUtils.random(level.getTextures().size - 1);
		Texture texture = level.getTextures().get(choice);
		region = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0][0];
			
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.StaticBody;
		bDef.position.set(x, y);
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
}
