package ru.zagidev.sprites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.AbstractObject;

public class Block implements AbstractObject {
    public Sprite sp;
    static Texture texture = new Texture("data/stena.jpg");

    public int dX;
    private int dY;

    public static final int WIDTH = Gdx.graphics.getWidth()/ MyAndroidGame.X_SIZE;
    public static final int HEIGHT = Gdx.graphics.getHeight()/ MyAndroidGame.Y_SIZE;

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
    public void render(SpriteBatch batch) {
        sp.draw(batch);
    }

    @Override
    public void update() {
        sp.setPosition(sp.getX()+dX,sp.getY());
    }
}