package ru.zagidev.sprites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ru.zagidev.sprites.AbstractObject;
import ru.zagidev.world.WorldMap;

import static ru.zagidev.RunningGame.currentGameLevel;

public class Block extends Actor {
    public Sprite sp;
    static Texture texture = new Texture("data/stena.jpg");

    public int dX;
    private int dY;

    public static int WIDTH = currentGameLevel.worldMap.CELL_WIDTH;
    public static int HEIGHT = currentGameLevel.worldMap.CELL_HEIGHT;

    public void setdX(int a) {
        this.dX = a;

    }

    public Block(int x, int y) {
        dX=0;
        dY=0;
        sp = new Sprite(texture,WIDTH,HEIGHT);
        sp.setPosition(WIDTH*x,HEIGHT*y);
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        sp.draw(batch);
    }

    @Override
    public void act(float delta) {
            sp.setPosition(sp.getX()+dX,sp.getY());
    }
}