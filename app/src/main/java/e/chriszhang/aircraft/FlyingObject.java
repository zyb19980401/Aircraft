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

    private SkyManager skyManager;

    /**
     *the speed of the flying object
     */
    private float speed;


    FlyingObject(){
        this.skyManager= GameActivity.getSkyManager();
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
        rectangle.bottom = y + rectangle.top;
    }


    boolean isHitBy(FlyingObject obj) {
        float px = 25 * getSkyManager().getRate();
        return checkLeftHit(px, this, obj) && checkTopHit(px, this, obj);

    }

    /**
     * A helper function.
     */
    boolean checkLeftHit(float px, FlyingObject objSelf, FlyingObject obj) {
        return objSelf.getRectangle().left - obj.getRectangle().left + px <= obj.getWidth()
                && obj.getRectangle().left - objSelf.getRectangle().left + 3 * px <= this.getWidth();
    }

    /**
     * A helper function.
     */
    boolean checkTopHit(float px, FlyingObject objSelf, FlyingObject obj) {
        return objSelf.getRectangle().top + px - obj.getRectangle().top <= obj.getHeight()
                && obj.getRectangle().top - objSelf.getRectangle().top + 3 * px <= objSelf.getHeight();
    }



    public void setRectangle(RectF rectangle) {
        this.rectangle = rectangle;
    }

    /**
     * @return the rectangle
     */
    public RectF getRectangle() {
        return rectangle;
    }
    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public SkyManager getSkyManager() {
        return skyManager;
    }

    public void setSkyManager(SkyManager skyManager) {
        this.skyManager = skyManager;
    }


    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
