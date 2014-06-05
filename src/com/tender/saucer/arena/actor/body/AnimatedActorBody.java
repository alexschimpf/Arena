package com.tender.saucer.arena.actor.body;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.update.ITransientUpdate;

public abstract class AnimatedActorBody extends ActorBody implements ITransientUpdate
{
	public static final int PLAYING = 0;
	public static final int PAUSED = 1;
	public static final int STOPPED = 2;
	
	protected Animation animation;
	protected float stateTime = 0;
	protected int state = STOPPED;
	
	public AnimatedActorBody(Level level)
	{
		super(level);
	}

	public AnimatedActorBody(Level level, String sheetFilename, int numRows, int numCols, float frameDuration)
	{
		super(level);
		
		Texture sheet = new Texture(Gdx.files.internal(sheetFilename));
        TextureRegion[][] regions = TextureRegion.split(sheet, sheet.getWidth() / numCols, sheet.getHeight() / numRows);
        TextureRegion[] frames = new TextureRegion[numRows * numCols];
        
        int idx = 0;
        for(int row = 0; row < numRows; row++)
        {
        	for(int col = 0; col < numCols; col++)
        	{
        		frames[idx++] = regions[row][col];
        	}
        }
        
        animation = new Animation(frameDuration, frames);
	}
	
	@Override
	public boolean update()
	{
		if(state == PLAYING)
		{
			stateTime += Gdx.graphics.getDeltaTime();
		}
		
		return false;
	}
	
	public void pauseAnimation()
	{
		state = PAUSED;
	}
	
	public void startAnimation()
	{
		state = PLAYING;
	}
	
	public void stopAnimation()
	{
		state = STOPPED;
		stateTime = 0;
	}
	
	@Override 
	public TextureRegion getTextureRegion()
	{
		return animation.getKeyFrame(stateTime, true);
	}
	
	public int getState()
	{
		return state;
	}
}
