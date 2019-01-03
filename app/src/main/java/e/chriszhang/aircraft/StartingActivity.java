package e.chriszhang.aircraft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;


/**
 * test for git
 */
public class StartingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting);
        addStartButtonListener();
    }

    void addStartButtonListener(){
        Button StartButton = findViewById(R.id.Start);
        StartButton.setOnClickListener(v->{
            Intent temp = new Intent(this, GameActivity.class);
            startActivity(temp);
        });
    }
}
