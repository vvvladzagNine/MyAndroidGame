package ru.zagidev.sprites.characters.behaviors;

import com.badlogic.gdx.audio.Sound;

import ru.zagidev.RunningGame;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.world.Effects;

public class MeleeAttack extends AttackBehavior {
    @Override
    public void attack() {
        if(charr.isAlive() && RunningGame.currentGameLevel.characters.isFight){
            if(charr.target==null){
                charr.setNearestEnemyAsATarget();
            }

            if(charr.target!=null){
                float d = charr.realDist(charr.target);
                if(d<200){
                    if(charr.target.isAlive()){
                        if(currentAttackReloadingState == attackReloading){
                            charr.target.currentHealth-=damage;
                            //target.sound.play(1f,0.7f+(float)(Math.random()),1f);
                            playAttackSound();
                            if(charr.isAlive())
                                charr.target.setTarget(charr);
                            currentAttackReloadingState=0;
                            Effects.explosions.add(new BloodExplosion(charr.target.sp.getX(),charr.target.sp.getY()));
                        }
                    }else{
                        charr.setNearestEnemyAsATarget();
                    }
                    currentAttackReloadingState++;

                }
                else {
                    charr.followTarget();
                }
            }
        }


    }

    public MeleeAttack(float damage, AbstractCharacter abstractCharacter, Sound attackSound,int attackReloading) {
        this(damage,abstractCharacter,attackSound);
        this.attackReloading=attackReloading;
    }

    public MeleeAttack(float damage, AbstractCharacter abstractCharacter, Sound attackSound) {
        this.damage=damage;
        this.charr=abstractCharacter;
        this.attackSound=attackSound;
    }
}
