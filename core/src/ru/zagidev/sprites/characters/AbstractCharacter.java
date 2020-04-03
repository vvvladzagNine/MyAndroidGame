package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.Point;
import ru.zagidev.logic.WaveAlgorithm;
import ru.zagidev.world.Team;
import ru.zagidev.world.WorldMap;

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
    public WorldMap worldMap=MyAndroidGame.worldMap;
    public Team team;
    private Team enemyTeam;
    protected float MAX_HEALTH=1000;
    protected float currentHealth;
    public boolean followTarget=true;
    protected int attackReloading=10;
    protected int currentAttackReloadingState=0;
    protected float damage=20;

    public boolean isAlive() {
        return currentHealth>0.0f;
    }

    private ShapeRenderer renderer;


    public void setdX(int a) {
        this.dX = a;

    }

    public Team getEnemyTeam() {
        return enemyTeam;
    }

    private void setEnemyTeam(){
        if(team!=null){
            if(worldMap.team1==team){
                enemyTeam=worldMap.team2;
            }
            else {
                enemyTeam=worldMap.team1;
            }
        }
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
        if(team!=null){
            batch.end();
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setProjectionMatrix(MyAndroidGame.camera.combined);
            renderer.setColor(Color.BLACK);
            renderer.rect(sp.getX(),sp.getY()+sp.getHeight(),100,21);
            renderer.setColor(team.color);
            renderer.rect(sp.getX()+5,sp.getY()+3+sp.getHeight(),90*(currentHealth/MAX_HEALTH),15);
            renderer.end();
            batch.begin();
        }


    }


    public void setTarget(AbstractCharacter a){
        if(target==null || !target.isAlive())
        target=a;
    }


    protected int computeDistance(AbstractCharacter character){
        return waveAlgorithm.computeDist(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(character.getCenterX(), character.getCenterY()));
    }

    public abstract void attack();



    protected AbstractCharacter getNearestEnemy(){
        if(enemyTeam!=null && enemyTeam.getMembers().size()>0){
            AbstractCharacter nearest=null;
            int dist = 1000000;
            for(AbstractCharacter enemy:enemyTeam.getMembers()){
                if(enemy.isAlive()) {
                    int newDist = computeDistance(enemy);
                    if (dist > newDist) {
                        dist = computeDistance(enemy);
                        nearest = enemy;
                    }
                }
            }
            return nearest;
        }
        else {
            return null;
        }

    }

    public void setNearestEnemyAsATarget(){
        setTarget(getNearestEnemy());
    }

    public void followTarget(){
        if(target!=null && followTarget && isAlive())
        changePath((int)target.sp.getX(),(int)target.sp.getY());
    }

    @Override
    public void act(float delta) {
            walk();



    }



    private void walk() {
        if(isAlive()) {
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
    }

    public void init(float x,float y){
        init(x,y,null);
    }
    public void init(float x,float y,Team team){
        this.team=team;
        if(team!=null){
            team.addMembers(this);
            setEnemyTeam();
        }
        speed=5f;
        path = new ArrayList<>();
        waveAlgorithm = new WaveAlgorithm();
        dX = 0;
        dY = 0;
        sp = new Sprite(texture, 100, 120);
        sp.setCenter(x, y);
        meshPoint = MyAndroidGame.getMatricsCords((int) getCenterX(), worldMap.GAME_WORLD_HEIGHT - (int) getCenterY());
        setX(sp.getX());
        setY(sp.getY());
        currentHealth=MAX_HEALTH;
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }


    public void changePath(float x, float y) {
        if (waveAlgorithm.computeDist(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(x, y)) != -1)
            path = waveAlgorithm.getPath(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(x, y));
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+":  X-"+sp.getX() + " Y-"+sp.getY();
    }
}
