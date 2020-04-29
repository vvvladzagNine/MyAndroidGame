package ru.zagidev.levels;

import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import ru.zagidev.save.GreenZone;
import ru.zagidev.save.JsonBlock;
import ru.zagidev.save.JsonChar;
import ru.zagidev.save.Loader;
import ru.zagidev.save.SavedGameLevel;
import ru.zagidev.sprites.characters.CharacterClass;
import ru.zagidev.world.BlockType;

public class GameLevelManager {

    public List<GameLevel> levels;
    Viewport viewport;
    public static Loader loader;
    public boolean internalSources;
    public GameLevelManager(boolean internalSources,Viewport viewport) {
        loader=new Loader();
        this.viewport=viewport;
        this.internalSources =internalSources;
        reload();

    }

    public void reload(){
        levels = new ArrayList<>();
        if (internalSources) {
            initLevels();
        }else {
            loadLevels();
        }
    }




    void initLevels() {
        addLevel1();
    }


    void loadLevels() {

        loadLevel("26");
        loadLevel("146");
        loadLevel("429");
        loadLevel("628");
        loadLevel("675");


    }



    CharacterClass getCharClass(String s){
        switch (s){
            case "FISTER":return CharacterClass.FISTER;
            case "SHOTER":return CharacterClass.SHOTER;
            default:return CharacterClass.FISTER;
        }
    }

    BlockType getBlockType(String s){
        switch (s){
            case "BRICK":return BlockType.BRICK;
            case "WATER":return BlockType.WATER;
            case "WOOD":return BlockType.WOOD;
            default:return BlockType.WOOD;
        }
    }

    boolean getBlockShooted(String s){
        switch (s){
            case "BRICK":return false;
            case "WATER":return true;
            case "WOOD":return false;
            default:return false;
        }
    }



    void loadLevel(String fileName) {

        SavedGameLevel s = loader.load(fileName);

        int ys = s.ySize;
        int xs = s.xSize;

        List<SavedCharacterObject> objects2 = new ArrayList<>();
        for(JsonChar jsonChar:s.chars)
            objects2.add(new SavedCharacterObject(getCharClass(jsonChar.type),jsonChar.x,jsonChar.y));

        List<SavedWorldObject> objects = new ArrayList<>();
        for(JsonBlock jsonBlock:s.blocks)
            objects.add(new SavedWorldObject(getBlockType(jsonBlock.type),jsonBlock.x,jsonBlock.y, getBlockShooted(jsonBlock.type)));





        GameLevel gl =new GameLevel(viewport,xs,ys,s.money,s.greens,objects);
        gl.fillLevel(objects2);

        levels.add(gl);
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

        GameLevel gl =new GameLevel(viewport,xs,ys,500 ,new ArrayList<GreenZone>(),objects);
        gl.fillLevel(objects2);

        levels.add(gl);
    }


}
