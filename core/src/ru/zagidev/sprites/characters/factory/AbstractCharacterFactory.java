package ru.zagidev.sprites.characters.factory;

import java.util.logging.Level;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.FisterCharacter;
import ru.zagidev.sprites.characters.KnightCharacter;
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
    public abstract FisterCharacter createFister(float x, float y);
    public abstract KnightCharacter createKnight(float x, float y);
}
