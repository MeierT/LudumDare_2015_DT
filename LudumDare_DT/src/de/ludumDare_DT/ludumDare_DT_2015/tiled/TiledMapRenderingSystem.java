package de.ludumDare_DT.ludumDare_DT_2015.tiled;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.MapLoader;

public class TiledMapRenderingSystem extends EntitySystem{

	private static OrthogonalTiledMapRenderer tiledMapRenderer;

	public TiledMapRenderingSystem() {
		tiledMapRenderer = new OrthogonalTiledMapRenderer(MapLoader.currentMap,
				1.0f / GameConstants.BOX2D_SCALE);
	}
	
	public static void setMap(TiledMap newMap){
		tiledMapRenderer.setMap(newMap);
	}
	
	@Override
	public void update(float deltaTime) {
//		ProfilerGlobal.startTime();
		tiledMapRenderer.setView(EntityCreator.camSystem.getCamera());
		tiledMapRenderer.render();
//		ProfilerGlobal.endTime();
//		ProfilerGlobal.outMax("tiled-");		
		super.update(deltaTime);
	}
}
