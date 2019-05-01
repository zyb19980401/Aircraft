package e.chriszhang.aircraft;

public class Missile extends FlyingObject implements Runnable {
    private EnemyAirCraft target;
    private float targetX;
    private float targetY;
    private float speedX;
    private float speedY;
    private float missileX;
    private float missileY;
    private boolean running;


    Missile(float missileX, float missileY, float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.missileX = missileX;
        this.missileY = missileY;
        setWidth(175 * getSkyManager().getRate());
        setHeight(175 * getSkyManager().getRate());
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
            for (AirCraft airCraft : getSkyManager().getEnemyAirCraftList()) { // check enemyaircraftlist
                if (this.isHitBy(airCraft)) {
                    airCraft.setRunning(false);
                    this.setRunning(false);
                    break;
                }
                running = getRectangle().top < getSkyManager().getHeight();
            }
        }
        catch(java.util.ConcurrentModificationException exception){
                //
            }
        }
        getSkyManager().removeMissileList(this);
    }
}
