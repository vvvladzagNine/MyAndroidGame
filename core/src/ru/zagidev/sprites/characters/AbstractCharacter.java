package ru.zagidev.sprites.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import ru.zagidev.Point;
import ru.zagidev.RunningGame;
import ru.zagidev.levels.GameLevel;
import ru.zagidev.logic.WaveAlgorithm;
import ru.zagidev.world.Team;
import ru.zagidev.world.WorldMap;

import static ru.zagidev.RunningGame.getMatricsCords;
import static ru.zagidev.RunningGame.getRealCords;

public abstract class AbstractCharacter extends Actor {

    public GameLevel level;

    public Sprite sp;
    public Texture texture;
    public WaveAlgorithm waveAlgorithm;
    public ArrayList<Point> path;
    public Point nextCell;
    public Point meshPoint;
    protected float dX;
    protected float dY;
    public AbstractCharacter target;
    public float speed;
    public Team team;
    private Team enemyTeam;
    protected float MAX_HEALTH = 1000;
    public float currentHealth;
    protected float lastHealth;
    public boolean followTarget = true;
    protected int attackReloading = 25;
    protected int currentAttackReloadingState = 0;
    protected float damage = 200;

    protected static ShapeRenderer renderer;


    protected int animationDelay = 5;

    protected int currentAnimWalk = 0;
    protected int pastAnimWalk = 0;
    protected ArrayList<Sprite> animationWalk;
    protected ArrayList<Sprite> animationWalkRight;
    protected ArrayList<Sprite> animationWalkDown;
    protected ArrayList<Sprite> animationWalkUp;
    public ArrayList<Texture> animationWalkRightTextures;
    public ArrayList<Texture> animationWalkDownTextures;
    public ArrayList<Texture> animationWalkUpTextures;

    protected int currentAnimFight = 0;
    protected int pastAnimFight = 0;
    protected ArrayList<Sprite> animationFight;
    public ArrayList<Texture> animationFightTextures;

    protected Sprite stay;

    protected Sprite dead;

    public Texture deadTexture;
    public Texture deadTexture2;

    protected float lastdX;
    protected float lastdY;

    public int price=50;

    public Sound sound;


    //sounds of punch
    // {
    public static Sound punch1 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/punch/punch.mp3"));
    public static Sound punch2 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/punch/punch-3.mp3"));
    public static Sound swordHit1 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sword/sword-sound-effects-all-sounds-mp3cut_ItfeRU9.mp3"));
    public static Sound swordHit3 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sword/drop-sword-soundbible.mp3"));
    public static Sound swordHit2 = Gdx.audio.newSound(Gdx.files.internal("data/sounds/sword/swoosh-3-soundbible.mp3"));
    //  }



    public Sound deadSound = Gdx.audio.newSound(Gdx.files.internal("data/sounds/roblox-death-sound_1.mp3"));

    protected int changeTargetCoolDown = 20;
    protected int currentChangeTargetCoolDown = 0;


    public void directionControllerHorizontal() {
        if (lastdX * dX < 0) {
            stay.flip(true, false);
            if (animationWalkRight != null) {
                for (Sprite p : animationWalkRight) {
                    p.flip(true, false);
                }
            }
        }
        lastdX = dX;
    }



    public void directionControllerVerticalOrHorizontal() {

        if (Math.abs(dY) > Math.abs(dX)) {
            if (dY < 0 && animationWalk != animationWalkDown)
                animationWalk = animationWalkDown;
            if (dY > 0 && animationWalk != animationWalkUp)
                animationWalk = animationWalkUp;
        }

        if (Math.abs(dY) < Math.abs(dX) && animationWalk != animationWalkRight) {
            animationWalk = animationWalkRight;
        }
        lastdX = dX;
        lastdY = dY;
    }


    public float realDist(AbstractCharacter t) {
        float drx = t.getCenterX() - getCenterX();
        float dry = t.getCenterY() - getCenterY();
        return (float) Math.sqrt(drx * drx + dry * dry);
    }

