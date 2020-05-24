package ru.zagidev.sprites.characters;

public abstract class KnightCharacter extends MeleeCharacter {
    @Override
    public void playPunchSound() {
        double a = Math.random();
        if (a < 0.33)
            swordHit1.play();
        else if (a > 0.67) swordHit2.play();
        else swordHit3.play();

    }
}
