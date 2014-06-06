package com.tender.saucer.arena.stuff;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.level.Level;

/**
 * This is a static class that contains global game objects and data.
 */
public final class Backbone
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final OrthographicCamera CAMERA = new OrthographicCamera();	
	public static final Array<Entity> ENTITIES = new Array<Entity>();	
	
	public static Level level;
	
	public static void init()
	{
		CAMERA.setToOrtho(false, WIDTH, HEIGHT);
	}

	private Backbone()
	{
	}
}
