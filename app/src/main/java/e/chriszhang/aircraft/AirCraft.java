package e.chriszhang.aircraft;

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
        try {
            if (this instanceof MyAircraft) {
                Thread.sleep(200);
                float x = this.getRectangle().left + this.getWidth() / 2 - 50 * getSkyManager().getRate() / 2;
                float y = this.getRectangle().top - 50 * getSkyManager().getRate() / 2;
                switch (((MyAircraft) this).getWeaponType()) {
                    case 1:
                        new Bullet(this, x - (getWidth() - 100), y, 0, -6 * getSkyManager().getRate());
                        new Bullet(this, x + (getWidth() - 100), y, 0, -6 * getSkyManager().getRate());
                        break;
                    case 2:
                        new Bullet(this, x, y, 0, -6 * getSkyManager().getRate());
                        int End_time = (getSkyManager().getmTimeLeftInMillis() / 1000) % 60;
                        int time_difference = End_time - startTime;
                        System.out.println("this is the fking time differ" + time_difference);
                        if(time_difference > 1) {
                            new Missile(x - (getWidth()), y, 0, -6 * getSkyManager().getRate());
                            new Missile(x + (getWidth()-150), y, 0, -6 * getSkyManager().getRate());
                            startTime = End_time;
                        }
                        break;
                    default:
                        new Bullet(this, x, y, 0, -6 * getSkyManager().getRate());
                }

            }
        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
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
