package de.ludumDare_DT.ludumDare_DT_2015.game.util;

import java.nio.file.AccessDeniedException;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;

public class MapLoader {

	public static void generateWorldFromTiledMap(PooledEngine engine,
			TiledMap map, PhysicsSystem physicsSystem, CameraSystem cameraSystem) {

		// TODO dynamisch berechnen
		try {
			GameConstants.setTileSizeX(32);
			GameConstants.setTileSizeY(32);
		} catch (AccessDeniedException e) {
			e.printStackTrace();
		}

		MapLayers mapLayers = map.getLayers();
		TiledMapTileLayer mapLayer = (TiledMapTileLayer) mapLayers.get(0);

		int mapHeight = mapLayer.getHeight();
		int mapWidth = mapLayer.getWidth();

		// Go through the tiles horizontally
		for (int i = 0; i < mapHeight; i++) {
			// Go through tiles vertically
			for (int j = 0; j < mapWidth; j++) {

				int positionX = (int) (j * GameConstants.getTileSizeX() + 0.5f * GameConstants.getTileSizeX());
				int positionY = (int) (i * GameConstants.getTileSizeY()+ 0.5f * GameConstants.getTileSizeY());

				Cell cell = mapLayer.getCell(j, i);
				if(cell != null){
					String type = cell.getTile().getProperties()
							.get("Type", "", String.class);
					switch (type) {
					case "Floor":
						EntityCreator.createFloorTile(positionX, positionY);
						break;

					default:
						break;
					}
				}
				

			}

		}
	}
}
