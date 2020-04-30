package ru.zagidev.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import ru.zagidev.save.GreenZone;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.sprites.characters.factory.DuckFactory;

import ru.zagidev.world.Characters;
import ru.zagidev.world.Effects;
import ru.zagidev.world.WorldMap;


public class GameLevel {

    public WorldMap worldMap;
    public Characters characters;
    public Stage stage;
    public Effects effects;
    private Viewport viewport;
    public List<GreenZone> greenZones;
    public int money;

    public GameLevel(
            Viewport vp,
            int xSize,
            int ySize,
            int money,
            List<GreenZone> greenZones,
            List<SavedWorldObject> objectList) {
        this.greenZones=greenZones;
        this.money=money;
        viewport=vp;
        worldMap=new WorldMap(xSize,ySize,objectList,greenZones);

    }

     void fillLevel(List<SavedCharacterObject> characterList){
         characters=new Characters();
         characters.init(this,characterList,new DuckFactory(this));
         stage=new Stage(viewport);
     }

    public void dispose() {
        for(AbstractCharacter c: characters.team1.getMembers()){
            c.texture.dispose();
            c.deadTexture.dispose();
            c.sound.dispose();
            c.deadSound.dispose();
            c.deadTexture2.dispose();
            for(Texture t :c.animationWalkRightTextures) t.dispose();
            for(Texture t :c.animationWalkDownTextures) t.dispose();
            for(Texture t :c.animationWalkUpTextures) t.dispose();
            for(Texture t :c.animationFightTextures) t.dispose();
            if(c instanceof RangeCharacter){
                ((RangeCharacter) c).shotSound.dispose();
            }
        }
        for(AbstractCharacter c: characters.team2.getMembers()){
            c.texture.dispose();
            c.deadTexture.dispose();
            c.sound.dispose();
            c.deadSound.dispose();
            c.deadTexture2.dispose();
            for(Texture t :c.animationWalkRightTextures) t.dispose();
            for(Texture t :c.animationWalkDownTextures) t.dispose();
            for(Texture t :c.animationWalkUpTextures) t.dispose();
            for(Texture t :c.animationFightTextures) t.dispose();
            if(c instanceof RangeCharacter){
                ((RangeCharacter) c).shotSound.dispose();
            }
        }
    }
}
