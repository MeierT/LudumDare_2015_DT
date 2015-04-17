package de.ludumDare_DT.ludumDare_DT_2015.game;

import org.lwjgl.opengl.GL11;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2D;

import de.ludumDare_DT.ludumDare_DT_2015.game.system.InputSystem;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;

public class Game implements ApplicationListener{
	
	
	private PooledEngine engine;
	
	/**
	 * Testing!
	 */
	private SpriteBatch testBatch;
	private Texture testTex;
	private OrthographicCamera ortho;

	private final InputSystem inputSystem = new InputSystem(10);

	@Override
	public void create() {
		// creating the Ashley engine
		engine = new PooledEngine();
		
		// initialise Box2D
		Box2D.init();
		
		this.addSystems();
		
		/**
		 * Test n stuff
		 */
		testBatch = new SpriteBatch();
		testTex = new Texture("resources/images/test.jpg");
		
		ortho = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		
		testBatch.setProjectionMatrix(ortho.combined);
		
		ortho.setToOrtho(false, Gdx.graphics.getWidth()/2 + 300, Gdx.graphics.getHeight()/2);
		
	}
	
	private void addSystems() {
		engine.addSystem(inputSystem);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		engine.update(Gdx.graphics.getDeltaTime());
		ortho.update();
		testBatch.setProjectionMatrix(ortho.combined);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
		testBatch.begin();
		testBatch.draw(testTex, 0, 0);
		testBatch.end();
		
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
