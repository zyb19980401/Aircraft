package e.chriszhang.aircraft;

public class Bullet extends FlyingObject implements Runnable{

    private AirCraft airCraft;
    private float speedX;
    private  float speedY;
    private final int attack = 1;
    private float bulletX;
    private float bulletY;
    private  boolean flyingUp;
    private boolean running;
    private int exploingState;
    private boolean isExploding = false;



    Bullet(AirCraft airCraft, float bulletX, float bulletY, float speedX, float speedY ){

        this.airCraft = airCraft;
        this.speedX = speedX;
        this.speedY = speedY;
        this.bulletX = bulletX;
        this.bulletY = bulletY;
        if (airCraft instanceof MyAircraft){
            flyingUp = true;
            getSkyManager().addMyBulletLIst(this);
            setWidth(200 * getSkyManager().getRate());
            setHeight(200 * getSkyManager().getRate());
            SetX(bulletX - getWidth()/3);
            SetY(bulletY - getHeight()/2);
        }
        else if( airCraft instanceof EnemyAirCraft){  // set the bullet to corresponding aircraft's bullet list  list
            getSkyManager().addEnemyBulletList(this);
            setWidth(50 * getSkyManager().getRate());
            setHeight(50 * getSkyManager().getRate());
            SetX(bulletX - getWidth()/3);
            SetY(bulletY - getHeight()/2);
        }

        setRunning(true);
        new Thread(this).start();
    }

    public int getExploingState() {
        return exploingState;
    }

    public void setExploingState(int exploingState) {
        this.exploingState = exploingState;
    }


    void setRunning( boolean running){
        this.running = running;
    }


    @Override
    public void run(){
        try{
        while(running && getSkyManager().isRunning()){
            try{
                Thread.sleep(5);
            } catch(InterruptedException e){
                e.printStackTrace();
            }

            SetY(getRectangle().top + speedY);
            SetX(getRectangle().left + speedX);
            if (flyingUp) {
                for (EnemyAirCraft airCraft: getSkyManager().getEnemyAirCraftList()){ // check enemyaircraftlist
                    if(this.isHitBy(airCraft)){
                        airCraft.decreaseHpBy(attack);
                        airCraft.setHited(true);   //in case we want to do some animation when it gets hitted.
                        System.out.println(airCraft.getHP());
                        if(!airCraft.isStillHealth()) {
                            airCraft.setRunning(false);
                            getSkyManager().addNumkill(1);
                        }
                        this.setRunning(false);
                        isExploding = true;
                        break;
                    }
                }
                if(running) {
                    running = getRectangle().top + getHeight() > 0;
                }
            }
            else {
                if(this.isHitBy(getSkyManager().getMyAircraft())){
                    getSkyManager().getMyAircraft().decreaseHpBy(attack);
                    this.setRunning(false);
                }
                if(running) {
                    running = getRectangle().top < getSkyManager().getHeight();
                }
            }
        }
        SetX(getRectangle().left);
        SetY(getRectangle().top);

        if(isExploding) {
            while (getExploingState() < 3) {
                try {
                    Thread.sleep(100);
                    int a = getExploingState();
                    setExploingState(a + 1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        }catch(java.util.ConcurrentModificationException exception){
            exception.printStackTrace();
        }

        if(airCraft instanceof  MyAircraft){
            getSkyManager().removeMyBulletLIst(this);
        }
        else if(airCraft instanceof EnemyAirCraft){
            getSkyManager().removeEnemyBulletList(this);
        }
    }
}
