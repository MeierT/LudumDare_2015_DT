package de.ludumDare_DT.ludumDare_DT_2015.game;

import org.lwjgl.opengl.GL11;

import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

import de.ludumDare_DT.ludumDare_DT_2015.audio.MusicManager;
import de.ludumDare_DT.ludumDare_DT_2015.audio.SoundManager;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.CameraSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.InputSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.MovementSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.PhysicsSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.system.UpdatePositionSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.MapLoader;
import de.ludumDare_DT.ludumDare_DT_2015.input.InputManager;

public class Game implements ApplicationListener {

	private PooledEngine engine;

	/**
	 * Testing!
	 */
	private SpriteBatch testBatch;
	private Texture testTex;
	private Box2DDebugRenderer box2DDebugRenderer;

	private OrthogonalTiledMapRenderer tiledMapRenderer;

	private final InputSystem inputSystem = new InputSystem(10);
	// add PhysicSystem
	private final PhysicsSystem physicsSystem = new PhysicsSystem(
			GameConstants.BOX2D_VELOCITY_ITERATIONS,
			GameConstants.BOX2D_POSITIONS_ITERATIONS,
			GameConstants.BOX2D_SCALE, GameConstants.PHYSICS_PRIORITY);
	
	
	private RayHandler rayHandler;
	private PointLight light;

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
		TiledMap map = new TmxMapLoader()
				.load("resources/tilesets/example.tmx");

		tiledMapRenderer = new OrthogonalTiledMapRenderer(map,
				1.0f / GameConstants.BOX2D_SCALE);

		/* MapLoader */
		MapLoader.generateWorldFromTiledMap(engine, map, physicsSystem,
				EntityCreator.camSystem);
        
		/*
		 * Test n stuff
		 */
		testBatch = new SpriteBatch();
		testTex = new Texture("resources/images/test.jpg");

		testBatch.setProjectionMatrix(EntityCreator.camSystem
				.getCombinedMatrix());
		
		rayHandler = new RayHandler(physicsSystem.getWorld());
        rayHandler.setCombinedMatrix(EntityCreator.camSystem.getCombinedMatrix());
        
        light = new PointLight(rayHandler, 50, Color.GREEN, 15, 5,5);

		box2DDebugRenderer = new Box2DDebugRenderer();
	}

	private void addSystems() {
		engine.addSystem(inputSystem);
		engine.addSystem(physicsSystem);

		EntityCreator.physicsSystem = physicsSystem;
		engine.addSystem(physicsSystem);
		physicsSystem.setGravity(new Vector2(0, -10)); // erstmal keine gravity,

		// add MovementSystem
		engine.addSystem(new MovementSystem(GameConstants.PHYSICS_PRIORITY + 1));

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
		engine.update(Gdx.graphics.getDeltaTime());

		EntityCreator.camSystem.getCamera().update();

		tiledMapRenderer.setView(EntityCreator.camSystem.getCamera());
		tiledMapRenderer.render();
		box2DDebugRenderer.render(EntityCreator.physicsSystem.getWorld(),
				EntityCreator.camSystem.getCamera().combined);

		testBatch.setProjectionMatrix(engine.getSystem(CameraSystem.class)
				.getCombinedMatrix());
		rayHandler.updateAndRender();
		//
		// testBatch.begin();
		// testBatch.draw(testTex, 0, 0);
		// testBatch.end();
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
