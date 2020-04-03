package ru.zagidev.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class Shop {
    private Sprite pressedSprite;
    private Sprite releasedSprite;
    public Sprite sprite;
    private float scale;
    private OrthographicCamera camera;
    private int size;

    private float xScreen;
    private float yScreen;

    public Shop(OrthographicCamera c) {
        camera=c;
        scale=1;
        size=300;

        xScreen=Gdx.graphics.getWidth()-size*0.5f;
        yScreen=20+size*0.5f;

        Texture realesedTexture= new Texture("ui/shop-icon.png");
        Texture pressedTexture= new Texture("ui/shop-iconPressed.png");
        pressedSprite=new Sprite(pressedTexture,size,size);
        releasedSprite=new Sprite(realesedTexture,size,size);
        sprite=releasedSprite;
        sprite.setPosition(Gdx.graphics.getWidth()-size,Gdx.graphics.getHeight()-size);
        sprite.setScale(scale);

    }

    public void pressDetect(float x, float y){
        if(
                x > xScreen
                        && x < (xScreen + size*0.5f)
                        && y > yScreen
                        && y < (yScreen + size*0.5f)
        )
            if(sprite==releasedSprite){
                sprite=pressedSprite;
            }
            else {
                sprite=releasedSprite;
            }
    }

    public void draw(Batch batch){
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    public void update(){
        float z = camera.zoom;
        sprite.setScale(z*0.5f);
        sprite.setPosition(
                (Gdx.graphics.getWidth()*z*0.5f)-size*sprite.getScaleX()-(size-size*sprite.getScaleX())/2 -30*z*0.5f,
                (Gdx.graphics.getHeight()*z*0.5f)-size*2*sprite.getScaleY()-(size-size*sprite.getScaleY())/2 -60*z*0.5f
        );

    }
}
