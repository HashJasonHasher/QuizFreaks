package edu.utsa.cs3443.quizfreaks;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This activity will
 * @author Ricardo Reyes
 */

public class SettingsActivity extends AppCompatActivity {
    private ImageButton homeButton;
    private boolean sfxOn;
    private boolean musicOn;

    BackgroundMusicService music = new BackgroundMusicService();

    /**
     * On creation the activity will create a view for the user to select which setting they want turned on and off.
     * It will also allow the user to go to the credits screen.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("IncidentTrafficker", "IN LEADERBOARD ACTIVITY");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        /**
         * Initialize and find the buttons and checkboxes needed for the settings menu
         */
        homeButton = findViewById(R.id.bt_home);
        Button credits = findViewById(R.id.bt_credits);
        CheckBox toggleSFX = findViewById(R.id.bt_sfx);
        CheckBox toggleMusic = findViewById(R.id.bt_music);

        /**
         * The values for the sound effects and music will be set true on default
         */
        sfxOn = true;
        musicOn = true;
        toggleSFX.setChecked(true);
        toggleMusic.setChecked(true);



        //onClick: It will send the user back to the main menu
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        credits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launchCreditsActivity();
            }

        });

        toggleSFX.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    sfxOn = true;
                }
                else
                {
                    sfxOn = false;
                }
            }
        });
        toggleMusic.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked)
                {
                    musicOn = true;
                }
                else
                {
                    musicOn = false;
                    Intent stopIntent = new Intent(SettingsActivity.this, BackgroundMusicService.class);
                    stopService(stopIntent);
                    // Music will never turn back on since button state isn't saved
                }
            }
        });
    }


    /**
     * Method to launch the the credits activity
     */
    private void launchCreditsActivity() {
        Intent intent = new Intent(this, CreditsActivity.class);
        startActivity(intent);
    }
}
