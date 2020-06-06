package ru.zagidev.sprites.characters.bullets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.awt.Button;
import java.util.ArrayList;

import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.world.Team;
import ru.zagidev.world.WorldMap;

import static ru.zagidev.RunningGame.currentGameLevel;

public class Bullet extends Actor {

    public static Texture texture= new Texture("data/bullet.png");

    public static Sound hitmarker = Gdx.audio.newSound(Gdx.files.internal("data/sounds/hitmarker_2.mp3"));

    public boolean collide=false;


    public static ArrayList<Bullet> bulletsToRemove = new ArrayList<>();


    public Sprite sprite;

    private float dX;
    private float dY;
    private float damage;
    private Team teamToHurt;

    public Bullet(float x, float y, float angle, float speed,Team t,float damage) {
        teamToHurt=t;
        this.damage=damage;
        this.dX = (float) (speed*Math.cos(angle));
        this.dY = (float) (speed*Math.sin(angle));
        sprite=new Sprite(texture);
        sprite.setPosition(x,y);
    }


    boolean isRemoving(){
        return sprite.getX()<-30 ||
                sprite.getX()> currentGameLevel.worldMap.GAME_WORLD_WIDTH+30 ||
                sprite.getY()<-30 ||
                sprite.getY()> currentGameLevel.worldMap.GAME_WORLD_HEIGHT+30 ||
                collide
                ;
    }

    public static void removeAllBullets(){
        for(int i=0;i<bulletsToRemove.size();i++){
            bulletsToRemove.get(i).remove();
        }
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(!isRemoving())
        sprite.draw(batch);
    }

    public static void addToRemoveList(Bullet b){
        bulletsToRemove.add(b);
    }

    @Override
    public void act(float delta) {
        for(AbstractCharacter c:teamToHurt.getMembers()){
            if(sprite.getX()>c.sp.getX()&&
                    sprite.getX()<(c.sp.getX()+c.sp.getWidth())&&
                    sprite.getY()>c.sp.getY()&&
                    sprite.getY()<(c.sp.getY()+c.sp.getHeight())
            ){
                if(c.isAlive() && !isRemoving()){
                    c.currentHealth-=damage;
                    collide=true;
                    addToRemoveList(this);
                }

            }
        }
        sprite.translate(dX,dY);
        if(isRemoving()){
            addToRemoveList(this);
        }
    }
}
