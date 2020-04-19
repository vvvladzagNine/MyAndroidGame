package ru.zagidev;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import javax.swing.Renderer;

import ru.zagidev.GUI.GUI;
import ru.zagidev.GUI.GuiState;
import ru.zagidev.GUI.Map;
import ru.zagidev.GUI.Shop;
import ru.zagidev.inputHandling.SimpleDirectionGestureDetector;
import ru.zagidev.levels.GameLevel;
import ru.zagidev.levels.GameLevelManager;
import ru.zagidev.save.GreenZone;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.CharacterFactory;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.sprites.characters.bullets.Bullet;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.sprites.objects.Block;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Effects;
import ru.zagidev.world.WorldMap;
import ru.zagidev.world.blocks.BrickWall;
import ru.zagidev.world.blocks.Water;
import ru.zagidev.world.blocks.WoodWall;



public class RunningGame implements Screen {



    public static GameLevel currentGameLevel;

    SpriteBatch batch;
    Vector3 touchPos;
    Block block;

    public static int VIEW_WIDTH;
    public static int VIEW_HEIGHT;
    public static final int X_SIZE = 40;
    public static final int Y_SIZE = 20;
    public static final Block[][] matrix = new Block[X_SIZE][Y_SIZE];
    private StretchViewport viewport;
    public static OrthographicCamera camera;


    public static GameLevelManager gameLevelManager;


    private GUI gui;


    private Float camXspeed = 0f;
    private Float camYspeed = 0f;

    private Float slowerX = camXspeed * 0.06f;
    private Float slowerY = camYspeed * 0.06f;

    private Float axeler = 45f;

    static Music music;

    public static Sound eror;

    public static Point getMatricsCords(float x, float y) {
        float xr = (x /currentGameLevel.worldMap.CELL_WIDTH);
        float yr = (y /currentGameLevel.worldMap.CELL_HEIGHT);
        return new Point((int) xr, (int) yr);
    }

    public static Point getRealCords(int x, int y) {
        int xr = (int) (x * currentGameLevel.worldMap.CELL_WIDTH + currentGameLevel.worldMap.CELL_WIDTH / 2);
        int yr = (int) (y * currentGameLevel.worldMap.CELL_HEIGHT + currentGameLevel.worldMap.CELL_HEIGHT / 2);
        return new Point(xr, yr);
    }

    public static Point getMatricsCords(float x, float y,GameLevel level) {
        float xr = (x /level.worldMap.CELL_WIDTH);
        float yr = (y /level.worldMap.CELL_HEIGHT);
        return new Point((int) xr, (int) yr);
    }

    public static Point getRealCords(int x, int y,GameLevel level) {
        int xr = (int) (x * level.worldMap.CELL_WIDTH + level.worldMap.CELL_WIDTH / 2);
        int yr = (int) (y * level.worldMap.CELL_HEIGHT + level.worldMap.CELL_HEIGHT / 2);
        return new Point(xr, yr);
    }





    public RunningGame() {



        eror= Gdx.audio.newSound(Gdx.files.internal("data/sounds/engineer_no01_1.mp3"));

        VIEW_WIDTH = Gdx.graphics.getWidth();
        VIEW_HEIGHT = Gdx.graphics.getHeight();

        camera = new OrthographicCamera(VIEW_WIDTH, VIEW_HEIGHT);
        viewport = new StretchViewport(camera.viewportWidth, camera.viewportHeight, camera);

        gameLevelManager=new GameLevelManager(false,viewport);

        batch = new SpriteBatch();

        levelInit(0);

    }

    private void levelInit(int i) {


        gameLevelManager.reload();
        currentGameLevel=gameLevelManager.levels.get(i);

        gui = new GUI(camera);
        Shop.money=currentGameLevel.money;

        Gdx.input.setInputProcessor(currentGameLevel.stage);


        currentGameLevel.effects = new Effects();

        /**
         * character creating
         */

        currentGameLevel.stage.addActor(currentGameLevel.worldMap);

        currentGameLevel.characters.fillStage(currentGameLevel.stage);

        currentGameLevel.stage.addActor(currentGameLevel.effects);

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
            }

