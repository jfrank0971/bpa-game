package com.relativity.game.handlers;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.relativity.game.handlers.entities.types.Player;

public class CollisionHandler implements ContactListener {

	// collision bit filters
	public static final short ORB = 8;
	public static final short PLAYER = 4;
	public static final short GROUND = 2;

	private Fixture fixA, fixB;
	
	public void beginContact(Contact c) {
		
		try {
			
			if (c.getFixtureA().getUserData() == "player" || c.getFixtureA().getUserData() == "foot") {
				fixA = c.getFixtureA();				
			} else if (c.getFixtureB().getUserData() == "player" || c.getFixtureB().getUserData() == "foot") {
				fixA = c.getFixtureB();
			}
			
			fixB = c.getFixtureB();

			switch (fixA.getUserData().toString()) {
			
			case "foot":
				Player.incrementFootContact(1);
				break;
			case "orb":
				break;
			}
			
			
			
		} catch (Exception e) {
			//do the error stuff
			
			
		}

	}

	public void endContact(Contact c) {

		fixA = c.getFixtureA();
		fixB = c.getFixtureB();

		if (fixA == null || fixB == null) {
			return;
		}

		if (fixA.getUserData() != null && fixA.getUserData().equals("foot")) {
			Player.incrementFootContact(-1);
		}
		if (fixB.getUserData() != null && fixB.getUserData().equals("foot")) {
			Player.incrementFootContact(-1);
		}

	}

	public void preSolve(Contact c, Manifold manifold) {
	}

	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
