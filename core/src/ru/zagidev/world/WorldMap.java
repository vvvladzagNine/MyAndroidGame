package ru.zagidev.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import ru.zagidev.RunningGame;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.PigeonCharacter;
import ru.zagidev.sprites.effects.BloodExplosion;

import static ru.zagidev.world.blocks.BlockFactory.createPlaceable;

public class WorldMap extends Actor {

    public  float GAME_WORLD_WIDTH;
    public  float GAME_WORLD_HEIGHT;

    public final int CELL_WIDTH = 120;
    public final int CELL_HEIGHT = 120;

    public final int X_SIZE;
    public final int Y_SIZE;

    public final Cell[][] matrix;
    private List<Cell> cells = new ArrayList<>();

    private ShapeRenderer renderer;

    public ArrayList<BloodExplosion> explosions;




    public WorldMap(int x_s,int y_s) {
        X_SIZE=x_s;
        Y_SIZE=y_s;
        matrix = new Cell[Y_SIZE][X_SIZE];
        GAME_WORLD_WIDTH= CELL_WIDTH*X_SIZE;
        GAME_WORLD_HEIGHT= CELL_HEIGHT*Y_SIZE;


        explosions=new ArrayList<>();


        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {

                matrix[row][col] = new Cell(CELL_WIDTH * row, CELL_HEIGHT * col, CELL_WIDTH, CELL_HEIGHT);
                cells.add(matrix[row][col]);
            }
        }


        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 6 && i!=2 && i!=3 && i!=14)
                addPlaceable(BlockType.BRICK,9, i,false);
        }

        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 2 && i!=17)
                addPlaceable(BlockType.BRICK,15, i,false);
        }

        for (int i = 0; i < X_SIZE; i++) {
            if (i != 8 && i!=9 && i!=4)
                addPlaceable(BlockType.WOOD,i, 6,false);
        }

    }




    public void addPlaceable(BlockType name, int x, int y) {
        matrix[x][y].justPlace(createPlaceable(name, matrix[x][y]));
    }

    public void addPlaceable(BlockType name, int x, int y,boolean tf) {
        matrix[x][y].justPlace(createPlaceable(name, matrix[x][y]),tf);
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(255, 255, 255, 255);
        for (Cell c : cells) {
            if (!c.isPlaced()) c.drawCell(renderer);
        }
        renderer.setProjectionMatrix(RunningGame.camera.combined);
        renderer.end();
        batch.begin();
        for (Cell c : cells) {
            if (c.isPlaced()) c.drawPlaceable(batch);
        }
    }

    public void act(float delta) {

    }
}
