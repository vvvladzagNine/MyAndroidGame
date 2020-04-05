package ru.zagidev.inputHandling;

import com.badlogic.gdx.input.GestureDetector;

public class SimpleDirectionGestureDetector extends GestureDetector {
    public interface DirectionListener {
//        void onLeft();
//
//        void onRight(float x);
//
//        void onUp();
//
        void zoom(float initialDist,float dist);

        void swipe(float x,float y);

        void tap(float x,float y, int count);
    }

    public SimpleDirectionGestureDetector(DirectionListener directionListener) {
        super(new DirectionGestureListener(directionListener));
    }

    private static class DirectionGestureListener extends GestureAdapter{
        DirectionListener directionListener;

        public DirectionGestureListener(DirectionListener directionListener){
            this.directionListener = directionListener;
        }

        @Override
        public boolean zoom(float initialDistance, float distance) {
            directionListener.zoom(initialDistance,distance);
            return super.zoom(initialDistance,distance);
        }

        @Override
        public boolean pan(float x, float y, float deltaX, float deltaY) {
            directionListener.swipe(deltaX,deltaY);
            return super.pan(x, y, deltaX, deltaY);
        }

        @Override
        public boolean fling(float velocityX, float velocityY, int button) {
//            if(Math.abs(velocityX)<6000 && Math.abs(velocityY)<6000 )
//            directionListener.swipe(velocityX,velocityY);
            return super.fling(velocityX, velocityY, button);
        }

        @Override
        public boolean tap(float x, float y, int count, int button) {
            directionListener.tap(x,y,count);
            return super.tap(x, y, count, button);
        }
    }

}
