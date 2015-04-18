package de.ludumDare_DT.ludumDare_DT_2015.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.MovementComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.ShootingComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.StartPointComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.TextureComponent;
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
	
	public static Entity createPlayer(float x, float y) {
		Entity entity = engine.createEntity();
		
		/* TextureComponent */
		TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
		
		textureComponent.texture = new TextureRegion(new Texture("resources/images/Amor2.png"));
		
		entity.add(textureComponent);
				
		
		/*
		 * PhysicsBody
		 */
		float width = textureComponent.texture.getRegionWidth();
		float height = textureComponent.texture.getRegionHeight();
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody,
				physicsSystem).fixedRotation(true).position(x, y);

		physicsBody.init(bodyDef, physicsSystem, entity);
		
		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height / 2.0f);
		
		physicsBody.createFixture(fixtureDef);
		
		entity.add(physicsBody);
		
		// InputComponent
		entity.add(engine.createComponent(InputComponent.class));
		
		// PositionComponent
		PositionComponent positionComponet = engine.createComponent(PositionComponent.class);
		positionComponet.x = x;
		positionComponet.y = y;
		entity.add(positionComponet);
		
		//MovementComponent
		MovementComponent movementComponent = engine.createComponent(MovementComponent.class);
		movementComponent.speed = 5.0f;
		entity.add(movementComponent);
		
		//PlayerComponent 
		entity.add(engine.createComponent(PlayerComponent.class));
		
		engine.addEntity(entity);
		return entity;
	}
	
	public static Entity createStartPoint(float x, float y) {
		EntityCreator.createPlayer(x, y);
		
		Entity entity = engine.createEntity();
		
		/* Position */
		PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
		
		positionComponent.x = x;
		positionComponent.y = y;
		
		entity.add(positionComponent);
		
		/* Unique start point component */
		entity.add(engine.createComponent(StartPointComponent.class));
		
		engine.addEntity(entity);
		return entity;
	}
	
	public static Entity createHeart(float x, float y){
		Entity entity = engine.createEntity();
		
		/* TextureComponent 
		TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
		
		textureComponent.texture = new TextureRegion(new Texture("resources/images/Amor2.png"));
		
		entity.add(textureComponent);
				*/
		
		/*
		 * PhysicsBody
		 */
		/*
		float width = textureComponent.texture.getRegionWidth();
		float height = textureComponent.texture.getRegionHeight();
		*/
		
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody,
				physicsSystem).fixedRotation(true).position(x, y);

		physicsBody.init(bodyDef, physicsSystem, entity);
		
		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(32);
		
		Fixture fixture = physicsBody.createFixture(fixtureDef);
		fixture.setUserData(physicsBody);
		
		entity.add(physicsBody);
		
		// position Component
		PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
		entity.add(positionComponent);
		
		
		// ShootingComponent
		entity.add(new ShootingComponent());
		
		
		engine.addEntity(entity);
		return entity;
	}
}
