package ru.zagidev.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import java.util.List;

import ru.zagidev.levels.GameLevel;
import ru.zagidev.levels.SavedCharacterObject;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.CharacterFactory;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.GunnerDuckCharacter;
import ru.zagidev.sprites.characters.GunnerPigeonCharacter;
import ru.zagidev.sprites.characters.PigeonCharacter;

public class Characters {
    public boolean isFight = false;
    public Team team1;
    public Team team2;

    GameLevel level;


    public Characters() {
    }






    public void init(GameLevel level,List<SavedCharacterObject> characterList ){
        this.level=level;
        team1= new Team();
        team2= new Team();
        for(SavedCharacterObject s:characterList){
            if(!s.isFirstTeam){
                switch (s.c){
                    case FISTER: new PigeonCharacter(s.x*WorldMap.CELL_WIDTH,s.y*WorldMap.CELL_HEIGHT,team2,level);
                    case SHOTER: new GunnerPigeonCharacter(s.x*WorldMap.CELL_WIDTH,s.y*WorldMap.CELL_HEIGHT,team2,level);
                }

            }
        }
    }


    public void fillStage(Stage stage){
        for(AbstractCharacter c:team2.getMembers()){
            stage.addActor(c);
        }
        for(AbstractCharacter c:team1.getMembers()){
            stage.addActor(c);
        }
    }
}
