package e.chriszhang.aircraft;

public class SmallEnemyAirCraft extends EnemyAirCraft {
    public SmallEnemyAirCraft(float X, float Y, float SpeedX, float SpeedY){
        super(X,Y,SpeedX,SpeedY);
        setHeight(120 * getSkyManager().getRate());
        setWidth(120 * getSkyManager().getRate());
        SetX(X);
        SetY(Y);
        setHP(1);
        getSkyManager().addSmallEnemyAircraftList(this);
        new Thread(this).start();
    }

    @Override
    public void run(){
        while (isRunning() && getSkyManager().isRunning()) {
            try {
                    Thread.sleep(50);
                    float newX = getRectangle().left + getSpeedX() * getSkyManager().getRate();
                    float newY = getRectangle().top + getSpeedY() * getSkyManager().getRate();
                    SetY(newY);
                    SetX(newX);
                    notifyObservers(newX, newY);
                    if(this.isRunning()&&this.isHitBy(getSkyManager().getMyAircraft())){
                        setRunning(false);
                        getSkyManager().getMyAircraft().decreaseHpBy(1);
                    }
            } catch (Exception e) {
                    e.printStackTrace();
            }
            if(isRunning()){
                setRunning(getRectangle().top < getSkyManager().getHeight());
            }  //如果已经被HIt 已经为False
        }
        notifyObservers( -1, -1);
        deleteObservers();

        while(getExplodingState() < 4){
            try{Thread.sleep(100);
                int a = getExplodingState();
                setExplodingState(a +1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        getSkyManager().removeEnemyAirCraftList(this); //TODO this need to be changed
        getSkyManager().removeSmallEnemyAirCraftList(this);
    }



}
