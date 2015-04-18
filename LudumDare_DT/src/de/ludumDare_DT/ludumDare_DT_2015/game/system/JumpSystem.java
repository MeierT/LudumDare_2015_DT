package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.JumpComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;

public class JumpSystem extends IteratingSystem {
	
	public JumpSystem(int priority) {
		super(Family.all(InputComponent.class, PhysicsBodyComponent.class, JumpComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		// When jump is false
		if(!entity.getComponent(InputComponent.class).jump) {
			return;
		}
		else {
			float force = 1000.0f;
			entity.getComponent(JumpComponent.class).force = force;
			entity.getComponent(JumpComponent.class).forceDown = force / 60;
		}
		
		if(entity.getComponent(JumpComponent.class).force <= 0.0f) {
			return;
		}
		
		entity.getComponent(PhysicsBodyComponent.class).getBody().applyForceToCenter(0.0f, 
			entity.getComponent(JumpComponent.class).force, true);
		entity.getComponent(JumpComponent.class).force = (entity.getComponent(JumpComponent.class).force - entity.getComponent(JumpComponent.class).forceDown > 0) ? entity.getComponent(JumpComponent.class).force - entity.getComponent(JumpComponent.class).forceDown : 0.0f;
		
	}
}
