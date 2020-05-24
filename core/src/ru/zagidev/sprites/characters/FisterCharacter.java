package ru.zagidev.sprites.characters;

public abstract class FisterCharacter extends MeleeCharacter {
    @Override
    public void playPunchSound() {
        if (Math.random() > 0.5)
            punch1.play();
        else
            punch2.play();
    }
}
