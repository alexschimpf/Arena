package com.tender.saucer.arena;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.tender.saucer.arena.input.InputListener;
import com.tender.saucer.arena.level.Environment;
import com.tender.saucer.arena.stuff.Backbone;
import com.tender.saucer.arena.stuff.Textures;

public class Main extends Game
{	
	private SpriteBatch batch = new SpriteBatch();

	@Override
	public void create()
	{
		Backbone.init();
		Textures.init();
		Environment.init();
	
		Gdx.input.setInputProcessor(new InputListener());
	}

	@Override
	public void render()
	{
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		Backbone.camera.update();
		batch.setProjectionMatrix(Backbone.camera.combined);
		
		batch.begin();
			// batch.draw(...)
		batch.end();
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
	}
}
