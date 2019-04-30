package e.chriszhang.aircraft;

public class EnemyAirCraft extends AirCraft{
    private float x;
    private float y;
    private float speedX;
    private float speedY;




    EnemyAirCraft(float X , float Y, float SpeedX, float SpeedY){
        super();
        this.x = X;
        this.y = Y;
        this.speedX = SpeedX;
        this.speedY = SpeedY;
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
