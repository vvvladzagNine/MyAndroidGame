package ru.zagidev.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GUI {


    OrthographicCamera camera;
    Map map;
    Shop shop;

    public GUI(OrthographicCamera c) {
        camera = c;
        map = new Map(c);
        shop = new Shop(c);
    }

    public void draw(Batch batch) {

        map.draw(batch);
        shop.draw(batch);
    }


    public void detectGuiTap(float x, float y) {
            map.pressDetect(x,y);
            if(Shop.state!=GuiState.PLACING)
            shop.pressDetect(x,y);
    }

    public void update() {
        map.update();
        shop.update();
    }


}
