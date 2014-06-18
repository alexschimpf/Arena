package com.tender.saucer.arena.entity.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.tender.saucer.arena.animation.IAnimate;
import com.tender.saucer.arena.entity.Entity;
import com.tender.saucer.arena.entity.platform.Platform;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.miscellaneous.Backbone;

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
	public static final int BASE_NUM_JUMPS = 100;
	public static final float BASE_SPEED = 10;
	public static final float BASE_ATTACK_COOLDOWN = 300;

	protected int health = BASE_HEALTH;
	protected int damage = BASE_DAMAGE;
	protected int numJumpsLeft = BASE_NUM_JUMPS;
	protected int frameIndex = 0;
	protected float speed = BASE_SPEED;
	protected float attackCooldown = BASE_ATTACK_COOLDOWN;
	protected float animationStateTime = 0;
	protected long lastAttackTime = 0;
	protected Direction direction;
	
	protected float dy = 0;
	
	private AnimationState animationState = AnimationState.STOPPED;
	private Animation animationLeft;
	private Animation animationRight;
	
	protected Player(Level level, float x, float y, String sheetFilename, int numRows, int numCols, float frameDuration)
	{
		super(level);
		
		direction = MathUtils.random() < 0.5 ? Direction.LEFT : Direction.RIGHT;
		
		sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(Level.CELL_SIZE * 0.95f, Level.CELL_SIZE * 0.95f);
		
		Texture sheet = new Texture(Gdx.files.internal(sheetFilename));
        TextureRegion[][] regions = TextureRegion.split(sheet, sheet.getWidth() / numCols, sheet.getHeight() / numRows);
        
        // We split the frames into left and right movement arrays.
        // We assume the following:
        //     - The # of frames is even.
        //     - The left movement frames all come before the right movement frames.
        int numFrames = numRows * numCols;
        TextureRegion[] framesLeft = new TextureRegion[numFrames / 2];
        TextureRegion[] framesRight = new TextureRegion[numFrames / 2];
        
    	for(int col = 0; col < numCols; col++)
    	{
    		if(col < numFrames / 2)
    		{
    			framesLeft[col] = regions[0][col];
    		}
    		else
    		{
    			framesRight[col % (numCols / 2)] = regions[0][col]; 
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
		
		sprite.setY(sprite.getY() + Gdx.graphics.getDeltaTime() * dy);
		
		int cellSize = Level.CELL_SIZE;
		float correction = 5;
		boolean landed = false;
		
		if(dy <= 0)
		{			
			float left = sprite.getX();
			float right = sprite.getX() + sprite.getWidth();
			float bottom = sprite.getY() - sprite.getHeight();
			
			Platform lPlatform = Backbone.level.getPlatformAt(left, bottom);
			if(lPlatform != null)
			{
				float pTop = lPlatform.getSprite().getY();
				if(Math.abs(pTop - bottom) <= correction)
				{
					dy = 0;
					sprite.setY(pTop + sprite.getHeight());
					landed = true;
				}
			}
			
			Platform rPlatform = Backbone.level.getPlatformAt(right, bottom);
			if(rPlatform != null)
			{
				float pTop = rPlatform.getSprite().getY();
				if(Math.abs(pTop - bottom) <= correction)
				{
					dy = 0;
					sprite.setY(pTop + sprite.getHeight());
					landed = true;
				}
			}
		}
		
		if(sprite.getY() <= cellSize)
		{
			dy = 0;
			sprite.setY(cellSize);
			landed = true;
		}
		
		if(!landed)
		{
			dy -= Gdx.graphics.getDeltaTime() * 600;
		}	
		
//		float lbx = sprite.getX();
//		float lby = sprite.getY() - sprite.getHeight();
//		float rbx = sprite.getX() + sprite.getWidth();
//		float rby = sprite.getY() - sprite.getHeight();
//		int cs = Level.CELL_SIZE;
//		lbx = (int)(lbx / cs) * cs;
//		lby = ((int)(lby / cs) + 1) * cs;
//		rbx = (int)(rbx / cs) * cs;
//		rby = ((int)(rby / cs) + 1) * cs;
//		boolean l = Backbone.level.platformExistsAt(lbx, lby);
//		boolean r = Backbone.level.platformExistsAt(rbx, rby);
//		boolean e = l || r;
//		if(dy <= 0 && e)
//		{
//			dy = 0;
//			sprite.setY(lby + sprite.getHeight());
//		}
//		
//		sprite.setY(sprite.getY() + Gdx.graphics.getDeltaTime() * dy);
//		dy -= Gdx.graphics.getDeltaTime() * 600;
//		
//		if(sprite.getY() <= cs)
//		{
//			dy = 0;
//			sprite.setY(cs);
//		}
		
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
		
		sprite.setX(sprite.getX() - Gdx.graphics.getDeltaTime() * 500);
	}
	
	public void moveRight()
	{
		direction = Direction.RIGHT;
		resumeAnimation();
		
		sprite.setX(sprite.getX() + Gdx.graphics.getDeltaTime() * 500);
	}
	
	public void stopMove()
	{
		stopAnimation();	
	}
	
	public void jump()
	{
		if(numJumpsLeft > 0)
		{
			stopAnimation();
			
			dy = 300;
			
			numJumpsLeft--;
		}
	}
	
	public void stopJump()
	{
		if(numJumpsLeft > 0)
		{
			if(dy > 0)
			{
				dy /= 5;
			}
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
