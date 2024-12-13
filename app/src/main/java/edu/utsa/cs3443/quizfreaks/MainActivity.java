package edu.utsa.cs3443.quizfreaks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

/**
 * This is the main activity. It the first class that is run on startup.
 * This activity will display our main menu which holds all of the menu options
 *
 * @author Jason Engelbrecht
 */

public class MainActivity extends AppCompatActivity {

    /**
     * On creation the activity will create a main menu for the user
     * It will allow them to enter a game mode of their choosing.
     * Also it will let them travel to the game rules screen, or settings screen.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        /**
         * This will receive the music intent on creation and
         * will start the music on creation.
         */
        Intent serviceIntent = new Intent(this, BackgroundMusicService.class);
        startService(serviceIntent);

        /**
         * This will find and initialize the buttons
         */
        Button onePlayer = findViewById(R.id.bt_1P);
        Button twoPlayer = findViewById(R.id.bt_2P);
        Button leaderboard = findViewById(R.id.bt_leaderboard);
        ImageButton settings = findViewById(R.id.bt_settings);
        Button gameRulesBtn = findViewById(R.id.bt_gamerules);



        //Launch Single Player activity
        onePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSingleActivity();
            }
        });

        //Launch Two Player activity
        twoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchMultiActivity();
            }
        });

        //Launch Leaderboard activity
        leaderboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchLeaderboardActivity();
            }
        });

        //Launch Settings activity
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchSettingsActivity();
            }
        });

        //Launch the gameRules activity
        gameRulesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchGameRulesActivity();
            }
        });
    }

    /**
     * These are the methods for launching the various activities.
     */
    private void launchSingleActivity() {
        Log.d("IncidentTrafficker", "IN SINGLELAUNCH ACTIVITY");
        Intent intent = new Intent(this, SetupSinglePlayerActivity.class);
        Log.d("IncidentTrafficker", "ABOUT TO START SETUP SINGLE");
        startActivity(intent);
    }
    private void launchMultiActivity() {
        Log.d("IncidentTrafficker", "IN MULTILAUNCH ACTIVITY");
        Intent intent = new Intent(this, SetupMultiPlayerActivity.class);
        Log.d("IncidentTrafficker", "ABOUT TO START SETUP MULTI ");
        startActivity(intent);
    }
    private void launchLeaderboardActivity() {
        Intent intent = new Intent(this, LeaderboardActivity.class);
        startActivity(intent);
    }
    private void launchSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }
    private void launchGameRulesActivity() {
        Intent intent = new Intent(this, GameRulesActivity.class);
        startActivity(intent);
    }

}