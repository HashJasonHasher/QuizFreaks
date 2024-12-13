package edu.utsa.cs3443.quizfreaks;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

/**
 * BackgroundMusicService is a controller class that controls the music within the app
 *
 * @author Brandon Fischer
 */

public class BackgroundMusicService extends Service {

    /**
     * Our mediaPlayer Object to manage in game sound
     */
    private MediaPlayer mediaPlayer;


    /**
     * This method will create the mediaPlayer object with the chosen background music and set it to looping
     */
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize MediaPlayer with the background music resource
        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        mediaPlayer.setLooping(true); // Loop the music
    }

    /**
     * This method will start the music when called
     *
     * @param intent The Intent supplied to {@link android.content.Context#startService},
     * as given.  This may be null if the service is being restarted after
     * its process has gone away, and it had previously returned anything
     * except {@link #START_STICKY_COMPATIBILITY}.
     * @param flags Additional data about this start request.
     * @param startId A unique integer representing this specific request to
     * start.  Use with {@link #stopSelfResult(int)}.
     *
     * @return START_STICKY
     * Start Sticky Constantly returns from onStartCommand: if this service's process is killed while it is started
     * then it will be left in the started state but don't retain this delivered intent. Later the system will
     * try to re-create the service. Because it is in the started state, it will guarantee to call
     * onStartCommand after creating the new service instance.
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
        }
        return START_STICKY;
    }

    /**
     * This method will destroy the mediaPlayer
     *
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
    }

    /**
     * This method will stop the music when called
     *
     */
    public void stopMusic() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        // Optionally stop the service if no longer needed
        stopSelf();
    }

    /**
     * onBind is used to return an IBinder instance, which allows other components (e.g., activities) to bind to the service for interaction.
     *
     * @param intent
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
