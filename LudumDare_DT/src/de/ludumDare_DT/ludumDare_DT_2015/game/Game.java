package de.ludumDare_DT.ludumDare_DT_2015.game;

import org.lwjgl.opengl.GL11;

import com.badlogic.ashley.core.PooledEngine;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Box2D;

public class Game implements ApplicationListener{
	private PooledEngine engine;


	@Override
	public void create() {
		// creating the Ashley engine
		engine = new PooledEngine();
		
		// initialise Box2D
		Box2D.init();
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		engine.update(Gdx.graphics.getDeltaTime());
		
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		
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
