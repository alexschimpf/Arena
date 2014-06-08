package com.tender.saucer.arena.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.tender.saucer.arena.level.Level;

/**
 * This is a type of entity that is animated.
 */
public abstract class AnimatedEntity extends Entity
{
	public enum AnimationState
	{
		PLAYING,
		PAUSED,
		STOPPED
	}
	
	protected Animation animation;
	protected float animationStateTime = 0;
	protected AnimationState animationState = AnimationState.STOPPED;
	
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
		
		if(animationState == AnimationState.PLAYING)
		{
			animationStateTime += Gdx.graphics.getDeltaTime();
		}
		
		return false;
	}
	
	public void pauseAnimation()
	{
		animationState = AnimationState.PAUSED;
	}
	
	public void resumeAnimation()
	{
		animationState = AnimationState.PLAYING;
	}
	
	public void stopAnimation()
	{
		animationState = AnimationState.STOPPED;
		animationStateTime = 0;
	}
	
	public void startAnimation()
	{
		animationState = AnimationState.PLAYING;
		animationStateTime = 0;
	}
	
	public boolean isAnimationPlaying()
	{
		return animationState == AnimationState.PLAYING;
	}
	
	@Override 
	public Sprite getSprite()
	{
		sprite.setRegion(animation.getKeyFrame(animationStateTime, true));
		return sprite;
	}
	
	public AnimationState getAnimationState()
	{
		return animationState;
	}
}
