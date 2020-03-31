package ru.zagidev.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ru.zagidev.world.Cell;
import ru.zagidev.MyAndroidGame;

public class WorldMap extends Actor {

    final  float GAME_WORLD_WIDTH = MyAndroidGame.WIDTH;
    final  float GAME_WORLD_HEIGHT = MyAndroidGame.HEIGHT;

    public  final float CELL_WIDTH = GAME_WORLD_WIDTH/ MyAndroidGame.X_SIZE;
    public  final float CELL_HEIGHT = GAME_WORLD_HEIGHT/ MyAndroidGame.Y_SIZE;

    public static final int X_SIZE=50;
    public static final int Y_SIZE=25;

    public static final Cell[][] matrix = new Cell[X_SIZE][Y_SIZE];
    private  List<Cell> cells = new ArrayList<>();

    private ShapeRenderer renderer;


    public WorldMap() {
        renderer=new ShapeRenderer();
        renderer.setAutoShapeType(true);
        for (int row=0; row < matrix.length; row++)
        {
            for (int col=0; col < matrix[row].length; col++)
            {

                matrix[row][col]=new Cell(CELL_WIDTH*row,CELL_HEIGHT*col,CELL_WIDTH,CELL_HEIGHT);
                cells.add(matrix[row][col]);
            }
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(255,255,255,255);
        for(Cell c:cells){
            c.draw(renderer);
        }
        renderer.setProjectionMatrix(MyAndroidGame.camera.combined);
        renderer.end();
        batch.begin();
    }

    public void act(float delta) {

    }
}
