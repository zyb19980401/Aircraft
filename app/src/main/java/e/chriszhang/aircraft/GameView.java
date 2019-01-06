package e.chriszhang.aircraft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.View;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;


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
    private Bitmap myAircraft = BitmapFactory.decodeResource(getResources(), R.mipmap.myaircraft);//加载图片
    private Bitmap background = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
    private Bitmap bullet = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet1);
    private Bitmap smallAircraft0 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane0);
    private Bitmap smallAircraft1 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane1);
    private Bitmap smallAircraft2 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane2);
    private Bitmap smallAircraft3 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane3);
    private Bitmap smallAircraft4 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane4);





    @Override
    protected void onDraw(Canvas g) {// draw everything on the screen
        super.onDraw(g);
        g.drawBitmap(background, null, skyManager.getBackGround().getRectangle(), p);//draw background
        g.drawBitmap(myAircraft, null, skyManager.getMyAircraft().getRectangle(), p);
        drawList(g, skyManager.getMyBulletLIst(),bullet);
        List temp  = new ArrayList<Bitmap>();
        temp.add(smallAircraft0);
        temp.add(smallAircraft1);
        temp.add(smallAircraft2);
        temp.add(smallAircraft3);
        temp.add(smallAircraft4);
        drawListaircrafts(g,skyManager.getEnemyAirCraftList(),temp);  // 用HashMap 重新写
    }



    protected  void drawList(Canvas g, List<? extends FlyingObject> list, Bitmap image){
        try{
        for(FlyingObject i : list){
            g.drawBitmap(image, null, i.getRectangle(),p);
        }}catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
    }

    protected  void drawListaircrafts(Canvas g, List<? extends AirCraft> list, List<Bitmap> images){
        try{
            for(AirCraft i : list){
                    if (i.getExploingState() ==0){
                        g.drawBitmap(images.get(0), null, i.getRectangle(),p);}
                     else if(i.getExploingState() ==1){
                        g.drawBitmap(images.get(1), null, i.getRectangle(),p);}

                     else if(i.getExploingState() ==2){
                        g.drawBitmap(images.get(2), null, i.getRectangle(),p);}
                    else if(i.getExploingState() ==3){
                        g.drawBitmap(images.get(3), null, i.getRectangle(),p);}
                    else if(i.getExploingState() ==4){
                        g.drawBitmap(images.get(4), null, i.getRectangle(),p);}
                }
            }catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
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
