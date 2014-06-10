package com.tender.saucer.arena.level;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ObjectMap;
import com.tender.saucer.arena.miscellaneous.Textures;;

/**
 * This is a static class used for generating random texture sets to be used by {@link Level}.
 */
public final class Environment
{
	public enum EnvironmentType
	{
		NORMAL
	}
	
	private static final ObjectMap<EnvironmentType, Array<Texture>> MAP = new ObjectMap<EnvironmentType, Array<Texture>>();
	
	public static void init()
	{
		Array<Texture> normals = new Array<Texture>();
		normals.add(Textures.NORMAL_1);
		normals.add(Textures.NORMAL_2);
		normals.add(Textures.NORMAL_3);
		normals.add(Textures.NORMAL_4);
		MAP.put(EnvironmentType.NORMAL, normals);
	}
	
	public static Array<Texture> getRandTextures()
	{
		int choice = MathUtils.random(EnvironmentType.values().length - 1);
		return MAP.get(EnvironmentType.values()[choice]);
	}
	
	private Environment()
	{
	}
}
