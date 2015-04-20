package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool.Poolable;

public class DeathComponent extends Component implements Poolable{

	public float timer = 1.0f;;
	
	@Override
	public void reset() {
		timer = 1.0f;
	}

}
