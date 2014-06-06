package com.tender.saucer.arena.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tender.saucer.arena.level.Level;

public abstract class AnimatedEntity extends Entity
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
	
	public AnimatedEntity(Level level)
	{
		super(level);
	}

	public AnimatedEntity(Level level, String sheetFilename, int numRows, int numCols, float frameDuration)
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
        animation.setPlayMode(PlayMode.LOOP);
	}
	
	@Override
	public boolean update()
	{
		super.update();
		
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
	public Sprite getSprite()
	{
		sprite.setRegion(animation.getKeyFrame(stateTime, true));
		return sprite;
	}
	
	public AnimationState getState()
	{
		return state;
	}
}
