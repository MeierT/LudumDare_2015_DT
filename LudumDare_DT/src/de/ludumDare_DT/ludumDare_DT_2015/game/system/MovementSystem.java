package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.MovementComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;

public class MovementSystem extends IteratingSystem {


	public MovementSystem(int priority) {
		super(Family.all(MovementComponent.class, PhysicsBodyComponent.class)
				.get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		MovementComponent movement = CompMappers.movement.get(entity);
		PhysicsBodyComponent physicsBody = CompMappers.physicsBody.get(entity);
		InputComponent input = CompMappers.input.get(entity);

		if (input != null) {
			// set the velocity to the direction vector given by input
			// multiplicated by the scalar of the movement speed
			//movement.velocity.set(input.direction.x * movement.speed,
			//		input.direction.y * movement.speed);
		}
		physicsBody.getBody().setLinearVelocity(movement.velocity);
		
	}

}
