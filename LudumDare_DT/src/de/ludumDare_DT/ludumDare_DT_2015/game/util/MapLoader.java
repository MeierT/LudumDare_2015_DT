package de.ludumDare_DT.ludumDare_DT_2015.game.util;

import java.nio.file.AccessDeniedException;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;

public class MapLoader {
	private static boolean loaded = false;
	
	public static int mapHeight = 0;
	public static int mapWidth = 0;

	public static void generateWorldFromTiledMap(PooledEngine engine,
			TiledMap map, PhysicsSystem physicsSystem, CameraSystem cameraSystem) {

		MapLayers mapLayers = map.getLayers();

		for (int x = 0; x < mapLayers.getCount(); x++) {
			
			TiledMapTileLayer mapLayer;
			
			try {
				// is it a TiledMapTileLayer?
				mapLayer = (TiledMapTileLayer) mapLayers.get(x);
				mapHeight = mapLayer.getHeight();
				mapWidth = mapLayer.getWidth();
				if(!loaded){
					try {
						GameConstants.setTileSizeX((int) mapLayer.getTileWidth());
						GameConstants.setTileSizeY((int) mapLayer.getTileHeight());
						loaded = true;
					} catch (AccessDeniedException e) {
						e.printStackTrace();
					}
				}
			} catch (Exception e) {
				// nope, it is an ObjectLayer. Dont do this at home kids...
				 MapLayer objectLayer = mapLayers.get(x);
				 
				 for(MapObject mapObject: objectLayer.getObjects()){
					String name = mapObject.getName();
					float positionX, positionY;
					
					// this is just plain stupid!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
					try {
						RectangleMapObject rectMapObject = (RectangleMapObject) mapObject;
						positionX = rectMapObject.getRectangle().getX();
						positionY = rectMapObject.getRectangle().getY();
					} catch (Exception e2) {
						positionX = positionY = 0;
						e2.printStackTrace();
					}
					switch (name) {
					case "Start": 
						EntityCreator.createStartPoint(positionX, positionY);
						break;
					default:
						break;
					}
				 }
				
				//e.printStackTrace();
				continue;
			}
			

			// Go through the tiles horizontally
			for (int i = 0; i < mapHeight; i++) {
				// Go through tiles vertically
				for (int j = 0; j < mapWidth; j++) {

					int positionX = (int) (j * GameConstants.getTileSizeX() + 0.5f * GameConstants
							.getTileSizeX());
					int positionY = (int) (i * GameConstants.getTileSizeY() + 0.5f * GameConstants
							.getTileSizeY());

					Cell cell = mapLayer.getCell(j, i);
					if (cell != null) {
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
}
