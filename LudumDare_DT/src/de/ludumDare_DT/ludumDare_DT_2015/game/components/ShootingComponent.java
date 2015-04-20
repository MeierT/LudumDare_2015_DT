package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool.Poolable;

public class ShootingComponent extends Component implements Poolable{
	
	public Vector2 shotDirection = new Vector2();
	public Vector2 origin = new Vector2();
	
	public float shotSpeed = 0f;
	public float bouncesLeft = 0f;
	
	public boolean receivedImpulse = false;

	@Override
	public void reset() {		
		shotDirection.setZero();
		origin.setZero();
		shotSpeed = bouncesLeft = 0f;
		receivedImpulse = false;
	}

}
