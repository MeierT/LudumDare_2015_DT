package de.ludumDare_DT.ludumDare_DT_2015.game.contactlistener;

import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Family;
import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;

import de.ludumDare_DT.ludumDare_DT_2015.game.EntityCreator;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.JumpComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PhysicsBodyComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.components.ShootingComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;

public class MyContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.getUserData().equals("Jump")
				|| fixtureB.getUserData().equals("Jump")) {		
//		if((fixtureA.getUserData().equals("enemy") && fixtureB.getUserData().equals("player")) || (fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("enemy"))) {
//			System.out.println("TOD");
//		}
		/* ignore player body */
//		if((fixtureA.getUserData().equals("jump") && !fixtureB.getUserData().equals("player")) || (!fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("jump"))) {
			Family family = Family.all(PlayerComponent.class).get();
			ImmutableArray<Entity> players = EntityCreator.engine
					.getEntitiesFor(family);

			for (Entity player : players) {
				JumpComponent jumpC = CompMappers.jump.get(player);
				if (jumpC != null) {
					jumpC.groundContacts++;
				}
			}
		} else {
			PhysicsBodyComponent physicsBodyComponentA = null;
			PhysicsBodyComponent physicsBodyComponentB = null;
			if (fixtureA.getUserData().getClass()
					.equals(PhysicsBodyComponent.class)) {
				physicsBodyComponentA = (PhysicsBodyComponent) fixtureA
						.getUserData();
			}
			if (fixtureB.getUserData().getClass()
					.equals(PhysicsBodyComponent.class)) {
				physicsBodyComponentB = (PhysicsBodyComponent) fixtureB
						.getUserData();
			}

			if (physicsBodyComponentA != null && physicsBodyComponentB != null) {
				Entity entityA = physicsBodyComponentA.getEntity();
				Entity entityB = physicsBodyComponentB.getEntity();

				ShootingComponent shootingA = CompMappers.shooting.get(entityA);
				ShootingComponent shootingB = CompMappers.shooting.get(entityB);
				if (shootingA != null ^ shootingB != null) {
					if (shootingA != null) {
						if (shootingA.bouncesLeft > 0) {
							shootingA.bouncesLeft--;
						} else {
							EntityCreator.engine.removeEntity(entityA);
						}

					} else {
						if (shootingB.bouncesLeft > 0) {
							shootingB.bouncesLeft--;
						} else {
							EntityCreator.engine.removeEntity(entityB);
						}

					}
				}
			}
		}

	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();

		if (fixtureA.getUserData().equals("Jump")
				|| fixtureB.getUserData().equals("Jump")) {
		/* ignore player body */
		//if((fixtureA.getUserData().equals("jump") && !fixtureB.getUserData().equals("player")) || (!fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("jump"))){
			Family family = Family.all(PlayerComponent.class).get();
			ImmutableArray<Entity> players = EntityCreator.engine
					.getEntitiesFor(family);

			for (Entity player : players) {
				JumpComponent jumpC = CompMappers.jump.get(player);
				if (jumpC != null) {
					jumpC.groundContacts--;
				}
			}
		}
	}

	@Override
	public void preSolve(Contact contact, Manifold oldManifold) {
		// TODO Auto-generated method stub

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse impulse) {
		// TODO Auto-generated method stub

	}
}
