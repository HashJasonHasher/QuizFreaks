package edu.utsa.cs3443.quizfreaks;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import edu.utsa.cs3443.quizfreaks.model.Leaderboard;
import edu.utsa.cs3443.quizfreaks.model.Player;
/**
 * On creation this activity will receive the winning player's score and name via the intent from
 * the previous activity (either MultiPlayerQuizBowlActivity or the SinglePlayerQuizBowlActivity.
 *
 * After receiving the intent, it will display the winning player's score and name and then lead
 * the player back to the main menu.
 * @author Jason Engelbrecht
 */
public class GameOverActivity extends AppCompatActivity {
    private Player winner;
    private Leaderboard leaderboard;

    /**
     * On creation the activity will create a view for the user to see their score and name.
     * It will also allow them to return to the main menu.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gameover);

        //Get the winning players score and name with the intent
        Intent intent = getIntent();
        String name = intent.getStringExtra("keyPlayerName");
        int score = intent.getIntExtra("keyPlayerScore",1);
        winner=new Player(name,score);

        //Add our winner to the leaderboard with the addLeader method
        addLeader(winner);

        //Find the home button
        Button homeButton = findViewById(R.id.bt_finish);

        //Find the Textviews for the Player score and name
        TextView playerName = findViewById(R.id.name_view);
        TextView playerScore = findViewById(R.id.score_view);

        //Set the text for the Textviews
        playerName.setText(name);
        playerScore.setText(String.valueOf(score));

        //onClick: Send the player back to the main menu
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Return to the home screen
                Intent intent = new Intent(GameOverActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Updates the leaderboard by adding Player objects into it.
     */
    public void addLeader(Player winner)
    {
        leaderboard=new Leaderboard(this);
        leaderboard.updateLeaderboard(winner);
        leaderboard.saveLeaderboard();
    }
}