package e.chriszhang.aircraft;


/**
 * The background class.
 */
public class BackGround extends FlyingObject implements Runnable {
    /**
     * The constructor of BackGround.
     */
    BackGround() {
        setWidth(getSkyManager().getWidth());
        setHeight(getSkyManager().getHeight()*2);//background height is twice of the height of screen
        SetX(0);
        SetY(-getSkyManager().getHeight());
        new Thread(this).start();
    }

    @Override
    public void run() {
        //this controls the background moving downwards all the time
        while (getSkyManager().isRunning()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (getRectangle().top + 2 <= 0) {
                SetY(getRectangle().top + 2);
            } else {
                SetY(-getSkyManager().getHeight());
            }
        }
    }
}
