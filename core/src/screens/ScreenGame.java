package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;

import characters.Bird;
import characters.Tube;
import components.MovingBackground;
import components.PointCounter;


public class ScreenGame implements Screen {
    MyGdxGame myGdxGame;
    boolean isGameOver;
    Bird bird;
    PointCounter pointCounter;
    MovingBackground background = new MovingBackground();

    final int pointCounterMarginTop = 40;
    final int pointCounterMarginRight = 400;

    int tubeCount = 3;
    Tube[] tubes;
    int gamePoints = 0;

    public void initTubes(){
        tubes = new Tube[tubeCount];
        for (int i = 0; i < tubeCount; i++) {
            tubes[i] = new Tube(tubeCount, i);
        }
    }

    public ScreenGame(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        this.bird = new Bird(0,MyGdxGame.SCR_HEIGHT/2,    new Texture("birdTiles/bird0.png"),5);
        initTubes();

    }

    @Override
    public void show() {
        pointCounter = new PointCounter(MyGdxGame.SCR_WIDTH - pointCounterMarginRight,
                MyGdxGame.SCR_HEIGHT - pointCounterMarginTop);
        isGameOver = false;
        gamePoints = 0;

    }

    @Override
    public void render(float delta) {
        for (Tube tube : tubes) {
            tube.move();
            if (tube.isHit(bird)) {
                System.out.println("дэбилxxx");
                isGameOver = true;
            }else if (tube.needAddPoint(bird)) {
                tube.isPointReceived = true;
                gamePoints += 1;
                System.out.println(gamePoints);
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
        pointCounter.draw(myGdxGame.batch, gamePoints);
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
        background.dispose();
    }
}
