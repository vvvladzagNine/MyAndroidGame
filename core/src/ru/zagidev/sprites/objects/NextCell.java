package ru.zagidev.sprites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.characters.DuckCharacter;

public class NextCell extends Actor {

    public Sprite sp;
    static Texture texture = new Texture("data/dot.png");

    public int dX;
    private int dY;
    private DuckCharacter d;

    public static final int STEP_X = MyAndroidGame.WIDTH / MyAndroidGame.X_SIZE;
    public static final int STEP_Y = MyAndroidGame.HEIGHT/ MyAndroidGame.Y_SIZE;

    public static final int WIDTH = 70;
    public static final int HEIGHT = 70;

    public void setdX(int a) {
        this.dX = a;

    }

    public NextCell(DuckCharacter duckCharacter) {
        d=duckCharacter;
        dX = 0;
        dY = 0;
        sp = new Sprite(texture, WIDTH, HEIGHT);
        if(d.nextCell!=null)
        sp.setPosition(STEP_X * d.nextCell.x + (STEP_X - WIDTH) / 2, STEP_Y * d.nextCell.y + (STEP_Y - HEIGHT) / 2);
        else  sp.setPosition(0,0);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sp.draw(batch);
    }

    @Override
    public void act(float delta) {
        if(d.nextCell!=null)
            sp.setPosition(STEP_X * d.nextCell.x + (STEP_X - WIDTH) / 2, STEP_Y * d.nextCell.y + (STEP_Y - HEIGHT) / 2);
    }
}

