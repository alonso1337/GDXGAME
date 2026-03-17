package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
public class MyGdxGame extends Game {
	ScreenGame screenGame;
	public OrthographicCamera camera;
	public static final int SCR_WIDTH = 1280;
	public static final int SCR_HEIGHT = 720;
	SpriteBatch batch;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);


		screenGame = new ScreenGame(this);
		setScreen(screenGame);
	}
	@Override
	public void dispose () {
		batch.dispose();
	}
}