package com.tender.saucer.arena.stuff;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.tender.saucer.arena.entity.Entity;

public final class Backbone
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final OrthographicCamera CAMERA = new OrthographicCamera();	
	public static final Array<Entity> entities = new Array<Entity>();

	private Backbone()
	{
	}

	public static void init()
	{
		CAMERA.setToOrtho(false, WIDTH, HEIGHT);
	}
}
