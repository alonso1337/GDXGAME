package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

import com.mygdx.game.characters.Bird;
import com.mygdx.game.characters.Tube;
import com.mygdx.game.components.MovingBackground;
import com.mygdx.game.components.PointCounter;
import com.mygdx.game.components.TextButton;

import java.io.BufferedReader;
import java.io.IOException;


public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    boolean isGameOver;
    FileHandle file = Gdx.files.local("assets/record.txt");
    Bird bird;
    Texture birdTexture;
    PointCounter pointCounter;
    MovingBackground background;

    final int pointCounterMarginTop = 40;
    final int pointCounterMarginRight = 400;

    BufferedReader reader = new BufferedReader(file.reader());
    String record  = null;


    int tubeCount = 3;
    Tube[] tubes;
    public static int gamePoints;

    public void initTubes() {
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        background = new MovingBackground("backgrounds/game_bg.png");
        birdTexture = new Texture("birdTiles/bird0.png");
        bird = new Bird(200, MyGdxGame.SCR_HEIGHT / 2, birdTexture);
        initTubes();
        pointCounter = new PointCounter(MyGdxGame.SCR_WIDTH - pointCounterMarginRight, MyGdxGame.SCR_HEIGHT - pointCounterMarginTop);
    }

    @Override
    public void show() {
        gamePoints = 0;
        isGameOver = false;
        bird.setY(MyGdxGame.SCR_HEIGHT / 2);
        initTubes();
    }


    @Override
    public void render(float delta) {
        if (isGameOver) {
            myGdxGame.screenRestart.gamePoints = gamePoints;
            myGdxGame.setScreen(new ScreenRestart(myGdxGame));
        }
            for (Tube tube : tubes) {
                tube.move();
               if (tube.isHit(bird)) {
                    System.out.println("дэбилxxx");
                    isGameOver = true;
                } else if (tube.needAddPoint(bird)) {
                    tube.isPointReceived = true;
                    gamePoints += 1;
                    System.out.println(gamePoints);
                   try {
                       record = reader.readLine();
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }
                    if(gamePoints > Integer.parseInt(record) ) {
                        file.writeString(String.valueOf(gamePoints), false);
                    }
                    
                }
            }


        if (Gdx.input.justTouched()) {
            bird.onClick();
        }

        bird.fly();
        background.move();
        if (!bird.isInField()) {
            System.out.println("not in field");
            isGameOver = true;
        }


        ScreenUtils.clear(1, 0, 0, 1);
        myGdxGame.camera.update();
        myGdxGame.batch.setProjectionMatrix(myGdxGame.camera.combined);
        myGdxGame.batch.begin();
        background.draw(myGdxGame.batch);
        for (Tube tube : tubes) tube.move();
        for (Tube tube : tubes) tube.draw(myGdxGame.batch);
        bird.draw(myGdxGame.batch);
        pointCounter.draw(myGdxGame.batch, gamePoints, "Count");
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
        bird.dispose();
        background.dispose();

    }
}
