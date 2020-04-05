package ru.zagidev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.zagidev.GUI.GUI;
import ru.zagidev.inputHandling.SimpleDirectionGestureDetector;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.PigeonCharacter;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.sprites.objects.Block;
import ru.zagidev.sprites.objects.DotsPath;
import ru.zagidev.sprites.objects.NextCell;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Effects;
import ru.zagidev.world.WorldMap;
import ru.zagidev.world.blocks.BrickWall;
import ru.zagidev.world.blocks.Water;
import ru.zagidev.world.blocks.WoodWall;

import static ru.zagidev.world.WorldMap.CELL_HEIGHT;
import static ru.zagidev.world.WorldMap.CELL_WIDTH;

public class MyAndroidGame extends ApplicationAdapter {
    SpriteBatch batch;
    Vector3 touchPos;
    Block block;

    public static int VIEW_WIDTH;
    public static int VIEW_HEIGHT;
    public static final int X_SIZE = 40;
    public static final int Y_SIZE = 20;
    public static final Block[][] matrix = new Block[X_SIZE][Y_SIZE];
    //public DotsPath dots;
    private Stage stage;
    private StretchViewport viewport;
    public static OrthographicCamera camera;
    private NextCell nextCell;
    public static WorldMap worldMap;
    public static Effects effects;

    public static Characters characters;

    private GUI gui;

    AbstractCharacter character;
    AbstractCharacter teammate;
    AbstractCharacter npc;


    //cameraBEGIN
    private Float camXspeed = 0f;
    private Float camYspeed = 0f;

    private Float slowerX = camXspeed * 0.06f;
    private Float slowerY = camYspeed * 0.06f;

    private Float axeler = 45f;

    static Music music;

    //cameraEND


    public static Point getMatricsCords(float x, float y) {
        float xr = (x /CELL_WIDTH);
        float yr = (y /CELL_HEIGHT);
        return new Point((int) xr, (int) yr);
    }

    public static Point getRealCords(int x, int y) {
        int xr = (int) (x * CELL_WIDTH + CELL_WIDTH / 2);
        int yr = (int) (y * CELL_HEIGHT + CELL_HEIGHT / 2);
        return new Point(xr, yr);
    }

    @Override
    public void create() {




        VIEW_WIDTH = Gdx.graphics.getWidth();
        VIEW_HEIGHT = Gdx.graphics.getHeight();


        camera = new OrthographicCamera(VIEW_WIDTH, VIEW_HEIGHT);
        viewport = new StretchViewport(camera.viewportWidth, camera.viewportHeight, camera);

        gui = new GUI(camera);
        stage = new Stage(viewport);
        Gdx.input.setInputProcessor(stage);

        worldMap = new WorldMap();

        effects = new Effects();

        //dots=new DotsPath();
        touchPos = new Vector3();
        batch = new SpriteBatch();

        /**
         * character creating
         */

        characters = new Characters();
        character = new DuckCharacter(230, 760, Characters.team1);
        character.followTarget = false;


//        teammate = new DuckCharacter(250, 160, Characters.team1);
//        npc = new PigeonCharacter(250, 500, Characters.team2);
//        teammate.setNearestEnemyAsATarget();
//        npc.setNearestEnemyAsATarget();


        nextCell=new NextCell(character);
//

		//dots.path=character.waveAlgorithm.getDots(new Point(3,3),new Point(18,8));


        stage.addActor(worldMap);

        stage.addActor(character);

        //stage.addActor(dots);

        characters.fillStage(stage);

//        stage.addActor(npc);
//
//        stage.addActor(teammate);

        stage.addActor(effects);

        stage.addActor(nextCell);

        camera.zoom = 1.5f;

        Gdx.input.setInputProcessor(new SimpleDirectionGestureDetector(new SimpleDirectionGestureDetector.DirectionListener() {

            @Override
            public void zoom(float initialDist, float dist) {
                float otn = initialDist / dist;
                if (otn > 0.3f && otn < 2f)
                    camera.zoom = otn;
            }

            @Override
            public void swipe(float x, float y) {
                camera.translate(-x,y,0);
//                camYspeed += y * 0.015f;
            }

            @Override
            public void tap(float x, float y, int count) {
                gui.detectTap(x, y);
            }
        }));

        music = Gdx.audio.newMusic(Gdx.files.internal("data/music/undertale-megalovania-mp3cut.mp3"));
//        music.setLooping(true);
//        music.play();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.12f, 0.50f, 0.12f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        handleInput();
        float delta = Gdx.graphics.getDeltaTime();
        stage.act(delta);
        stage.draw();


        batch.setProjectionMatrix(camera.projection);
        gui.draw(batch);
        gui.update();

//		camera.position.set(character.sp.getX(),character.sp.getY(),0);
        moveCamera();

    }

    private void moveCamera() {
        camera.translate(camXspeed, camYspeed, 0);

        slowerX = camXspeed * 0.06f;
        slowerY = camYspeed * 0.06f;

        if (Math.abs(camXspeed) < 0.8f) {
            camXspeed = 0f;
        }
        if (Math.abs(camYspeed) < 0.8f) {
            camYspeed = 0f;
        }
        if (camXspeed > 0) {
            camXspeed -= Math.abs(slowerX);
        }
        if (camXspeed < 0) {
            camXspeed += Math.abs(slowerX);
        }
        if (camYspeed > 0) {
            camYspeed -= Math.abs(slowerY);
        }
        if (camYspeed < 0) {
            camYspeed += Math.abs(slowerY);
        }
    }


    private void handleInput() {

//		List<Vector3> touches = new ArrayList<>();
//		for()
//			Vector3 v = camera.unproject(new Vector3(Gdx.input.getX(1),Gdx.input.getY(1),0));
//			camera.position.set(v.x,v.y,0);
//		}


        if (Gdx.input.isTouched()) {
            Vector3 v = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));

            //setDots((int)v.x, (int)v.y);
            character.changePath((int) v.x, (int) v.y);
        }
    }

//	private void setDots(int x,int y) {
//		if(character.waveAlgorithm.computeDist(getMatricsCords((int)character.getCenterX(),(int)character.getCenterY()),getMatricsCords(x,y))!=-1)
//		dots.path=character.waveAlgorithm.getDots(getMatricsCords((int)character.getCenterX(),(int)character.getCenterY()),getMatricsCords(x,y));
//	}

    @Override
    public void dispose() {
        batch.dispose();
        BloodExplosion.t.dispose();
        for(AbstractCharacter c: Characters.team1.getMembers()){
            c.texture.dispose();
            c.deadTexture.dispose();
            c.sound.dispose();
            c.deadSound.dispose();
            for(Texture t :c.animationWalkTextures) t.dispose();
            for(Texture t :c.animationFightTextures) t.dispose();
        }
        for(AbstractCharacter c: Characters.team2.getMembers()){
            c.texture.dispose();
            c.deadTexture.dispose();
            c.sound.dispose();
            c.deadSound.dispose();
            for(Texture t :c.animationWalkTextures) t.dispose();
            for(Texture t :c.animationFightTextures) t.dispose();
        }
        BrickWall.texture.dispose();
        Water.texture.dispose();
        WoodWall.texture.dispose();
        music.dispose();



    }

}
