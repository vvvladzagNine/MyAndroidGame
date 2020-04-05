package ru.zagidev.world;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.PigeonCharacter;
import ru.zagidev.sprites.effects.BloodExplosion;

import static ru.zagidev.world.blocks.BlockFactory.createPlaceable;

public class WorldMap extends Actor {

    public static float GAME_WORLD_WIDTH;
    public static float GAME_WORLD_HEIGHT;

    public static final int CELL_WIDTH = 120;
    public static final int CELL_HEIGHT = 120;

    public static final int X_SIZE = 20;
    public static final int Y_SIZE = 20;

    public static final Cell[][] matrix = new Cell[Y_SIZE][X_SIZE];
    private List<Cell> cells = new ArrayList<>();

    private ShapeRenderer renderer;

    public static ArrayList<BloodExplosion> explosions;

    static {
        explosions=new ArrayList<>();
    }


    public WorldMap() {



        GAME_WORLD_WIDTH= CELL_WIDTH*X_SIZE;
        GAME_WORLD_HEIGHT= CELL_HEIGHT*Y_SIZE;

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
                addPlaceable("brick",9, i);
        }

        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 2 && i!=17)
                addPlaceable("brick",15, i);
        }

        for (int i = 0; i < X_SIZE; i++) {
            if (i != 8 && i!=9 && i!=4)
                addPlaceable("wood",i, 6);
        }

    }




    public void addPlaceable(String name, int x, int y) {
        matrix[x][y].justPlace(createPlaceable(name, matrix[x][y]));
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        renderer.begin(ShapeRenderer.ShapeType.Line);
        renderer.setColor(255, 255, 255, 255);
        for (Cell c : cells) {
            if (!c.isPlaced()) c.drawCell(renderer);
        }
        renderer.setProjectionMatrix(MyAndroidGame.camera.combined);
        renderer.end();
        batch.begin();
        for (Cell c : cells) {
            if (c.isPlaced()) c.drawPlaceable(batch);
        }
    }

    public void act(float delta) {

    }
}
