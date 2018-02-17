package com.relativity.game.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.utils.Array;
import com.relativity.game.handlers.entities.types.Player;

/**
 * 
 * Listens to the World object in GameMapLoader
 * 
 * @version BPA SET 2018
 * @author Jacob Frank, Jerry Zeng, and Eddie Tang
 */

public class CollisionHandler implements ContactListener {

	// these shorts define the type of object
	public static final short ORB = 8;
	public static final short PLAYER = 4;
	public static final short GROUND = 2;

	private Fixture fixA, fixB;
	private Array<Body> bodyResetList;

	public CollisionHandler() {
		super();
		bodyResetList = new Array<>();
	}

	public void beginContact(Contact c) {

		fixA = c.getFixtureA();
		fixB = c.getFixtureB();

		try {

			if (fixA.getUserData().equals("Foot") || fixB.getUserData().equals("Foot")) {
				Player.incrementFootContact(1);
			}
			if (fixA.getUserData().equals("Orb") || fixB.getUserData().equals("Orb")) {
				if (fixA.getUserData().equals("Orb")) {
					bodyResetList.add(fixA.getBody());
				} else {
					bodyResetList.add(fixB.getBody());
				}
			}
			if (fixA.getUserData().equals("Missile") || fixB.getUserData().equals("Missile")) {
				if (fixA.getUserData().equals("Missile")) {
					bodyResetList.add(fixA.getBody());
				} else {
					bodyResetList.add(fixB.getBody());
				}
			}

		} catch (Exception e) {
			Gdx.app.error("CollissionHandler.class (beginContact)", e.getMessage(), e);
		}

	}

	public void endContact(Contact c) {

		fixA = c.getFixtureA();
		fixB = c.getFixtureB();

		try {

			if (fixA.getUserData().equals("Foot") || fixB.getUserData().equals("Foot")) {
				Player.incrementFootContact(-1);
			}

		} catch (Exception e) {
			Gdx.app.error("CollissionHandler.class (endContact)", e.getMessage(), e);
		}

	}

	public Array<Body> getBodies() {
		return bodyResetList;
	}

	public void preSolve(Contact c, Manifold manifold) {
	}

	public void postSolve(Contact contact, ContactImpulse impulse) {

	}

}
