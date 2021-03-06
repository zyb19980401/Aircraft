package e.chriszhang.aircraft;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
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
     * the Hp heart in the game, there are at most three hearts.
     */
    RectF heart1 = new RectF();
    RectF heart2 = new RectF();
    RectF heart3 = new RectF();

    /**
     * the time images in the game, there are five elements of the timer.
     */
    RectF timeTenMin = new RectF();
    RectF timeMin = new RectF();
    RectF timeDot = new RectF();
    RectF timeSec = new RectF();
    RectF timePointSec = new RectF();

    private List<Bitmap> smallEnemy = new ArrayList<Bitmap>();

    private List<Bitmap> mediumEnemy = new ArrayList<Bitmap>();

    private List<Bitmap> bigEnemy = new ArrayList<Bitmap>();

    private List<Bitmap> timers = new ArrayList<Bitmap>();

    private List<Bitmap> defaultBullets = new ArrayList<Bitmap>();

    private List<Bitmap> bigEnemyHitted = new ArrayList<Bitmap>();


    /**
     * Set the time part  x and y.
     */
    private void setTime(){
        timePointSec.left = 1000;
        timePointSec.right = 1050;
        timePointSec.top = 0;
        timePointSec.bottom = 75;

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
        smallEnemy.add(smallAircraft0);
        smallEnemy.add(smallAircraft1);
        smallEnemy.add(smallAircraft2);
        smallEnemy.add(smallAircraft3);
        smallEnemy.add(smallAircraft4);
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
        //
        defaultBullets.add(bullet);
        defaultBullets.add(bullet1);
        defaultBullets.add(bullet2);
        //
        mediumEnemy.add(mediumAircraft0);
        mediumEnemy.add(mediumAircraft1);
        mediumEnemy.add(mediumAircraft2);
        mediumEnemy.add(mediumAircraft3);
        mediumEnemy.add(mediumAircraft4);
        //
        bigEnemy.add(bigAircraft0);
        bigEnemy.add(bigAircraft1);
        bigEnemy.add(bigAircraft2);
        bigEnemy.add(bigAircraft3);
        bigEnemy.add(bigAircraft4);
        bigEnemy.add(bigAircraft5);
        //
        bigEnemyHitted.add(bigAircraftHit1);
        bigEnemyHitted.add(bigAircraftHit2);
    }

    protected void drawTime(Canvas g) {
        int timer = skyManager.getmTimeLeftInMillis();
        int minutes = (timer / 1000) / 60;
        int seconds = (timer / 1000) % 60;
        String result = String.format("%02d%02d", minutes, seconds);
        setTime();
        int pointSec = Character.getNumericValue(result.charAt(3));  //this is the number of 0.1sec
        int sec = Character.getNumericValue(result.charAt(2));      //this is the number of sec
        int min = Character.getNumericValue(result.charAt(1));      //this is the number of one min
        int tenMin = Character.getNumericValue(result.charAt(0));   //this is the number of ten min
        g.drawBitmap(timers.get(pointSec), null, timePointSec, p);
        g.drawBitmap(timers.get(sec), null, timeSec, p);
        g.drawBitmap(timerDot, null, timeDot, p);
        g.drawBitmap(timers.get(min), null, timeMin, p);
        g.drawBitmap(timers.get(tenMin), null, timeTenMin, p);
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
    private Bitmap myAircraft = BitmapFactory.decodeResource(getResources(), R.mipmap.myaircraft);
    private Bitmap background = BitmapFactory.decodeResource(getResources(), R.mipmap.background);
    private Bitmap bulletEnemy = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet1);
    private Bitmap bullet = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet3a);
    private Bitmap bullet2 = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet3b);
    private Bitmap bullet1 = BitmapFactory.decodeResource(getResources(), R.mipmap.bullet3c);
    private Bitmap smallAircraft0 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane0);
    private Bitmap smallAircraft1 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane1);
    private Bitmap smallAircraft2 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane2);
    private Bitmap smallAircraft3 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane3);
    private Bitmap smallAircraft4 = BitmapFactory.decodeResource(getResources(),R.mipmap.smallplane4);
    private Bitmap mediumAircraft0 = BitmapFactory.decodeResource(getResources(),R.mipmap.mediumplane0);
    private Bitmap mediumAircraft1 = BitmapFactory.decodeResource(getResources(),R.mipmap.mediumplane1);
    private Bitmap mediumAircraft2 = BitmapFactory.decodeResource(getResources(),R.mipmap.mediumplane2);
    private Bitmap mediumAircraft3 = BitmapFactory.decodeResource(getResources(),R.mipmap.mediumplane3);
    private Bitmap mediumAircraft4 = BitmapFactory.decodeResource(getResources(),R.mipmap.mediumplane4);
    private Bitmap mediumAircrafthit = BitmapFactory.decodeResource(getResources(),R.mipmap.mediumplanehit);
    private Bitmap bigAircraft0 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplane0);
    private Bitmap bigAircraft1 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplane1);
    private Bitmap bigAircraft2 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplane2);
    private Bitmap bigAircraft3 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplane3);
    private Bitmap bigAircraft4 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplane4);
    private Bitmap bigAircraft5 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplane5);
    private Bitmap bigAircraftHit1 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplanehit1);
    private Bitmap bigAircraftHit2 = BitmapFactory.decodeResource(getResources(),R.mipmap.bigplanehit2);
    private Bitmap powerUpItem = BitmapFactory.decodeResource(getResources(), R.mipmap.powerupbullet);
    private Bitmap missile = BitmapFactory.decodeResource(getResources(), R.mipmap.missile);
    private Bitmap heart = BitmapFactory.decodeResource(getResources(), R.mipmap.hp);
    private Bitmap HP = BitmapFactory.decodeResource(getResources(), R.mipmap.heart);
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
        g.drawBitmap(background, null, skyManager.getBackGround().getRectangle(), p);   //draw background
        g.drawBitmap(myAircraft, null, skyManager.getMyAircraft().getRectangle(), p);   //draw my aircraft
        if(skyManager.Boss != null){
            drawBoss(g, (BigEnemyAircraft) skyManager.Boss, bigEnemy,bigEnemyHitted);}
