package ru.zagidev.GUI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

import ru.zagidev.RunningGame;
import ru.zagidev.sprites.characters.CharacterClass;
import ru.zagidev.world.Characters;
import ru.zagidev.world.Team;

public class Shop {
    private Sprite pressedSprite;
    private Sprite releasedSprite;
    public Sprite sprite;
    private float scale;
    private OrthographicCamera camera;
    private int size;
    private ShapeRenderer renderer;
    public static Team currentTeam = RunningGame.currentGameLevel.characters.team1;
    private float iconInterval;
    public static int money=100000;

    public ArrayList<CharacterShopIcon> arrayList;

    public float iconWidth=500f;
    public float iconHeight=600f;

    public CharacterShopIcon icon1;
    public float incon1X;
    public float inconY;
    public CharacterShopIcon icon2;
    public float incon2X;
    public CharacterShopIcon icon3;
    public float incon3X;
    public CharacterShopIcon icon4;
    public float incon4X;

    public Sprite strelochka;
    public Sprite strelochkaLeft;

    public static GuiState state=GuiState.IN_GAME;
    public static CharacterClass currentClass=CharacterClass.FISTER;

    public static Texture realesedTexture;
    public static Texture pressedTexture;

    public static Texture t1;
    public static Texture t2;
    public static Texture t3;
    public static Texture t4;

    public static Texture str1;
    public static Texture str2;

    public static Sound cashSound;
    public static Sound noCashSound;

    BitmapFont font;
    float textX;
    float textY;
    float textSize=10;
    float TEXT_SIZE=10;


    private float xScreen;
    private float yScreen;

    public Shop(OrthographicCamera c) {
        cashSound = Gdx.audio.newSound(Gdx.files.internal("data/cash.mp3"));
        noCashSound = Gdx.audio.newSound(Gdx.files.internal("data/wheres-my-money-bitch-.mp3"));
        font = new BitmapFont();
        camera = c;
        scale = 1;
        size = 300;
        font.getData().setScale(textSize);


        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);


        xScreen = Gdx.graphics.getWidth() - size * 0.5f;
        yScreen = 20 + size * 0.5f;

        incon1X= iconWidth * 0.5f +iconInterval;
        incon2X= iconWidth + iconInterval * 2;
        incon3X= Gdx.graphics.getWidth() -iconWidth *2* 0.5f -iconInterval;
        incon4X= Gdx.graphics.getWidth() -iconWidth * 0.5f * 3-iconInterval * 2;
        inconY=Gdx.graphics.getHeight() - iconHeight*0.5f-10;

        realesedTexture = new Texture("ui/shop-icon.png");
        pressedTexture = new Texture("ui/shop-iconPressed.png");
        pressedSprite = new Sprite(pressedTexture, size, size);
        releasedSprite = new Sprite(realesedTexture, size, size);
        sprite = releasedSprite;
        sprite.setPosition(Gdx.graphics.getWidth() - size, Gdx.graphics.getHeight() - size);
        sprite.setScale(scale);

        arrayList = new ArrayList<>();
        t1 = new Texture("ui/utyaIcon.png");
        t3 = new Texture("ui/utyaIcongun.png");
        t2 = new Texture("ui/ugolubIcon.png");
        t4 = new Texture("ui/ugolubgunicon.png");

        arrayList.add(new CharacterShopIcon(t1,t1, CharacterClass.FISTER,RunningGame.currentGameLevel.characters.team1));
        arrayList.add(new CharacterShopIcon(t3,t3,CharacterClass.SHOTER,RunningGame.currentGameLevel.characters.team1));
        arrayList.add(new CharacterShopIcon(t2,t2,CharacterClass.FISTER,RunningGame.currentGameLevel.characters.team2));
        arrayList.add(new CharacterShopIcon(t4,t4,CharacterClass.SHOTER,RunningGame.currentGameLevel.characters.team2));

        icon1 = arrayList.get(0);
        icon2 = arrayList.get(1);
        icon3 = arrayList.get(2);
        icon4 = arrayList.get(3);

        str1=new Texture("ui/strelochka.png");
        str2=new Texture("ui/strelochkaLeft.png");

        strelochka=new Sprite(str1);
        strelochkaLeft=new Sprite(str2);

