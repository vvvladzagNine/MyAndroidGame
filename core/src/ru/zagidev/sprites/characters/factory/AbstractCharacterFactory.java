package ru.zagidev.sprites.characters.factory;

import java.util.logging.Level;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.MeleeCharacter;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.world.Team;

public abstract class AbstractCharacterFactory {

    Team team;
    GameLevel level;

    public AbstractCharacterFactory(Team t) {
        team=t;
    }
    public AbstractCharacterFactory(GameLevel level) {
        this.level=level;
    }

    public AbstractCharacterFactory(Team team, GameLevel level) {
        this.team = team;
        this.level = level;
    }

    public AbstractCharacterFactory() {
    }

    public void setTeam(Team t){
        team=t;
    }

    public void setLevel(GameLevel level) {
        this.level = level;
    }

    public abstract RangeCharacter createShooter(float x, float y);
    public abstract MeleeCharacter createFister(float x,float y);
}
