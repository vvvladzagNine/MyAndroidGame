package ru.zagidev.sprites.characters;

import ru.zagidev.RunningGame;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Team;

public class CharacterFactory {
    public static AbstractCharacter createCharacter(float x, float y, CharacterClass c, Team team) {
        switch (c) {
            case FISTER:
                if (team == RunningGame.currentGameLevel.characters.team1)
                    return new DuckCharacter(x, y, RunningGame.currentGameLevel.characters.team1,RunningGame.currentGameLevel);
                else if (team == RunningGame.currentGameLevel.characters.team2)
                    return new PigeonCharacter(x, y, RunningGame.currentGameLevel.characters.team2,RunningGame.currentGameLevel);
            case SHOTER:
                if (team == RunningGame.currentGameLevel.characters.team1)
                    return new GunnerDuckCharacter(x, y, RunningGame.currentGameLevel.characters.team1,RunningGame.currentGameLevel);
                else if (team == RunningGame.currentGameLevel.characters.team2)
                    return new GunnerPigeonCharacter(x, y, RunningGame.currentGameLevel.characters.team2,RunningGame.currentGameLevel);
            case KNIGHT:
                if (team == RunningGame.currentGameLevel.characters.team1)
                    return new DuckKnightCharacter(x, y, RunningGame.currentGameLevel.characters.team1,RunningGame.currentGameLevel);
                else if (team == RunningGame.currentGameLevel.characters.team2)
                    return new PigeonKnightCharacter(x, y, RunningGame.currentGameLevel.characters.team2,RunningGame.currentGameLevel);
            default:
                return null;

        }
    }
}
