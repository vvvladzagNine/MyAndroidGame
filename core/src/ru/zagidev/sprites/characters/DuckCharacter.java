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

public class DuckCharacter extends Actor {
    public Sprite sp;
    static Texture texture = new Texture("data/duck.png");
    public WaveAlgorithm waveAlgorithm;
    public ArrayList<Point> path;
    public Point nextCell;
    public Point meshPoint;
    public Point destination;
    public Mesh mesh;

    public int x2, y2;

    public float dX;
    private float dY;

    public void setdX(int a) {
        this.dX = a;

    }

    public float getCenterX(){
        return sp.getX()+sp.getWidth()/2;
    }

    public float getCenterY(){
        return sp.getY()+sp.getHeight()/2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        sr.begin();
        sp.draw(batch);
        sp.setColor(new Color(255, 255, 255, 255));
//        sr.line(sp.getX(),sp.getY(),x2,x1);
//        sr.end();


    }

    @Override
    public void act(float delta) {
        if (path != null && path.size() > 0) {
            meshPoint = MyAndroidGame.getMatricsCords((int) getCenterX(), (int) getCenterY());
            Gdx.app.log("CurrentCell", meshPoint.x + " " + meshPoint.y);
            if (path.indexOf(meshPoint) != 0) {
                nextCell = path.get(path.indexOf(meshPoint) - 1);
                Point p = getRealCords(nextCell.x, nextCell.y);
                float drx = p.x - getCenterX();
                float dry = p.y - getCenterY();
                float dist = (int) Math.sqrt(drx * drx + dry * dry);
                float sin = drx / dist;
                float cos = dry / dist;
                dX = (5f * sin);
                dY = (5f * cos);
                sp.translate(+dX, +dY);
            } else {
                path = new ArrayList<>();
            }


        }
    }

    public DuckCharacter() {
        path = new ArrayList<>();
        destination = new Point(18, 8);
        waveAlgorithm = new WaveAlgorithm();
        dX = 0;
        dY = 0;
        sp = new Sprite(texture, 100, 100);
        sp.setCenter(240, 160);
        meshPoint = MyAndroidGame.getMatricsCords((int) getCenterX(), HEIGHT - (int) getCenterY());
    }

    public void changePath(int x, int y) {
        if (waveAlgorithm.computeDist(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(x, y)) != -1)
            path = waveAlgorithm.getPath(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(x, y));
    }


}

