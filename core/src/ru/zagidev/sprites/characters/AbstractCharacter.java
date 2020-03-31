package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.Point;
import ru.zagidev.logic.WaveAlgorithm;

import static ru.zagidev.MyAndroidGame.HEIGHT;
import static ru.zagidev.MyAndroidGame.getMatricsCords;
import static ru.zagidev.MyAndroidGame.getRealCords;

public abstract class AbstractCharacter extends Actor {

    public Sprite sp;
    static Texture texture;
    public WaveAlgorithm waveAlgorithm;
    public ArrayList<Point> path;
    public Point nextCell;
    public Point meshPoint;
    protected float dX;
    protected float dY;
    public AbstractCharacter target;
    public float speed;

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

        sp.draw(batch);
        sp.setColor(new Color(255, 255, 255, 255));

    }


    public void setTarget(AbstractCharacter a){
        if(target==null)
        target=a;
    }

    public void followTarget(){
        if(target!=null)
        changePath((int)target.sp.getX(),(int)target.sp.getY());
    }

    @Override
    public void act(float delta) {
        walk();
    }

    private void walk() {
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
                dX = (speed * sin);
                dY = (speed * cos);
                sp.translate(+dX, +dY);
            } else {
                path = new ArrayList<>();
            }
        }
    }

    public void init(float x,float y){
        speed=5f;
        path = new ArrayList<>();
        waveAlgorithm = new WaveAlgorithm();
        dX = 0;
        dY = 0;
        sp = new Sprite(texture, 100, 100);
        sp.setCenter(x, y);
        meshPoint = MyAndroidGame.getMatricsCords((int) getCenterX(), HEIGHT - (int) getCenterY());
        setX(sp.getX());
        setY(sp.getY());
    }


    public void changePath(float x, float y) {
        if (waveAlgorithm.computeDist(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(x, y)) != -1)
            path = waveAlgorithm.getPath(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(x, y));
    }


}
