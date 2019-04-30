package e.chriszhang.aircraft;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

public class SkyManager extends Observable implements Runnable {

    /**
     * The rate in order to adapt different screens.
     */
    private float rate;

    private int width;

    private int height;

    public boolean isRunning(){return running;}

    private List<Bullet>  EnemyBulletList =  Collections.synchronizedList(new ArrayList<>());

    private List<AirCraft> EnemyAirCraftList = Collections.synchronizedList (new ArrayList<>());

    private List<Bullet>  MyBulletLIst = Collections.synchronizedList (new ArrayList<>());

    private List<PowerUpItem>  PowerUpItemList = Collections.synchronizedList (new ArrayList<>());

    private MyAircraft myAircraft;

    private int mTimeLeftInMillis;

    private BackGround backGround;

    public void setRunning(boolean running) {
        this.running = running;
    }

    private boolean running = true;


    void addTime() {
        mTimeLeftInMillis += 1000;
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

    public List<AirCraft> getEnemyAirCraftList() {
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

    public void removePowerUpItem(PowerUpItem item) {
        PowerUpItemList.remove(item);
    }


    public void addEnemyAirCraftList(AirCraft airCraft) {
        EnemyAirCraftList.add(airCraft);
    }



    public void addMyBulletLIst(Bullet bullet) {
        MyBulletLIst.add(bullet);
    }



    public void addEnemyBulletList(Bullet bullet) {
        EnemyBulletList.add(bullet);
    }

    public void addPowerUpItem(PowerUpItem item){
        PowerUpItemList.add(item);
    }



    public void setEnemyAirCraftList(List<AirCraft> enemyAirCraftList) {
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




    @Override
    public void run(){  //这里控制GameState
        while (isRunning()){
         try {
                 Thread.sleep(1600);
                 float x = (float) (Math.random() * (getWidth() - 100));
                 float y = 0;  // small enemyAircraft;s height
                 new SmallEnemyAirCraft(x, y, 0, 20);
//                 System.out.println("this is the enenmy aircraft list!" + getEnemyAirCraftList().size());
            }
             catch(Exception e){
                 e.printStackTrace();
                }

            try {
                Thread.sleep(1600);
                float x = (float) (Math.random() * (getWidth() - 100));
                float y = 0;  // small enemyAircraft;s height
                new PowerUpItem(x, y, 0, 20);
                System.out.println("this is the new power up item  list!" + getPowerUpItemList().size());
            }
            catch(Exception e){
                e.printStackTrace();
            }
         }
        }
    }




