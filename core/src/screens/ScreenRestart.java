package screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.mygdx.game.MyGdxGame;

import components.MovingBackground;
import components.PointCounter;

public class ScreenRestart implements Screen {
    int gamePoints;
    MyGdxGame myGdxGame;
    MovingBackground background;

    TextButton buttonHome;
    PointCounter pointCounter;
    TextButton buttonRestart;

    public ScreenRestart(MyGdxGame myGdxGame) {
        this.myGdxGame = myGdxGame;
        buttonRestart = new TextButton(100, 400, "Restart");

        pointCounter = new PointCounter(750, 530);
        background = new MovingBackground("backGround/restart_bg.png");
    }

    @Override
    public void show() {

    }

    public void render(float delta) {

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
