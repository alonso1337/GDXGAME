package com.mygdx.game;

public class MySperScreen implements MyScreen{
    @Override
    public void create() {
        System.out.println("Load sprites");
        System.out.println("I ready to display");
    }

    @Override
    public void render() {
        System.out.println("Draw bird");
        System.out.println("Move bird");
    }
}
