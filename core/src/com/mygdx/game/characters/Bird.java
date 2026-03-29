package com.mygdx.game.characters;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.game.MyGdxGame;

public class Bird {
    int x, y;
    int speed;
    float vY = 0;
    float gravity = -0.5f;
    Texture texture;
    int jumpHeight = 10;
    final int maxHeightOfJump = 200;
    boolean jump;
    int frameCounter =  0;
    Texture[] framesArray;
    int width;
    int height;

    public boolean isInField() {
        if (y + height < 0) return false;
        if (y > MyGdxGame.SCR_HEIGHT) return false;
        return true;
    }
    public void setY(int y) {
        this.y = y;
    }



    public Bird(int x, int y, Texture texture){
        this.x = x;
        this.y = y;
        this.texture = texture;
        //this.speed = speed;
        this.width = 250;
        this.height = 200;
        framesArray = new Texture[]{
                new Texture("birdTiles/bird0.png"),
                new Texture("birdTiles/bird1.png"),
                new Texture("birdTiles/bird2.png"),
                new Texture("birdTiles/bird1.png"),
        };
    }

    public void dispose() {
        for (Texture texture : framesArray) {
            texture.dispose();
        }
    }

    public void onClick() {
        if (vY < 0) {
            vY = jumpHeight;
        } else {
            vY += jumpHeight;
        }
    }
    public void fly() {
        vY += gravity;  // Гравитация постоянно уменьшает скорость
        y += vY;  // Изменяем координату Y на значение скорости


    }
    public void draw(Batch batch) {
        int frameMultiplier = 10;
        batch.draw(framesArray[frameCounter / frameMultiplier], x, y, width, height);
        if (frameCounter++ == framesArray.length * frameMultiplier - 1) frameCounter = 0;
    }

}