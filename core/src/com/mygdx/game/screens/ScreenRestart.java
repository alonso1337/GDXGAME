package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

import com.mygdx.game.characters.Tube;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;
import com.mygdx.game.components.TextButton;

public class ScreenRestart implements Screen {
    int gamePoints;
    MyGdxGame myGdxGame;
    MovingBackground background;

    TextButton buttonHome;
    PointCounter pointCounter;
    TextButton buttonRestart;
    TextButton buttonMenu;

    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new TextButton(100, 400, "Restart");
        buttonMenu = new TextButton(100, 150, "Menu");

        pointCounter = new PointCounter(750, 530);
        background = new MovingBackground("backgrounds/restart_bg.png");
    }

    @Override
    public void show() {

    }
    public boolean isClickedRestart() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonRestart.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }
    public boolean isClickedMenu() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonMenu.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }


    public void render(float delta) {
        if (isClickedRestart()){
            myGdxGame.setScreen(new ScreenGame(myGdxGame));

        }
        if (isClickedMenu()){
            myGdxGame.setScreen(new ScreenMenu(myGdxGame));

        }






        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonRestart.draw(myGdxGame.batch);
        buttonMenu.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch,ScreenGame.gamePoints, "Count");
        myGdxGame.batch.end();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

}
