package e.chriszhang.aircraft;

public class AirCraft extends  FlyingObject implements  Runnable{


    /**
     * the HP of the airCraft
     */
    private int HP;

    /**
     * the aircraftestate of the current aircraft;
     */
    private AirCraftState airCraftState;


    @Override
    public void run(){
        try{
            if(this instanceof MyAircraft){
                Thread.sleep(200);
                new Bullet();
            }

        }
        catch(InterruptedException e) {
            e.printStackTrace();
        }
    }


    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public AirCraftState getAirCraftState() {
        return airCraftState;
    }

    public void setAirCraftState(AirCraftState airCraftState) {
        this.airCraftState = airCraftState;
    }




}
