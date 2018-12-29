package e.chriszhang.aircraft;

import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

public class SkyManager {

    private int width;
    private int height;
    private List<Bullet>  EnemyBulletList = new ArrayList<>();
    private List<AirCraft> EnemyAirCraftList = new ArrayList<>();
    private List<Bullet>  MyBulletLIst = new ArrayList<>();
    private MyAircraft myAircraft;
    private int mTimeLeftInMillis;


    /**
     * The rate in order to adapt different screens.
     */
    private float rate;


    /**
     * @return return the rate.
     */
    float getRate() {
        return rate;
    }

    /**
     * set rate.
     */
    void setRate(float rate) {
        this.rate = rate;
    }

    void addTime() {
        mTimeLeftInMillis += 1000;
    }


}


