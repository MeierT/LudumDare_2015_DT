package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import box2dLight.Light;
import box2dLight.RayHandler;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.physics.box2d.World;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.profiling.Profiler;
import de.ludumDare_DT.ludumDare_DT_2015.profiling.ProfilerGlobal;

public class LightSystem extends EntitySystem {

	public static RayHandler rayHandler;

	public LightSystem(int priority) {
		super(priority);
		Light.setContactFilter(EntityCreator.LIGHT, (short) 0, EntityCreator.WORLDOBJECT);
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
