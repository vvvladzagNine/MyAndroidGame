package ru.zagidev;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

import java.util.ArrayList;

import ru.zagidev.logic.WaveAlgorithm;
import ru.zagidev.sprites.characters.DuckCharacter;
import ru.zagidev.sprites.objects.Anime;
import ru.zagidev.sprites.objects.Block;
import ru.zagidev.sprites.objects.Dot;

public class MyAndroidGame extends ApplicationAdapter {
	SpriteBatch batch;
	DuckCharacter character;
	Vector3 touchPos;
	Block block;
	public static final int X_SIZE=30;
	public static final int Y_SIZE=15;
	public static final Block[][] matrix = new Block[X_SIZE][Y_SIZE];
	public ArrayList<Dot> dots;




	public static Point getMatricsCords(int x, int y){
		int xr = (int)(new Double(x)/Gdx.graphics.getWidth() * X_SIZE);
		int yr = (int)(new Double(y)/Gdx.graphics.getHeight() * Y_SIZE);
		return new Point(xr,Y_SIZE-1-yr);
	}

	@Override
	public void create () {

		touchPos = new Vector3();
		batch = new SpriteBatch();
		character = new DuckCharacter();
		for(int i=0;i<Y_SIZE;i++){
			if(i!=6)
				addBlock(8,i);
		}
		for(int i=0;i<Y_SIZE;i++){
			if(i!=9)
				addBlock(10,i);
		}
		for(int i=0;i<Y_SIZE;i++){
			if(i!=2)
				addBlock(15,i);
		}
		for(int i=0;i<Y_SIZE;i++){
			if(i!=8)
				addBlock(20,i);
		}

		for(int i=0;i<X_SIZE;i++){
			if(i!=2 && i!=7 && i!=22)
				addBlock(i,12);
		}
		dots=character.waveAlgorithm.getDots(new Point(3,3),new Point(18,8));


	}

	private void addBlock(int x, int y) {
		matrix[x][y] = new Block(x,y);
	}

	@Override
	public void render () {
		handleInput();
		Gdx.gl.glClearColor(0.2f, 0.36f, 0.1f, 0.85f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		character.render(batch);
		for (int row=0; row < matrix.length; row++)
		{
			for (int col=0; col < matrix[row].length; col++)
			{
				if(matrix[row][col] != null){
					matrix[row][col].render(batch);
				}
				// Do stuff
			}
		}
		for(Dot d : dots){
			d.render(batch);
		}
		batch.end();
		character.update();
	}

	private void handleInput() {
		if(Gdx.input.isTouched()) {
			setDots(Gdx.input.getX(), Gdx.input.getY());
			character.changePath(Gdx.input.getX(), Gdx.input.getY());
		}
		else {


		}
	}

	private void setDots(int x,int y) {
		if(character.waveAlgorithm.computeDist(getMatricsCords((int)character.sp.getX(),Gdx.graphics.getHeight()-(int)character.sp.getY()),getMatricsCords(x,y))!=-1)
		dots=character.waveAlgorithm.getDots(getMatricsCords((int)character.sp.getX(),Gdx.graphics.getHeight()-(int)character.sp.getY()),getMatricsCords(x,y));
	}

	@Override
	public void dispose () {
		batch.dispose();

	}
}
