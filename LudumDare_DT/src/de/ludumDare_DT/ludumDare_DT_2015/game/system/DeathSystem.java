package de.ludumDare_DT.ludumDare_DT_2015.game.system;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.systems.IteratingSystem;
import com.badlogic.ashley.utils.ImmutableArray;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.DeathComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.StartPointComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.TextureComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.DrawUtil;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.GameConstants;

public class DeathSystem extends IteratingSystem {

	public DeathSystem(int priority) {
		super(Family.all(DeathComponent.class, PositionComponent.class,
				TextureComponent.class).get(), priority);
	}

	@Override
	protected void processEntity(Entity entity, float deltaTime) {
		DeathComponent deathComp = CompMappers.death.get(entity);
		// PositionComponent positionComp = CompMappers.position.get(entity);
		TextureComponent textureComp = CompMappers.texture.get(entity);

		if (CompMappers.physicsBody.has(entity)) {
			// if (CompMappers.input.has(entity)) {
			// entity.remove(InputComponent.class);
			// }
			// entity.remove(PhysicsBodyComponent.class);
		}

		if (deathComp.timer > 0) {
			deathComp.timer -= deltaTime;
			textureComp.width *= 0.99f;
			textureComp.height *= 0.99f;
			

		} else {
			if (CompMappers.player.has(entity)) {
				entity.remove(DeathComponent.class);

				Family family = Family.all(StartPointComponent.class,
						PositionComponent.class).get();
				ImmutableArray<Entity> startpoints = EntityCreator.engine
						.getEntitiesFor(family);

				PositionComponent startPosition = CompMappers.position
						.get(startpoints.first());

				PhysicsBodyComponent physicsBody = CompMappers.physicsBody
						.get(entity);
				physicsBody.getBody().setTransform(
						startPosition.x / GameConstants.BOX2D_SCALE,
						startPosition.y / GameConstants.BOX2D_SCALE, 0);

				textureComp.width = textureComp.texture.getRegionWidth();
				textureComp.height = textureComp.texture.getRegionHeight();

			} else {
				EntityCreator.engine.removeEntity(entity);
				EntityCreator.enemyCounter--;
			}
		}
	}

}
