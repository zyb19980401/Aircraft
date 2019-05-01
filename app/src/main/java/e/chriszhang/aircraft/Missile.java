package e.chriszhang.aircraft;

public class Missile extends FlyingObject implements Runnable {
    private EnemyAirCraft target;
    private int targetX;
    private int targetY;
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

    public void update(EnemyAirCraft target, int X, int Y){
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
            SetY(getRectangle().top + speedY);
            SetX(getRectangle().left + speedX);
            running = getRectangle().top + getHeight() > 0;
            for (AirCraft airCraft : getSkyManager().getEnemyAirCraftList()) { // check enemyaircraftlist
                if (this.isHitBy(airCraft)) {
                    airCraft.setRunning(false);
                    this.setRunning(false);
                    break;
                }
                running = getRectangle().top < getSkyManager().getHeight();
            }
        }
        getSkyManager().removeMissileList(this);
    }
}
