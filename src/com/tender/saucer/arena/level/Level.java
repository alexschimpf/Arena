package com.tender.saucer.arena.level;

import java.util.Iterator;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.entity.platform.Platform;
import com.tender.saucer.arena.entity.player.MeleePlayer;
import com.tender.saucer.arena.entity.player.Player;
import com.tender.saucer.arena.miscellaneous.Backbone;
import com.tender.saucer.arena.update.IUpdate;

/**
 * This represents the game level and physics world that players exist in.
 */
public class Level implements Disposable, IUpdate
{
	public static final int GRAVITY = -10;
	public static final int CELL_SIZE = 50;
	
	protected int width;
	protected int height;
	protected int numCols;
	protected int numRows;
	protected Array<Texture> environmentTextures;
	protected Array<Entity> entities = new Array<Entity>();
	protected Player player;
	
	public Level()
	{
		environmentTextures = Environment.getRandTextures();
		
		build();
	}
	
	@Override
	public void dispose()
	{
		
	}
	
	@Override
	public boolean update()
	{
		updateEntities();
		
		player.update();
		
		return false;
	}

	@Override
	public void onDone()
	{
		dispose();
	}
	
	public void draw(SpriteBatch batch)
	{
		for(Entity entity : entities)
		{
			entity.draw(batch);
		}
		
		player.draw(batch);
	}
	
	protected void build()
	{
		numRows = numCols = (Backbone.WIDTH * 4) / CELL_SIZE; 
		width = numCols * CELL_SIZE;
		height = numRows * CELL_SIZE;
		
		createFloor();		
		createRandTerrain();
		
		player = new MeleePlayer(this, 0, CELL_SIZE);
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

	protected void createFloor()
	{
		// Create a line of tiles at y = 0.
		for(int i = 0; i < numCols; i++)
		{
			Platform platform = new Platform(this, i * CELL_SIZE, 0);
			entities.add(platform);
		}
	}
	
	protected void createRandTerrain()
	{		
		int startRow = numRows - MathUtils.random(1, 3);
		int row = startRow;
		int col = MathUtils.random(0, numCols / 10);
		while(true)
		{
			int numLevels = MathUtils.random(1, 4);
			int unitWidth = MathUtils.random(1, 8);
			if(col + unitWidth >= numCols)
			{
				break;
			}							
			createRandPlatformUnits(row, col, unitWidth, numLevels);
			
			col += unitWidth + MathUtils.random(3, numCols / 10);
			row = startRow - MathUtils.random(0, 2);
		}
}
	
	protected void createRandPlatformUnits(int startRow, int startCol, int unitWidth, int numLevels)
	{	
		int row = startRow;
		int col = startCol;
		int unitHeight = 0;
		for(int level = 0; level < numLevels; level++)
		{
			for(int i = 0; i < unitWidth; i++)
			{
				int dRow = MathUtils.random() < 0.5f ? 0 : 1;
				dRow += MathUtils.random() < 0.5f ? 0 : -1;
				row = Math.min(row - dRow, startRow);
				col++; 
				
				if(col >= numCols)
				{
					break;
				}
				
				if(startRow - row > unitHeight)
				{
					unitHeight = startRow - row;
				}
				
				int numCellsDown = 1 + MathUtils.random(0, startRow - row);
				int currRow = row;
				for(int j = 0; j < numCellsDown; j++)
				{
					float x = (int)(col * CELL_SIZE);
					float y = height - (int)(currRow * CELL_SIZE);
					
					if(!platformExistsAt(x, y))
					{
						Platform platform = new Platform(this, x, y);
						entities.add(platform);
					}
					
					currRow++;
				}
			}
			
			col = Math.max(0, startCol + MathUtils.random(-unitWidth, unitWidth));
			row = startRow - MathUtils.random(unitHeight, unitHeight + 2);
			startRow = row;
		}
	}
	
	protected boolean platformExistsAt(float x, float y)
	{
		for(Entity entity : entities)
		{
			if(entity instanceof Platform)
			{
				if(entity.getSprite().getX() == x && entity.getSprite().getY() == y)
				{
					return true;
				}
			}
		}
		
		return false;
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

	public Array<Texture> getEnvironmentTextures()
	{
		return environmentTextures;
	}

	public Array<Entity> getEntities()
	{
		return entities;
	}
	
	public Player getPlayer()
	{
		return player;
	}
}
