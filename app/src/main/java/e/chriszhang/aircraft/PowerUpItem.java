package e.chriszhang.aircraft;

public class PowerUpItem extends  FlyingObject implements  Runnable {
    private float speedX;
    private float speedY;
    private float ItemX;
    private float ItemY;
    private boolean running;


    PowerUpItem(float ItemX, float ItemY, float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.ItemX = ItemX;
        this.ItemY = ItemY;
        setWidth(175 * getSkyManager().getRate());
        setHeight(175 * getSkyManager().getRate());
        SetX(ItemX);
        SetY(ItemY);
        setRunning(true);
        getSkyManager().addPowerUpItem(this);
        new Thread(this).start();
    }

    void setRunning(boolean running) {
        this.running = running;
    }

    boolean isRunning() {
        return running;
    }

    @Override
    public void run() {

        while (isRunning() && getSkyManager().isRunning()) {
            try {
                Thread.sleep(50);
                SetY((getRectangle().top + speedY * getSkyManager().getRate()));
                SetX((getRectangle().left + speedX * getSkyManager().getRate()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (isRunning()) {
                setRunning(getRectangle().top < getSkyManager().getHeight());
            }
            if (isHitBy(getSkyManager().getMyAircraft())){
                if( getSkyManager().getMyAircraft().getWeaponType() == 1){
                    getSkyManager().getMyAircraft().setWeaponType(2);
                    getSkyManager().getMyAircraft().setMissileStartTime((getSkyManager().getmTimeLeftInMillis() / 1000) % 60);
                }
                else if(getSkyManager().getMyAircraft().getWeaponType() == 0) {
                    getSkyManager().getMyAircraft().setWeaponType(1);
                }
                setRunning(false);
                getSkyManager().removePowerUpItem(this);
            }
        }
    }
}
