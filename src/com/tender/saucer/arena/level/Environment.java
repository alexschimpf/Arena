package com.tender.saucer.arena.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;

public final class Environment
{
	public enum EnvironmentType
	{
		NORMAL
	}
	
	private static final ObjectMap<EnvironmentType, Array<Texture>> MAP = new ObjectMap<EnvironmentType, Array<Texture>>();
	
	private Environment()
	{
	}
	
	public static void init()
	{
		Array<Texture> normals = new Array<Texture>()
		{
			
		};
		MAP.put(EnvironmentType.NORMAL, normals);
	}
	
	public static Array<Texture> getRandom()
	{
		int choice = MathUtils.random(EnvironmentType.values().length - 1);
		return MAP.get(EnvironmentType.values()[choice]);
	}
}
