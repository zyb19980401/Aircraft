package e.chriszhang.aircraft;

import java.util.Vector;

public class EnemyAirCraft extends AirCraft{
    private float x;
    private float y;
    private float speedX;
    private float speedY;
    private boolean changed;
    private Vector<Missile> obs;




    EnemyAirCraft(float X , float Y, float SpeedX, float SpeedY){
        super();
        this.x = X;
        this.y = Y;
        this.speedX = SpeedX;
        this.speedY = SpeedY;
        changed = false;
        obs = new Vector<>();
        getSkyManager().addEnemyAirCraftList(this);
    }

    public synchronized  void addObserver(Missile missile){
        if(missile == null){
            throw new NullPointerException();
        }
        if(!obs.contains(missile)){
            obs.addElement(missile);
            System.out.println("observer added!!!!");
        }
    }

    public synchronized void deleteObservers(Missile missile){
        obs.removeElement(missile);
    }

    public void notifyObservers(float X, float Y){
        Object[] arrLocal;

        synchronized(this){
            arrLocal = obs.toArray();
            clearChanged();
        }
        for(int i = arrLocal.length -1; i>=0; i--){
            ((Missile)arrLocal[i]).update(this, X, Y);
        }
    }

    public synchronized void deleteObservers(){
        obs.removeAllElements();
    }

    protected synchronized void setChanged(){
        changed = true;
    }

    protected synchronized void clearChanged(){
        changed = false;
    }

    public synchronized boolean hasChanged(){
        return changed;
    }

    public synchronized int countObservers(){
        return obs.size();
    }

    @Override
    public void run() {


    }

    public float getSpeedX() {
        return speedX;
    }

    public float getSpeedY() {
        return speedY;
    }
}
