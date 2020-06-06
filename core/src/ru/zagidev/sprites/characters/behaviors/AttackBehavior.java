package ru.zagidev.sprites.characters.behaviors;

import com.badlogic.gdx.audio.Sound;

import ru.zagidev.sprites.characters.AbstractCharacter;

public abstract class AttackBehavior {

    public int attackReloading = 25;
    public int currentAttackReloadingState = 0;

    public float damage;
    public Sound attackSound;
    public AbstractCharacter charr;

    public abstract void attack();

    public void playAttackSound(){
        attackSound.play();
    }
}
