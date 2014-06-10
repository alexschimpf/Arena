package com.tender.saucer.arena.miscellaneous;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * This is static class that contains references to all game environmentTextures.
 */
public final class Textures
{	
	public static Texture NORMAL_1;
	public static Texture NORMAL_2;
	public static Texture NORMAL_3;
	public static Texture NORMAL_4;
	
	public static void init()
	{
		NORMAL_1 = new Texture(Gdx.files.internal("normal_1.png"));
		NORMAL_2 = new Texture(Gdx.files.internal("normal_2.png"));
		NORMAL_3 = new Texture(Gdx.files.internal("normal_3.png"));
		NORMAL_4 = new Texture(Gdx.files.internal("normal_4.png"));
	}
	
	public static void dispose()
	{
		
	}
	
	private Textures()
	{		
	}
}
