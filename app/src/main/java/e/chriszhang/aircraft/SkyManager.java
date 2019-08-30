package e.chriszhang.aircraft;


import android.graphics.PointF;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class SkyManager extends Observable implements Runnable {

    SkyManager(){
        this.startTime = 0;
        this.newRandomEnemyTime = 0;
        this.stateOne = true;
    }

    /**
     * The rate in order to adapt different screens.
     */
    private float rate;

    private int width;

    private int height;

    public EnemyAirCraft Boss = null;

    public boolean isRunning(){return running;}

    private List<Bullet>  EnemyBulletList =  Collections.synchronizedList(new ArrayList<>());

    private List<EnemyAirCraft> EnemyAirCraftList = Collections.synchronizedList (new ArrayList<>());

    public List<SmallEnemyAirCraft> getSmallEnemyAircraftList() {
        return SmallEnemyAircraftList;
    }

    public List<MediumEnemyAirCraft> getMediumEnemyAirCraftList() {
        return mediumEnemyAirCraftList;
    }

    private List<MediumEnemyAirCraft> mediumEnemyAirCraftList = Collections.synchronizedList (new ArrayList<>());

    private List<SmallEnemyAirCraft> SmallEnemyAircraftList = Collections.synchronizedList (new ArrayList<>());

    private List<Bullet>  MyBulletLIst = Collections.synchronizedList (new ArrayList<>());

    private List<PowerUpItem>  PowerUpItemList = Collections.synchronizedList (new ArrayList<>());

    private List<Missile>  MissileList = Collections.synchronizedList (new ArrayList<>());

    public List<BigEnemyAircraft> getBigEnemyAircraftList() {
        return bigEnemyAircraftList;
    }

    public void setBigEnemyAircraftList(List<BigEnemyAircraft> bigEnemyAircraftList) {
        this.bigEnemyAircraftList = bigEnemyAircraftList;
    }

    public void removeBigEnemyAircraftList(BigEnemyAircraft bigEnemyAircraft){

        bigEnemyAircraftList.remove(bigEnemyAircraft);

    }

    private List<BigEnemyAircraft> bigEnemyAircraftList = Collections.synchronizedList (new ArrayList<>());

    private MyAircraft myAircraft;

    private int mTimeLeftInMillis;

    private BackGround backGround;

    public void setRunning(boolean running) {
        this.running = running;
    }

    private boolean running = true;


    void addTime() {
        mTimeLeftInMillis += 200;
    }


    public MyAircraft getMyAircraft() {
        return myAircraft;
    }



    public int getmTimeLeftInMillis() {
        return mTimeLeftInMillis;
    }





    public BackGround getBackGround() {
        return backGround;
    }


    public List<Bullet> getEnemyBulletList() {
        return EnemyBulletList;
    }

    public List<EnemyAirCraft> getEnemyAirCraftList() {
        return EnemyAirCraftList;
    }

    public List<Bullet> getMyBulletLIst() {
        return MyBulletLIst;
    }



    public List<PowerUpItem> getPowerUpItemList() {
        return PowerUpItemList;
    }

    float getRate() {
        return rate;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }



    public void setEnemyBulletList(List<Bullet> enemyBulletList) {
        EnemyBulletList = enemyBulletList;
    }


    public void setmTimeLeftInMillis(int mTimeLeftInMillis) {
        this.mTimeLeftInMillis = mTimeLeftInMillis;
    }


    public void removeMyBulletLIst(Bullet bullet) {
        MyBulletLIst.remove(bullet);
    }



    public void removeEnemyBulletList(Bullet bullet) {
        EnemyBulletList.remove(bullet);
    }

    public void removeEnemyAirCraftList(AirCraft airCraft) {
        EnemyAirCraftList.remove(airCraft);
    }

    public void removeSmallEnemyAirCraftList(SmallEnemyAirCraft smallEnemyAirCraft){ SmallEnemyAircraftList.remove(smallEnemyAirCraft);}

    public void removeMediumEnemyAirCraftList(MediumEnemyAirCraft mediumEnemyAirCraft){ mediumEnemyAirCraftList.remove(mediumEnemyAirCraft);}

    public void removePowerUpItem(PowerUpItem item) {
        PowerUpItemList.remove(item);
    }


    public void addEnemyAirCraftList(EnemyAirCraft airCraft) {
        EnemyAirCraftList.add(airCraft);
    }

    public void addMissileList(Missile missile){
        MissileList.add(missile);
    }

    public void addMediumEnemyAirCraftList(MediumEnemyAirCraft mediumEnemyAirCraft){
        mediumEnemyAirCraftList.add(mediumEnemyAirCraft);
    }


    public void addBigEnemyAirCraftList(BigEnemyAircraft bigEnemyAircraft){
        bigEnemyAircraftList.add(bigEnemyAircraft);
    }

    public void removeMissileList(Missile missile){
        MissileList.remove(missile);
    }




    public void addMyBulletLIst(Bullet bullet) {
        MyBulletLIst.add(bullet);
    }



    public void addEnemyBulletList(Bullet bullet) {
        EnemyBulletList.add(bullet);
    }

    public void addSmallEnemyAircraftList(SmallEnemyAirCraft smallEnemyAirCraft){ SmallEnemyAircraftList.add(smallEnemyAirCraft);}

    public void addPowerUpItem(PowerUpItem item){
        PowerUpItemList.add(item);
    }



    public void setEnemyAirCraftList(List<EnemyAirCraft> enemyAirCraftList) {
        EnemyAirCraftList = enemyAirCraftList;
    }



    public void setMyBulletLIst(List<Bullet> myBulletLIst) {
        MyBulletLIst = myBulletLIst;
    }


    public void setBackGround(BackGround backGround) {
        this.backGround = backGround;
    }

    public void setMyAircraft(MyAircraft myAircraft) {
        this.myAircraft = myAircraft;
    }


    void setRate(float rate) {
        this.rate = rate;
    }


    public void setWidth(int width) {
        this.width = width;
    }


    public void setHeight(int height) {
        this.height = height;
    }



    /**
     * return if the time period is long enough to generate a new Bullet or Missile
     */
    boolean checkTime(int timeStart, int timePeriod){
        int timeDiff = getmTimeLeftInMillis() - timeStart;
        if(timeDiff >= timePeriod){
            return true;
        }
        return false;
    }


    public float calculateroating(){
        //Case 1, target is above missile
        PointF origin = new PointF(0, 0);
        PointF smallEnemytop = new PointF(0,  100);
        float targetX = getMyAircraft().x;
        float targetY = getMyAircraft().y;
        PointF target = new PointF(targetX, targetY);
        float rotatingDegree = (int)angleBetween2Lines(origin, smallEnemytop, origin, target);
        return rotatingDegree;
    }
    public static float angleBetween2Lines(PointF A1, PointF A2, PointF B1, PointF B2) {
        float angle1 = (float) Math.atan2(A2.y - A1.y, A1.x - A2.x);
        float angle2 = (float) Math.atan2(B2.y - B1.y, B1.x - B2.x);
        float calculatedAngle = (float) Math.toDegrees(angle1 - angle2);
        if (calculatedAngle < 0) calculatedAngle += 360;
        return calculatedAngle;
    }

    private void attackGroupSmall() {
        float RotatingDegree = calculateroating();
        float speedX = getMyAircraft().x / 100;
        float sppedY = getMyAircraft().y / 100;
        boolean onemore = checkTime(startTime, 800);
            if (onemore && fivePlaneCounter < 5) {
                SmallEnemyAirCraft firstone = new SmallEnemyAirCraft(0, 0, speedX, sppedY);
                firstone.setRoaRotatingdegree(RotatingDegree);
                startTime = getmTimeLeftInMillis();
                fivePlaneCounter++;
                System.out.println("one more" + onemore);
                System.out.println("one more" + startTime);
            }
        }

    private void attackSmallRandom(boolean NewEnmey){
        float y = 0;
        float x0 = (float) (Math.random() * (getWidth() - 100));
        float x1 = (float) (Math.random() * (getWidth() - 100));
        float x2 = (float) (Math.random() * (getWidth() - 100));
        if (NewEnmey) {
            float x = (float) (Math.random() * (getWidth() - 100));
            float c = 0;  // small enemyAircraft;s height
            new PowerUpItem(x, c, 0, 20);
            new SmallEnemyAirCraft(x0, y, 0, 10);
            new SmallEnemyAirCraft(x1, y, 0, 10);
            new SmallEnemyAirCraft(x2, y, 0, 10);
        }

    }

    private void attackRandom(boolean Medium, boolean NewEnmey) {
        float x0 = (float) (Math.random() * (getWidth() - 100));
        float x1 = (float) (Math.random() * (getWidth() - 100));
        float x2 = (float) (Math.random() * (getWidth() - 100));

        float x3 = (float) (Math.random() * (getWidth() - 100));
        float x4 = (float) (Math.random() * (getWidth() - 100));
        float x5 = (float) (Math.random() * (getWidth() - 100));

        float y = 0;  // small enemyAircraft;s height

        if (!Medium) {
            if (NewEnmey) {
                new SmallEnemyAirCraft(x0, y, 0, 10);
                new SmallEnemyAirCraft(x1, y, 0, 10);
                new SmallEnemyAirCraft(x2, y, 0, 10);
            }
        } else {
            if (NewEnmey) {
                new MediumEnemyAirCraft(x3, y, 0, 10);
                new MediumEnemyAirCraft(x4, y, 0, 10);
                new MediumEnemyAirCraft(x5, y, 0, 10);
            }
        }
    }

    boolean stateOne = true;
    boolean stateTwo = false;
    boolean stateThree;
    boolean stateFour;
    boolean bossState;
    int fivePlaneCounter = 0;
    int checker = 0;
    private int startTime;
    private int newRandomEnemyTime;
    private int newRandomMediumTime;

    @Override
    public void run() {  //这里控制GameState
        while (isRunning()) {
            boolean Medium = checkTime(newRandomMediumTime, 5000);
            boolean NewEnmey = checkTime(newRandomEnemyTime, 2000);
            System.out.println("wtfmmmm" + newRandomMediumTime);
            System.out.println("wtf2"+ stateTwo);
            System.out.println("wtf3" + getmTimeLeftInMillis());
            if (Medium && NewEnmey) {
                newRandomMediumTime = getmTimeLeftInMillis();
            }
            if (NewEnmey) {
                newRandomEnemyTime = getmTimeLeftInMillis();
            }
            if (getmTimeLeftInMillis() > 5000 && getmTimeLeftInMillis() <= 10000) {
                stateOne = false;
                stateTwo = true;
            }
            if(getmTimeLeftInMillis() > 10000 && getmTimeLeftInMillis() <= 15000){
                stateTwo = false;
                stateThree = true;
            }
            if(getmTimeLeftInMillis() > 15000 && getmTimeLeftInMillis() <= 20000){
                stateThree = false;
                stateFour = true;
            }


            if (stateOne) {
                attackSmallRandom(NewEnmey);
            }
            else if(stateTwo){
                attackRandom(Medium, NewEnmey);
            }
            else if(stateThree){
                float RotatingDegree = calculateroating();
                float speedX = getMyAircraft().x / 100;
                float sppedY = getMyAircraft().y / 100;
                boolean onemore = checkTime(startTime, 800);
                    if (onemore && fivePlaneCounter < 5) {
                        SmallEnemyAirCraft firstone = new SmallEnemyAirCraft(0, 0, speedX, sppedY);
                        firstone.setRoaRotatingdegree(RotatingDegree);
                        startTime = getmTimeLeftInMillis();
                        fivePlaneCounter++;
                        System.out.println("one more"+ onemore);
                        System.out.println("one more" + startTime);
                    }
            }
            else if(stateFour){
                if(checker < 2) {
                    Boss = new BigEnemyAircraft(400, 100, 20, 0);
                    new SmallEnemyAirCraft(300,300,0,0);
                    System.out.println("the sie of the big enmey is " + getBigEnemyAircraftList().size());
                    checker += 1;
                }

            }
            if(fivePlaneCounter >= 5){
                    fivePlaneCounter = 0;
            }
        }

        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("the size is ok" + getEnemyAirCraftList().size());

        try {
//                Thread.sleep(3000);
//                float x = (float) (Math.random() * (getWidth() - 100));
//                float y = 0;  // small enemyAircraft;s height
//                new PowerUpItem(x, y, 0, 20);
            System.out.println("this is the new power up item  list!" + getPowerUpItemList().size());
        } catch (Exception e) {
            e.printStackTrace();
        }
//    }
    }

    public List<Missile> getMissileList() {
        return MissileList;
    }

    public void setMissileList(List<Missile> missileList) {
        MissileList = missileList;
    }
}




