package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import box2dLight.Light;
import box2dLight.RayHandler;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;

public class LightSystem extends EntitySystem {

	public static RayHandler rayHandler;

	public LightSystem(int priority) {
		super(priority);
		Light.setContactFilter(EntityCreator.LIGHT, (short) 0, EntityCreator.WORLDOBJECT);
		LightSystem.rayHandler = new RayHandler(EntityCreator.physicsSystem.getWorld());
		LightSystem.rayHandler.setCombinedMatrix(EntityCreator.camSystem
				.getCamera());
		LightSystem.rayHandler.setShadows(true);
		LightSystem.rayHandler.setAmbientLight(0.0f);
	}

	@Override
	public void update(float deltaTime) {
		//ProfilerGlobal.startTime();
		rayHandler.setCombinedMatrix(EntityCreator.camSystem
				.getCamera().combined);
		rayHandler.updateAndRender();
		super.update(deltaTime);
		//ProfilerGlobal.endTime();
		//ProfilerGlobal.outMax("light-");
	}

	public static void setRayHandler(World world) {
		rayHandler = new RayHandler(world);
	}

}
