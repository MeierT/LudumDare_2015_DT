package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Add this to entities that should be able to jump
 * @author David
 *
 */
public class JumpComponent extends Component implements Pool.Poolable {
	
	boolean jump = false;

	@Override
	public void reset() {
		jump = false;
	}

}
