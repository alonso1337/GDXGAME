package com.mygdx.game.characters;

import static com.mygdx.game.MyGdxGame.SCR_HEIGHT;
import static com.mygdx.game.MyGdxGame.SCR_WIDTH;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Random;

public class Tube {
    int tubeCount = 1;
    Texture[] tubes;
    int padding = 100;
    int height = 500;
    int width = 200;
    int gapHeight = 400;
    Random r = new Random();
    int gapY;
    int x;
    int distanceBetweenTubes;
    int speed = 8;
    public boolean isPointReceived;

    Texture textureUpperTube;
    Texture textureDownTube;

    public boolean isHit(Bird bird) {
        if (bird.y <= gapY - gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;
        if (bird.y + bird.height >= gapY + gapHeight / 2 && bird.x + bird.width >= x && bird.x <= x)
            return true;

        return false;
    }

    public Tube(int tubeCount, int tubeIdx) {


        gapY = gapHeight / 2 + padding + r.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        distanceBetweenTubes = (SCR_WIDTH + width) / (tubeCount - 1);
        x = distanceBetweenTubes * tubeIdx + SCR_WIDTH;

        textureUpperTube = new Texture("tubes/tube_flipped.png");
        textureDownTube = new Texture("tubes/tube.png");
    }
    public void draw(Batch batch) {
        batch.draw(textureUpperTube, x, gapY + gapHeight / 2, width, height);
        batch.draw(textureDownTube, x, gapY - gapHeight / 2 - height, width, height);
    }
    void dispose() {
        textureDownTube.dispose();
        textureUpperTube.dispose();
    }
    public void move() {
        x -= speed;
        if (x < -width) {
            isPointReceived = false;
            x = SCR_WIDTH + distanceBetweenTubes;
            gapY = gapHeight / 2 + padding + r.nextInt(SCR_HEIGHT - 2 * (padding + gapHeight / 2));
        }
    }

    public boolean needAddPoint(Bird bird) {
        boolean b = bird.x >= x + width - speed;

        return b && !isPointReceived;
    }

}
