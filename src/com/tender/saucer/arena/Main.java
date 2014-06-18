package com.tender.saucer.arena;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tender.saucer.arena.input.InputListener;
import com.tender.saucer.arena.level.Environment;
import com.tender.saucer.arena.miscellaneous.Backbone;
import com.tender.saucer.arena.miscellaneous.Textures;

public final class Main extends ApplicationAdapter
{	
	private SpriteBatch batch;

	@Override
	public void create()
	{
		batch = new SpriteBatch();
		
		Textures.init();
		Environment.init();
		Backbone.init();
	
		Gdx.input.setInputProcessor(new InputListener());
	}

	@Override
	public void render()
	{
//		try
//		{
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			Backbone.CAMERA.position.x = Backbone.level.getPlayer().getSprite().getX();
			Backbone.CAMERA.position.y = Backbone.level.getPlayer().getSprite().getY();
			Backbone.CAMERA.update();
			batch.setProjectionMatrix(Backbone.CAMERA.combined);
			
			Backbone.level.update();
			
			batch.begin();
			{
				Backbone.level.draw(batch);
				
			}
			batch.end();
			
			if(Gdx.input.isTouched())
			{
				if(Gdx.input.getY() < Gdx.graphics.getHeight() / 2)
				{
					
				}			
				else if(Gdx.input.getX() < Gdx.graphics.getWidth() / 2)
				{
					Backbone.level.getPlayer().moveLeft();
				}
				else
				{
					Backbone.level.getPlayer().moveRight();
				}
			}
			else
			{
				Backbone.level.getPlayer().stopMove();
			}
//		}
//		catch(Exception e)
//		{
//			Gdx.app.log("schimpf", e.toString());
//		}
	}
	
	@Override
	public void resize(int width, int height) 
	{

	}

	@Override
	public void pause() 
	{
		
	}

	@Override
	public void resume() 
	{
		
	}
	
	@Override
	public void dispose()
	{
		batch.dispose();
		Textures.dispose();
		Backbone.dispose();
	}
}
