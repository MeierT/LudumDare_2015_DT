package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.MovementComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.TextureComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.DrawUtil;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;

public class TextureRenderer extends IteratingSystem {

	public TextureRenderer(int priority) {
		super(
				Family.all(TextureComponent.class, PositionComponent.class)
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
		TextureComponent texComp = CompMappers.texture.get(entity);

		// if needed, flip the texture
		MovementComponent moveComp = CompMappers.movement.get(entity);
		if (moveComp != null) {
			if (moveComp.velocity.x < 0 && !texComp.texture.isFlipX()) {
				texComp.texture.flip(true, false);
			} else if (moveComp.velocity.x > 0 && texComp.texture.isFlipX()) {
				texComp.texture.flip(true, false);
			}
		}

		// This stuff should definitely be calculated in the CamSystem...
		// DrawUtil.batch
		// .draw(texComp.texture,
		// (float) (entity.getComponent(PositionComponent.class).x
		// - EntityCreator.camSystem.viewpoint.x
		// + EntityCreator.camSystem.getCamera().viewportWidth
		// * GameConstants.getTileSizeX()
		// - texComp.texture.getRegionWidth() * 0.5 + texComp.width * scaleX),
		// (float) (entity.getComponent(PositionComponent.class).y
		// - EntityCreator.camSystem.viewpoint.y
		// + EntityCreator.camSystem.getCamera().viewportHeight
		// * GameConstants.getTileSizeY()
		// - texComp.texture.getRegionHeight() * 0.5 + texComp.height * scaleY),
		// texComp.width, texComp.height);
		PositionComponent position = CompMappers.position.get(entity);
		OrthographicCamera camera = EntityCreator.camSystem.getCamera();

		float transX = camera.position.x - (camera.viewportWidth / 2);
		float transY = camera.position.y - (camera.viewportHeight / 2);
		transX *= GameConstants.BOX2D_SCALE;
		transY *= GameConstants.BOX2D_SCALE;

		transX = position.x - transX - (texComp.width / 2);
		transY = position.y - transY - (texComp.height / 2);

//		 if (CompMappers.player.has(entity)) {
//		  System.out.println(camera.viewportWidth/2);
//		 // System.out.println(scaleX);
//		 // System.out.println("camera: " + camera.position);
//		 }

		DrawUtil.batch.draw(texComp.texture, transX, transY, texComp.width,
				texComp.height);
	}
}
