package e.chriszhang.aircraft;

import android.graphics.RectF;

public class FlyingObject {
    /**
     * the rectangle that determines location
     */
    private RectF rectangle = new RectF();

    /**
     * the width of the rectangle
     */
    private float width;

    /**
     * the height of the rectangle
     */
    private float height;


    /**
     * the skyManager of that Flying Object
     */
    private SkyManager skyManager;

    /**
     *the speed of the flying object
     */
    private float speed;


    FlyingObject(){
        this.skyManager=  GameActivity.getSkyManager();
    }


    /**
     * set the left and right.
     * */
    void SetX(float x){
        rectangle.left = x;
        rectangle.right = x + width;
    }
    /**
     * set the top and bottom.
     * */
    void SetY(float y){
        rectangle.top = y;
        rectangle.bottom = y + height;
    }



    public RectF getRectangle() {
        return rectangle;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public SkyManager getSkyManager() {
        return skyManager;
    }

    public float getSpeed() {
        return speed;
    }





    public void setWidth(float width) {
        this.width = width;
    }

    public void setHeight(float height) {
        this.height = height;
    }


    public void setSkyManager(SkyManager skyManager) {
        this.skyManager = skyManager;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setRectangle(RectF rectangle) {
        this.rectangle = rectangle;
    }

}
