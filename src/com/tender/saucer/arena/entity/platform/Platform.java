package com.tender.saucer.arena.entity.platform;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.level.Level;

/**
 * This represents a square block the player can walk on.
 */
public class Platform extends Entity
{
	public Platform(Level level, float x, float y)
	{
		super(level);
		
		int choice = MathUtils.random(level.getEnvironmentTextures().size - 1);
		Texture texture = level.getEnvironmentTextures().get(choice);
		sprite = new Sprite(texture, Level.CELL_SIZE, Level.CELL_SIZE);
		sprite.setPosition(x, y);
	}

	@Override
	public boolean update()
	{
		return super.update();
	}
	
	@Override
	public void onDone()
	{	
	}
}
