package e.chriszhang.aircraft;


import java.util.ArrayList;
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

    private List<Bullet>  EnemyBulletList = new ArrayList<>();

    private List<AirCraft> EnemyAirCraftList = new ArrayList<>();

    private List<Bullet>  MyBulletLIst = new ArrayList<>();

    private MyAircraft myAircraft;

    private int mTimeLeftInMillis;

    private BackGround backGround;

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
    public void run(){
        }
    }




