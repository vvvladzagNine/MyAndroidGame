package ru.zagidev.levels;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.List;

import ru.zagidev.GUI.Map;
import ru.zagidev.GUI.Shop;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.sprites.characters.bullets.Bullet;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Effects;
import ru.zagidev.world.WorldMap;
import ru.zagidev.world.blocks.BrickWall;
import ru.zagidev.world.blocks.Water;
import ru.zagidev.world.blocks.WoodWall;

public class GameLevel {

    public WorldMap worldMap;
    public Characters characters;
    public Stage stage;
    public Effects effects;
    private Viewport viewport;

    public GameLevel(
            Viewport vp,
            int xSize,
            int ySize,
            List<SavedWorldObject> objectList) {
        viewport=vp;
        worldMap=new WorldMap(xSize,ySize,objectList);
    }

     void fillLevel(List<SavedCharacterObject> characterList){
         characters=new Characters();
         characters.init(this,characterList);
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
