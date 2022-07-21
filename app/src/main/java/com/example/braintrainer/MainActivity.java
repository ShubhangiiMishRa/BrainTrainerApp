package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    TextView sumtextView;
    TextView statustextView;
    TextView scoretextView;
    ArrayList<Integer> answer = new ArrayList<>();
    Button button0, button1, button2, button3;
    TextView timertextView;
    Button playAgainButton;
    int buttonOfCorrectAns;
    int score;
    int totalQues;
    ConstraintLayout gameLayout;


    public void playAgain(View view){
        score = 0;
        totalQues = 0;
        timertextView.setText("30s");
        statustextView.setText("Your score is "+Integer.toString(score)+"/"+Integer.toString(totalQues));
        nextQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);
        CountDownTimer countDownTimer = new CountDownTimer(30000,1000) {
            // parameters passed in CountDownTimer- kaha se countdown shuru krna hai, kitne interval ka gap
            @Override
            public void onTick(long millisUntilFinished) {
                // after passage of every second, we want to know that how much time is left
                timertextView.setText(((int)millisUntilFinished/1000)+"s"); // to covert this into seconds
            }

            @Override
            public void onFinish() {
                Toast.makeText(MainActivity.this, "TIME UP!!!!", Toast.LENGTH_SHORT).show();
                //button.setText("Start");
                statustextView.setText("Your score is "+Integer.toString(score)+"/"+Integer.toString(totalQues));
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);
            }
        }.start();

    }
    public void nextQuestion(){
        // for making random sums
        Random random = new Random();

        int a = random.nextInt(21);  // numbers upto 20
        int b = random.nextInt(21);
        int sum = a + b;
        sumtextView = findViewById(R.id.sumtextVew);
        sumtextView.setText(Integer.toString(a)+" + "+Integer.toString(b));

        // we want the location of the correct answer to randomly appear on any of the four buttons
        buttonOfCorrectAns = random.nextInt(4);
        answer.clear();   // clear the previous set of options before adding the new one
        //to display the options
        for(int i = 0; i < 4; i++){
            // to put the correct answer as an option
            if(i == buttonOfCorrectAns)
                answer.add(a+b);
            else{
                int wrongAnswer = random.nextInt(41); // display any random numbers in btwn 0 and 40 in the option
                // to make sure that no random number is generated same as  that of the correct answer
                while(wrongAnswer == (a+b)){
                    //if the random number as equal to the sum, generate other random number
                    wrongAnswer = random.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }

        // set the options in the button
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));
    }
    public void correctAns(View view){


        //
        if(Integer.toString(buttonOfCorrectAns).equals(view.getTag().toString())){
            statustextView.setText("Correct");
            Log.i("status","Correct");
            score++;
        }
        else {
            statustextView.setText("Wrong");
            Log.i("status", "Wrong");
        }
        totalQues++;
        scoretextView.setText(Integer.toString(score)+"/"+Integer.toString(totalQues));
        nextQuestion();
    }
    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.button0)); // we can pass any view, it does not really matter here

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startButton = findViewById(R.id.startButton);
        statustextView = findViewById(R.id.statustextView);
        scoretextView = findViewById(R.id.scoretextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        timertextView = findViewById(R.id.timertextView);
        playAgainButton = findViewById(R.id.playAgainButton);

        gameLayout = findViewById(R.id.gameLayout);
        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);



    }
}