        iconInterval = 40;

    }

    public boolean isActive() {
        return sprite == pressedSprite;
    }

    public void pressDetect(float x, float y) {
        if (
                x > xScreen
                        && x < (xScreen + size * 0.5f)
                        && y > yScreen
                        && y < (yScreen + size * 0.5f)
        )
            if (state==GuiState.IN_GAME) {
                sprite = pressedSprite;
                state=GuiState.SHOP;
            } else {
                if(state==GuiState.SHOP)
                sprite = releasedSprite;
                state=GuiState.IN_GAME;
            }

        if(state==GuiState.SHOP){
            if(x > incon1X && x < (incon1X + iconWidth * 0.5f) && y > inconY && y < (inconY + iconHeight * 0.5f))
                if(money>=50)
                icon1.press();
                else noCashSound.play();
            if(x > incon2X && x < (incon2X + iconWidth * 0.5f) && y > inconY && y < (inconY + iconHeight * 0.5f))
                if(money>=50)
                icon2.press();
                else noCashSound.play();
            if(x > incon3X && x < (incon3X + iconWidth * 0.5f) && y > inconY && y < (inconY + iconHeight * 0.5f))
                if(money>=50)
                icon3.press();
                else noCashSound.play();
            if(x > incon4X && x < (incon4X + iconWidth * 0.5f) && y > inconY && y < (inconY + iconHeight * 0.5f))
                if(money>=50)
                icon4.press();
                else noCashSound.play();

        }
    }

    public void draw(Batch batch) {
        batch.begin();
        if(state!=GuiState.PLACING && state!=GuiState.FIGHTING){
            sprite.draw(batch);
        }

        if (state==GuiState.SHOP) {
            icon1.sp.draw(batch);
            icon2.sp.draw(batch);
            icon3.sp.draw(batch);
            icon4.sp.draw(batch);
            strelochka.draw(batch);
            strelochkaLeft.draw(batch);

        }
        if (state==GuiState.SHOP || state==GuiState.PLACING) {
            font.draw(batch,money+" $",textX,textY);
        }



        batch.end();
    }


    public float getXX(float z,float a,float b){
        return 0 - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2 - (Gdx.graphics.getWidth() * z * 0.5f) + icon1.sp.getWidth() * icon1.sp.getScaleX()*a +iconInterval *b * z;
    }

    public float getXXX(float z,float a,float b){
        return 0 - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2 + (Gdx.graphics.getWidth() * z * 0.5f) - icon1.sp.getWidth() * icon1.sp.getScaleX()*a -iconInterval *(b-1) * z;
    }

    public void update() {
        float z = camera.zoom;




        sprite.setScale(z * 0.5f);
        sprite.setPosition(
                (Gdx.graphics.getWidth() * z * 0.5f) - size * sprite.getScaleX() - (size - size * sprite.getScaleX()) / 2 - 30 * z * 0.5f,
                (Gdx.graphics.getHeight() * z * 0.5f) - size * 2 * sprite.getScaleY() - (size - size * sprite.getScaleY()) / 2 - 60 * z * 0.5f
        );

        float y = (Gdx.graphics.getHeight() * z * 0.5f) - (Gdx.graphics.getHeight() * 1.9f) * icon1.sp.getScaleY() - (iconHeight - iconHeight * icon1.sp.getScaleY()) / 2 - 60 * z * 0.5f;
        float yS = (Gdx.graphics.getHeight() * z * 0.5f) - (Gdx.graphics.getHeight() * 1.9f) * icon1.sp.getScaleY() - (iconWidth - iconWidth * icon1.sp.getScaleY()) / 2 - 60 * z * 0.5f;


        textX=(Gdx.graphics.getWidth() * z * 0.25f);
        textY=(Gdx.graphics.getWidth() * z * 0.25f);

        textSize=TEXT_SIZE*z*z*0.5f;


        strelochkaLeft.setScale(z*0.5f);
        strelochkaLeft.setPosition(
                getXX(z,0,0),
//                0 - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2 - (Gdx.graphics.getWidth() * z * 0.5f),
                //(Gdx.graphics.getWidth() * z * 0.25f) - 500 * strelochkaLeft.getScaleX()/2 -iconInterval * 4.5f * z - strelochkaLeft.getWidth() * strelochkaLeft.getScaleX()*4 - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2,
                yS);

        icon1.sp.setScale(z * 0.5f);
        icon1.sp.setPosition(
                getXX(z,1,1),
//                0 - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2 - (Gdx.graphics.getWidth() * z * 0.5f) + icon1.sp.getWidth() * icon1.sp.getScaleX()*1 +iconInterval * 1f * z,
                //(Gdx.graphics.getWidth() * z * 0.25f) - iconWidth * icon1.sp.getScaleX()/2 -iconInterval * 3.5f * z - icon1.sp.getWidth() * icon1.sp.getScaleX() * 3 - (iconWidth - iconWidth * icon1.sp.getScaleX()) / 2,
                y);

        icon2.sp.setScale(z * 0.5f);
        icon2.sp.setPosition(
                getXX(z,2,2),
//                (Gdx.graphics.getWidth() * z * 0.25f) - iconWidth * icon1.sp.getScaleX()/2 -iconInterval * 2.5f * z - icon2.sp.getWidth() * icon2.sp.getScaleX() * 2 - (iconWidth - iconWidth * icon2.sp.getScaleX()) / 2,
                y);

        icon4.sp.setScale(z * 0.5f);
        icon4.sp.setPosition(
                getXXX(z,3,3),
//                (Gdx.graphics.getWidth() * z * 0.25f) - iconWidth * icon1.sp.getScaleX()/2 -iconInterval * 1.5f * z - icon3.sp.getWidth() * icon3.sp.getScaleX() - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2,
                y);

        icon3.sp.setScale(z * 0.5f);
        icon3.sp.setPosition(
                getXXX(z,2,2),
//                (Gdx.graphics.getWidth() * z * 0.25f) - iconWidth * icon1.sp.getScaleX()/2- iconInterval * 0.5f * z  - (iconWidth - iconWidth * icon4.sp.getScaleX()) / 2,
                y);

        strelochka.setScale(z*0.5f);
        strelochka.setPosition(
                getXXX(z,1,1),
//                (Gdx.graphics.getWidth() * z * 0.25f) - 500 * strelochka.getScaleX()/2 -iconInterval * -0.5f * z + strelochka.getWidth() * strelochka.getScaleX() - (iconWidth - iconWidth * icon3.sp.getScaleX()) / 2,
                yS);


        //(600 - 600 * icon1.sp.getScaleY()) / 2);

    }
}
