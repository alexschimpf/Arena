package com.tender.saucer.arena.level;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.tender.saucer.arena.collision.CollisionListener;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.entity.platform.Platform;
import com.tender.saucer.arena.entity.player.Player;
import com.tender.saucer.arena.miscellaneous.Backbone;
import com.tender.saucer.arena.miscellaneous.ConvertUtils;
import com.tender.saucer.arena.update.IUpdate;

/**
 * This represents the game level and physics world that players exist in.
 */
public class Level implements IUpdate
{
	public static final int GRAVITY = -10;
	public static final int CELL_SIZE = 25;
	
	protected int width;
	protected int height;
	protected int numCols;
	protected int numRows;
	protected Array<Texture> textures;
	protected World world = new World(new Vector2(0, GRAVITY), true);
	protected Platform[][] platforms;	
	protected Array<Entity> entities = new Array<Entity>();
	protected Player player;
	
	public static Vector2 toXY(Level level, int row, int col)
	{
		if(row >= level.getNumRows() || row < 0 || col >= level.getNumCols() || col < 0)
		{
			return null;
		}
		
		return new Vector2(col * CELL_SIZE, (level.getNumRows() - row) * CELL_SIZE);
	}
	
	public static Vector2 toRowCol(Level level, float x, float y)
	{
		if(x >= level.getWidth() || x < 0 || y >= level.getHeight() || y < 0)
		{
			return null;
		}
		
		int row = level.getNumRows() - MathUtils.ceil(y / CELL_SIZE);
		int col = (int)(x / CELL_SIZE);
		return new Vector2(row, col);
	}
	
	public Level()
	{
		world.setContactListener(new CollisionListener());
		textures = Environment.getRandom();
		
		build();
	}
	
	@Override
	public boolean update()
	{
		world.step(Gdx.graphics.getDeltaTime(), 3, 3);
		
		updateEntities();
		
		return false;
	}

	@Override
	public void onDone()
	{
		
	}
	
	protected void build()
	{
		int min = (Backbone.WIDTH * 3) / CELL_SIZE;
		int max = (Backbone.WIDTH * 6) / CELL_SIZE;
		numCols = MathUtils.random(min, max);
		numRows = MathUtils.random(min, max);
		width = numCols * CELL_SIZE;
		height = numRows * CELL_SIZE;
		platforms = new Platform[numCols][numRows]; 
		
		createBounds();		
		createRandTerrain();
	}
	
	protected void updateEntities()
	{
		Iterator<Entity> it = entities.iterator();
		while(it.hasNext())
		{
			Entity entity = (Entity)it.next();
			if(entity.update())
			{
				entity.onDone();
				it.remove();
			}
		}
	}
	
	protected void createBounds()
	{
		createSideBound(-1, height);
		createSideBound(width, height);	
		createFloor();
	}
	
	protected void createSideBound(float x, float y)
	{			
		BodyDef bDef = new BodyDef();
		bDef.type = BodyType.StaticBody;
		bDef.position.set(ConvertUtils.toMeters(x), ConvertUtils.toMeters(y));
		Body body = world.createBody(bDef);
		
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(1, height);
		
		FixtureDef fDef = new FixtureDef();
		fDef.density = 0;
		fDef.friction = 0;
		fDef.restitution = 0.4f;
		fDef.shape = shape;
		body.createFixture(shape, 0);
	}
	
	protected void createFloor()
	{
		// Create a line of tiles at y = 0.
		for(int i = 0; i < numCols; i++)
		{
			Platform platform = new Platform(this, i * CELL_SIZE, 0);
			entities.add(platform);
		}
	}
	
	/**
	 * 1. Start near the bottom left of the map.
	 * 2. Build random platform "units" across the map (from left to right) about some row.
	 * 3. Move up the map and repeat 2 until we get close to the ceiling.
	 */
	protected void createRandTerrain()
	{	
		int startRow = MathUtils.random(3, 5);
		while(startRow > 10)
		{
			int row = startRow;
			int col = MathUtils.random(0, numCols / 10);
			
			while(true)
			{
				int unitWidth = MathUtils.random(1, 10);
				if(col + unitWidth >= numCols)
				{
					break;
				}							
				createRandPlatformUnit(row, col, unitWidth);
				
				col += MathUtils.random(5, numCols / 10);
				row = startRow - MathUtils.random(0, 7);
			}
			
			startRow -= MathUtils.random(12, 20);
		}
	}
	
	protected void createRandPlatformUnit(int startRow, int startCol, int unitWidth)
	{	
		int row = startRow;
		int col = startCol;
		for(int i = 0; i < unitWidth; i++)
		{
			int dRow = MathUtils.random() < 0.5f ? 0 : 1;
			dRow += MathUtils.random() < 0.5f ? 0 : -1;
			row = Math.min(row - dRow, startRow);
			col++; 
			
			int numCellsDown = 1 + MathUtils.random(0, startRow - row);
			int currRow = row;
			for(int j = 0; j < numCellsDown; j++)
			{
				Vector2 pos = toXY(this, currRow, col);
				Platform platform = new Platform(this, pos.x, pos.y);
				platforms[currRow][col] = platform;
				entities.add(platform);
				
				currRow++;
			}
		}
	}
	
	public int getWidth()
	{
		return width;
	}

	public int getHeight()
	{
		return height;
	}

	public int getNumCols()
	{
		return numCols;
	}

	public int getNumRows()
	{
		return numRows;
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
	
	public Array<Entity> getEntities()
	{
		return entities;
	}
}
