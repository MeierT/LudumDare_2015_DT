package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class JumpComponent extends Component implements Pool.Poolable{

	public float force = 0.0f;
	public float forceDown = 0.0f;
	
	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}

}
