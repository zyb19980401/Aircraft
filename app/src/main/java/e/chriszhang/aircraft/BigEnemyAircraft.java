package e.chriszhang.aircraft;

public class BigEnemyAircraft extends EnemyAirCraft implements Runnable{

    private int bulletStartTime;

    private int AttackStarttime;

    public int getHitimage() {
        return hitImage;
    }

    private void setHitImage(int target){
        hitImage = target;
    }

    private int hitImage = 0;

    private int hittedTime;

    private int attackType = 1;

    private int SpeedX;

    private boolean checker = true;



    public BigEnemyAircraft(float X, float Y, float SpeedX, float SpeedY){
        super(X,Y,SpeedX,SpeedY);

        setHeight(300 * getSkyManager().getRate());
        setWidth(300 * getSkyManager().getRate());
        SpeedX = SpeedX;
        SetX(X);
        SetY(Y);
        setHP(50);
        getSkyManager().addBigEnemyAirCraftList(this);
        bulletStartTime = getSkyManager().getmTimeLeftInMillis();
        AttackStarttime = getSkyManager().getmTimeLeftInMillis();
        new Thread(this).start();
    }

    private void attack (int attackType){
        float x = this.getRectangle().left + this.getWidth() / 2 - 50 * getSkyManager().getRate() / 2;
        float y = this.getRectangle().bottom - 50 * getSkyManager().getRate() / 2;
        switch(attackType){
            case 1:
                new  Bullet(this, x , y, 0, 3 * getSkyManager().getRate());
                new  Bullet(this, x , y, 2*getSkyManager().getRate(), 3 * getSkyManager().getRate());
                new  Bullet(this, x , y, -2*getSkyManager().getRate(), 3 * getSkyManager().getRate());
            case 2:
                new Bullet(this, x , y, 0, 5 * getSkyManager().getRate());

        }

    }

    private void setDamageImage(){
        int currentHp = this.getHP();
        if (40 < currentHp && currentHp <=50){
            setHitImage(0);
        }
        if (25 < currentHp && currentHp <=40){
            setHitImage(1);
        }
        if(0 < currentHp && currentHp <= 25){
            setHitImage(2);
        }

    }


    private void moveLeftAndRight(){
        if(getRectangle().right >= getSkyManager().getWidth() || getRectangle().left <= 0){
            setSpeedX(-getSpeedX());
            System.out.println("change the direction");
        }
    }

    @Override
    public void run(){
        while (isRunning() && getSkyManager().isRunning()) {
            setDamageImage();
            boolean changeAttack = checkTime(AttackStarttime,  6000);
            if(changeAttack){
                AttackStarttime =  getSkyManager().getmTimeLeftInMillis();
                if(attackType == 1){
                    attackType = 0;
                }
                else if (attackType == 0) {
                    attackType = 1;
                }
            }

            try {
                if (attackType == 0){
                    if(getRectangle().left >= 0){
                        if(checker) {
                            SetX(10);
                            checker = false;
                        }
                        boolean newAttack = checkTime(bulletStartTime, 40);
                        int currentTime = getSkyManager().getmTimeLeftInMillis();
                        if (newAttack) {
                            SetX(getRectangle().left + 30);
                            attack(2);
                            bulletStartTime = currentTime;
                        }
                        if(getRectangle().left >= 600){
                            checker = true;
                        }
                    }

                }
                else {
                    Thread.sleep(50);
                    if (isRunning()) {
                        moveLeftAndRight();
                    }  //如果已经被HIt 已经为False
                    float newX = getRectangle().left + getSpeedX() * getSkyManager().getRate();
                    float newY = getRectangle().top + getSpeedY() * getSkyManager().getRate();
                    boolean newAttack = checkTime(bulletStartTime, 1000);
                    int currentTime = getSkyManager().getmTimeLeftInMillis();
                    if (newAttack) {
                        attack(1);
                        bulletStartTime = currentTime;
                    }

                    SetY(newY);
                    SetX(newX);
                    notifyObservers(newX, newY);
                }
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

        while(getExplodingState() < 6){
            try{Thread.sleep(100);
                int a = getExplodingState();
                setExplodingState(a +1);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        getSkyManager().removeEnemyAirCraftList(this);
        getSkyManager().removeBigEnemyAircraftList(this);

    }
}
