package ru.zagidev.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.zagidev.MyAndroidGame;

public class MenuBut {
    private Texture texture;
    public Sprite sprite;
    private float scale;
    private OrthographicCamera camera;
    private int size;

    private float xScreen;
    private float yScreen;

    public MenuBut(OrthographicCamera camera) {
        this.camera = camera;
        scale = 1;
        size = 300;
        xScreen = Gdx.graphics.getWidth() - size * 0.5f;
        yScreen = 20;
        texture = new Texture("ui/menu.png");
        sprite = new Sprite(texture, size, size);
        sprite.setPosition(Gdx.graphics.getWidth() - size, Gdx.graphics.getHeight() - size);
        sprite.setScale(scale);
    }

    public void pressDetect(float x, float y) {
        if (
                x > xScreen
                        && x < (xScreen + size * 0.5f)
                        && y > yScreen
                        && y < (yScreen + size * 0.5f)
                && Shop.state != GuiState.PLACING
        ) {
                MyAndroidGame.currentScreen=MyAndroidGame.menueScreen;
            }

    }

    public void draw(Batch batch) {
        if (Shop.state != GuiState.PLACING) {
            batch.begin();
            sprite.draw(batch);
            batch.end();
        }


    }

    public void update() {
        float z = camera.zoom;
        sprite.setScale(z * 0.5f);
        sprite.setPosition(
                (Gdx.graphics.getWidth() * z * 0.5f) - size * sprite.getScaleX() - (size - size * sprite.getScaleX()) / 2 - 30 * z * 0.5f,
                (Gdx.graphics.getHeight() * z * 0.5f) - size * sprite.getScaleY() - (size - size * sprite.getScaleY()) / 2 - 30 * z * 0.5f
        );

    }
}
