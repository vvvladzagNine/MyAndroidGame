package ru.zagidev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.zagidev.GUI.GUI;
import ru.zagidev.GUI.GuiState;
import ru.zagidev.GUI.Map;
import ru.zagidev.GUI.Shop;
import ru.zagidev.inputHandling.SimpleDirectionGestureDetector;
import ru.zagidev.menu.TestScreen;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.CharacterFactory;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.RangeCharacter;
import ru.zagidev.sprites.characters.bullets.Bullet;
import ru.zagidev.sprites.effects.BloodExplosion;
import ru.zagidev.sprites.objects.Block;
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

    public static Screen currentScreen;


    public static Screen gameScreen;
    public static Screen menueScreen;


    @Override
    public void create() {
        gameScreen=new RunningGame();

        menueScreen=new TestScreen();
        currentScreen=gameScreen;
    }

    @Override
    public void resize(int width, int height) {
        currentScreen.resize(width,height);
    }

    @Override
    public void render() {
        currentScreen.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        currentScreen.dispose();
    }






}
