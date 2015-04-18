package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

public class InputComponent extends Component implements Pool.Poolable {
	
	public Float plane = 0.0f;
	
	@Override
	public void reset() {
		this.plane = null;
		
	}

}
