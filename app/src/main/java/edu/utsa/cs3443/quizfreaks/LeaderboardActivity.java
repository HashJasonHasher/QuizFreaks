package edu.utsa.cs3443.quizfreaks;

import android.graphics.Color;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;

import edu.utsa.cs3443.quizfreaks.model.Leaderboard; //Leaderboard class
import edu.utsa.cs3443.quizfreaks.model.Player;

/**
 * Controller class to run the leaderboard
 * @author Raymond Harmon
 */
public class LeaderboardActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private Leaderboard leaderboard;

    /**
     * On creation the activity creates a Leaderboard object and displays it
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("IncidentTrafficker", "IN LEADERBOARD ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);

        Log.i("Leaderboard Activity","attempting to create leaderboard");
        createLeaderboard();
        Log.i("Leaderboard Activity","leaderboard created. attemping to display");
        displayLeaderboard(leaderboard.getPlayers());
        //buttons:
        homeButton = findViewById(R.id.bt_home);

        //onClick:
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });

    }

    /**
     * class to initialize the leaderboard class
     */
    public void createLeaderboard(){
       leaderboard = new Leaderboard(this);
    }

    /**
     * This method builds the scrollview and displays the leaderboard in the app
     * @param players the ArrayList of Players that needs to be displayed
     */
     public void displayLeaderboard(ArrayList<Player> players)
    {
        LinearLayout vert= findViewById(R.id.leaderboard);
        vert.removeAllViews();
        Log.i("Leaderboard Display","vertical view created pulling leaderboard players");
        if (players!= null)
        {
            Log.i("Leaderboard Display","players valid displaying players");
            //will only show the top 10
            int leaders=10;
            for (Player player : players) {
                if (leaders>0) {
                    LinearLayout horizontalLayout = new LinearLayout(this);
                    Log.i("Leaderboard Display", "horizontal bucket made");
                    horizontalLayout.setOrientation(LinearLayout.HORIZONTAL);
                    horizontalLayout.setLayoutParams(new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.WRAP_CONTENT
                    ));
                    ImageView gold = new ImageView(this);
                    ImageView silver= new ImageView(this);
                    ImageView bronze= new ImageView(this);
                    int goldRef=getResources().getIdentifier("medalfirst","drawable",getPackageName());
                    gold.setImageResource(goldRef);
                    gold.setLayoutParams(new LinearLayout.LayoutParams(
                            75,75));
                    gold.setPadding(10, 10, 10, 10);

                    int silverRef=getResources().getIdentifier("medalsecond","drawable",getPackageName());
                    silver.setImageResource(silverRef);
                    silver.setLayoutParams(new LinearLayout.LayoutParams(
                            75,75));
                    gold.setPadding(10, 10, 10, 10);

                    int bronzeRef=getResources().getIdentifier("medalthird","drawable",getPackageName());
                    bronze.setImageResource(bronzeRef);
                    bronze.setLayoutParams(new LinearLayout.LayoutParams(
                            75,75));
                    gold.setPadding(10, 10, 10, 10);

                    if(leaders==10)
                    {
                        horizontalLayout.addView(gold);
                    }
                    if(leaders ==9)
                    {
                        horizontalLayout.addView(silver);
                    }
                    if(leaders ==8)
                    {
                        horizontalLayout.addView(bronze);
                    }
                    Log.i("Leaderboard Display", "setting name text view");
                    TextView nameView = new TextView(this);
                    TextView score = new TextView(this);
                    Typeface standard= ResourcesCompat.getFont(this,R.font.stenc_ex);
                    nameView.setTextColor(Color.parseColor("#6F8DB1"));
                    nameView.setTypeface(standard);
                    nameView.setText(player.getName()+" ");
                    nameView.setTextSize(50);
                    Log.i("Leaderboard Display", player.getName() + " set. adding to layout");
                    horizontalLayout.addView(nameView);
                    Log.i("Leaderboard Display", player.getName() + " added");
                    Log.i("Leaderboard Display", "now to create the view for the score");

                    Log.i("Leaderboard Display", "suspected breakdown here");

                    score.setText(Integer.toString(player.getScore()));
                    Log.i("Leaderboard Display", "could it be a breakdown in Strings?");
                    score.setTextSize(30);
                    score.setTextColor(Color.parseColor("#6F8DB1"));
                    Log.i("Leaderboard Display", player.getScore() + " set. adding to layout");
                    horizontalLayout.addView(score);
                    Log.i("Leaderboard Display", player.getScore() + " added");
                    vert.addView(horizontalLayout);
                    leaders--;
                }
                else
                    break;
            }
        }
        else{
            Toast.makeText(getApplicationContext(), "failed", Toast.LENGTH_LONG).show();
        }


    }
}
