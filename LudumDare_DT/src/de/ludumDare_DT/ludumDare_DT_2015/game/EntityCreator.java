package de.ludumDare_DT.ludumDare_DT_2015.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.MovementComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.PhysicsBodyDef;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.PhysicsFixtureDef;

public class EntityCreator {
	public static PooledEngine engine;
	public static PhysicsSystem physicsSystem;
	public static CameraSystem camSystem;
	
	public static Entity createFloorTile(float x, float y){
		Entity entity = engine.createEntity();
		
		
		
		int width = GameConstants.getTileSizeX();
		int height = GameConstants.getTileSizeY();
		
		/*
		 * PhysicsBody
		 */
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.StaticBody,
				physicsSystem).fixedRotation(true).position(x, y);
		physicsBody.init(bodyDef, physicsSystem, entity);
		
		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeBox(width, height);
		
		physicsBody.createFixture(fixtureDef);
		
		entity.add(physicsBody);
		
		engine.addEntity(entity);
		return entity;	
	}
	
	public static Entity createPlayer(TextureRegion texutureRegion, float x, float y){
		Entity entity = engine.createEntity();
		
		/*
		 * PhysicsBody
		 */
		float width = texutureRegion.getRegionWidth();
		float height = texutureRegion.getRegionHeight();
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.StaticBody,
				physicsSystem).fixedRotation(true).position(x, y);

		physicsBody.init(bodyDef, physicsSystem, entity);
		
		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeBox(width, height);
		
		physicsBody.createFixture(fixtureDef);
		
		entity.add(physicsBody);
		
		// InputComponent
		entity.add(engine.createComponent(InputComponent.class));
		
		// PositionComponent
		entity.add(engine.createComponent(PositionComponent.class));
		
		// TextureComponent
		// entity.add(engine.createComponent(TextureComponent.class));
		
		//MovementComponent
		MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
		movementComponent.speed = 10.0f;
		entity.add(movementComponent);
		
		//PlayerComponent 
		entity.add(engine.createComponent(PlayerComponent.class));
		
		engine.addEntity(entity);
		return entity;
	}
}
