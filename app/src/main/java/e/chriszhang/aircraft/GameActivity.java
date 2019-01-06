package e.chriszhang.aircraft;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

public class GameActivity extends AppCompatActivity {

    //The coordinates when the screen is pressed
    private float x;
    private float y;

    //The coordinates of myAirCraft when the screen is pressed
    private float myAircraftX;
    private float myAircraftY;



    /**
     * the SkyManager of the current game;
     */
    private static SkyManager skyManager;


    /**
     * @return the current skymanger
     */
    public static SkyManager getSkyManager() {
        return skyManager;
    }



    private View.OnTouchListener onTouchListener;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        skyManager = new SkyManager();
        skyManager.setRunning(true);
        new Thread(skyManager).start();
        setOnTouchListener();
        GameView gameView = new GameView(this, getSkyManager());
        setContentView(gameView);
        gameView.setOnTouchListener(onTouchListener);
    }

    private void setOnTouchListener() {
        onTouchListener = (view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                view.performClick();
                x = motionEvent.getX();
                y = motionEvent.getY();
                myAircraftX = skyManager.getMyAircraft().getRectangle().left;
                myAircraftY = skyManager.getMyAircraft().getRectangle().top;
            }
            float newX = myAircraftX + motionEvent.getX() - x;
            float newY = myAircraftY + motionEvent.getY() - y;
            // my aircraft can't fly out of the screen
            if (newX >= skyManager.getWidth() - skyManager.getMyAircraft().getWidth() / 2) {
                newX = skyManager.getWidth() - skyManager.getMyAircraft().getWidth() / 2;
            }
            if (newX <= -skyManager.getMyAircraft().getWidth() / 2) {
                newX = -skyManager.getMyAircraft().getWidth() / 2;
            }
            if (newY >= skyManager.getHeight() - skyManager.getMyAircraft().getHeight() / 2) {
                newY = skyManager.getHeight() - skyManager.getMyAircraft().getHeight() / 2;
            }
            if (newY <= -skyManager.getMyAircraft().getHeight() / 2) {
                newY = -skyManager.getMyAircraft().getHeight() / 2;
            }

            skyManager.getMyAircraft().SetX(newX);
            skyManager.getMyAircraft().SetY(newY);
            return true;
        };

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        skyManager.setRunning(false);
        finish();
    }
    }



