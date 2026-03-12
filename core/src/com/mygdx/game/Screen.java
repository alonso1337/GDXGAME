package com.mygdx.game;

public interface Screen {

    public void show ();
    public void render (float delta);

    public void hide ();
    public void dispose ();
}
