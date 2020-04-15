package ru.zagidev.sprites.characters;

import ru.zagidev.world.Characters;
import ru.zagidev.world.Team;

public class CharacterFactory {
    public static AbstractCharacter createCharacter(float x, float y, CharacterClass c, Team team) {
        switch (c) {
            case FISTER:
                if (team == Characters.team1) return new DuckCharacter(x, y, Characters.team1);
                else if (team == Characters.team2) return new PigeonCharacter(x, y, Characters.team2);
            case SHOTER:
                if (team == Characters.team1) return new GunnerDuckCharacter(x, y, Characters.team1);
                else if (team == Characters.team2) return new GunnerPigeonCharacter(x, y, Characters.team2);
            default:
                return null;

        }
    }
}
