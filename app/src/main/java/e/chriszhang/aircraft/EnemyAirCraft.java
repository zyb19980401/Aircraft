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
    }

    public synchronized  void addObserver(Missile missile){
        if(missile == null){
            throw new NullPointerException();
        }
        if(!obs.contains(missile)){
            obs.addElement(missile);
        }
    }

    public synchronized void deleteObservers(Missile missile){
        obs.removeElement(missile);
    }

    public void notifyObservers(int X, int Y){
        Object[] arrLocal;

        synchronized(this){
            if(!changed)
                return;
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

        while (isRunning() && getSkyManager().isRunning()) {
            try {
                Thread.sleep(50);
                SetY((getRectangle().top + speedY * getSkyManager().getRate()));
                SetX((getRectangle().left + speedX * getSkyManager().getRate()));
                if(this.isRunning()&&this.isHitBy(getSkyManager().getMyAircraft())){
                    setRunning(false);
                    getSkyManager().getMyAircraft().decreaseHP();
                    System.out.println("撞到自己的飞机啦！！！！" );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(isRunning()){
                setRunning(getRectangle().top < getSkyManager().getHeight());}  //如果已经被HIt 已经为False
        }
        while(getExploingState() < 4){
            try{Thread.sleep(100);
                int a = getExploingState();
                setExploingState(a +1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        getSkyManager().removeEnemyAirCraftList(this); //TODO this need to be changed
    }
}
