package de.ludumDare_DT.ludumDare_DT_2015.game.util;

import com.badlogic.ashley.core.ComponentMapper;

import de.ludumDare_DT.ludumDare_DT_2015.game.components.BulletLightComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.DeathComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.EnemyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.InputComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.JumpComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.LightComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.MovementComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PositionComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.ShootingComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.TextureComponent;
import de.ludumDare_DT.ludumDare_DT_2015.physics.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.physics.PhysicsModifierComponent;

/**
 * Used to map components to their entities. Always use this to retrieve a
 * Component, using the entity.getComponent(Component.class) runs in O(log n),
 * the ComponentMapper in O(1)
 * 
 * @author David
 *
 */
public class CompMappers {
	public static final ComponentMapper<PhysicsModifierComponent> physicsModifier = ComponentMapper
			.getFor(PhysicsModifierComponent.class);
	public static final ComponentMapper<PhysicsBodyComponent> physicsBody = ComponentMapper
			.getFor(PhysicsBodyComponent.class);
	public static final ComponentMapper<PositionComponent> position = ComponentMapper
			.getFor(PositionComponent.class);
	public static final ComponentMapper<InputComponent> input = ComponentMapper
			.getFor(InputComponent.class);
	public static final ComponentMapper<PlayerComponent> player = ComponentMapper
			.getFor(PlayerComponent.class);
	public static final ComponentMapper<MovementComponent> movement = ComponentMapper
			.getFor(MovementComponent.class);
	public static final ComponentMapper<JumpComponent> jump = ComponentMapper
			.getFor(JumpComponent.class);
	public static final ComponentMapper<ShootingComponent> shooting = ComponentMapper
			.getFor(ShootingComponent.class);
	public static final ComponentMapper<LightComponent> light = ComponentMapper
			.getFor(LightComponent.class);
	public static final ComponentMapper<TextureComponent> texture = ComponentMapper
			.getFor(TextureComponent.class);
	public static final ComponentMapper<DeathComponent> death = ComponentMapper
			.getFor(DeathComponent.class);
	public static final ComponentMapper<EnemyComponent> enemy = ComponentMapper
			.getFor(EnemyComponent.class);
	public static final ComponentMapper<BulletLightComponent> bulletLight = ComponentMapper
			.getFor(BulletLightComponent.class);

}
