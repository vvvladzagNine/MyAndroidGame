package ru.zagidev.GUI;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;

public class GUI {


    OrthographicCamera camera;
    Map map;
    Shop shop;
    FightStarter fightStarter;


    public GUI(OrthographicCamera c) {
        camera = c;
        map = new Map(c);
        shop = new Shop(c);
        fightStarter = new FightStarter(c);
    }

    public void draw(Batch batch) {
        fightStarter.draw(batch);
        map.draw(batch);
        shop.draw(batch);
    }


    public void detectGuiTap(float x, float y) {
        map.pressDetect(x, y);

        if (Shop.state != GuiState.PLACING && Shop.state != GuiState.FIGHTING)
            shop.pressDetect(x, y);

        if (Shop.state == GuiState.IN_GAME || Shop.state == GuiState.FIGHTING)
            fightStarter.pressDetect(x, y);
    }

    public void update() {
        map.update();
        shop.update();
        fightStarter.update();
    }


}
