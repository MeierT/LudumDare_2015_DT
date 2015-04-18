package de.ludumDare_DT.ludumDare_DT_2015.game;

import org.lwjgl.opengl.GL11;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;

import de.ludumDare_DT.ludumDare_DT_2015.audio.MusicManager;
import de.ludumDare_DT.ludumDare_DT_2015.audio.SoundManager;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.InputSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.UpdatePositionSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.MapLoader;
import de.ludumDare_DT.ludumDare_DT_2015.input.InputManager;

public class Game implements ApplicationListener{
	
	
	private PooledEngine engine;
	
	/**
	 * Testing!
	 */
	private SpriteBatch testBatch;
	private Texture testTex;
	
	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private final InputSystem inputSystem = new InputSystem(10);
	// add PhysicSystem
	private final PhysicsSystem physicsSystem = new PhysicsSystem(
			GameConstants.BOX2D_VELOCITY_ITERATIONS,
			GameConstants.BOX2D_POSITIONS_ITERATIONS,
			GameConstants.BOX2D_SCALE, GameConstants.PHYSICS_PRIORITY);

	/** Manager */
	public InputManager inputManager;
	public static SoundManager soundManager;
	public static MusicManager musicManager;
	public static AssetManager assetManager;
	
	@Override
	public void create() {
		// creating the Ashley engine
		engine = new PooledEngine();
		EntityCreator.engine = engine;
		
		// initialise Box2D
		Box2D.init();
		
		/* Manager */
		inputManager = new InputManager();
		soundManager = new SoundManager();
		musicManager = new MusicManager();
		assetManager = new AssetManager();
		
		/* Systems */
		this.addSystems();
		
		/* Load TiledMap */
		TiledMap map = new TmxMapLoader().load("resources/tilesets/example.tmx");
		
		float unitScale = 1.0f;
		tiledMapRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
		
		/* MapLoader */ 
		MapLoader.generateWorldFromTiledMap(engine, map, physicsSystem, EntityCreator.camSystem);
		
		/*
		 * Test n stuff
		 */
		testBatch = new SpriteBatch();
		testTex = new Texture("resources/images/test.jpg");
				
		testBatch.setProjectionMatrix(engine.getSystem(CameraSystem.class).getCombinedMatrix());		
	}
	
	private void addSystems() {
		engine.addSystem(inputSystem);		
		engine.addSystem(physicsSystem);
		

		EntityCreator.physicsSystem = physicsSystem;
		engine.addSystem(physicsSystem);
		physicsSystem.setGravity(new Vector2(0, 0)); // erstmal keine gravity,
													
		// add UpdatePositionSystem
		engine.addSystem(new UpdatePositionSystem(
				GameConstants.PHYSICS_PRIORITY + 2));
		
		// CameraSystem
		CameraSystem camSystem = new CameraSystem(GameConstants.CAMERA_PRIORITY);
		EntityCreator.camSystem = camSystem;
		engine.addSystem(camSystem);
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		EntityCreator.camSystem.getCamera().update();
		tiledMapRenderer.setView(EntityCreator.camSystem.getCamera());
		tiledMapRenderer.render();
		
		
		engine.update(Gdx.graphics.getDeltaTime());
		testBatch.setProjectionMatrix(engine.getSystem(CameraSystem.class).getCombinedMatrix());

		
	
//				
//		testBatch.begin();
//		testBatch.draw(testTex, 0, 0);
//		testBatch.end();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
