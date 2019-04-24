package se.maj7.bpmcounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    long previousTime = 0;
    long currentTime = 0;
    long timeDifference = 0;
    int countClicks = 0;
    double bpm = 0;
    double bpmTotal = 0;
    double bpmFinal = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void tapTempo(View v) {

        TextView bpmTextView = findViewById(R.id.bpmTextView);

        if (previousTime == 0) {
            previousTime = System.currentTimeMillis();
        } else if ((System.currentTimeMillis()-previousTime) > 2200) {
            resetTempo(v);
        } else {
            currentTime = System.currentTimeMillis();
            timeDifference = currentTime - previousTime;
            previousTime = currentTime;
            bpm = 60 / (double)timeDifference;
            bpmTotal = bpmTotal + bpm;
            countClicks++;
            bpmFinal = (bpmTotal / countClicks) * 1000;
            String bpmString = new DecimalFormat("#").format(bpmFinal);
            bpmTextView.setText(bpmString);

        }
    }

    public void resetTempo(View v) {

        previousTime = 0;
        bpm = 0;
        bpmTotal = 0;
        bpmFinal = 0;
        countClicks = 0;

        TextView bpmTextView = findViewById(R.id.bpmTextView);
        bpmTextView.setText("-");

    }
}
