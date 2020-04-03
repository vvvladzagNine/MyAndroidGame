package ru.zagidev.world;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.world.blocks.BrickWall;
import ru.zagidev.world.blocks.Placeable;

import static ru.zagidev.world.blocks.BlockFactory.createPlaceable;

public class WorldMap extends Actor {

    public float GAME_WORLD_WIDTH;
    public float GAME_WORLD_HEIGHT;

    public static final int CELL_WIDTH = 120;
    public static final int CELL_HEIGHT = 120;

    public static final int X_SIZE = 50;
    public static final int Y_SIZE = 25;

    public Team team1;
    public Team team2;



    public static final Cell[][] matrix = new Cell[X_SIZE][Y_SIZE];
    private List<Cell> cells = new ArrayList<>();

    private ShapeRenderer renderer;


    public WorldMap() {
        team1=new Team();
        team2=new Team();

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
                addPlaceable("brick",0, i);
                addPlaceable("brick",X_SIZE-1, i);
        }

        for (int i = 0; i < X_SIZE; i++) {
                addPlaceable("brick",i, Y_SIZE-1);
                addPlaceable("brick",i, 0);
        }

        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 6)
                addPlaceable("brick",8, i);
        }
        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 9)
                addPlaceable("wood",10, i);
        }
        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 2)
                addPlaceable("brick",15, i);
        }
        for (int i = 0; i < Y_SIZE; i++) {
            if (i != 8)
                addPlaceable("wood",20, i);
        }

        for (int i = 0; i < X_SIZE; i++) {
            if (i != 2 && i != 7 && i != 22)
                addPlaceable("water",i, 12);
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
