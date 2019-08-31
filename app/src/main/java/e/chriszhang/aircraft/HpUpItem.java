package e.chriszhang.aircraft;

public class HpUpItem extends  FlyingObject implements  Runnable{
    private float speedX;
    private float speedY;
    private float ItemX;
    private float ItemY;
    private boolean running;


    HpUpItem(float ItemX, float ItemY, float speedX, float speedY) {
        this.speedX = speedX;
        this.speedY = speedY;
        this.ItemX = ItemX;
        this.ItemY = ItemY;
        setWidth(100 * getSkyManager().getRate());
        setHeight(100 * getSkyManager().getRate());
        SetX(ItemX);
        SetY(ItemY);
        setRunning(true);
        getSkyManager().addHpUpItemList(this);
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
            if (isHitBy(getSkyManager().getMyAircraft())) {
                int currentHp = getSkyManager().getMyAircraft().getHP();
                if (currentHp < 3) {
                    getSkyManager().getMyAircraft().setHP(currentHp + 1);
                }
                getSkyManager().removeHpUpItem(this);
            }
        }
            setRunning(false);
            getSkyManager().removeHpUpItem(this);
        }
    }

