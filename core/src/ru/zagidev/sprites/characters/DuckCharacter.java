package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.Point;
import ru.zagidev.logic.WaveAlgorithm;
import ru.zagidev.sprites.AbstractObject;

import static ru.zagidev.MyAndroidGame.HEIGHT;
import static ru.zagidev.MyAndroidGame.getMatricsCords;
import static ru.zagidev.MyAndroidGame.getRealCords;

public class DuckCharacter extends AbstractCharacter {
    static {
        texture = new Texture("data/duck.png");
    }

    public DuckCharacter(float x,float y) {
        init(x,y);
    }



}

