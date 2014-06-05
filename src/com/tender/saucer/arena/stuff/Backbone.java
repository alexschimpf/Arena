package com.tender.saucer.arena.stuff;

import com.badlogic.gdx.graphics.OrthographicCamera;

public final class Backbone
{
	public static final int WIDTH = 800;
	public static final int HEIGHT = 480;
	public static final OrthographicCamera CAMERA = new OrthographicCamera();	

	private Backbone()
	{
	}

	public static void init()
	{
		CAMERA.setToOrtho(false, WIDTH, HEIGHT);
	}
}
