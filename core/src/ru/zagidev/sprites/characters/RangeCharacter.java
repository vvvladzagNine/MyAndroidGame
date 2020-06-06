package ru.zagidev.sprites.characters;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

import ru.zagidev.Point;
import ru.zagidev.RunningGame;
import ru.zagidev.sprites.characters.behaviors.RangeAttack;
import ru.zagidev.sprites.characters.bullets.Bullet;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.world.Cell;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Effects;
import ru.zagidev.world.Team;
import ru.zagidev.world.WorldMap;
import ru.zagidev.world.blocks.Placeable;

import static ru.zagidev.RunningGame.currentGameLevel;
import static ru.zagidev.RunningGame.getMatricsCords;

public abstract class RangeCharacter extends AbstractCharacter {

    @Override
    public void adjustClass(float x, float y, Team team, String soundPath, float speed, float maxHealth, float damage, int price) {
        super.adjustClass(x, y, team, soundPath, speed, maxHealth, damage, price);

        attackBehavior=new RangeAttack(100,this,30,AbstractCharacter.shot1,20);

    }


    //    Color rayColor= Color.BLUE;
//
//    @Override
//    public void draw(Batch batch, float parentAlpha) {
//        super.draw(batch,parentAlpha);
////        if(isAlive()){
////            batch.end();
////            renderer.begin();
////            renderer.setColor(rayColor);
////            renderer.line(getCenterX(),getCenterY(),getCenterX() + 500*(float)Math.cos(angle),getCenterY()+ 500*(float)Math.sin(angle));
////            renderer.setProjectionMatrix(RunningGame.camera.combined);
////            renderer.end();
////            batch.begin();
////        }
//
//
//    }








}
