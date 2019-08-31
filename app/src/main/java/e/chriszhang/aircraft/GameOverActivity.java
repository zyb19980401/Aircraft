package e.chriszhang.aircraft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class GameOverActivity extends AppCompatActivity {
//    private  SkyManager skyManager = GameActivity.getSkyManager();
    /**
     * update time.
     */
    private String getTimeString() {
        SkyManager skyManager = GameActivity.getSkyManager();
        int minutes = (skyManager.getmTimeLeftInMillis() / 1000) / 60;
        int seconds = (skyManager.getmTimeLeftInMillis() / 1000) % 60;

        return String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
    }

    /**
     * @return return the score int
     */
    private int getScore() {
        SkyManager skyManager = GameActivity.getSkyManager();
        return skyManager.getNumkill() * skyManager.getmTimeLeftInMillis() / 1000;
    }

    /**
     * @return the_string_of how many u have killed
     */
    String killString() {
        return "You have killed  " + GameActivity.getSkyManager().getNumkill()+ "  enemy";
    }

    /**
     * @return the string of how long u have survived.
     */
    String timeString() {
        return "You have survived  " + getTimeString();
    }

    /**
     * @return the score that u get
     */
    String scoreString() {
        return "You have got " + Integer.toString(getScore()) + " points !!!!";

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameover);
        addRestartButtonListener();
        TextView kills = findViewById(R.id.givekills);
        TextView times = findViewById(R.id.givetime);
        TextView score = findViewById(R.id.givescore);
        kills.setText(killString());
        times.setText(timeString());
        score.setText(scoreString());
    }

    void addRestartButtonListener(){
        Button restartButton = findViewById(R.id.restart_button);
        restartButton.setOnClickListener(v->{
            Intent temp = new Intent(this, GameActivity.class);
            startActivity(temp);
        });
    }

}
