package de.ludumDare_DT.ludumDare_DT_2015.game;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.tiled.TiledMap;

import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;

public class EntityCreator {
	public static PooledEngine engine;
	public static PhysicsSystem physicsSystem;
	public static CameraSystem camSystem;
	
	public static Entity createFloorTile(TiledMap map){
		Entity entity = engine.createEntity();
		
		
		
		engine.addEntity(entity);
		return entity;	
	}
}
