package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import box2dLight.RayHandler;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.World;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.LightComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;

public class LightSystem extends EntitySystem {

	public static RayHandler rayHandler;

	public LightSystem(int priority) {
		super(priority);
	}

	@Override
	public void update(float deltaTime) {
		rayHandler.setCombinedMatrix(EntityCreator.camSystem
				.getCombinedMatrix());
		rayHandler.updateAndRender();
		super.update(deltaTime);
	}

	public static void setRayHandler(World world) {
		rayHandler = new RayHandler(world);
	}

}