            @Override
            public void tap(float x, float y, int count) {
                if(count==5) {
                    MyAndroidGame.currentScreen=MyAndroidGame.menueScreen;MyAndroidGame.currentScreen.show();}


                if(x<Gdx.graphics.getWidth()-200)
                    if(Shop.state== GuiState.PLACING){
                        Vector3 v = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                        Point point = getMatricsCords(v.x,v.y);
                        if(v.x<currentGameLevel.worldMap.GAME_WORLD_WIDTH-50 && v.x>50 && v.y<currentGameLevel.worldMap.GAME_WORLD_HEIGHT-50 && v.y>50
                            && (isInGreen(point)) && ((Shop.money-Shop.currentPrice) >= 0)
                        ){
                            AbstractCharacter c = CharacterFactory.createCharacter(v.x,v.y,Shop.currentClass,Shop.currentTeam);
                            Shop.money-=Shop.currentPrice;
                            Shop.cashSound.play();
                            currentGameLevel.stage.addActor(c);
                        }
                        else {
                            eror.play();
                        }

                    }
                gui.detectGuiTap(x, y);
            }
        }));

        music = Gdx.audio.newMusic(Gdx.files.internal("data/music/undertale-megalovania-mp3cut.mp3"));
    }

    public boolean isInGreen(Point p){
        boolean inGreen=false;
        for(GreenZone g:currentGameLevel.greenZones){
            inGreen= inGreen || (g.x==p.x && g.y==p.y);
        }
        return inGreen;
    }


    @Override
    public void resize(int width, int height) {
        currentGameLevel.stage.getViewport().update(width, height, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.09f, 0.40f, 0.09f, 0.5f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        currentGameLevel.stage.act(delta);


        currentGameLevel.stage.draw();


        batch.setProjectionMatrix(camera.projection);
        gui.draw(batch);
        gui.update();


        boolean stay=false;
        for(AbstractCharacter c:currentGameLevel.characters.team2.getMembers()){
            stay=stay || c.isAlive();
        }
        if(!stay){
            Shop.state=GuiState.IN_GAME;
            levelInit(gameLevelManager.levels.indexOf(currentGameLevel)+1);
        }

        if(Shop.state==GuiState.FIGHTING){
            boolean restart=false;
            for(AbstractCharacter c:currentGameLevel.characters.team1.getMembers()){
                restart=restart || c.isAlive();
            }
            if(!restart){
                Shop.state=GuiState.IN_GAME;
                levelInit(gameLevelManager.levels.indexOf(currentGameLevel));
            }
        }



    }

    @Override
    public void dispose() {
        batch.dispose();
        BloodExplosion.t.dispose();

        currentGameLevel.dispose();

        BrickWall.texture.dispose();
        Water.texture.dispose();
        WoodWall.texture.dispose();
        music.dispose();
        Shop.cashSound.dispose();
        Shop.noCashSound.dispose();
        Shop.realesedTexture.dispose();
        Shop.pressedTexture.dispose();
        Shop.t1.dispose();
        Shop.t2.dispose();
        Shop.t3.dispose();
        Shop.str1.dispose();
        Shop.str2.dispose();
        Map.realeseTexture.dispose();
        Map.pressTexture.dispose();
        eror.dispose();
        Bullet.texture.dispose();
        Bullet.hitmarker.dispose();
        for(GameLevel g:gameLevelManager.levels){
            g.dispose();
        }
    }

    @Override
    public void show() {
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
                if(x<Gdx.graphics.getWidth()-200)
                    if(Shop.state==GuiState.PLACING){
                        Vector3 v = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                        if(v.x<currentGameLevel.worldMap.GAME_WORLD_WIDTH-50 && v.x>50 && v.y<currentGameLevel.worldMap.GAME_WORLD_HEIGHT-50 && v.y>50){
                            AbstractCharacter c =CharacterFactory.createCharacter(v.x,v.y,Shop.currentClass,Shop.currentTeam);
                            c.setNearestEnemyAsATarget();
                            Shop.money-=50;
                            Shop.cashSound.play();
                            currentGameLevel.stage.addActor(c);
                        }
                        else {
                            eror.play();
                        }

                    }
                gui.detectGuiTap(x, y);
            }
        }));
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
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
                if(x<Gdx.graphics.getWidth()-200)
                    if(Shop.state==GuiState.PLACING){
                        Vector3 v = camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
                        if(v.x<currentGameLevel.worldMap.GAME_WORLD_WIDTH-50 && v.x>50 && v.y<currentGameLevel.worldMap.GAME_WORLD_HEIGHT-50 && v.y>50){
                            AbstractCharacter c =CharacterFactory.createCharacter(v.x,v.y,Shop.currentClass,Shop.currentTeam);
                            c.setNearestEnemyAsATarget();
                            Shop.money-=50;
                            Shop.cashSound.play();
                            currentGameLevel.stage.addActor(c);
                        }
                        else {
                            eror.play();
                        }

                    }
                gui.detectGuiTap(x, y);
            }
        }));
    }

    @Override
    public void hide() {

    }

}
