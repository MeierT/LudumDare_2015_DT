package de.ludumDare_DT.ludumDare_DT_2015.input;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector2;

public class KeyboardMouseProcessor implements InputProcessor {

	@Override
	public boolean keyDown(int keycode) {
		System.out.println(keycode);
		switch(keycode) {
			case 29: InputPuffer.puffer.put(InputKey.MOVE_LEFT, 1.0f);
			case 32: InputPuffer.puffer.put(InputKey.MOVE_RIGHT, 1.0f);
			case 47: InputPuffer.puffer.put(InputKey.MOVE_DOWN, 1.0f);
			case 51: InputPuffer.puffer.put(InputKey.MOVE_UP, 1.0f);
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {			
		switch(button) {
			case 0: InputPuffer.puffer2.put(InputKey.MOUSE_LEFT, new Vector2(screenX, screenY));
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
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
