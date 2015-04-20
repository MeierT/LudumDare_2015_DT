package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import box2dLight.RayHandler;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.World;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.LightComponent;

public class LightSystem extends IteratingSystem {

    public static RayHandler rayHandler;
    
    public LightSystem() {
        super(Family.all(LightComponent.class).get());
    }

    @Override
    protected void processEntity(Entity entity, float deltaTime) {
        // TODO Auto-generated method stub
        rayHandler.setCombinedMatrix(EntityCreator.camSystem.getCombinedMatrix());
        rayHandler.updateAndRender();
    }
    
    public static void setRayHandler(World world){
        rayHandler = new RayHandler(world);
    }

}
