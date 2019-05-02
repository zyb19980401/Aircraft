package e.chriszhang.aircraft;

import java.util.List;
import java.util.Random;
import java.util.Vector;

public class AirCraft extends  FlyingObject implements  Runnable{


    /**
     * the HP of the airCraft
     */
    private int HP;

    private int exploingState;

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    private int startTime;

    public int getExploingState() {
        return exploingState;
    }

    public void setExploingState(int exploingState) {
        this.exploingState = exploingState;
    }



    private boolean isRunning;

    public AirCraft(){

        this.setRunning(true);
    }


    /**
     * the aircraftestate of the current aircraft;
     */

    @Override
    public void run(){

    }





    public int getHP() {
        return HP;
    }

    public void decreaseHP(){
        this.HP --;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }



}
