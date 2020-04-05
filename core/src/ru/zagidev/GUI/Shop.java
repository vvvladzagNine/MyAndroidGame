package ru.zagidev.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class Shop {
    private Sprite pressedSprite;
    private Sprite releasedSprite;
    public Sprite sprite;
    private float scale;
    private OrthographicCamera camera;
    private int size;
    private ShapeRenderer renderer;
    private float iconInterval;

    public ArrayList<CharacterShopIcon> arrayList;

    public CharacterShopIcon icon1;
    public CharacterShopIcon icon2;
    public CharacterShopIcon icon3;
    public CharacterShopIcon icon4;

    public Sprite strelochka;
    public Sprite strelochkaLeft;


    private float xScreen;
    private float yScreen;

    public Shop(OrthographicCamera c) {
        camera = c;
        scale = 1;
        size = 300;

        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);


        xScreen = Gdx.graphics.getWidth() - size * 0.5f;
        yScreen = 20 + size * 0.5f;

        Texture realesedTexture = new Texture("ui/shop-icon.png");
        Texture pressedTexture = new Texture("ui/shop-iconPressed.png");
        pressedSprite = new Sprite(pressedTexture, size, size);
        releasedSprite = new Sprite(realesedTexture, size, size);
        sprite = releasedSprite;
        sprite.setPosition(Gdx.graphics.getWidth() - size, Gdx.graphics.getHeight() - size);
        sprite.setScale(scale);

        arrayList = new ArrayList<>();
        Texture t1 = new Texture("ui/utyaIcon.png");
        Texture t2 = new Texture("ui/ugolubIcon.png");

        arrayList.add(new CharacterShopIcon(t1));
        arrayList.add(new CharacterShopIcon(t2));
        arrayList.add(new CharacterShopIcon(t1));
        arrayList.add(new CharacterShopIcon(t2));

        icon1 = arrayList.get(0);
        icon2 = arrayList.get(1);
        icon3 = arrayList.get(2);
        icon4 = arrayList.get(3);

        strelochka=new Sprite(new Texture("ui/strelochka.png"));
        strelochkaLeft=new Sprite(new Texture("ui/strelochkaLeft.png"));

        iconInterval = 40;

    }

    public boolean isActive() {
        return sprite == pressedSprite;
    }

    public void pressDetect(float x, float y) {
        if (
                x > xScreen
                        && x < (xScreen + size * 0.5f)
                        && y > yScreen
                        && y < (yScreen + size * 0.5f)
        )
            if (sprite == releasedSprite) {
                sprite = pressedSprite;
            } else {
                sprite = releasedSprite;
            }
    }

    public void draw(Batch batch) {
        batch.begin();
        sprite.draw(batch);
        if (isActive()) {
            icon1.sp.draw(batch);
            icon2.sp.draw(batch);
            icon3.sp.draw(batch);
            icon4.sp.draw(batch);
            strelochka.draw(batch);
            strelochkaLeft.draw(batch);

        }

        batch.end();
    }

    public void update() {
        float z = camera.zoom;


        sprite.setScale(z * 0.5f);
        sprite.setPosition(
                (Gdx.graphics.getWidth() * z * 0.5f) - size * sprite.getScaleX() - (size - size * sprite.getScaleX()) / 2 - 30 * z * 0.5f,
                (Gdx.graphics.getHeight() * z * 0.5f) - size * 2 * sprite.getScaleY() - (size - size * sprite.getScaleY()) / 2 - 60 * z * 0.5f
        );

        float y = (Gdx.graphics.getHeight() * z * 0.5f) - (Gdx.graphics.getHeight() * 1.9f) * icon1.sp.getScaleY() - (600 - 600 * icon1.sp.getScaleY()) / 2 - 60 * z * 0.5f;
        float yS = (Gdx.graphics.getHeight() * z * 0.5f) - (Gdx.graphics.getHeight() * 1.9f) * icon1.sp.getScaleY() - (500 - 500 * icon1.sp.getScaleY()) / 2 - 60 * z * 0.5f;


        icon1.sp.setScale(z * 0.5f);
        icon1.sp.setPosition(
                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * icon1.sp.getScaleX()/2 -iconInterval * 3.5f * z - icon1.sp.getWidth() * icon1.sp.getScaleX() * 3 - (500 - 500 * icon1.sp.getScaleX()) / 2,
                y);

        icon2.sp.setScale(z * 0.5f);
        icon2.sp.setPosition(
                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * icon1.sp.getScaleX()/2 -iconInterval * 2.5f * z - icon2.sp.getWidth() * icon2.sp.getScaleX() * 2 - (500 - 500 * icon2.sp.getScaleX()) / 2,
                y);

        icon3.sp.setScale(z * 0.5f);
        icon3.sp.setPosition(
                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * icon1.sp.getScaleX()/2 -iconInterval * 1.5f * z - icon3.sp.getWidth() * icon3.sp.getScaleX() - (500 - 500 * icon3.sp.getScaleX()) / 2,
                y);

        icon4.sp.setScale(z * 0.5f);
        icon4.sp.setPosition(
                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * icon1.sp.getScaleX()/2- iconInterval * 0.5f * z  - (500 - 500 * icon4.sp.getScaleX()) / 2,
                y);

        strelochka.setScale(z*0.5f);
        strelochka.setPosition(
                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * strelochka.getScaleX()/2 -iconInterval * -0.5f * z + strelochka.getWidth() * strelochka.getScaleX() - (500 - 500 * icon3.sp.getScaleX()) / 2,
                yS);

        strelochkaLeft.setScale(z*0.5f);
        strelochkaLeft.setPosition(
                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * strelochkaLeft.getScaleX()/2 -iconInterval * 4.5f * z - strelochkaLeft.getWidth() * strelochkaLeft.getScaleX()*4 - (500 - 500 * icon3.sp.getScaleX()) / 2,
                yS);

        //(600 - 600 * icon1.sp.getScaleY()) / 2);

    }
}
