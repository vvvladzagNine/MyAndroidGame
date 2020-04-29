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
import ru.zagidev.sprites.characters.factory.AbstractCharacterFactory;

public class Characters {
    public boolean isFight = false;
    public Team team1;
    public Team team2;

    GameLevel level;

    AbstractCharacterFactory factory;


    public Characters() {
    }

    public Characters(AbstractCharacterFactory f) {
        factory=f;
    }






    public void init(GameLevel level,List<SavedCharacterObject> characterList,AbstractCharacterFactory f){
        if(f!=null)factory=f;
        this.level=level;
        team1= new Team();
        team2= new Team();
        factory.setTeam(team2);
        for(SavedCharacterObject s:characterList){
            if(!s.isFirstTeam){
                switch (s.c){
                    case FISTER: factory.createFister(s.x*WorldMap.CELL_WIDTH,s.y*WorldMap.CELL_HEIGHT);break;
                    case SHOTER: factory.createShooter(s.x*WorldMap.CELL_WIDTH,s.y*WorldMap.CELL_HEIGHT);break;
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
