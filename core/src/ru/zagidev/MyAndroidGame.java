package ru.zagidev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import ru.zagidev.sprites.characters.AbstractCharacter;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.characters.DuckGirlCharacter;
import ru.zagidev.sprites.objects.Block;
import ru.zagidev.sprites.objects.DotsPath;
import ru.zagidev.sprites.objects.NextCell;
import ru.zagidev.world.WorldMap;

public class MyAndroidGame extends ApplicationAdapter {
	SpriteBatch batch;
	Vector3 touchPos;
	Block block;
	public static int VIEW_WIDTH;
	public static int VIEW_HEIGHT;
	public static  int WIDTH;
	public static  int HEIGHT;
	public static final int X_SIZE=50;
	public static final int Y_SIZE=25;
	public static final Block[][] matrix = new Block[X_SIZE][Y_SIZE];
	public DotsPath dots;
	private Stage stage;
	private StretchViewport viewport;
	public static OrthographicCamera camera;
	private NextCell nextCell;
	public WorldMap worldMap;

	AbstractCharacter character;
	AbstractCharacter npc;





	public static Point getMatricsCords(float x, float y){
		float xr = (x* X_SIZE/WIDTH );
		float yr = (y * Y_SIZE/HEIGHT);
		return new Point((int)xr,(int)yr);
	}
	public static Point getRealCords(int x, int y){
		int xr = (int)(x* WIDTH/X_SIZE ) + WIDTH/X_SIZE/2;
		int yr = (int)(y * HEIGHT/Y_SIZE) + HEIGHT/Y_SIZE/2;
		return new Point(xr,yr);
	}

	@Override
	public void create () {




		VIEW_WIDTH=Gdx.graphics.getWidth();
		VIEW_HEIGHT=Gdx.graphics.getHeight();

		WIDTH=VIEW_WIDTH*2;
		HEIGHT= VIEW_HEIGHT*2;

		camera=new OrthographicCamera(VIEW_WIDTH, VIEW_HEIGHT);
		viewport=new StretchViewport(camera.viewportWidth,camera.viewportHeight,camera);
		stage = new Stage(viewport);
		Gdx.input.setInputProcessor(stage);

		worldMap=new WorldMap();

		dots=new DotsPath();
		touchPos = new Vector3();
		batch = new SpriteBatch();
		character = new DuckCharacter(240,160);
		nextCell=new NextCell(character);
		npc=new DuckGirlCharacter(100,500);
		npc.setTarget(character);
		Gdx.app.log("MyTag", Gdx.graphics.getWidth() + " " +Gdx.graphics.getHeight() );
//

//		dots.path=character.waveAlgorithm.getDots(new Point(3,3),new Point(18,8));


		stage.addActor(worldMap);

		stage.addActor(character);

		stage.addActor(dots);

		stage.addActor(npc);

		stage.addActor(nextCell);








	}


	@Override
	public void resize(int width, int height) {
		stage.getViewport().update(width, height, true);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0.12f,0.50f,0.12f,0.5f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.setProjectionMatrix(camera.combined);
		handleInput();
		float delta = Gdx.graphics.getDeltaTime();

		stage.act(delta);
		stage.draw();
		camera.position.set(character.sp.getX(),character.sp.getY(),0);
	}


	private void handleInput() {
		if(Gdx.input.isTouched()) {
			Vector3 v = camera.unproject(new Vector3(Gdx.input.getX(),Gdx.input.getY(),0));

			setDots((int)v.x, (int)v.y);
			character.changePath((int)v.x, (int)v.y);
		}
	}

	private void setDots(int x,int y) {
		if(character.waveAlgorithm.computeDist(getMatricsCords((int)character.getCenterX(),(int)character.getCenterY()),getMatricsCords(x,y))!=-1)
		dots.path=character.waveAlgorithm.getDots(getMatricsCords((int)character.getCenterX(),(int)character.getCenterY()),getMatricsCords(x,y));
	}

	@Override
	public void dispose () {
		batch.dispose();

	}

}
