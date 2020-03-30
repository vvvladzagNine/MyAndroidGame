package ru.zagidev.sprites.objects;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;


import ru.zagidev.sprites.characters.DuckCharacter;

import static ru.zagidev.MyAndroidGame.HEIGHT;
import static ru.zagidev.MyAndroidGame.VIEW_HEIGHT;
import static ru.zagidev.MyAndroidGame.VIEW_WIDTH;
import static ru.zagidev.MyAndroidGame.WIDTH;

public class VectorLine extends Actor {

    DuckCharacter d;
    int x1,y1,x2,y2;
    ShapeRenderer sr;



    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        sr.begin();
        sr.line(x1,y1,x2,y2);
        sr.end();
        batch.begin();

    }

    @Override
    public void act(float delta) {
        try{
            x1=(int)d.sp.getX()*WIDTH/VIEW_WIDTH;y1=(int)d.sp.getY();x2=d.x2;y2=d.y2;
        }catch (Exception e){

        }

    }

    public VectorLine(DuckCharacter d) {
        sr=new ShapeRenderer();
        sr.setAutoShapeType(true);
        this.d = d;
    }
}
