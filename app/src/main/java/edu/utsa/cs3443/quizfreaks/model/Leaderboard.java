package edu.utsa.cs3443.quizfreaks.model;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Collector;

/**
 * This is the model class for the Leaderboard mainly built to handle the leaderboard file
 * @author Raymond Harmon
 */
public class Leaderboard {
    private ArrayList<Player>players;
    private ArrayList<Player> emptyDefault;
    private final String filename;
    private final Activity activity;
    private final String tag="Leaderboard";

    /**
     * constructor for the leaderboard class
     * @param activity the activity the constructor is being initialized on
     */
    public Leaderboard(Activity activity)
    {
        this.activity=activity;
        this.filename="leaderboard.csv";
        players=new ArrayList<Player>();
        startLeaderboard();
    }

    /**
     * This method attempts to open the file with the leaderboard data. If it succeeds it reads the data.
     * If it fails it creates a default leaderboard writes it to a new file
     */
    private void startLeaderboard()
{
    //tries to find the leaderboard file
    try
    {
        Log.i(tag,"attempting to read from file");
        InputStream in= activity.openFileInput(filename);
    //once it has the file it will call a method to read it
        Log.i(tag,"attempting to read file");
        //loadDefault();
        //Log.i(tag,"temp:default players read");
        loadLeaderboard(in);
        saveLeaderboard();
    }
    //if it fails it will attempt to create one
    catch (FileNotFoundException in)
    {
        Log.i(tag,"no file found creating new");
        try
        {
            //creates an empty leaderboard file
            OutputStream out =activity.openFileOutput(filename, Context.MODE_PRIVATE);
            // default file will contain 10 default players(Plato, Aristotle etc.)
            loadDefault();
            saveLeaderboard();

        }
        catch (FileNotFoundException out)
        {
            Log.e(tag, "Unable to create file. " + filename);
        }

    }
}

    /**
     * method reads in a leaderboard from a file and sorts it for use
     * @param in the input stream used for reading
     */
    private void loadLeaderboard(InputStream in)
    {
        if( in != null )
        {
            Scanner scan = new Scanner(in);
            String playerIn="";
            String [] playerData;
            Log.i(tag,"attempting to read player");
            while (scan.hasNextLine())
            {
                Log.i(tag,"got a player");
                playerIn=scan.nextLine();
                Log.i(tag,playerIn);
                playerData=playerIn.split(",");
                Player p= new Player(playerData[0].trim(),Integer.parseInt(playerData[1].trim()));
                addPlayer(players,p);
                Log.i(tag,"Player read");
            }
        }
        sortLeaderboard();
    }

    /**
     * creates a group of default Player objects and adds it to the player ArrayList
     */
    private void loadDefault()
{
    Log.i(tag,"loading default players");
    emptyDefault=new ArrayList<Player>();
    Player plato= new Player("Plato",100);
    addPlayer(players,plato);
    Player aristotle= new Player("Aristotle",90);
    addPlayer(players,aristotle);
    Player socrates = new Player("Socrates",80);
    addPlayer(players,socrates);
    Player kant= new Player("Kant",70);
    addPlayer(players,kant);
    Player descartes= new Player("Descartes",60);
    addPlayer(players,descartes);
    Player nietzsche= new Player("Nietzsche",50);
    addPlayer(players,nietzsche);
    Player locke= new Player("Locke",40);
    addPlayer(players,locke);
    Player hume= new Player("Hume",30);
    addPlayer(players,hume);
    Player confucius= new Player("Confucius",20);
    addPlayer(players,confucius);
    Player sartre= new Player("Sartre",10);
    addPlayer(players,sartre);




}

    /**
     * a method that adds a player object to a Payer ArrayList
     * @param p the ArrayList the Player is going to
     * @param player the PLayer object to be added
     */
//takes an ArrayList for use with the default list and the player list
private void addPlayer(ArrayList<Player> p,Player player)
{
    if( p != null)
    {
        Log.i(tag,"adding new player "+ player.getName() +" to arrayList");
        p.add(player);
        Log.i(tag,player.getName()+" added");
    }
}

    /**
     * Attempts to write the players in the leaderboard to the leaderboard file
     */
    public void saveLeaderboard()
{
    try
    {
        OutputStream out = activity.openFileOutput(filename, Context.MODE_PRIVATE);
        if(players != null){
            for(Player player : players){
                out.write(player.getName().getBytes(StandardCharsets.UTF_8) );
                out.write(", ".getBytes(StandardCharsets.UTF_8) );
                out.write(String.valueOf(player.getScore()).getBytes(StandardCharsets.UTF_8));
                out.write("\n".getBytes(StandardCharsets.UTF_8) );
            }
            out.close();
            Log.i(tag,"Leaderboard saved");
        }


    }
    catch(IOException e)
    {
        Log.i(tag,"Failed to write to file. " + filename);
    }
}

    /**
     * calls the sort method to the players ArrayList
     */
    private void sortLeaderboard()
{
    Collections.sort(players);
}

    /**
     * adds a player to the players ArrayList and sorts it
     * @param update the new player being added
     */
    public void updateLeaderboard(Player update)
{
    addPlayer(players,update);
    sortLeaderboard();
}

    public ArrayList<Player> getPlayers() {
        sortLeaderboard();
        return players;
    }
}
