package e.chriszhang.aircraft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
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
     * the Hp heart in the game.
     */
    RectF heart1 = new RectF();
    RectF heart2 = new RectF();
    RectF heart3 = new RectF();
    RectF timeTenMin = new RectF();
    RectF timeMin = new RectF();
    RectF timeDot = new RectF();
    RectF timeSec = new RectF();
    RectF timeMilisec = new RectF();


    private List temp  = new ArrayList<Bitmap>();

    private List timers = new ArrayList<Bitmap>();

    /**
     * Set the time part  x and y.
     */
    private void setTime(){
        timeMilisec.left = 1000;
        timeMilisec.right = 1050;
        timeMilisec.top = 0;
        timeMilisec.bottom = 75;

        timeSec.left = 950;
        timeSec.right = 1000;
        timeSec.top = 0;
        timeSec.bottom = 75;


        timeDot.left = 925;
        timeDot.right = 950;
        timeDot.top = 0;
        timeDot.bottom = 75;


        timeMin.left = 875;
        timeMin.right = 925;
        timeMin.top = 0;
        timeMin.bottom = 75;


        timeTenMin.left = 825;
        timeTenMin.right = 875;
        timeTenMin.top = 0;
        timeTenMin.bottom = 75;
    }

    private void setPictureList(){
        temp.add(smallAircraft0);
        temp.add(smallAircraft1);
        temp.add(smallAircraft2);
        temp.add(smallAircraft3);
        temp.add(smallAircraft4);

        //need to use a helper, and should not be in onDraw.
//        List timers = new ArrayList<Bitmap>();
        timers.add(timer0);
        timers.add(timer1);
        timers.add(timer2);
        timers.add(timer3);
        timers.add(timer4);
        timers.add(timer5);
        timers.add(timer6);
        timers.add(timer7);
        timers.add(timer8);
        timers.add(timer9);
    }

    protected void drawTime(Canvas g) {
        int timer = skyManager.getmTimeLeftInMillis();

        int minutes = (timer / 1000) / 60;
        int seconds = (timer / 1000) % 60;
        String result = String.format("%02d%02d", minutes, seconds);
        setTime();
        int milsec = Character.getNumericValue(result.charAt(3));  //this is the number of 0.1sec
        int sec = Character.getNumericValue(result.charAt(2));      //this is the number of sec
        int min = Character.getNumericValue(result.charAt(1));      //this is the number of one min
        int tenmin = Character.getNumericValue(result.charAt(0));   //this is the number of ten min

        g.drawBitmap((Bitmap) timers.get(milsec), null, timeMilisec, p);
        g.drawBitmap((Bitmap) timers.get(sec), null, timeSec, p);
        g.drawBitmap(timerDot, null, timeDot, p);
        g.drawBitmap((Bitmap) timers.get(min), null, timeMin, p);
        g.drawBitmap((Bitmap) timers.get(tenmin), null, timeTenMin, p);
    }



    /**
     * Set the Heart x and y.
     */
    private void setHeart(){
        heart1.left = 0;
        heart1.bottom = 0;
        heart1.right = 100;
        heart1.top = 100;

        heart2.left = 100;
        heart2.bottom = 0;
        heart2.right = 200;
        heart2.top = 100;

        heart3.left = 200;
        heart3.bottom = 0;
        heart3.right = 300;
        heart3.top = 100;
    }
    /**
     * The constructor of the game view.
     */
    public GameView(Context context,SkyManager skyManager) {
        super(context);
        this.context = context;
        this.skyManager = skyManager;
        setPictureList();
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
    private Bitmap powerUpItem = BitmapFactory.decodeResource(getResources(), R.mipmap.powerup);
    private Bitmap missile = BitmapFactory.decodeResource(getResources(), R.mipmap.missile);
    private Bitmap heart = BitmapFactory.decodeResource(getResources(), R.mipmap.hp);
    private Bitmap timer0 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer0);
    private Bitmap timer1 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer1);
    private Bitmap timer2 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer2);
    private Bitmap timer3 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer3);
    private Bitmap timer4 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer4);
    private Bitmap timer5 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer5);
    private Bitmap timer6 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer6);
    private Bitmap timer7 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer7);
    private Bitmap timer8 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer8);
    private Bitmap timer9 = BitmapFactory.decodeResource(getResources(), R.mipmap.timer9);
    private Bitmap timerDot = BitmapFactory.decodeResource(getResources(), R.mipmap.timerdot);





    @Override
    protected void onDraw(Canvas g) {// draw everything on the screen
        super.onDraw(g);
        g.drawBitmap(background, null, skyManager.getBackGround().getRectangle(), p);//draw background
        g.drawBitmap(myAircraft, null, skyManager.getMyAircraft().getRectangle(), p);
        drawList(g, skyManager.getMyBulletLIst(),bullet);
        drawList(g, skyManager.getPowerUpItemList(),powerUpItem);
        drawList(g, skyManager.getMissileList(), missile);
        drawHp(g);
        drawTime(g);
        drawListaircrafts(g,skyManager.getEnemyAirCraftList(),temp);  // 用HashMap 重新写 整理出一个Funciton.
    }

    protected void drawHp(Canvas g){
        int hp = skyManager.getMyAircraft().getHP();
        setHeart();
        switch(hp) {
            case 3:
                g.drawBitmap(heart, null, heart1, p);
                g.drawBitmap(heart, null, heart2, p);
                g.drawBitmap(heart, null, heart3, p);
                break;
            case 2:
                g.drawBitmap(heart, null, heart1, p);
                g.drawBitmap(heart, null, heart2, p);
                break;
            case 1:
                g.drawBitmap(heart, null, heart1, p);
        }
    }



    protected  void drawList(Canvas g, List<? extends FlyingObject> list, Bitmap image){
        try{
            for(FlyingObject i : list){
                g.drawBitmap(image, null, i.getRectangle(),p);
            }}catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
        catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("NullPointerException");
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
        catch(java.lang.NullPointerException e){
            e.printStackTrace();
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
