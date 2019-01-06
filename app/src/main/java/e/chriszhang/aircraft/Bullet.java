package e.chriszhang.aircraft;

public class Bullet extends FlyingObject implements Runnable{

    private AirCraft airCraft;
    private float speedX;
    private  float speedY;
    private float bulletX;
    private float bulletY;
    private  boolean flyingUp;
    private boolean running;



    Bullet(AirCraft airCraft, float bulletX, float bulletY, float speedX, float speedY ){

        this.airCraft = airCraft;
        this.speedX = speedX;
        this.speedY = speedY;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        setWidth(50 * getSkyManager().getRate());
        setHeight(50 * getSkyManager().getRate());
        SetX(bulletX);
        SetY(bulletY);
        if (airCraft instanceof MyAircraft){
            flyingUp = true;
            getSkyManager().addMyBulletLIst(this);
        }
        else if( airCraft instanceof EnemyAirCraft){  // set the bullet to corresponding aircraft's bullet list  list
            getSkyManager().addEnemyBulletList(this);
        }
        setRunning(true);
        new Thread(this).start();
    }

    void setRunning( boolean running){
        this.running = running;
    }


    @Override
    public void run(){
        while(running && getSkyManager().isRunning()){
            try{
                Thread.sleep(5);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            SetY(getRectangle().top + speedY);
            SetX(getRectangle().left + speedX);
            if (flyingUp) {
                running = getRectangle().top + getHeight() > 0;
                for (AirCraft airCraft: getSkyManager().getEnemyAirCraftList()){ // check enemyaircraftlist
                    if(this.isHitBy(airCraft)){
                        airCraft.setRunning(false);
                        this.setRunning(false);
                    }
                }
            }
            else {
                running = getRectangle().top < getSkyManager().getHeight();
            }
        }

        if(airCraft instanceof  MyAircraft){
            getSkyManager().removeMyBulletLIst(this);
        }
        else if(airCraft instanceof EnemyAirCraft){
            getSkyManager().removeEnemyBulletList(this);
        }
    }





}
