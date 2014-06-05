package com.tender.saucer.arena.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public class Environment
{
	private static final int NUM_ENVIRONMENTS = 1;
	private static final int NORMAL = 0;
	private static final ObjectMap<Integer, Array<Texture>> MAP = new ObjectMap<Integer, Array<Texture>>();
	
	private Environment()
	{
	}
	
	public static void init()
	{
		Array<Texture> normals = new Array<Texture>()
		{
			
		};
		MAP.put(NORMAL, normals);
	}
	
	public static Array<Texture> getRandom()
	{
		int choice = MathUtils.random(NUM_ENVIRONMENTS - 1);
		return MAP.get(choice);
	}
}
