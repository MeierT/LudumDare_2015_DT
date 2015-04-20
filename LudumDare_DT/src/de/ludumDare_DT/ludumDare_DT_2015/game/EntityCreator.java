package de.ludumDare_DT.ludumDare_DT_2015.game;

import box2dLight.ConeLight;
import box2dLight.PointLight;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.JumpComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.LightComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.MovementComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.ShootingComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.StartPointComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.TextureComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.LightSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.PhysicsBodyDef;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.PhysicsFixtureDef;

public class EntityCreator {
	public static PooledEngine engine;
	public static PhysicsSystem physicsSystem;
	public static CameraSystem camSystem;
	public static LightSystem lightSystem;

	public static short EVERYTHING = 0xFFF;
	public static short WORLDOBJECT = 0x002;
	public static short HEARTH = 0x004;
	public static short PLAYER = 0x006;

	public static Entity createFloorTile(float x, float y) {
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

		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem)
				.shapeBox(width, height).category(WORLDOBJECT);

		Fixture fixture = physicsBody.createFixture(fixtureDef);
		fixture.setUserData(physicsBody);

		entity.add(physicsBody);

		engine.addEntity(entity);
		return entity;
	}

	public static Entity createPlayer(float x, float y) {
		Entity entity = engine.createEntity();

		/* TextureComponent */
		TextureComponent textureComponent = engine
				.createComponent(TextureComponent.class);

		textureComponent.texture = new TextureRegion(new Texture(
				"resources/images/Amor2.png"));

		entity.add(textureComponent);

		/*
		 * PhysicsBody
		 */
		float width = textureComponent.texture.getRegionWidth();
		float height = textureComponent.texture.getRegionHeight();
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody,
				physicsSystem).fixedRotation(true).position(x, y)
				.gravityScale(10.0f);

		physicsBody.init(bodyDef, physicsSystem, entity);

		// body
		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem)
				.shapeCircle(height / 2.0f).category(PLAYER).mask(WORLDOBJECT);

		Fixture fixture = physicsBody.createFixture(fixtureDef);
		fixture.setUserData(physicsBody);

		// jumpsensor
		fixtureDef = new PhysicsFixtureDef(physicsSystem)
				.shapeCircle(height / 10.0f, new Vector2(0, -height * 0.5f))
				.sensor(true).category(PLAYER).mask(WORLDOBJECT);

		fixture = physicsBody.createFixture(fixtureDef);
		fixture.setUserData("Jump");

		entity.add(physicsBody);

		// InputComponent
		entity.add(engine.createComponent(InputComponent.class));

		// PositionComponent
		PositionComponent positionComponet = engine
				.createComponent(PositionComponent.class);
		positionComponet.x = x;
		positionComponet.y = y;
		entity.add(positionComponet);

		// MovementComponent
		MovementComponent movementComponent = engine
				.createComponent(MovementComponent.class);
		movementComponent.speed = 4.0f;
		entity.add(movementComponent);

		// PlayerComponent
		entity.add(engine.createComponent(PlayerComponent.class));

		entity.add(engine.createComponent(JumpComponent.class));

		engine.addEntity(entity);
		return entity;
	}

	public static Entity createStartPoint(float x, float y) {
		EntityCreator.createPlayer(x, y);

		Entity entity = engine.createEntity();

		/* Position */
		PositionComponent positionComponent = engine
				.createComponent(PositionComponent.class);

		positionComponent.x = x;
		positionComponent.y = y;

		
		
		entity.add(positionComponent);

		/* Unique start point component */
		entity.add(engine.createComponent(StartPointComponent.class));

		engine.addEntity(entity);
		return entity;
	}

	public static Entity createHeart(float x, float y, float shotDirectionX,
			float shotDirectionY) {
		Entity entity = engine.createEntity();

		// textureComponent
		TextureComponent textureComponent = engine
				.createComponent(TextureComponent.class);

		textureComponent.texture = new TextureRegion(new Texture(
				"resources/images/herz.png"));

		entity.add(textureComponent);

		float width = textureComponent.texture.getRegionWidth();
		float height = textureComponent.texture.getRegionHeight();

		// physicsBody
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody,
				physicsSystem).fixedRotation(true).position(x, y)
				.gravityScale(0);

		physicsBody.init(bodyDef, physicsSystem, entity);

		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem)
				.shapeCircle(height / 3).restitution(0.8f).category(HEARTH)
				.mask(WORLDOBJECT);

		Fixture fixture = physicsBody.createFixture(fixtureDef);
		fixture.setUserData(physicsBody);

		entity.add(physicsBody);

		// position Component
		PositionComponent positionComponent = engine
				.createComponent(PositionComponent.class);
		entity.add(positionComponent);

		// ShootingComponent
		ShootingComponent shootingComponent = engine
				.createComponent(ShootingComponent.class);
		shootingComponent.shotDirection.set(shotDirectionX, shotDirectionY);
		shootingComponent.origin.set(x, y);
		shootingComponent.shotSpeed = 10.0f;
		shootingComponent.bouncesLeft = 2.0f;
		entity.add(shootingComponent);

		engine.addEntity(entity);
		return entity;
	}
	
	public static Entity createEnemy(float x, float y) {
		Entity entity = engine.createEntity();
		
		/* Texture */
		TextureComponent textureComponent = engine.createComponent(TextureComponent.class);
		
		textureComponent.texture = new TextureRegion(new Texture("resources/images/Enemy1_64pix.png"));
		
		entity.add(textureComponent);
		
		/*
		 * PhysicsBody
		 */
		float width = textureComponent.texture.getRegionWidth();
		float height = textureComponent.texture.getRegionHeight();
		PhysicsBodyComponent physicsBody = engine
				.createComponent(PhysicsBodyComponent.class);
		PhysicsBodyDef bodyDef = new PhysicsBodyDef(BodyType.DynamicBody,
				physicsSystem).fixedRotation(true).position(x, y).gravityScale(10.0f);

		physicsBody.init(bodyDef, physicsSystem, entity);
		
		PhysicsFixtureDef fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height / 2.0f);
		
		Fixture fixture = physicsBody.createFixture(fixtureDef);
		
		fixtureDef = new PhysicsFixtureDef(physicsSystem).shapeCircle(height / 2.0f).sensor(true);
		
		fixture = physicsBody.createFixture(fixtureDef);
		fixture.setUserData("enemy");
		
		entity.add(physicsBody);
		
		/* Position */
		PositionComponent positionComponent = engine.createComponent(PositionComponent.class);
		
		positionComponent.x = x;
		positionComponent.y = y;
		
		entity.add(positionComponent);
		
		engine.addEntity(entity);
		return entity;
	}
	
	public static Entity createConeLight(float x, float y) {
        Entity entity = engine.createEntity();
        
        LightComponent lightCompo = engine.createComponent(LightComponent.class);
        entity.add(lightCompo);
        
        ConeLight coneLight = new ConeLight(LightSystem.rayHandler, 1000, Color.WHITE, 10, x, y, 270, 45);
        
        return entity;
    }
	
	public static Entity createPointLight(float x, float y) {
        Entity entity = engine.createEntity();
        
        LightComponent lightCompo = engine.createComponent(LightComponent.class);
        entity.add(lightCompo);
        
        PointLight pointLight = new PointLight(LightSystem.rayHandler, 1000, Color.WHITE, 10, x, y);
        
        return entity;
    }
}
