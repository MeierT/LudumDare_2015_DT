package de.ludumDare_DT.ludumDare_DT_2015.game.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.utils.Pool;

/**
 * Add this to entities that should be able to jump
 * @author David
 *
 */
public class JumpComponent extends Component implements Pool.Poolable {
	
	public boolean jump = false;
	public int groundContacts = 0;

	@Override
	public void reset() {
		jump = false;
		groundContacts = 0;
	}

}
