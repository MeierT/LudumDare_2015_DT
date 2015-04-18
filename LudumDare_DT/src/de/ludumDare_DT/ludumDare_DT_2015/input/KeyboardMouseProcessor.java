package de.ludumDare_DT.ludumDare_DT_2015.input;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;

public class KeyboardMouseProcessor implements InputProcessor {
	
	private void move(float plane, float up, float down) {
		for(Entity entity : EntityCreator.engine.getEntitiesFor(Family.all(InputComponent.class, PlayerComponent.class).get())) {
			entity.getComponent(InputComponent.class).x += plane;
		}
	}
	
	private void jump() {
		for(Entity entity : EntityCreator.engine.getEntitiesFor(Family.all(InputComponent.class, PlayerComponent.class).get())) {
			entity.getComponent(InputComponent.class).jump = true;
		}
	}
	
	private void shoot(boolean toShoot){
		for(Entity entity : EntityCreator.engine.getEntitiesFor(Family.all(InputComponent.class, PlayerComponent.class).get())) {
			entity.getComponent(InputComponent.class).shoot = toShoot;
		}
	}

	@Override
	public boolean keyDown(int keycode) {
		float plane = 0.0f;
		switch(keycode) {
			case Input.Keys.A: plane -= 1.0f; break;
			case Input.Keys.D: plane += 1.0f; break;
			case Input.Keys.W: this.jump(); break;
		}
		this.move(plane, 0.0f, 0.0f);
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		float plane = 0.0f;
		switch(keycode) {
			case Input.Keys.A: plane += 1.0f; break;
			case Input.Keys.D: plane -= 1.0f; break;
		}
		this.move(plane, 0.0f, 0.0f);
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {			
		this.shoot(true);
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		this.shoot(false);
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
