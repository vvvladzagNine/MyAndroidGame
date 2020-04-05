package ru.zagidev.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.PigeonCharacter;

public class Characters {

    public static Team team1;
    public static Team team2;

    public Characters() {
        team1=new Team();
        team2=new Team();

        for(int i=0;i<15;i++){

            DuckCharacter c2 =new DuckCharacter((float)Math.random()*(WorldMap.GAME_WORLD_WIDTH-200)/2,
                    (float)Math.random()*(WorldMap.GAME_WORLD_HEIGHT-200),team1);

            PigeonCharacter c =new PigeonCharacter((WorldMap.GAME_WORLD_WIDTH)/2+(float)Math.random()*(WorldMap.GAME_WORLD_WIDTH-100)/2,
                    (float)Math.random()*(WorldMap.GAME_WORLD_HEIGHT-200),team2);
        }
        for(int i=0;i<10;i++){
            team2.getMembers().get(i).setNearestEnemyAsATarget();
            team1.getMembers().get(i).setNearestEnemyAsATarget();
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
