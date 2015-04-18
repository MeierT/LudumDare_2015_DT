package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class InputComponent extends Component implements Pool.Poolable {
	
	public float x = 0.0f;
	public float y = 0.0f;
	
	public boolean jump = false;
	
	@Override
	public void reset() {
		this.x = 0.0f;
		this.y = 0.0f;
		
		this.jump = false;
	}

}
