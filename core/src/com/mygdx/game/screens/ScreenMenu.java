package com.mygdx.game.screens;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;
import com.mygdx.game.components.TextButton;

import java.io.BufferedReader;
import java.io.IOException;

public class ScreenMenu implements Screen {
    MyGdxGame myGdxGame;
    MovingBackground background;
    FileHandle file = Gdx.files.local("assets/record.txt");
    BufferedReader reader = new BufferedReader(file.reader());
    PointCounter recordPoint = new PointCounter(800, 350);
    String record  = null;




    TextButton buttonStart;
    TextButton buttonExit;
    public ScreenMenu(MyGdxGame myGdxGame){
        this.myGdxGame = myGdxGame;
        buttonStart= new TextButton(100, 400, "Play");
        background = new MovingBackground("backgrounds/restart_bg.png");
        buttonExit= new TextButton(100, 150, "Exit");
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
    public void show() {;
        try {
            record = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(record);
    }

    @Override
    public void render(float delta) {
        if (isClickedPlay()){
            myGdxGame.setScreen(new ScreenGame(myGdxGame));
        }

        if (isClickedExit()){
            Gdx.app.exit();

        }

        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        buttonStart.draw(myGdxGame.batch);
        buttonExit.draw(myGdxGame.batch);
        recordPoint.draw(myGdxGame.batch, Integer.parseInt(record), "Record");
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