    public void lifeController() {
        if (currentHealth <= 0 && lastHealth > 0) {
            float x = sp.getX();
            float y = sp.getY();
            sp = dead;
            sp.setPosition(x, y);
            deadSound.play();
            lastHealth = currentHealth;
        } else lastHealth = currentHealth;
    }


    public boolean isAlive() {
        return currentHealth > 0.0f;
    }



    public void setdX(int a) {
        this.dX = a;

    }

    public Team getEnemyTeam() {
        return enemyTeam;
    }

    private void setEnemyTeam() {
        if (team != null) {
            if (level.characters.team1 == team) {
                enemyTeam = level.characters.team2;
            } else {
                enemyTeam = level.characters.team1;
            }
        }
    }

    public float getCenterX() {
        return sp.getX() + sp.getWidth() / 2;
    }

    public float getCenterY() {
        return sp.getY() + sp.getHeight() / 2;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        sp.draw(batch);
        sp.setColor(new Color(255, 255, 255, 255));
        if (team != null && isAlive()) {
            batch.end();
            renderer.begin(ShapeRenderer.ShapeType.Filled);
            renderer.setProjectionMatrix(RunningGame.camera.combined);
            renderer.setColor(Color.BLACK);
            renderer.rect(sp.getX(), sp.getY() + sp.getHeight(), 100, 21);
            renderer.setColor(team.color);
            renderer.rect(sp.getX() + 5, sp.getY() + 3 + sp.getHeight(), 90 * (currentHealth / MAX_HEALTH), 15);
            renderer.end();
            batch.begin();
        }


    }


    public void setTarget(AbstractCharacter a) {
        if (target == null || !target.isAlive())
            target = a;
    }


    protected int computeDistance(AbstractCharacter character) {
        return waveAlgorithm.computeDist(getMatricsCords((int) getCenterX(), (int) getCenterY()), getMatricsCords(character.getCenterX(), character.getCenterY()));
    }

    public abstract void attack();


    protected AbstractCharacter getNearestEnemy() {
        if (enemyTeam != null && enemyTeam.getMembers().size() > 0) {
            AbstractCharacter nearest = null;
            int dist = 1000000;
            for (AbstractCharacter enemy : enemyTeam.getMembers()) {
                if (enemy.isAlive()) {
                    int newDist = computeDistance(enemy);
                    if (dist > newDist) {
                        dist = computeDistance(enemy);
                        nearest = enemy;
                    }
                }
            }
            return nearest;
        } else {
            return null;
        }

    }

    public void setNearestEnemyAsATarget() {
        setTarget(getNearestEnemy());
    }

    public void followTarget() {
        if (target != null && followTarget && isAlive())
            changePath((int) target.sp.getX(), (int) target.sp.getY());
    }

    @Override
    public void act(float delta) {
        walk();
    }


    public boolean checkCell(Point p) {
        for (AbstractCharacter c : team.getMembers()) {
            if (p.equals(c.meshPoint) && c.isAlive()) return false;
        }
        return true;
    }


    private void walk() {
        if (isAlive()) {
            if (path != null && path.size() > 0) {
                meshPoint = getMatricsCords((int) getCenterX(), (int) getCenterY());
                if (path.indexOf(meshPoint) != 0) {
                    nextCell = path.get(path.indexOf(meshPoint) - 1);
//                    xif(checkCell(nextCell)){
                    Point p = getRealCords(nextCell.x, nextCell.y);
                    float drx = p.x - getCenterX();
                    float dry = p.y - getCenterY();
                    float dist = (int) Math.sqrt(drx * drx + dry * dry);
                    float sin = drx / dist;
                    float cos = dry / dist;
                    dX = (speed * sin);
                    dY = (speed * cos);

                    if (!animationWalk.isEmpty()) {
                        currentAnimWalk++;
                        if (currentAnimWalk / animationDelay != pastAnimWalk / animationDelay) {
                            pastAnimWalk = currentAnimWalk;
                            float x = sp.getX();
                            float y = sp.getY();
                            sp = animationWalk.get((currentAnimWalk / animationDelay) % animationWalk.size());
                            sp.setPosition(x, y);
                        }
                    }

                    sp.translate(+dX, +dY);
//                    }else {
//                        if(sp!=stay){
//                            float x= sp.getX();
//                            float y= sp.getY();
//                            sp=stay;
//                            sp.setPosition(x,y);
//                        }
//
//                    }

                } else {
                    path = new ArrayList<>();
                    float x = sp.getX();
                    float y = sp.getY();
                    sp = stay;
                    sp.setPosition(x, y);
                }
            }
            directionControllerHorizontal();
            directionControllerVerticalOrHorizontal();
        }
        lifeController();

    }

