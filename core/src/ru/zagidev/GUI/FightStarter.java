package ru.zagidev.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;

import ru.zagidev.RunningGame;
import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.world.Characters;

public class FightStarter {
    private Sprite pressedSprite;
    private Sprite releasedSprite;
    public static Texture realeseTexture;
    public static Texture pressTexture;
    private Texture texture;
    public Sprite sprite;
    private float scale;
    private OrthographicCamera camera;
    private int size;

    private float xScreen;
    private float yScreen;


    public FightStarter(OrthographicCamera c) {
        camera = c;
        scale = 1;
        size = 300;
        xScreen = 15;
        yScreen = 20 + size * 0.5f;

        realeseTexture = new Texture("ui/swords.png");
        pressTexture = new Texture("ui/swordsP.png");
        releasedSprite = new Sprite(realeseTexture, size, size);
        pressedSprite = new Sprite(pressTexture, size, size);
        sprite = releasedSprite;
        sprite.setPosition(Gdx.graphics.getWidth() - size, Gdx.graphics.getHeight() - size);
        sprite.setScale(scale);

    }


    public void pressDetect(float x, float y) {
        if (
                x > xScreen
                        && x < (xScreen + size * 0.5f)
                        && y > yScreen
                        && y < (yScreen + size * 0.5f)
        ) {

            if (Shop.state == GuiState.FIGHTING) {
            } else {
                Shop.state = GuiState.FIGHTING;
                sprite=pressedSprite;
                RunningGame.currentGameLevel.characters.isFight = true;
                for (AbstractCharacter c : RunningGame.currentGameLevel.characters.team1.getMembers()) {
                    c.setNearestEnemyAsATarget();
                }
                for (AbstractCharacter c : RunningGame.currentGameLevel.characters.team2.getMembers()) {
                    c.setNearestEnemyAsATarget();
                }
            }

        }


    }


    public boolean isActive() {
        return sprite == pressedSprite;
    }

    public void draw(Batch batch) {
        if (Shop.state != GuiState.PLACING) {
            batch.begin();
            sprite.draw(batch);
            batch.end();
        }


    }

    public float getXX(float z) {
        return 0 - (300 - 300 * sprite.getScaleX()) / 2 - (Gdx.graphics.getWidth() * z * 0.5f) + 15 * z;
    }

    public void update() {
        float z = camera.zoom;
        sprite.setScale(z * 0.5f);
        sprite.setPosition(
                getXX(z),
                (Gdx.graphics.getHeight() * z * 0.5f) - size * 2 * sprite.getScaleY() - (size - size * sprite.getScaleY()) / 2 - 30 * z * 0.5f
        );

    }
}
