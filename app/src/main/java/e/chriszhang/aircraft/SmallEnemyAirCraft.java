package e.chriszhang.aircraft;

public class SmallEnemyAirCraft extends EnemyAirCraft {
    public SmallEnemyAirCraft(float X, float Y, float SpeedX, float SpeedY){
        super(X,Y,SpeedX,SpeedY);
        setHeight(120 * getSkyManager().getRate());
        setWidth(120 * getSkyManager().getRate());
        SetX(X);
        SetY(Y);
        setHP(1);
        getSkyManager().addEnemyAirCraftList(this);
        new Thread(this).start();

    }


    @Override
    public void run(){
        super.run();
    }



}
