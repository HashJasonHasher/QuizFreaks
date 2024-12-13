package edu.utsa.cs3443.quizfreaks;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;


import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import edu.utsa.cs3443.quizfreaks.model.Player;
import edu.utsa.cs3443.quizfreaks.model.QuizBowl; //Quizbowl class

public class SinglePlayerQuizBowlActivity extends AppCompatActivity {
    /**
     * this is the edu.utsa.cs3443.quizfreaks.model.Player class for holding player objects
     * @author Everyone
     */
    // Instance variables based on the UML diagram
    private Button nextButton;
    private Button buzzPlayerOne;
    private EditText answerInput;
    private String name;
    private ImageButton homeButton;
    private QuizBowl quizBowl;
    private Player player1;
    private CountDownTimer countDownTimer;

    /**
     * On creation this activity will initialize the view  and give functions to the buttons.
     * It will begin the display question method and start the timer. It will also keep track
     * of the score the player has behind the scenes.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("IncidentTrafficker", "IN SINGLE PLAYER QUIZ BOW ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singlequiz);
        //player1 = new Player("Test",100);

        String[] booleanKeys = {"history", "literature", "art", "currEvents", "popCult", "science", "easy", "medium", "hard"};
        boolean history = false;
        boolean literature = false;
        boolean art = false;
        boolean currEvents = false;
        boolean popCult = false;
        boolean science = false;
        boolean easy = false;
        boolean medium = false;
        boolean hard = false;


        Intent intent = getIntent();
        name = intent.getStringExtra("playerName");

        for (String key : booleanKeys) {
            boolean value = intent.getBooleanExtra(key, false);  // Default value is false if the key doesn't exist

            // Assign values to corresponding variables based on the key
            switch (key) {
                case "history":
                    history = value;
                    break;
                case "literature":
                    literature = value;
                    break;
                case "art":
                    art = value;
                    break;
                case "currEvents":
                    currEvents = value;
                    break;
                case "popCult":
                    popCult = value;
                    break;
                case "science":
                    science = value;
                    break;
                case "easy":
                    easy = value;
                    break;
                case "medium":
                    medium = value;
                    break;
                case "hard":
                    hard = value;
                    break;
            }
        }


        // Initialize UI components
        nextButton = findViewById(R.id.bt_next);
        buzzPlayerOne = findViewById(R.id.bt_buzz_answer);
        answerInput = findViewById(R.id.answerInput);
        homeButton = findViewById(R.id.bt_home);

        // Initialize QuizBowl instance, potentially with data from the previous activity
        createQuizBowl(easy, medium, hard, history, literature, art, currEvents, popCult, science);

        // Set up the question display
        displayQuestion();
        startTimer();

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Displaying next question
                nextQuestion();
                displayQuestion();
            }
        });

        // Set up OnClick listeners
        buzzPlayerOne.setOnClickListener(view -> {
            // Show the EditText for input
            answerInput.setVisibility(View.VISIBLE);
            answerInput.requestFocus();

            // Show the keyboard
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.showSoftInput(answerInput, InputMethodManager.SHOW_IMPLICIT);
            }

                String answer = quizBowl.getCurrentQuestion().getAnswer();
            // Check answer when done
            answerInput.setOnEditorActionListener((v, actionId, event) -> {
                String userAnswer = answerInput.getText().toString().trim();
                if (userAnswer.equalsIgnoreCase(answer)) {
                    TextView questionText = findViewById(R.id.question_text);
                    questionText.setText("Correct Answer!");
                    TextView scoreTextView = findViewById(R.id.scoreTextView);
                    quizBowl.updatePlayerScore(0, 10);
                    scoreTextView.setText("Score: " +  quizBowl.getPlayers().get(0).getScore());
                    waitAndNextQuestion();
                } else {
                    Toast.makeText(this, "Wrong Answer. Try Again!", Toast.LENGTH_SHORT).show();
                }

                // Optionally hide the keyboard after the answer is checked
                if (imm != null) {
                    imm.hideSoftInputFromWindow(answerInput.getWindowToken(), 0);
                }

                // Clear input field and hide it if you want to reset
                answerInput.setText("");
                answerInput.setVisibility(View.GONE);
                return true;
            });
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the home screen
                Intent intent = new Intent(SinglePlayerQuizBowlActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // Set up timer if required
        //startTimer();
    }

    /**
     * Handles click events
     */
    public void onClick(View v) {
        // Handle other clicks if necessary
    }

