package ru.zagidev.sprites.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.zagidev.sprites.AbstractObject;

public class Anime implements AbstractObject {

    public Sprite sp;
    static Texture texture = new Texture("data/unnamed.png");

    public int dX;
    private int dY;

    public void setdX(int a) {
        this.dX = a;

    }

    public Anime() {
        dX = 0;
        dY = 0;
        sp = new Sprite(texture, 512, 512);
        sp.setPosition(600, 160);
    }

    @Override
    public void render(SpriteBatch batch) {
        sp.draw(batch);
    }

    @Override
    public void update() {
        sp.setPosition(sp.getX() + dX, sp.getY());
    }
}
