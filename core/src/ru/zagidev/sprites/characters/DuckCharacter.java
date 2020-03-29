package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

import ru.zagidev.Point;
import ru.zagidev.logic.WaveAlgorithm;
import ru.zagidev.sprites.AbstractObject;

import static ru.zagidev.MyAndroidGame.getMatricsCords;

public class DuckCharacter implements AbstractObject {
     public Sprite sp;
     static Texture texture = new Texture("data/duck.png");
     public WaveAlgorithm waveAlgorithm;
     public ArrayList<Point> path;

     public Point destination;

     public int dX;
     private int dY;

    public void setdX(int a) {
        this.dX = a;

    }

    public DuckCharacter() {
        path = new ArrayList<>();
        destination=new Point(18,8);
        waveAlgorithm=new WaveAlgorithm();
        dX=0;
        dY=0;
        sp = new Sprite(texture,100,100);
        sp.setPosition(240,160);
    }

    public void changePath(int x,int y){
            if(waveAlgorithm.computeDist(getMatricsCords((int)sp.getX(), Gdx.graphics.getHeight()-(int)sp.getY()),getMatricsCords(x,y))!=-1)
                path=waveAlgorithm.getPath(getMatricsCords((int)sp.getX(),Gdx.graphics.getHeight()-(int)sp.getY()),getMatricsCords(x,y));
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

