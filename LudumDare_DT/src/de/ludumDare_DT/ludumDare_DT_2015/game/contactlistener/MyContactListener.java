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
import de.ludumDare_DT.ludumDare_DT_2015.game.components.PlayerComponent;
import de.ludumDare_DT.ludumDare_DT_2015.game.util.CompMappers;

public class MyContactListener implements ContactListener {

	@Override
	public void beginContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		
		if((fixtureA.getUserData().equals("enemy") && fixtureB.getUserData().equals("player")) || (fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("enemy"))) {
			System.out.println("TOD");
		}
		/* ignore player body */
		if((fixtureA.getUserData().equals("jump") && !fixtureB.getUserData().equals("player")) || (!fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("jump"))) {
			Family family = Family.all(PlayerComponent.class).get();
			ImmutableArray<Entity> players =  EntityCreator.engine.getEntitiesFor(family);
			
			for(Entity player : players){
				JumpComponent jumpC = CompMappers.jump.get(player);
				if(jumpC != null){
					jumpC.groundContacts++;
				}
			}
		}
	}

	@Override
	public void endContact(Contact contact) {
		Fixture fixtureA = contact.getFixtureA();
		Fixture fixtureB = contact.getFixtureB();
		/* ignore player body */
		if((fixtureA.getUserData().equals("jump") && !fixtureB.getUserData().equals("player")) || (!fixtureA.getUserData().equals("player") && fixtureB.getUserData().equals("jump"))){
			Family family = Family.all(PlayerComponent.class).get();
			ImmutableArray<Entity> players =  EntityCreator.engine.getEntitiesFor(family);
			
			for(Entity player : players){
				JumpComponent jumpC = CompMappers.jump.get(player);
				if(jumpC != null){
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
