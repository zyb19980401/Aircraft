package e.chriszhang.aircraft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;


/**
 * The game view class.
 */
public class GameView extends View {

    /**
     * A new paint.
     */
    private Paint p = new Paint();

    /**
     * The context.
     */
    private Context context;

    /**
     * The skyManager.
     */
    private SkyManager skyManager;

    /**
     * the bullet style.
     */
    private static int bulletStyle = R.mipmap.bullet1;

    /**
     * The constructor of the game view.
     */
    public GameView(Context context,SkyManager skyManager) {
        super(context);
        this.context = context;
        this.skyManager = skyManager;
        new Thread(new reDraw()).start();
    }

    // Load images of my crafts, enemy crafts, bullet and background
    private Bitmap myAircraftImage = BitmapFactory.decodeResource(getResources(), R.mipmap.myaircraft);//加载图片
    private Bitmap background = BitmapFactory.decodeResource(getResources(), R.mipmap.background);


    @Override
    protected void onDraw(Canvas g) {// draw everything on the screen
        super.onDraw(g);
        g.drawBitmap(background, null, skyManager.getBackGround().getRectangle(), p);//draw background
        g.drawBitmap(myAircraftImage, null, skyManager.getMyAircraft().getRectangle(), p);
    }

    /*
     * Citation
     * Get screen display metrics in application class
     * https://stackoverflow.com/questions/9114436/how-to-get-screen-display-metrics-in-application-class
     * this method gets called on the reDrawing processes.
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldW, int oldH) {
        // this method can get height and width of the screen
        super.onSizeChanged(w, h, oldW, oldH);
        skyManager.setWidth(w);
        skyManager.setHeight(h);
        DisplayMetrics metrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        skyManager.setRate((float) (Math.sqrt(skyManager.getWidth() * skyManager.getHeight()) / Math.sqrt(width * height)));
        skyManager.setMyAircraft(new MyAircraft());
        skyManager.setBackGround(new BackGround());
    }

    /*
     * Citation
     * Force view to redraw
     * https://forums.xamarin.com/discussion/37750/force-view-to-redraw
     */
    private class reDraw implements Runnable {
        @Override
        public void run() {
            // refresh pages every 1 millis.
            while (skyManager.isRunning()) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // refresh
                postInvalidate();

            }
        }
    }
}
