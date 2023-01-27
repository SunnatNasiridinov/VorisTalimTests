package com.isystem.tests.app;

import android.app.Application;
import android.media.MediaPlayer;

import com.isystem.tests.cache.MemoryHelper;

public class App extends Application {
    private MediaPlayer mediaPlayer;

    @Override
    public void onCreate() {
        super.onCreate();
        startMusic();
        MemoryHelper.init(this);

    }

    private void startMusic() {
        mediaPlayer=new MediaPlayer();
        mediaPlayer.start();
    }
}
