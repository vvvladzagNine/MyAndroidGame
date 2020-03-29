package ru.zagidev;

public class Point {
    public int x;
    public int y;
    public Point() {
        this(0, 0);
    }

    public Point(int var1, int var2) {
        this.x = var1;
        this.y = var2;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean equals(Object var1) {
        if (!(var1 instanceof Point)) {
            return super.equals(var1);
        } else {
            Point var2 = (Point)var1;
            return this.x == var2.x && this.y == var2.y;
        }
    }

    public String toString() {
        return this.getClass().getName() + "[x=" + this.x + ",y=" + this.y + "]";
    }


}
