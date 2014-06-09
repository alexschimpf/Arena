package com.tender.saucer.arena.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.tender.saucer.arena.animation.IAnimate;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.level.Level;

/**
 * This represents a playable entity that the user (or AI) controls.
 */
public abstract class Player extends Entity implements IAnimate
{	
	public enum Direction
	{
		LEFT,
		RIGHT
	}
	
	private enum AnimationState
	{
		PLAYING,
		PAUSED,
		STOPPED
	}
	
	public static final int BASE_HEALTH = 10;
	public static final int BASE_DAMAGE = 1;
	public static final int BASE_NUM_JUMPS = 2;
	public static final float BASE_SPEED = 10;
	public static final float BASE_JUMP_IMPULSE = 15;
	public static final float BASE_ATTACK_COOLDOWN = 300;

	protected int health = BASE_HEALTH;
	protected int damage = BASE_DAMAGE;
	protected int numJumpsLeft = BASE_NUM_JUMPS;
	protected int frameIndex = 0;
	protected float speed = BASE_SPEED;
	protected float jumpImpulse = BASE_JUMP_IMPULSE;
	protected float attackCooldown = BASE_ATTACK_COOLDOWN;
	protected float animationStateTime = 0;
	protected long lastAttackTime = 0;
	protected Direction direction;
	
	private AnimationState animationState = AnimationState.STOPPED;
	private Animation animationLeft;
	private Animation animationRight;
	
	protected Player(Level level, float x, float y, String sheetFilename, int numRows, int numCols, float frameDuration)
	{
		super(level);
		
		direction = MathUtils.random() < 0.5 ? Direction.LEFT : Direction.RIGHT;
		
		sprite = new Sprite();
        sprite.setPosition(x, y);
		
		Texture sheet = new Texture(Gdx.files.internal(sheetFilename));
        TextureRegion[][] regions = TextureRegion.split(sheet, sheet.getWidth() / numCols, sheet.getHeight() / numRows);
        
        // We split the frames into left and right movement arrays.
        // We assume the following:
        //     - The # of frames is even.
        //     - The left movement frames all come before the right movement frames.
        int numFrames = numRows * numCols;
        TextureRegion[] framesLeft = new Sprite[numFrames / 2];
        TextureRegion[] framesRight = new Sprite[numFrames / 2];
        
        int idx = 0;
        for(int row = 0; row < numRows; row++)
        {
        	for(int col = 0; col < numCols; col++)
        	{
        		if(idx < numFrames / 2)
        		{
        			framesLeft[idx++] = regions[row][col];
        		}
        		else
        		{
        			framesRight[idx++] = regions[row][col];
        		}    		
        	}
        }
        
        animationLeft = new Animation(frameDuration, framesLeft);
        animationRight = new Animation(frameDuration, framesRight);
	}
	
	public abstract void attack();
	
	@Override
	public boolean update()
	{
		super.update();
		
		updateSprite();
		
		if(animationState == AnimationState.PLAYING)
		{
			animationStateTime += Gdx.graphics.getDeltaTime();
		}
		
		return health <= 0;
	}
	
	@Override
	public void onDone()
	{
		super.onDone();
	}
	
	@Override
	public void pauseAnimation()
	{
		animationState = AnimationState.PAUSED;
	}
	
	@Override
	public void resumeAnimation()
	{
		animationState = AnimationState.PLAYING;
	}
	
	@Override
	public void stopAnimation()
	{
		animationState = AnimationState.STOPPED;
		animationStateTime = 0;
	}
	
	@Override
	public void startAnimation()
	{
		animationState = AnimationState.PLAYING;
		animationStateTime = 0;
	}
	
	public void moveLeft()
	{
		direction = Direction.LEFT;
		resumeAnimation();
		
		body.setLinearVelocity(-speed, 0);
	}
	
	public void moveRight()
	{
		direction = Direction.RIGHT;
		resumeAnimation();
		
		body.setLinearVelocity(speed, 0);
	}
	
	public void stopMove()
	{
		stopAnimation();	
		body.setLinearVelocity(0, body.getLinearVelocity().y);
	}
	
	public void jump()
	{
		if(numJumpsLeft > 0)
		{
			stopAnimation();
			
			numJumpsLeft--;
			
			float x = body.getWorldCenter().x;
			float y = body.getWorldCenter().y;
			body.applyLinearImpulse(0, jumpImpulse, x, y, true);
		}
	}
	
	public void stopJump()
	{
		if(numJumpsLeft > 0)
		{
			float x = body.getLinearVelocity().x;
			float y = body.getLinearVelocity().y / 5;
			body.setLinearVelocity(x, y);
		}
	}

	private void updateSprite()
	{
		// Update the sprite's texture region to the current animation's frame.
		Animation animation = direction == Direction.LEFT ? animationLeft : animationRight;
		TextureRegion region = animation.getKeyFrame(animationStateTime, true);
		sprite.setRegion(region);
	}
	
	public int getHealth()
	{
		return health;
	}

	public void setHealth(int health)
	{
		this.health = health;
	}

	public int getDamage()
	{
		return damage;
	}

	public void setDamage(int damage)
	{
		this.damage = damage;
	}

	public float getSpeed()
	{
		return speed;
	}

	public void setSpeed(float speed)
	{
		this.speed = speed;
	}

	public float getJumpImpulse()
	{
		return jumpImpulse;
	}

	public void setJumpImpulse(float jumpImpulse)
	{
		this.jumpImpulse = jumpImpulse;
	}

	public float getAttackCooldown()
	{
		return attackCooldown;
	}

	public void setAttackCooldown(float attackCooldown)
	{
		this.attackCooldown = attackCooldown;
	}

	public Direction getDirection()
	{
		return direction;
	}

	public void setDirection(Direction direction)
	{
		this.direction = direction;
	}
}
