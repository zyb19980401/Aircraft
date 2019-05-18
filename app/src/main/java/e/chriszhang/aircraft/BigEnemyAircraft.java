package e.chriszhang.aircraft;

public class BigEnemyAircraft extends EnemyAirCraft implements Runnable{

    private int bulletStartTime;

    public boolean isChangeHitImage() {
        return changeHitImage;
    }

    private boolean changeHitImage;

    private int hittedTime;

    private int attackType;


    public BigEnemyAircraft(float X, float Y, float SpeedX, float SpeedY){
        super(X,Y,SpeedX,SpeedY);
        setHeight(300 * getSkyManager().getRate());
        setWidth(300 * getSkyManager().getRate());
        SetX(X);
        SetY(Y);
        setHP(50);
        getSkyManager().addBigEnemyAirCraftList(this);
        bulletStartTime = getSkyManager().getmTimeLeftInMillis();

        new Thread(this).start();
    }

    private void attack (int attackType){
        float x = this.getRectangle().left + this.getWidth() / 2 - 50 * getSkyManager().getRate() / 2;
        float y = this.getRectangle().bottom - 50 * getSkyManager().getRate() / 2;
        switch(attackType){
            case 1:
                new  Bullet(this, x , y, 0, 6 * getSkyManager().getRate());
                new  Bullet(this, x , y, 1*getSkyManager().getRate(), 5 * getSkyManager().getRate());
                new  Bullet(this, x , y, -1*getSkyManager().getRate(), 5 * getSkyManager().getRate());
        }

    }




    @Override
    public void run(){
        System.out.println("wtttff" + getSkyManager().getBigEnemyAircraftList().size());
//        while (isRunning() && getSkyManager().isRunning()) {
//            System.out.println("wtttff" + getSkyManager().getBigEnemyAircraftList().size());
//            System.out.println("fffff11111\n");
//            try {
//                Thread.sleep(50);
//                boolean newBullet = checkTime(bulletStartTime, 700);
//                int currentTime = getSkyManager().getmTimeLeftInMillis();
//                System.out.println("ffff22222\n");
////                float x = this.getRectangle().left + this.getWidth() / 2 - 50 * getSkyManager().getRate() / 2;
////                float y = this.getRectangle().bottom - 50 * getSkyManager().getRate() / 2;
////                if(newBullet){
////                    new Bullet(this, x , y, 0, 6 * getSkyManager().getRate());
////
////                }
//
//                if(newBullet){
//                    attack(1);
//                    bulletStartTime = currentTime;
//                }
//                float newX = getRectangle().left + getSpeedX() * getSkyManager().getRate();
//                float newY = getRectangle().top + getSpeedY() * getSkyManager().getRate();
//                SetY(newY);
//                SetX(newX);
//                System.out.println("ffff333333\n");
//                notifyObservers(newX, newY);
//                if(this.isRunning()&&this.isHitBy(getSkyManager().getMyAircraft())){
////                    setRunning(false);
//                    getSkyManager().getMyAircraft().decreaseHpBy(1);// hit by Enemy will decrease HP by 1
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            if(isRunning()){                //如果已经被HIt 已经为False
//                setRunning(getRectangle().top < getSkyManager().getHeight());
//            }
//            if(isHited()){
//                changeHitImage = true;
//                System.out.println("fffff55555\n");
//            }
//            System.out.println("ffff666\n");
//            System.out.println("wtttff" + getSkyManager().getBigEnemyAircraftList().size());
////            if(isHited() && hittedTime == 0){
////                hittedTime = getSkyManager().getmTimeLeftInMillis();
////                changeHitImage = true;
////            }
////            boolean changeBack = checkTime(bulletStartTime, 500);
////            if(changeBack){
////                setHited(false);
////                changeHitImage = false;
////            }
//        }
//        notifyObservers( -1, -1);
//        deleteObservers();
//
//        while(getExplodingState() < 4){
//            try{Thread.sleep(100);
//                int a = getExplodingState();
//                setExplodingState(a +1);
//            }
//            catch (Exception e){
//                e.printStackTrace();
//            }
//        }
//        getSkyManager().removeEnemyAirCraftList(this);
//        getSkyManager().removeBigEnemyAircraftList(this);

    }
}
