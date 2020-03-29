package ru.zagidev.sprites.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.AbstractObject;

public class Dot implements AbstractObject {
        public Sprite sp;
        static Texture texture = new Texture("data/dot.png");

        public int dX;
        private int dY;

        public static final int STEP_X = Gdx.graphics.getWidth()/ MyAndroidGame.X_SIZE;
        public static final int STEP_Y = Gdx.graphics.getHeight()/ MyAndroidGame.Y_SIZE;

    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;

        public void setdX(int a) {
            this.dX = a;

        }

        public Dot(int x, int y) {
            dX=0;
            dY=0;
            sp = new Sprite(texture,WIDTH,HEIGHT);
            sp.setPosition(STEP_X*x + (STEP_X-WIDTH)/2,STEP_Y*y + (STEP_Y-HEIGHT)/2);
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
