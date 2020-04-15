package ru.zagidev.sprites.characters;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.Point;
import ru.zagidev.sprites.characters.bullets.Bullet;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.world.Cell;
import ru.zagidev.world.Effects;
import ru.zagidev.world.WorldMap;
import ru.zagidev.world.blocks.Placeable;

public abstract class RangeCharacter extends AbstractCharacter {


    public Sound shotSound;
    public static float bulletSpeed;
    float angle=0;

    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch,parentAlpha);
        if(isAlive()){
            batch.end();
            renderer.begin();
            renderer.setColor(Color.RED);
            renderer.line(getCenterX(),getCenterY(),getCenterX() + 500*(float)Math.cos(angle),getCenterY()+ 500*(float)Math.sin(angle));
            renderer.setProjectionMatrix(MyAndroidGame.camera.combined);
            renderer.end();
            batch.begin();
        }


    }

    public void shoot(float angle) {
        getStage().addActor(new Bullet(getCenterX(), getCenterY(), angle, bulletSpeed, getEnemyTeam(), damage));
    }

    public float isAimed(AbstractCharacter character) {
        float rx = character.getCenterX() - getCenterX();
        float ry = character.getCenterY() - getCenterY();
        float tan= ry / rx;
        angle = (float) Math.atan(tan);
        if(rx<0)angle+=Math.PI;
        float x = getCenterX();
        float y = getCenterY();
        Point charP = MyAndroidGame.getMatricsCords(character.getCenterX(), character.getCenterY());
        Point p = MyAndroidGame.getMatricsCords(x, y);
        while (!isNear(x,y,target)) {
//            try{
//                WorldMap.matrix[p.y][p.x].isShooted();
//            }catch (ArrayIndexOutOfBoundsException e){
//                return angle;
//            }
            if (!WorldMap.matrix[p.x][p.y].isShooted() && !isNear(x,y,target)){
                return -123456789;
            }


            x += 20 * Math.cos(angle);
            y += 20 * Math.sin(angle);
            p = MyAndroidGame.getMatricsCords(x, y);

        }
        return angle;
//        float fx = y+tan*character.getCenterX();
//        float fy = x+cotan*character.getCenterY();
//        if((fx>character.getCenterY() && fx<(character.getCenterY()+character.sp.getHeight())) ||
//                (fy>character.getCenterX() && fy<(character.getCenterX()+character.sp.getWidth()))
//        ){
//            return (float) Math.atan(tan);
//        }

    }

    boolean isNear(float x,float y, AbstractCharacter c){
        float rx = c.getCenterX() - x;
        float ry = c.getCenterY() - y;
        return  ((rx*rx+ry*ry) < 9000);
    }

    @Override
    public void attack() {
        if (isAlive()) {
            if (target == null) {
                setNearestEnemyAsATarget();
            }
            if (target != null) {
                //float d = realDist(target);
                float angle = isAimed(target);
                if (angle != -123456789) {
                    if (target.isAlive()) {
                        if (currentAttackReloadingState == attackReloading) {
                            shotSound.play();
                            shoot(angle);
                            currentAttackReloadingState = 0;
                            //Effects.explosions.add(new BloodExplosion(target.sp.getX(), target.sp.getY()));
                        }
                    } else {
                        setNearestEnemyAsATarget();
                    }
                    currentAttackReloadingState++;

                } else {
                    followTarget();
                }
            }
        }
    }
}
