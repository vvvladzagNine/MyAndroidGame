package ru.zagidev.sprites.characters.factory;

import ru.zagidev.RunningGame;
import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.DuckKnightCharacter;
import ru.zagidev.sprites.characters.FisterCharacter;
import ru.zagidev.sprites.characters.GunnerDuckCharacter;
import ru.zagidev.sprites.characters.KnightCharacter;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.world.Team;

public class DuckFactory extends AbstractCharacterFactory {
    public DuckFactory(Team t) {
        super(t);
    }

    public DuckFactory(GameLevel level) {
        super(level);
    }

    public DuckFactory(Team team, GameLevel level) {
        super(team, level);
    }

    public DuckFactory() {
        super();
    }

    @Override
    public RangeCharacter createShooter(float x,float y) {
        return new GunnerDuckCharacter(x,y,team, level);
    }

    @Override
    public FisterCharacter createFister(float x, float y) {
        return new DuckCharacter(x,y,team, level);
    }

    @Override
    public KnightCharacter createKnight(float x, float y) {
        return new DuckKnightCharacter(x,y,team,level);
    }
}
