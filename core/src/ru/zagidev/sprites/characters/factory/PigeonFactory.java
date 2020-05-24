package ru.zagidev.sprites.characters.factory;

import ru.zagidev.RunningGame;
import ru.zagidev.levels.GameLevel;
import ru.zagidev.sprites.characters.FisterCharacter;
import ru.zagidev.sprites.characters.GunnerPigeonCharacter;
import ru.zagidev.sprites.characters.KnightCharacter;
import ru.zagidev.sprites.characters.PigeonCharacter;
import ru.zagidev.sprites.characters.PigeonKnightCharacter;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.world.Team;

public class PigeonFactory extends AbstractCharacterFactory {
    public PigeonFactory(Team t) {
        super(t);
    }

    public PigeonFactory() {
        super();
    }

    public PigeonFactory(Team team, GameLevel level) {
        super(team, level);
    }

    public PigeonFactory(GameLevel level) {
        super(level);
    }

    @Override
    public RangeCharacter createShooter(float x, float y) {
        return new GunnerPigeonCharacter(x,y,team, level);
    }

    @Override
    public FisterCharacter createFister(float x, float y) {
        return new PigeonCharacter(x,y,team, level);
    }

    @Override
    public KnightCharacter createKnight(float x, float y) {
        return new PigeonKnightCharacter(x,y,team,level);
    }
}
