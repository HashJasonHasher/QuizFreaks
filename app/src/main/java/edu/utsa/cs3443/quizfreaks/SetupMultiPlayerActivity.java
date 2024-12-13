package edu.utsa.cs3443.quizfreaks;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;

import edu.utsa.cs3443.quizfreaks.model.QuizBowl; //Quizbowl class

/**
 * SetupMultiPlayerActivity is a controller class that manages what topics are selected and
 * what difficulties are selected. It will also take the players name and put it in the leaderboard.
 *
 * @author Brandon Fischer, Jason Engelbrecht, Ricardo Reyes, Raymond Harmon
 */

public class SetupMultiPlayerActivity extends AppCompatActivity {

    // Instance variables based on the UML diagram
    private EditText playerNameBox;
    private EditText playerNameBox2;
    private boolean addHistory = false;
    private boolean addLit = false;
    private boolean addArt = false;
    private boolean addCurrEvents = false;
    private boolean addPopCult = false;
    private boolean addScience = false;
    private boolean addEasy = false;
    private boolean addMedium = false;
    private boolean addHard = false;


    /**
     * On creation the activity will create a view for the user to input what difficulty and what topics they want to be quized on
     * After that, it will Take these inputs and send them to the LaunchMultiplayerQuizBowlActivity
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("IncidentTrafficker", "IN Multi SETUP ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_setup);

        Log.d("IncidentTrafficker", "About to set up textboxes");
        //textbox for name
        playerNameBox = findViewById(R.id.answerInput);
        playerNameBox2 = findViewById(R.id.answerInput2);

        // setup buttons for single player menu screen
        Button begin = findViewById(R.id.bt_begin);
        ImageButton home = findViewById(R.id.bt_home);


        Log.d("IncidentTrafficker", "About to set up checkboxes");
        // quiz category buttons:
        CheckBox history = findViewById(R.id.bt_history);
        CheckBox literature = findViewById(R.id.bt_literature);
        CheckBox fineArts = findViewById(R.id.bt_fineArts);
        CheckBox currEvents = findViewById(R.id.bt_currEvents);
        CheckBox popCulture = findViewById(R.id.bt_popCulture);
        CheckBox science = findViewById(R.id.bt_science);
        CheckBox easy = findViewById(R.id.cb_easy);
        CheckBox medium = findViewById(R.id.cb_medium);
        CheckBox hard = findViewById(R.id.cb_Hard);

        //onClick listeners:
       begin.setOnClickListener(new View.OnClickListener() {     //Launch Multi Player activity
            @Override
            public void onClick(View view) {
                launchMultiPlayerQuizbowlActivity();
            }
        });

        home.setOnClickListener(new View.OnClickListener() {     //Go back home
            @Override
            public void onClick(View view) {
                finish();
            }

        });

        //toggle buttons for categories: (need to figure out how to do toggle visual appearance)
        //if true show  check mark.png?
        //add a start button to load questions and start the activity?
        //array of topic Strings and difficulty strings

        history.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addHistory = true;

                }
                else
                {
                    addHistory = false;
                }
            }
        });

        literature.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addLit = true;

                }
                else
                {
                    addLit = false;
                }
            }
        });

        fineArts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addArt = true;

                }
                else
                {
                    addArt = false;
                }
            }
        });

        currEvents.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addCurrEvents = true;

                }
                else
                {
                    addCurrEvents = false;
                }
            }
        });

        popCulture.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addPopCult = true;

                }
                else
                {
                    addPopCult = false;
                }
            }
        });

        science.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addScience = true;

                }
                else
                {
                    addScience = false;
                }
            }
        });

        easy.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addEasy = true;

                }
                else
                {
                    addEasy = false;
                }
            }
        });

        medium.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addMedium = true;

                }
                else
                {
                    addMedium = false;
                }
            }
        });

        hard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    addHard = true;

                }
                else
                {
                    addHard = false;
                }
            }
        });
    }



    /**
     * Method to launch the quiz activity
     */
    private void launchMultiPlayerQuizbowlActivity() {
        Map<String, Boolean> booleanMap = new HashMap<>();
        booleanMap.put("history", addHistory);
        booleanMap.put("literature", addLit);
        booleanMap.put("art", addArt);
        booleanMap.put("currEvents", addCurrEvents);
        booleanMap.put("popCult", addPopCult);
        booleanMap.put("science", addScience);
        booleanMap.put("easy", addEasy);
        booleanMap.put("medium", addMedium);
        booleanMap.put("hard", addHard);




        //for now:
        Intent intent = new Intent(this, MultiPlayerQuizBowlActivity.class);
        for (Map.Entry<String, Boolean> entry : booleanMap.entrySet()) {
            intent.putExtra(entry.getKey(), entry.getValue());
        }

        if(addHistory || addLit || addArt || addCurrEvents || addPopCult || addScience) {
            Log.d("IncidentTrafficker", addHistory + " " + addLit + " " + addArt + " " + addCurrEvents + " " + addPopCult + " " + addScience);

            if (addEasy || addMedium || addHard) {
                Log.d("IncidentTrafficker", addEasy + " " + addMedium + " " + addHard);

                String name1 = playerNameBox.getText().toString();
                String name2 = playerNameBox2.getText().toString();
                if (name1.equals("") || name2.equals("")) {
                    Toast.makeText(this, "Please Enter Your Names", Toast.LENGTH_SHORT).show();

                } else {
                    intent.putExtra("playerName1", playerNameBox.getText().toString());
                    intent.putExtra("playerName2", playerNameBox2.getText().toString());

                    startActivity(intent);
                }
            } else {
                Toast.makeText(this, "Please Select a difficulty of difficulties", Toast.LENGTH_SHORT).show();

            }

        }
        else{
            Toast.makeText(this, "Please Select a topic or topics", Toast.LENGTH_SHORT).show();

        }
    }};


