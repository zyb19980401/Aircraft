package e.chriszhang.aircraft;


import java.util.List;
import java.util.Random;

/**
 * The MyAircraft class.
 */
public class MyAircraft extends AirCraft {


    public int getWeaponType() {
        return weaponType;
    }

    public void setWeaponType(int weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * The number of enemy crafts killed.
     */
    private int numKilled;

    /**
     * The weapon type of my aircraft. 0 means one bullet, 1 means two bullets, 2 means three bullets
     */
    private int weaponType;

    public int getMissileStartTime() {
        return missileStartTime;
    }

    public void setMissileStartTime(int missileStartTime) {
        this.missileStartTime = missileStartTime;
    }

    /**
     * the time checker for the missile
     */
    private int missileStartTime;

    private int bulletStartTime;

    /**
     * The constructor of the MyAircraft class.
     */
    MyAircraft() {
        super();
        setHeight(200 * getSkyManager().getRate());
        setWidth(200 * getSkyManager().getRate());
        this.setHP(3);
        SetX(getSkyManager().getWidth() / 2 - this.getWidth() / 2);
        SetY(getSkyManager().getHeight() * 0.7f - this.getHeight() / 2);
        new Thread(this).start();
        bulletStartTime = 0;
    }

    /**
     * add one to number of enemy crafts killed.
     */
    void addNumKilled() {
        numKilled++;
    }



    @Override
    public void run() {
        while (getSkyManager().isRunning()) {
            try {
                    Thread.sleep(50);
                    float x = this.getRectangle().left + this.getWidth() / 2 - 50 * getSkyManager().getRate() / 2;
                    float y = this.getRectangle().top - 50 * getSkyManager().getRate() / 2;

                    boolean newBullet = checkTime(bulletStartTime, 1000);
                    boolean newMissile = checkTime(missileStartTime, 2000);
                    int currentTime = getSkyManager().getmTimeLeftInMillis();
                    switch (this.getWeaponType()) {
                        case 1:
                            if(newBullet) {
                                new Bullet(this, x - (getWidth() - 100), y, 0, -6 * getSkyManager().getRate());
                                new Bullet(this, x + (getWidth() - 100), y, 0, -6 * getSkyManager().getRate());
                            bulletStartTime = currentTime;
                            }
                            break;
                        case 2:
                            if(newBullet) {
                                new Bullet(this, x, y, 0, -6 * getSkyManager().getRate());
                                bulletStartTime = currentTime;
                            }
                            if(newMissile) {
                                List<EnemyAirCraft> enemyAirCrafts = getSkyManager().getEnemyAirCraftList();
                                int min = 0;
                                int max = enemyAirCrafts.size();
                                if (max > 0) {
                                    Random randomNum = new Random();
                                    int leftMissileTarget = min + randomNum.nextInt(max);
                                    int rightMissileTarget = min + randomNum.nextInt(max);
                                    Missile leftMissile = new Missile(x - (getWidth()), y, 0, -6 * getSkyManager().getRate());
                                    Missile rightMissile = new Missile(x + (getWidth() - 150), y, 0, -6 * getSkyManager().getRate());

                                    try {
                                        enemyAirCrafts.get(leftMissileTarget).addObserver(leftMissile);
                                        enemyAirCrafts.get(rightMissileTarget).addObserver(rightMissile);
                                    } catch (java.lang.NullPointerException e) {
                                        e.printStackTrace();
                                    }
                                    missileStartTime = currentTime;
                                } else {
                                    new Missile(x - (getWidth()), y, 0, -6 * getSkyManager().getRate());
                                    new Missile(x + (getWidth() - 150), y, 0, -6 * getSkyManager().getRate());
                                    missileStartTime = currentTime;
                                }
                            }
                            break;
                        default:
                            if(newBullet) {
                                new Bullet(this, x, y, 0, -6 * getSkyManager().getRate());
                                bulletStartTime = currentTime;
                            }
                    }


            }
            catch(InterruptedException e) {
                e.printStackTrace();
            }

        }}}