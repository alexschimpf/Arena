package com.tender.saucer.arena.miscellaneous;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.tender.saucer.arena.level.Level;

/**
 * This is a static class that contains global game objects and data.
 */
public final class Backbone
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 500;
	public static final OrthographicCamera CAMERA = new OrthographicCamera();		
	
	public static Level level;
	
	public static void init()
	{
		CAMERA.setToOrtho(false, WIDTH, HEIGHT);
		
		// We are temporarily assuming the level is instantly created.
		level = new Level();
	}
	
	public static void dispose()
	{
		level.dispose();
	}

	private Backbone()
	{
	}
}
