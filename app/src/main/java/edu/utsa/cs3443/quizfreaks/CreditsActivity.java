package edu.utsa.cs3443.quizfreaks;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

/**
 * This is a controller class that exists to show the names of our group members
 * @author Jason Engelbrecht
 */
public class CreditsActivity extends AppCompatActivity
{


    /**
     * On creation the activity will create a view for the user to see who worked on this project
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        //Find the home button
        Button backButton = findViewById(R.id.bt_back);

        //onClick: Send the user back by one screen
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
    }
}
