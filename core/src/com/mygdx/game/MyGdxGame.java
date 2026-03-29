package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import screens.ScreenGame;
import screens.ScreenRestart;

public class MyGdxGame extends Game {
	ScreenGame screenGame;
	public OrthographicCamera camera;
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public SpriteBatch batch;
	public ScreenRestart screenRestart;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		screenRestart = new ScreenRestart(this);
		screenGame = new ScreenGame(this);
		setScreen(screenGame);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}