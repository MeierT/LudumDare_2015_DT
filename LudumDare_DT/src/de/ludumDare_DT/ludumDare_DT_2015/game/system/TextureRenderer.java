package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.TextureComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.DrawUtil;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;

public class TextureRenderer extends IteratingSystem {

	public TextureRenderer(int priority) {
		super(Family.all(TextureComponent.class, PositionComponent.class)
				.get(), priority);
	}
	
	@Override
	public void update(float deltaTime) {
		DrawUtil.batch.begin();
		super.update(deltaTime);
		DrawUtil.batch.end();
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		DrawUtil.batch.draw(entity.getComponent(TextureComponent.class).texture,
				(float) (entity.getComponent(PositionComponent.class).x - EntityCreator.camSystem.viewpoint.x + EntityCreator.camSystem.getCamera().viewportWidth * GameConstants.getTileSizeX() - entity.getComponent(TextureComponent.class).texture.getRegionWidth() * 0.5), 
				(float) (entity.getComponent(PositionComponent.class).y - EntityCreator.camSystem.viewpoint.y + EntityCreator.camSystem.getCamera().viewportHeight * GameConstants.getTileSizeY() - entity.getComponent(TextureComponent.class).texture.getRegionHeight() * 0.5)
				);		
	}

}
