package edu.utsa.cs3443.quizfreaks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
 * This activity exists only to find a textview and display the rules of our game to the user.
 *
 * @author Jason Engelbrecht
 */

public class GameRulesActivity extends AppCompatActivity {

    /**
     * On creation the activity will create a view for the user to see who the rules of this game
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_game_rules);

        /**
         * This will find The home button
         */
        Button homeButton = findViewById(R.id.bt_home);


        /**
         * onClick: Send the user back to the main menu
         */
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the home screen
                Intent intent = new Intent(GameRulesActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}