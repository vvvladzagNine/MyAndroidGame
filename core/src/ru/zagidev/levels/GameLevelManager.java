package ru.zagidev.levels;

import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ru.zagidev.sprites.characters.CharacterClass;
import ru.zagidev.world.BlockType;

public class GameLevelManager {

    public List<GameLevel> levels;
    Viewport viewport;

    public GameLevelManager(boolean internalSources,Viewport viewport) {
        this.viewport=viewport;
        if (internalSources) {
            initLevels();
        }
    }




    void initLevels() {
        levels = new ArrayList<>();
        addLevel1();

    }

    void addLevel1() {
        int ys = 20;
        int xs = 20;

        List<SavedCharacterObject> objects2 = new ArrayList<>();

        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,19,19));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,19,18));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,19,17));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,19,16));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,18,19));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,18,18));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,18,17));
        objects2.add(new SavedCharacterObject(CharacterClass.SHOTER,18,16));




        List<SavedWorldObject> objects = new ArrayList<>();
        for (int i = 0; i < ys; i++) {
            if (i != 6 && i != 2 && i != 3 && i != 14) {
                objects.add(new SavedWorldObject(BlockType.BRICK, i, 5, false));
            }

        }
        for (int i = 0; i < ys; i++) {
            if (i != 2 && i != 17)
                objects.add(new SavedWorldObject(BlockType.WOOD, i, 15, false));
        }

        for (int i = 0; i < xs; i++) {
            if (i != 8 && i != 9 && i != 4)
                objects.add(new SavedWorldObject(BlockType.BRICK, 10, i, false));
        }

        GameLevel gl =new GameLevel(viewport,20,20,objects);
        gl.fillLevel(objects2);

        levels.add(gl);
    }


}
