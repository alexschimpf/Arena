package com.tender.saucer.arena.stuff;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class Backbone
{
	public static int WIDTH = 800;
	public static int HEIGHT = 480;
	public static OrthographicCamera camera = new OrthographicCamera();	

	private Backbone()
	{
	}

	public static void init()
	{
		camera.setToOrtho(false, WIDTH, HEIGHT);
	}
}