//        drawList(g, skyManager.getMyBulletLIst(),bullet);
        drawBulletList(g,skyManager.getMyBulletLIst(), defaultBullets);  //draw the default bullet list
        drawList(g,skyManager.getEnemyBulletList(), bulletEnemy);   //draw the enemy bullet list
        drawList(g, skyManager.getPowerUpItemList(),powerUpItem);   //draw power up items list
        drawMissileList(g, skyManager.getMissileList(), missile);   //draw the missile list
        drawList(g, skyManager.getHpUpItemList(),HP);
        drawHp(g);  //draw the three HP heart
        drawTime(g);    //draw the timer onto the right-top
//        drawAircraftsList(g,skyManager.getMediumEnemyAirCraftList(), mediumEnemy);  //draw the medium enemy aircraft list
        drawMediumAircraftList(g, skyManager.getMediumEnemyAirCraftList(), mediumEnemy, mediumAircrafthit);
//        drawAircraftsList(g,skyManager.getSmallEnemyAircraftList(), smallEnemy);  // draw the small enemy aircraft List
        drawSmallEnemyLlist(g,skyManager.getSmallEnemyAircraftList(), smallEnemy);
//        drawAircraftsList(g,skyManager.getBigEnemyAircraftList(), bigEnemy);
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



    /**
     * draw the the list based on image
     */
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

    /**
     * draw the missile list
     */
    protected  void drawMissileList(Canvas g, List<Missile> list, Bitmap image){
        try{
            Bitmap bInput = image;
            for(Missile i : list){
                int Rotatingdegree = i.getRotatingDegree();
                int degrees = 0;
                if(Rotatingdegree!= 0){
                    degrees = Rotatingdegree + 180;
                }
                //rotation degree
                Bitmap bOutput = rotateBitmap(bInput, degrees);
                g.drawBitmap(bOutput, null, i.getRectangle(),p);
            }}catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
        catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("NullPointerException");
        }
    }

    protected  void drawAircraftsList(Canvas g, List<? extends AirCraft> list, List<Bitmap> images){
        try{
            for(AirCraft i : list){
                if (i.getExplodingState() == 0){
                    g.drawBitmap(images.get(0), null, i.getRectangle(),p);}
                else if(i.getExplodingState() ==1){
                    g.drawBitmap(images.get(1), null, i.getRectangle(),p);}

                else if(i.getExplodingState() ==2){
                    g.drawBitmap(images.get(2), null, i.getRectangle(),p);}
                else if(i.getExplodingState() ==3){
                    g.drawBitmap(images.get(3), null, i.getRectangle(),p);}
                else if(i.getExplodingState() ==4){
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

    protected  void drawSmallEnemyLlist(Canvas g, List<SmallEnemyAirCraft> list, List<Bitmap> images){
        try{
            Bitmap bInput = images.get(0);
            for(SmallEnemyAirCraft i : list){
                int Rotatingdegree = (int)i.getRoaRotatingdegree();
                int degrees = 0;
                if(Rotatingdegree!= 0){
                    degrees = Rotatingdegree;
                }
                //rotation degree
                Bitmap bOutput = rotateBitmap(bInput, degrees);
                g.drawBitmap(bOutput, null, i.getRectangle(),p);
                if (i.getExplodingState() == 0) {
                    g.drawBitmap(bOutput, null, i.getRectangle(), p);
//                        g.drawBitmap(images.get(0), null, i.getRectangle(),p);}
                }
                    else if(i.getExplodingState() ==1){
                        g.drawBitmap(images.get(1), null, i.getRectangle(),p);}

                    else if(i.getExplodingState() ==2){
                        g.drawBitmap(images.get(2), null, i.getRectangle(),p);}
                    else if(i.getExplodingState() ==3){
                        g.drawBitmap(images.get(3), null, i.getRectangle(),p);}
                    else if(i.getExplodingState() ==4){
                        g.drawBitmap(images.get(4), null, i.getRectangle(),p);}
            }}catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
        catch(NullPointerException e){
            e.printStackTrace();
            System.out.println("NullPointerException");
        }
    }



    protected  void drawMediumAircraftList(Canvas g, List<MediumEnemyAirCraft> list, List<Bitmap> images, Bitmap temp){
        try{
            for(MediumEnemyAirCraft i : list){
                if (i.getExplodingState() ==0) {
                    if (! i.isChangeHitImage()) {
                        g.drawBitmap(images.get(0), null, i.getRectangle(), p);
                    } else {
                        g.drawBitmap(temp, null, i.getRectangle(), p);
                    }
                    }
                else if(i.getExplodingState() ==1){
                    g.drawBitmap(images.get(1), null, i.getRectangle(),p);}

                else if(i.getExplodingState() ==2){
                    g.drawBitmap(images.get(2), null, i.getRectangle(),p);}
                else if(i.getExplodingState() ==3){
                    g.drawBitmap(images.get(3), null, i.getRectangle(),p);}
                else if(i.getExplodingState() ==4){
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

    protected  void drawBoss (Canvas g, BigEnemyAircraft Boss, List<Bitmap> images, List<Bitmap> temp){
        try{
            if(Boss != null) {
                if (Boss.getExplodingState() == 0) {
                    int Hitimages = Boss.getHitimage();
                    if (Hitimages == 0) {
                        g.drawBitmap(images.get(0), null, Boss.getRectangle(), p);
                    }
                    if (Hitimages == 1) {
                        g.drawBitmap(temp.get(0), null, Boss.getRectangle(), p);
                    }
                    if (Hitimages == 2) {
                        g.drawBitmap(temp.get(1), null, Boss.getRectangle(), p);
                    }
                } else if (Boss.getExplodingState() == 1) {
                    g.drawBitmap(images.get(1), null, Boss.getRectangle(), p);
                } else if (Boss.getExplodingState() == 2) {
                    g.drawBitmap(images.get(2), null, Boss.getRectangle(), p);
                } else if (Boss.getExplodingState() == 3) {
                    g.drawBitmap(images.get(3), null, Boss.getRectangle(), p);
                } else if (Boss.getExplodingState() == 4) {
                    g.drawBitmap(images.get(4), null, Boss.getRectangle(), p);
                } else if (Boss.getExplodingState() == 5) {
                    g.drawBitmap(images.get(5), null, Boss.getRectangle(), p);
                }
            }

        }catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
        catch(java.lang.NullPointerException e){
            e.printStackTrace();
        }
    }


    protected  void drawBulletList(Canvas g, List<? extends Bullet> list, List<Bitmap> images){
        try{
            for(Bullet i : list){
                if (i.getExploingState() ==0){
                    System.out.println("exlotion state  为 0000");
                    g.drawBitmap(images.get(0), null, i.getRectangle(),p);}
                else if(i.getExploingState() ==1){
                    System.out.println("exlotion state  为 1111");
                    g.drawBitmap(images.get(1), null, i.getRectangle(),p);}
                else if(i.getExploingState() ==2){
                    System.out.println("exlotion state  为 2222");
                    g.drawBitmap(images.get(2), null, i.getRectangle(),p);}
            }
        }catch(ConcurrentModificationException e){
            e.printStackTrace();
            System.out.println("ConcurrentModificationException");
        }
        catch(java.lang.NullPointerException e){
            e.printStackTrace();
        }
    }


    private Bitmap rotateBitmap(Bitmap bitmap, int rotationAngleDegree){

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int newW=w, newH=h;
        if (rotationAngleDegree==90 || rotationAngleDegree==270){
            newW = h;
            newH = w;
        }
        Bitmap rotatedBitmap = Bitmap.createBitmap(newW,newH, bitmap.getConfig());
        Canvas canvas = new Canvas(rotatedBitmap);

        Rect rect = new Rect(0,0,newW, newH);
        Matrix matrix = new Matrix();
        float px = rect.exactCenterX();
        float py = rect.exactCenterY();
        matrix.postTranslate(-bitmap.getWidth()/2, -bitmap.getHeight()/2);
        matrix.postRotate(rotationAngleDegree);
        matrix.postTranslate(px, py);
        canvas.drawBitmap(bitmap, matrix, new Paint( Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG ));
        matrix.reset();

        return rotatedBitmap;
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
