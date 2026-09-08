//(8/9/2026)(Sarthak Mittal)(DegamieSign)(MainActivity)#impl.1.1.1.1.1.1
package com.example.cargame;

import android.os.Bundle;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import com.example.cargame.View.GameView;
import com.google.androidgamesdk.GameActivity;

public class MainActivity extends GameActivity {
    void setGameView(GameView gameView){this.gameView=gameView;}
    MainActivity(GameView gameView){
        this.gameView=gameView;
    }
    public GameView gameView;
    static {
        System.loadLibrary("cargame");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);
    }

    protected void onPause() {
        super.onPause();
        gameView.pause();
    }

    protected void onResume() {
        super.onResume();
        gameView.resume();
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (hasFocus) {
            hideSystemUi();
        }
    }

    private void hideSystemUi() {
        WindowInsetsController insetsController = getWindow().getInsetsController();
        if (insetsController != null) {
            insetsController.hide(WindowInsets.Type.systemBars());
            insetsController.setSystemBarsBehavior(WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE);
        }
    }
}