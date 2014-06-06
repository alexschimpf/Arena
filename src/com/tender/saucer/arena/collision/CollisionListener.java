package com.tender.saucer.arena.collision;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.tender.saucer.arena.entity.Entity;

/**
 * This is the contact listener used by the physics world in {@link Level}.
 */
public class CollisionListener implements ContactListener
{
	@Override
	public void beginContact(Contact contact)
	{
		Fixture fixA = contact.getFixtureA();
		Fixture fixB = contact.getFixtureB();
		Body bodyA = fixA.getBody();
		Body bodyB = fixB.getBody();
		Entity ownerA = (Entity)bodyA.getUserData();
		Entity ownerB = (Entity)bodyB.getUserData();
		ownerA.collide(ownerB);
		ownerB.collide(ownerA);
	}

	@Override
	public void endContact(Contact contact)
	{
	
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold)
	{

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse)
	{

	}
}
