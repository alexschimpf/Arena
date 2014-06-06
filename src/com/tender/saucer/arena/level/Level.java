package com.tender.saucer.arena.level;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.tender.saucer.arena.collision.CollisionListener;
import com.tender.saucer.arena.entity.platform.Platform;
import com.tender.saucer.arena.stuff.Backbone;
import com.tender.saucer.arena.update.IUpdate;

/**
 * This represents the game level and physics world that players play in.
 */
public class Level implements IUpdate
{
	public static final int GRAVITY = -10;
	public static final int CELL_SIZE = 25;
	
	protected int width;
	protected int height;
	protected int numCellsWide;
	protected int numCellsHigh;
	protected Array<Texture> textures;
	protected World world = new World(new Vector2(0, GRAVITY), true);
	protected Platform[][] platforms;	
	
	public Level()
	{
		world.setContactListener(new CollisionListener());
		textures = Environment.getRandom();
		
		build();
	}
	
	protected void build()
	{
		int min = (Backbone.WIDTH * 3) / CELL_SIZE;
		int max = (Backbone.WIDTH * 6) / CELL_SIZE;
		numCellsWide = MathUtils.random(min, max);
		numCellsHigh = MathUtils.random(min, max);
		width = numCellsWide * CELL_SIZE;
		height = numCellsHigh * CELL_SIZE;
		platforms = new Platform[numCellsWide][numCellsHigh]; 
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getNumCellsWide()
	{
		return numCellsWide;
	}

	public int getNumCellsHigh()
	{
		return numCellsHigh;
	}

	public Array<Texture> getTextures()
	{
		return textures;
	}

	public World getWorld()
	{
		return world;
	}

	public Platform[][] getPlatforms()
	{
		return platforms;
	}

	@Override
	public boolean update()
	{
		world.step(Gdx.graphics.getDeltaTime(), 3, 3);
		
		return false;
	}

	@Override
	public void onDone()
	{
		
	}
}
