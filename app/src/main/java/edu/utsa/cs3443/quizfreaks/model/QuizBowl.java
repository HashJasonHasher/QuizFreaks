package edu.utsa.cs3443.quizfreaks.model;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.opencsv.CSVReader;

import java.io.IOError;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import edu.utsa.cs3443.quizfreaks.MainActivity;

/**
 * Represents a quiz bowl game that manages questions, players, and scores.
 * Handles loading questions from a CSV file, managing the current question,
 * and determining the highest-scoring player.
 * @author Brandon Fischer
 */
public class QuizBowl {


    private ArrayList<Question> questions;
    private ArrayList<Player> players;
    private Question currentQuestion;
    private int qIndex;

    public static final String TAG = "IncidentTrafficker";

    /**
     * Constructs a new QuizBowl instance with empty lists of questions and players.
     */
    public QuizBowl(){
        questions = new ArrayList<Question>();
        players = new ArrayList<Player>();
        qIndex = 0;
        currentQuestion = null;
    }

    /**
     * Loads questions from a CSV file.
     *
     * @param filename The name of the CSV file containing the questions.
     * @param activity The current context of the application.
     */
    public void loadQuestions(String filename, Context activity) {
        Log.d(TAG, "Loading questions");

        AssetManager manager = activity.getAssets();
        Log.d(TAG, "created asset manager");

        try (InputStream file = manager.open(filename);
             InputStreamReader inputStreamReader = new InputStreamReader(file);
             CSVReader csvReader = new CSVReader(inputStreamReader)) {

            Log.d(TAG, "Created InputStream and CSVReader");

            String[] tokens;

            // Skip the first line if it's a header
            csvReader.readNext();  // Read and ignore header line
            Log.d(TAG, "Skipped first line");

            Log.d(TAG, "Entering while loop for parsing questions");
            while ((tokens = csvReader.readNext()) != null) {
                Log.d(TAG, "Inside while loop");

                Question question = new Question(
                        tokens[0].trim(),
                        tokens[1].trim(),
                        tokens[2].trim(),
                        Integer.parseInt(tokens[3].trim())
                );
                questions.add(question);
                Log.d(TAG, "Successfully added question to arrayList");
            }
        } catch (Exception e) {
            Log.e(TAG, "Error loading questions", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Gets the current question based on the current index.
     *
     * @return The current question, or null if the index is out of bounds.
     */
    public Question getCurrentQuestion() {
        Log.d(TAG, "Getting current question");

        if (getqIndex() >= 0 && getqIndex() < questions.size()) {
            currentQuestion = questions.get(getqIndex());
            Log.d(TAG, "Successfully got current Question");
            return currentQuestion;
        } else {
            Log.d(TAG, "Returning null question");
            return null;
        }
    }

    /**
     * Moves to the next question in the list by incrementing the index.
     *
     * @return True if the next question is available, false if there are no more questions.
     */
    public boolean nextQuestion() {
        Log.d(TAG, "Incrementing question index");

        if (getqIndex() < questions.size() - 1) {
            setqIndex(getqIndex() + 1);
            Log.d(TAG, "Index incremented");
            return true;
        } else {
            currentQuestion = null;
            Log.d(TAG, "No more questions left to increment");
            return false;
        }
    }

    /**
     * Sets the current question.
     *
     * @param currentQuestion The question to set as the current question.
     */
    public void setCurrentQuestion(Question currentQuestion) {
        this.currentQuestion = currentQuestion;
    }

    /**
     * Gets the list of questions.
     *
     * @return The list of questions.
     */
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    /**
     * Sets the list of questions.
     *
     * @param questions The list of questions to set.
     */
    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    /**
     * Gets the current question index.
     *
     * @return The current question index.
     */
    public int getqIndex() {
        return qIndex;
    }

    /**
     * Sets the current question index.
     *
     * @param qIndex The index to set.
     */
    public void setqIndex(int qIndex) {
        this.qIndex = qIndex;
    }

    /**
     * Loads players into the game.
     *
     * @param numPlayers The number of players to load (1 or 2).
     */
    public void loadPlayers(int numPlayers){
        Log.d(TAG, "Loading players");
            Player playerOne = new Player(
                    "Player 1",
                    0
            );
        Log.d(TAG, "Player One created successfully");
        players.add(playerOne);

        if (numPlayers == 2){
            Log.d(TAG, "Loading second player");

            Player playerTwo = new Player(
                    "Player 2",
                    0
            );
            Log.d(TAG, "Player two created successfully");

            players.add(playerTwo);
        }
    }

    /**
     * Updates the score of a specific player.
     *
     * @param playerIndex The index of the player.
     * @param points The points to add to the player's score.
     */
    public void updatePlayerScore(int playerIndex, int points){
        Log.d(TAG, "Updating player score with Player: " + playerIndex + ", points: " + points);


            Player player = players.get(playerIndex);
            int newScore = player.getScore() + points;
            player.setScore(newScore);
            Log.d(TAG, "player score updated successfully");

    }

    /**
     * Gets the player with the highest score.
     *
     * @return The player with the highest score, or null if the scores are tied.
     */
    public Player getHighScorePlayer(){
        Log.d(TAG, "Getting highest scoring player");

        Player highScorePlayer = null;

        Player playerOne = players.get(0);
        Player playerTwo = players.get(1);
        Log.d(TAG, "Player Scores: PlayerOne: " + playerOne.getScore() + " | PlayerTwo: " + playerTwo.getScore());


        if (playerOne.getScore() > playerTwo.getScore()){
            highScorePlayer = playerOne;
            Log.d(TAG, "Player one highest scorer: " + playerOne.getScore());

        }
        else if (playerOne.getScore() < playerTwo.getScore()){
            highScorePlayer = playerTwo;
            Log.d(TAG, "Player two highest scorer: " + playerTwo.getScore());

        }
        else {
            Log.d(TAG, "Player scores were equal");

            return null;
        }

        Log.d(TAG, "Successfully returning highest scoring player");
        return highScorePlayer;
    }

    /**
     * Gets the list of players.
     *
     * @return The list of players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * Sets the list of players.
     *
     * @param players The list of players to set.
     */
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
}
