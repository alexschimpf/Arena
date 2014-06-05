package com.tender.saucer.arena.actor.body.player;

import com.tender.saucer.arena.actor.body.AnimatedActorBody;
import com.tender.saucer.arena.level.Level;
import com.tender.saucer.arena.update.ITransientUpdate;

public abstract class Player extends AnimatedActorBody
{	
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
	
	public Player(Level level)
	{
		super(level);
	}
	
	@Override
	public boolean update()
	{
		super.update();
		
		return health <= 0;
	}
	
	@Override
	public void onDone()
	{
		super.update();
	}
	
	public void moveLeft()
	{
		
	}
	
	public void moveRight()
	{
		
	}
	
	public void stopMove()
	{
		
	}
	
	public void jump()
	{
		
	}
	
	public void stopJump()
	{
		
	}
	
	public abstract void attack();
}