    public void adjustClass(float x, float y) {
        adjustClass(x, y, null,"",3,1000,50,50);
    }


    /**
     *
     * Обязательно вызвать этот метод в конструкторах дочерних классов
     *
     * @param x - положение х
     * @param y - положение у
     * @param team - команда
     * @param soundPath - путь к файлу со звуком
     * @param speed - скорость
     * @param maxHealth - здоровье
     * @param damage -урон
     *
     */
    public void adjustClass(float x, float y, Team team, String soundPath, float speed, float maxHealth, float damage,int price) {
        this.team = team;
        if (team != null) {
            team.addMembers(this);
            setEnemyTeam();
        }
        this.price=price;
        this.damage=damage;
        this.speed = speed;
        path = new ArrayList<>();
        waveAlgorithm = new WaveAlgorithm(this);
        dX = 1;
        dY = 1;
        if (texture == null) throw new RuntimeException("Texture 'texture' must be set");
        stay = new Sprite(texture, 100, 120);
        if (deadTexture == null) deadTexture = texture;
        if (Math.random() > 0.5)
            dead = new Sprite(deadTexture, 120, 100);
        else
            dead = new Sprite(deadTexture2, 120, 100);
        sp = stay;
        sp.setPosition(x, y);
        meshPoint = getMatricsCords((int) getCenterX(), (int) getCenterY(),level);
        setX(sp.getX());
        setY(sp.getY());
        lastdX = dX;
        MAX_HEALTH=maxHealth;
        currentHealth = MAX_HEALTH;
        lastHealth = currentHealth;
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);

        if(soundPath==null && sound==null) throw new RuntimeException("soundPath or sound must be set");

        setSound(soundPath);


        //Добавляем текстуры
        animationWalkRight = new ArrayList<>();
        animationWalkDown = new ArrayList<>();
        animationWalkUp = new ArrayList<>();
        if (animationWalkRightTextures == null) animationWalkRightTextures = new ArrayList<>();
        if (animationWalkDownTextures == null) animationWalkDownTextures = new ArrayList<>();
        if (animationWalkUpTextures == null) animationWalkUpTextures = new ArrayList<>();
        for (Texture t : animationWalkRightTextures) {
            animationWalkRight.add(new Sprite(t, 100, 120));
        }
        for (Texture t : animationWalkDownTextures) {
            animationWalkDown.add(new Sprite(t, 100, 120));
        }
        for (Texture t : animationWalkUpTextures) {
            animationWalkUp.add(new Sprite(t, 100, 120));
        }

        animationWalk = animationWalkRight;
        animationFight = new ArrayList<>();
        if (animationFightTextures == null) animationFightTextures = new ArrayList<>();
        for (Texture t : animationFightTextures) {
            animationFight.add(new Sprite(t, 100, 120));
        }
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }


    public void changePath(float x, float y) {
        if (waveAlgorithm.computeDist(getMatricsCords(getCenterX(), getCenterY()), getMatricsCords(x, y)) != -1)
            path = waveAlgorithm.getPath(getMatricsCords(getCenterX(), getCenterY()), getMatricsCords(x, y));
    }

    protected void setSound(String p){
        sound= Gdx.audio.newSound(Gdx.files.internal(p));
    }


    @Override
    public String toString() {
        return this.getClass().getSimpleName() + ":  X-" + sp.getX() + " Y-" + sp.getY();
    }

    public static void dispose(){
        punch1.dispose();
        punch2.dispose();
        swordHit1.dispose();
        swordHit2.dispose();
        swordHit3.dispose();
    }
}
