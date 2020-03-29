package ru.zagidev.logic;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import ru.zagidev.MyAndroidGame;
import ru.zagidev.Point;
import ru.zagidev.sprites.AbstractObject;
import ru.zagidev.sprites.objects.Block;
import ru.zagidev.sprites.objects.Dot;

public class WaveAlgorithm implements AbstractObject
{
    private static Block[][] table;
    private int rows;
    private int cols;
    private Point startPoint;
    private Point finishPoint;
    private LinkedHashMap<String,Point> previousPoints;

    static {
        table= MyAndroidGame.matrix;
    }


    /**
     * Constructor sets initial values to data fields.
     *
     */
    public WaveAlgorithm()
    {

        this.rows = table.length;
        this.cols = table[0].length;
    }


    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public void setFinishPoint(Point finishPoint) {
        this.finishPoint = finishPoint;
    }

    /**
     * @return the distance between the start point and the finish point
     *         or -1 if a path isn't found.
     */
    public int computeDist(Point s,Point f)
    {

        this.startPoint=s;
        this.finishPoint=f;
        int[][] dist = new int[rows][cols];
        int[][] directions = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        ArrayList<Point> oldFront = new ArrayList<Point>();
        ArrayList<Point> newFront = new ArrayList<Point>();
        int currentDist = 0;
        for(int i = 0; i < rows; i++)
            for(int j = 0; j < cols; j++)
            {
                dist[i][j] = table[i][j]==null ? -1 : Integer.MAX_VALUE;
            }
        oldFront.add(startPoint);
        dist[(int)startPoint.getX()][(int)startPoint.getY()] = 0;
        previousPoints = new LinkedHashMap<String,Point>();
        while(true)
        {
            currentDist++;
            for(Point p : oldFront)
            {
                for(int[] dir : directions)
                {
                    Point newPoint = new Point((int)(p.getX() + dir[0]), (int)(p.getY() + dir[1]));
                    if(isOutOfField(newPoint)) continue;
                    if(dist[(int) newPoint.getX()][(int)newPoint.getY()] != -1) continue;
                    dist[(int)newPoint.getX()][(int)newPoint.getY()] = currentDist;
                    previousPoints.put(newPoint.toString(), p);
                    if(newPoint.equals(finishPoint)) return currentDist;
                    newFront.add(newPoint);
                }
            }
            if(newFront.isEmpty()) return -1;
            oldFront = new ArrayList<Point>(newFront);
            newFront.clear();
        }
    }

    /**
     * This method check if the point p in the table or not.
     *
     * @param p the link to some point
     * @return true if the point p is out of range of the table.
     *         Otherwise return false.
     */
    public boolean isOutOfField(Point p)
    {
        return (p.getX() < 0 || p.getX() >= rows || p.getY() < 0 || p.getY() >= cols);
    }

    /**
     * @return the list of points that form a path from the start point
     *         to the finish point.
     */
    public ArrayList<Point> getPath(Point startPoint, Point endPoind)
    {
        this.startPoint=startPoint;
        this.finishPoint=endPoind;

        computeDist(startPoint,endPoind);
        if(!previousPoints.containsKey(finishPoint.toString()))
            return null;
        Point lastPoint = finishPoint;
        ArrayList<Point> path = new ArrayList<Point>();
        while (!lastPoint.equals(startPoint))
        {
            path.add(lastPoint);
            lastPoint = previousPoints.get(lastPoint.toString());
        }
        path.add(startPoint);
        return path;
    }
    public ArrayList<Dot> getDots(Point startPoint, Point endPoind)
    {


        computeDist(startPoint,endPoind);
        if(!previousPoints.containsKey(finishPoint.toString()))
            return null;
        Point lastPoint = finishPoint;
        ArrayList<Dot> path = new ArrayList<Dot>();
        while (!lastPoint.equals(startPoint))
        {
            path.add(new Dot(lastPoint.x,lastPoint.y));
            lastPoint = previousPoints.get(lastPoint.toString());
        }
        path.add(new Dot(startPoint.x,startPoint.y));
        return path;
    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void update() {

    }
}
