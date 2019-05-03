package e.chriszhang.aircraft;

import static e.chriszhang.aircraft.GameActivity.getSkyManager;

public class MediumEnemyAirCraft extends EnemyAirCraft implements Runnable{

    private int bulletStartTime;

    boolean checkTime(int timeStart, int timePeriod){
        int timeDiff = getSkyManager().getmTimeLeftInMillis() - timeStart;
        if(timeDiff >= timePeriod){
            return true;
        }
        return false;
    }



    public MediumEnemyAirCraft(float X, float Y, float SpeedX, float SpeedY){
        super(X,Y,SpeedX,SpeedY);
        setHeight(165 * getSkyManager().getRate());
        setWidth(165 * getSkyManager().getRate());
        SetX(X);
        SetY(Y);
        setHP(2);
        getSkyManager().addMediumEnemyAirCraftList(this);
        bulletStartTime = getSkyManager().getmTimeLeftInMillis();
        new Thread(this).start();




    }

    @Override
    public void run(){
        while (isRunning() && getSkyManager().isRunning()) {
            try {
                Thread.sleep(50);
                boolean newBullet = checkTime(bulletStartTime, 700);
                int currentTime = getSkyManager().getmTimeLeftInMillis();
                float x = this.getRectangle().left + this.getWidth() / 2 - 50 * getSkyManager().getRate() / 2;
                float y = this.getRectangle().bottom - 50 * getSkyManager().getRate() / 2;
                if(newBullet){
                    new Bullet(this, x , y, 0, 6 * getSkyManager().getRate());
                    bulletStartTime = currentTime;
                }
                float newX = getRectangle().left + getSpeedX() * getSkyManager().getRate();
                float newY = getRectangle().top + getSpeedY() * getSkyManager().getRate();
                SetY(newY);
                SetX(newX);
                notifyObservers(newX, newY);
                if(this.isRunning()&&this.isHitBy(getSkyManager().getMyAircraft())){
                    setRunning(false);
                    getSkyManager().getMyAircraft().decreaseHP();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(isRunning()){
                setRunning(getRectangle().top < getSkyManager().getHeight());}  //如果已经被HIt 已经为False
        }
        notifyObservers( -1, -1);
        deleteObservers();

        while(getExploingState() < 4){
            try{Thread.sleep(100);
                int a = getExploingState();
                setExploingState(a +1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        getSkyManager().removeEnemyAirCraftList(this);
        getSkyManager().removeMediumEnemyAirCraftList(this);

    }

}
