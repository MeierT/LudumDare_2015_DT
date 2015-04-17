package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;

public class InputSystem extends IteratingSystem {

	public InputSystem(int priority) {
		super(Family.all(InputComponent.class).get(), priority);
	}
		
	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// TODO Auto-generated method stub
		
	}

}
