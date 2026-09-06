//2I(5/9/2026)(Sarthak Mittal)(DegamieSign)(MainActivity)#impl.1.1.1.1.1
package com.example.cargame;

import android.view.WindowInsets;
import android.view.WindowInsetsController;

import com.example.cargame.View.GameView;
import com.google.androidgamesdk.GameActivity;

public class MainActivity extends GameActivity {
    MainActivity(GameView gameView){
        this.gameView=gameView;
    }
    public GameView gameView;
    static {
        System.loadLibrary("cargame");
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