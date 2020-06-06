package ru.zagidev.sprites.characters.behaviors;

import com.badlogic.gdx.audio.Sound;

import ru.zagidev.Point;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.bullets.Bullet;

import static ru.zagidev.RunningGame.currentGameLevel;
import static ru.zagidev.RunningGame.getMatricsCords;

public class RangeAttack extends AttackBehavior{

    public  float bulletSpeed;


    @Override
    public void attack() {
        if (charr.isAlive() && currentGameLevel.characters.isFight) {
            if (charr.target == null) {
                charr.setNearestEnemyAsATarget();
            }
            if (charr.target != null) {
                float angle = isAimed(charr.target);
                if (angle != -123456789) {
                    if (charr.target.isAlive()) {
                        if (currentAttackReloadingState == attackReloading) {
                            playAttackSound();
                            shoot(angle);
                            currentAttackReloadingState = 0;
                            //Effects.explosions.add(new BloodExplosion(target.sp.getX(), target.sp.getY()));
                        }
                    } else {
                        charr.setNearestEnemyAsATarget();
                    }
                    currentAttackReloadingState++;

                } else {
                    charr.followTarget();
                }
            }
        }
    }


    public void shoot(float angle) {
        charr.getStage().addActor(new Bullet(charr.getCenterX(), charr.getCenterY(), angle, bulletSpeed, charr.getEnemyTeam(), damage));
    }

    public float isAimed(AbstractCharacter character) {
        float rx = character.getCenterX() - charr.getCenterX();
        float ry = character.getCenterY() - charr.getCenterY();
        float tan= ry / rx;
        float angle = (float) Math.atan(tan);
        if(rx<0)angle+=Math.PI;
        float x = charr.getCenterX();
        float y = charr.getCenterY();
        Point charP = getMatricsCords(character.getCenterX(), character.getCenterY());
        Point p = getMatricsCords(x, y);
        while (!isNear(x,y,charr.target)) {
            if (!currentGameLevel.worldMap.matrix[p.x][p.y].isShooted() && !isNear(x,y,charr.target)){
                return -123456789;
            }


            x += 20 * Math.cos(angle);
            y += 20 * Math.sin(angle);
            p = getMatricsCords(x, y);

        }
        return angle;

    }

    boolean isNear(float x,float y, AbstractCharacter c){
        float rx = c.getCenterX() - x;
        float ry = c.getCenterY() - y;
        return  ((rx*rx+ry*ry) < 9000);
    }

    public RangeAttack(float damage, AbstractCharacter abstractCharacter,float bulletSpeed,Sound attackSound,int attackReloading) {
        this(damage,abstractCharacter,bulletSpeed,attackSound);
        this.attackReloading=attackReloading;
    }

    public RangeAttack(float damage, AbstractCharacter abstractCharacter,float bulletSpeed,Sound attackSound) {
        this.damage=damage;
        this.charr=abstractCharacter;
        this.attackSound=attackSound;
        this.bulletSpeed=bulletSpeed;
    }
}
