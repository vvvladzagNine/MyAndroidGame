package ru.zagidev.levels;

import ru.zagidev.sprites.characters.CharacterClass;

public class SavedCharacterObject {
    boolean isFirstTeam=false;
    CharacterClass c;
    int x;
    int y;

    public SavedCharacterObject(CharacterClass c, int x, int y) {
        this.c = c;
        this.x = x;
        this.y = y;
    }

    public SavedCharacterObject(boolean isFirstTeam, CharacterClass c, int x, int y) {
        this.isFirstTeam = isFirstTeam;
        this.c = c;
        this.x = x;
        this.y = y;
    }
}
