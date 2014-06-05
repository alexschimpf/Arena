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
	public enum AnimationState
	{
		PLAYING,
		PAUSED,
		STOPPED
	}
	
	protected Animation animation;
	protected float stateTime = 0;
	protected AnimationState state = AnimationState.STOPPED;
	
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
		if(state == AnimationState.PLAYING)
		{
			stateTime += Gdx.graphics.getDeltaTime();
		}
		
		return false;
	}
	
	public void pauseAnimation()
	{
		state = AnimationState.PAUSED;
	}
	
	public void startAnimation()
	{
		state = AnimationState.PLAYING;
	}
	
	public void stopAnimation()
	{
		state = AnimationState.STOPPED;
		stateTime = 0;
	}
	
	@Override 
	public TextureRegion getTextureRegion()
	{
		return animation.getKeyFrame(stateTime, true);
	}
	
	public AnimationState getState()
	{
		return state;
	}
}
