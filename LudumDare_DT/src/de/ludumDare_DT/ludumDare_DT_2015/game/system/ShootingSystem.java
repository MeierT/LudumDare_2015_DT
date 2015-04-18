package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;

public class ShootingSystem extends IteratingSystem{

	public ShootingSystem(int priority) {
		super(Family.all(InputComponent.class, PositionComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		InputComponent inputComp = CompMappers.input.get(entity);
		PositionComponent position = CompMappers.position.get(entity);
		if(inputComp.shoot && inputComp.shootTimer <= 0){
			inputComp.shootTimer = 1.0f;
			EntityCreator.createHeart(position.x, position.y);
		}else if(inputComp.shoot){
			inputComp.shootTimer -= deltaTime;
		}else if(inputComp.shootTimer>=0){
			inputComp.shootTimer -= deltaTime;
		}
		
	}

	

}
