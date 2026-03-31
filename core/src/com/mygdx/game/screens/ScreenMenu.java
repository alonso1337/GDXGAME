package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;
import com.mygdx.game.components.TextButton;

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;
    MovingBackground background;
    PointCounter recordPoint = new PointCounter(800, 350);
    String record = "0";

    TextButton buttonStart;
    TextButton buttonExit;

    public ScreenMenu(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonStart = new TextButton(100, 400, "Play");
        background = new MovingBackground("backgrounds/restart_bg.png");
        buttonExit = new TextButton(100, 150, "Exit");
    }

    public boolean isClickedPlay() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonStart.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }

    public boolean isClickedExit() {
        if (Gdx.input.justTouched()) {
            Vector3 touch = new Vector3().set(Gdx.input.getX(), Gdx.input.getY(), 0);
            myGdxGame.camera.unproject(touch);
            return buttonExit.isHit((int) touch.x, (int) touch.y);
        }
        return false;
    }

    @Override
    public void show() {
        FileHandle file = Gdx.files.local("assets/record.txt");
        if (file.exists()) {
            record = file.readString().trim();
            if (record.isEmpty()) {
                record = "0";
            }
        } else {
            record = "0";
        }
        System.out.println("Current Record: " + record);
    }

    @Override
    public void render(float delta) {
        if (isClickedPlay()) {
            myGdxGame.setScreen(new ScreenGame(myGdxGame));
        }

        if (isClickedExit()) {
            Gdx.app.exit();
        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);

        int recordValue = 0;
        try {
            recordValue = Integer.parseInt(record);
        } catch (NumberFormatException e) {
            recordValue = 0;
        }
        recordPoint.draw(myGdxGame.batch, recordValue, "Record");
        myGdxGame.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        background.dispose();
    }
}