    /**
     * Initializes the QuizBowl instance with selected categories
     */
    private void createQuizBowl(boolean easy, boolean medium, boolean hard, boolean history, boolean literature, boolean art, boolean currEvents, boolean popCult, boolean science) {
        Log.d("IncidentTrafficker", "IN CREATEQUIZBOWL");

        quizBowl = new QuizBowl();
        Log.d("IncidentTrafficker", "Created new quizbowl object");

        quizBowl.loadPlayers(1);
        // Retrieve selected categories from intent extras passed from SetupSinglePlayerActivity
        Log.d("IncidentTrafficker", "Checking boolean expressions for topics and levels");

        if (easy) {

            Log.d("SecondActivity", "Easy Level Selected");

            // Directly checking topics under Easy level
            if (history) {
                quizBowl.loadQuestions("HistoryEasy.csv", this);
                Log.d("SecondActivity", "Easy Level: Topic: History");
            }
            if (literature) {
                quizBowl.loadQuestions("LiteratureEasy.csv", this);
                Log.d("SecondActivity", "Easy Level: Topic: Literature");
            }
            if (art) {
                quizBowl.loadQuestions("FineArtsEasy.csv", this);
                Log.d("SecondActivity", "Easy Level: Topic: Art");
            }
            if (currEvents) {
                quizBowl.loadQuestions("CurrentEventsEasy.csv", this);
                Log.d("SecondActivity", "Easy Level: Topic: Current Events");
            }
            if (popCult) {
                quizBowl.loadQuestions("PopularCultureEasy.csv", this);
                Log.d("SecondActivity", "Easy Level: Topic: Pop Culture");
            }
            if (science) {
                quizBowl.loadQuestions("ScienceEasy.csv", this);
                Log.d("SecondActivity", "Easy Level: Topic: Sports");
            }
        }

        // Checking medium level
        if (medium) {
            Log.d("SecondActivity", "Medium Level Selected");

            // Directly checking topics under Medium level
            if (history) {
                quizBowl.loadQuestions("HistoryMedium.csv", this);
                Log.d("SecondActivity", "Medium Level: Topic: History");
            }
            if (literature) {
                quizBowl.loadQuestions("LiteratureMedium.csv", this);
                Log.d("SecondActivity", "Medium Level: Topic: Literature");
            }
            if (art) {
                quizBowl.loadQuestions("FineArtsMedium.csv", this);
                Log.d("SecondActivity", "Medium Level: Topic: Art");
            }
            if (currEvents) {
                quizBowl.loadQuestions("CurrentEventsMedium.csv", this);
                Log.d("SecondActivity", "Medium Level: Topic: Current Events");
            }
            if (popCult) {
                quizBowl.loadQuestions("PopularCultureMedium.csv", this);
                Log.d("SecondActivity", "Medium Level: Topic: Pop Culture");
            }
            if (science) {
                quizBowl.loadQuestions("ScienceMedium.csv", this);
                Log.d("SecondActivity", "Medium Level: Topic: Sports");
            }
        }

        // Checking hard level
        if (hard) {
            Log.d("SecondActivity", "Hard Level Selected");

            // Directly checking topics under Hard level
            if (history) {
                quizBowl.loadQuestions("HistoryHard.csv", this);
                Log.d("SecondActivity", "Hard Level: Topic: History");
            }
            if (literature) {
                quizBowl.loadQuestions("LiteratureHard.csv", this);
                Log.d("SecondActivity", "Hard Level: Topic: Literature");
            }
            if (art) {
                quizBowl.loadQuestions("FineArtsHard.csv", this);
                Log.d("SecondActivity", "Hard Level: Topic: Art");
            }
            if (currEvents) {
                quizBowl.loadQuestions("CurrentEventsHard.csv", this);
                Log.d("SecondActivity", "Hard Level: Topic: Current Events");
            }
            if (popCult) {
                quizBowl.loadQuestions("PopularCultureHard.csv", this);
                Log.d("SecondActivity", "Hard Level: Topic: Pop Culture");
            }
            if (science) {
                quizBowl.loadQuestions("ScienceHard.csv", this);
                Log.d("SecondActivity", "Hard Level: Topic: Sports");
            }
        }

        Log.d("IncidentTrafficker", "LEAVING CREATE QUIZBOWL");
    }

    /**
     * Displays the current question on the screen
     * Refer to the Rowdy quiz for display question implementation
     * We could use this, but we don't have to have a complex setup like this since it can just
     * restart the questions once the end is reached.
     */

    private void displayQuestion() {
        // Logic to display the question
        Log.d("IncidentTrafficker", "IN DISPLAYQUESTION");
        Log.d("IncidentTrafficker", "checking if there is a current question");
        if (quizBowl.getCurrentQuestion() != null && quizBowl.nextQuestion()) {
            String question = quizBowl.getCurrentQuestion().getQuestion();

            TextView questionText = findViewById(R.id.question_text); // need implementation for question text
            questionText.setText(question);
            Log.d("IncidentTrafficker", "displaying question");
        } else {
            Log.d("IncidentTrafficker", "current question is null");
            Intent intent = new Intent(this, GameOverActivity.class);
            ArrayList<Player> players;
            players = quizBowl.getPlayers();
            player1 = players.get(0);
            intent.putExtra("keyPlayerName", name);
            intent.putExtra("keyPlayerScore", player1.getScore());
            startActivity(intent);
            countDownTimer.cancel();
        }
    }

    /**
     * delays the reveal of the next question by 5 seconds, for enhanced game flow.
     */
    public void waitAndNextQuestion() {
        // Create a delay using Handler
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Code to display the next question
                nextQuestion();
            }
        }, 2500); // Delay of 5 seconds (5000 milliseconds)
    }
    /**
     * goes to the next question, resets the timer back to 30 seconds, then starts the timer again.
     */
    public void nextQuestion(){
        // Displaying next question
        quizBowl.nextQuestion();
        resetAndStartTimer();

    }
    /**
     * starts the timer, counting down from 30 seconds
     */
    private void startTimer() {
        // Initialize the timer
        countDownTimer = new CountDownTimer(31000, 1000) { // 30 seconds timer, ticking every second
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the timer TextView (if you have one)
                TextView timerTextView = findViewById(R.id.timerTextView);
                timerTextView.setText("Time Left: " + millisUntilFinished / 1000 + "s");
            }
            @Override
            public void onFinish() {
                TextView timerTextView = findViewById(R.id.timerTextView);
                timerTextView.setText("Times up!");
                waitAndNextQuestion();
            }
        };

        // Start the timer
        countDownTimer.start();
    }
    /**
     * resets the timer back to 30 seconds, and starts it again
     */
    private void resetAndStartTimer() {
        // Cancel the previous timer if it's running
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }

        // Start a new timer
        startTimer();
    }


}
