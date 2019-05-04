package e.chriszhang.aircraft;

public class AirCraft extends  FlyingObject implements  Runnable{


    /**
     * the HP of the airCraft
     */
    private int HP;

    /**
     * the exploding state
     */
    private int explodingState;

    /**
     * the isRunning boolean
     */
    private boolean isRunning;




    public int getExplodingState() {
        return explodingState;
    }

    public void setExplodingState(int explodingState) {
        this.explodingState = explodingState;
    }


    public int getHP() {
        return HP;
    }

    public void decreaseHP(){
        this.HP --;
    }

    public void setHP(int HP){
        this.HP = HP;
    }

    public boolean isRunning(){
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    public boolean isStillHealth(){
        return this.HP > 0;
    }

    public AirCraft(){
        this.setRunning(true);
    }

    /**
     * return if the time period is long enough to generate a new Bullet or Missile
     */
    boolean checkTime(int timeStart, int timePeriod){
        int timeDiff = getSkyManager().getmTimeLeftInMillis() - timeStart;
        if(timeDiff >= timePeriod){
            return true;
        }
        return false;
    }


    /**
     * the aircraftestate of the current aircraft;
     */

    @Override
    public void run(){

    }








}
