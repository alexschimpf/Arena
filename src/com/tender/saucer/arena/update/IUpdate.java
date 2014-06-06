package com.tender.saucer.arena.update;

/**
 * This should be implemented by all objects that need to be updated in the render loop.
 */
public interface IUpdate
{
	public boolean update();
	
	public void onDone();
}
