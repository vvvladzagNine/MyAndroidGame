package ru.zagidev.sprites.characters;

public abstract class MeleeCharacter extends AbstractCharacter {
    @Override
    public void attack() {
        if(isAlive()){
            if(target==null){
                setNearestEnemyAsATarget();
            }
            if(target!=null){
                if(computeDistance(target)<2){
                    if(target.isAlive()){
                        if(currentAttackReloadingState ==attackReloading){
                            target.currentHealth-=damage;
                            currentAttackReloadingState=0;
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
