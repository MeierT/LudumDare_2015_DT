package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntityListener;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;

/**
 * CameraSystem. Follows the entity with the most recently added PlayerComponent
 * 
 * @author David
 *
 */
public class CameraSystem extends EntitySystem implements EntityListener {

	/**
	 * target that should be followed by the CameraSystem
	 */
	private Entity target;

	private OrthographicCamera camera;

	public CameraSystem(int priority) {
		super(priority);
		camera = new OrthographicCamera();
		resizeCameraViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}

	@Override
	public void update(float deltaTime) {
		if (target != null) {
			PositionComponent posComp = CompMappers.position.get(target);
			if (posComp != null) {
				setCameraPosition(posComp.x, posComp.y);
			}
		}
	}

	@Override
	public void addedToEngine(Engine engine) {
		engine.addEntityListener(
				Family.all(PlayerComponent.class, PositionComponent.class)
						.get(), this);
	}

	@Override
	public void entityAdded(Entity entity) {
		if (target == null) {
			target = entity;
		}
	}

	@Override
	public void entityRemoved(Entity entity) {
		target = null;
	}

	/* CameraControls */
	/**
	 * will be automatically downscaled!
	 * 
	 * @param width
	 * @param height
	 */
	public void resizeCameraViewport(int width, int height) {
		camera.setToOrtho(GameConstants.YDOWN, width
				/ GameConstants.BOX2D_SCALE, height / GameConstants.BOX2D_SCALE);
		camera.update();
	}

	public OrthographicCamera getCamera() {
		return camera;
	}

	public void setCameraPosition(float x, float y) {
		camera.position.x = x / GameConstants.BOX2D_SCALE;
		camera.position.y = y / GameConstants.BOX2D_SCALE;
		camera.update();
	}

	public Matrix4 getCombinedMatrix() {
		return camera.combined;
	}

}
