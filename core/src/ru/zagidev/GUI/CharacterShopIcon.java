package ru.zagidev.GUI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public class CharacterShopIcon {
    Texture texture;
    public Sprite sp;

    public CharacterShopIcon(Texture texture) {
        this.texture = texture;
        sp = new Sprite(texture,500,600);
    }
}
