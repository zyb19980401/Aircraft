package e.chriszhang.aircraft;

import android.graphics.PointF;

public class Missile extends FlyingObject implements Runnable {
    private EnemyAirCraft target;
    private float targetX;
    private float targetY;
    private float speedX;
    private float speedY;
    private float missileX;
    private float missileY;
    private final int attack = 2;

    public int getRotatingDegree() {
        return rotatingDegree;
    }

    private int rotatingDegree;
    private boolean running;


    Missile(float missileX, float missileY, float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.missileX = missileX;
        this.missileY = missileY;
        setWidth(110 * getSkyManager().getRate());
        setHeight(220 * getSkyManager().getRate());
        SetX(missileX);
        SetY(missileY);
        setRunning(true);
        getSkyManager().addMissileList(this);
        new Thread(this).start();
    }

    void setRunning(boolean running) {
        this.running = running;
    }

    public void update(EnemyAirCraft target, float X, float Y){
        this.target = target;
        this.targetX = X;
        this.targetY = Y;
        calculateroating();
    }

    public void calculateroating(){
        //Case 1, target is above missile
        PointF origin = new PointF(missileX, missileY);
        PointF missiletop = new PointF(missileX, missileY + 10);
        PointF target = new PointF(targetX, targetY);
        rotatingDegree = (int)angleBetween2Lines(origin, missiletop, origin, target);

    }
    public static float angleBetween2Lines(PointF A1, PointF A2, PointF B1, PointF B2) {
        float angle1 = (float) Math.atan2(A2.y - A1.y, A1.x - A2.x);
        float angle2 = (float) Math.atan2(B2.y - B1.y, B1.x - B2.x);
        float calculatedAngle = (float) Math.toDegrees(angle1 - angle2);
        if (calculatedAngle < 0) calculatedAngle += 360;
        return calculatedAngle;
    }


    @Override

    public void run() {
        while (running && getSkyManager().isRunning()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//            System.out.println("the new updated x y is " + targetX  + targetY);
            if(targetX == 0 && targetY == 0) {
                SetY(getRectangle().top + speedY);
                SetX(getRectangle().left + speedX);
            }
            else if(targetX == -1 && targetY == -1){
                rotatingDegree = 0;
                SetY(getRectangle().top + speedY);
                SetX(getRectangle().left + speedX);
            }

            else{
                float NewspeedY = (targetY - getRectangle().top)/ 30;
                float NewspeedX = (targetX - getRectangle().left)/ 30;
                SetY(getRectangle().top + NewspeedY);
                SetX(getRectangle().left + NewspeedX);
            }
            running = getRectangle().top + getHeight() > 0;
            try{
            for (EnemyAirCraft airCraft : getSkyManager().getEnemyAirCraftList()) { // check enemyaircraftlist
                if (this.isHitBy(airCraft)) {
//                    airCraft.setRunning(false);
//                    this.setRunning(false);
//                    break;
                    airCraft.decreaseHpBy(attack);
                    airCraft.setHited(true);   //in case we want to do some animation when it gets hitted.
                    System.out.println(airCraft.getHP());
                    if(!airCraft.isStillHealth()) {
                        airCraft.setRunning(false);
                        getSkyManager().addNumkill(1);
                    }
                    this.setRunning(false);
                    break;


                }
                if(running){
                    running = getRectangle().top < getSkyManager().getHeight();
                }
            }
        }
        catch(java.util.ConcurrentModificationException exception){
                //
            }
        }
        getSkyManager().removeMissileList(this);
    }
}
