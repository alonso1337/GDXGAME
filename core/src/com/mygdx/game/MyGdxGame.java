package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.mygdx.game.screens.ScreenGame;
import com.mygdx.game.screens.ScreenMenu;
import com.mygdx.game.screens.ScreenRestart;

public class MyGdxGame extends Game {
	ScreenGame screenGame;
	public OrthographicCamera camera;
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	public SpriteBatch batch;
	public ScreenRestart screenRestart;
	ScreenMenu screenMenu;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		screenRestart = new ScreenRestart(this);
		screenGame = new ScreenGame(this);
		screenMenu = new ScreenMenu(this);
		setScreen(screenMenu);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}