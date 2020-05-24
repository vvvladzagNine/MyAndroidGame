package ru.zagidev.sprites.characters;

import ru.zagidev.RunningGame;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Effects;
import ru.zagidev.world.WorldMap;

public abstract class MeleeCharacter extends AbstractCharacter {

    public abstract void playPunchSound();


    @Override
    public void attack() {
        if(isAlive() && RunningGame.currentGameLevel.characters.isFight){
            if(target==null){
                setNearestEnemyAsATarget();
            }

            if(target!=null){
                float d = realDist(target);
                if(d<200){
                    if(target.isAlive()){
                        if(currentAttackReloadingState ==attackReloading){
                            target.currentHealth-=damage;
                            //target.sound.play(1f,0.7f+(float)(Math.random()),1f);
                            playPunchSound();
                            if(isAlive())
                            target.setTarget(this);
                            currentAttackReloadingState=0;
                            Effects.explosions.add(new BloodExplosion(target.sp.getX(),target.sp.getY()));
                        }
                    }else{
                        setNearestEnemyAsATarget();
                    }
                    currentAttackReloadingState++;

                }
                else {
                    followTarget();
                }
            }
        }


    }
}
