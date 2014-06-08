package com.tender.saucer.arena.entity.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.tender.saucer.arena.entity.AnimatedEntity;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.update.IUpdate;

/**
 * This represents a playable entity that the user (or AI) controls.
 */
public abstract class Player extends AnimatedEntity
{	
	public enum Direction
	{
		LEFT,
		RIGHT
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
	protected float speed = BASE_SPEED;
	protected float jumpImpulse = BASE_JUMP_IMPULSE;
	protected float attackCooldown = BASE_ATTACK_COOLDOWN;
	protected long lastAttackTime = 0;
	protected Direction direction;
	
	public Player(Level level)
	{
		super(level);
		
		direction = MathUtils.random() < 0.5 ? Direction.LEFT : Direction.RIGHT;
	}
	
	public abstract void attack();
	
	@Override
	public boolean update()
	{
		super.update();
		
		return health <= 0;
	}
	
	@Override
	public void onDone()
	{
		super.onDone();
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
	
	protected void tryFlipSprite(Direction direction)
	{
		if(direction == Direction.RIGHT && sprite.isFlipX())
		{
			sprite.flip(false, false);
		}
		
		if(direction == Direction.LEFT && !sprite.isFlipX())
		{
			sprite.flip(true, false);
		}
	}
	
	@Override
	public void draw(SpriteBatch batch)
	{
		tryFlipSprite(direction);	
		super.draw(batch);
	}
	
	@Override
	public Sprite getSprite()
	{
		tryFlipSprite(direction);	
		return super.getSprite();
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
