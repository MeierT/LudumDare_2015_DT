package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import box2dLight.RayHandler;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;

public class LightSystem extends EntitySystem {

	public static RayHandler rayHandler;

	public LightSystem(int priority) {
		super(priority);
	}

	@Override
	public void update(float deltaTime) {
		rayHandler.setCombinedMatrix(EntityCreator.camSystem
				.getCamera().combined);
		rayHandler.updateAndRender();
		super.update(deltaTime);
	}

	public static void setRayHandler(World world) {
		rayHandler = new RayHandler(world);
	}

}
