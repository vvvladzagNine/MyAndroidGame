package ru.zagidev.sprites.characters;

import ru.zagidev.sprites.characters.behaviors.MeleeAttack;
import ru.zagidev.world.Team;

public abstract class KnightCharacter extends AbstractCharacter {
    @Override
    public void adjustClass(float x, float y, Team team, String soundPath, float speed, float maxHealth, float damage, int price) {
        super.adjustClass(x, y, team, soundPath, speed, maxHealth, damage, price);
        attackBehavior= new MeleeAttack(120,this,AbstractCharacter.swordHit1);
    }
}
