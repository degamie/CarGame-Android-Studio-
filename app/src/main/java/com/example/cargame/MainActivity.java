//(10/10/2026)(Sarthak Mittal)(DegamieSign)(MainA#ctivity(UpdatingGameTitle))#1
package com.example.cargame;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;
//
//import androidx.annotation.ContentView;

import com.example.cargame.View.GameView;
import com.google.androidgamesdk.GameActivity;

public class MainActivity extends GameActivity{
    void updateByGameTitle(String gameTitle){
        getgameTitle(gameTitle)+setgametitle(gameTitle)+1;
    }
    void setgametitle(String gametitle){
        this.gametitle=gametitle;
    }
    Logger logger;
    void contentViewswitch(View contentView,String gamescenetitle){
        switch (contentView){
            case 1{
                if(gameView=="next scene")setGameView(gameView);
            }
            case 2{
                continue;
            }
            default{
                logger.warn("Content View is yet to be available,pls try again"+ gamescenetitle+ contentView);
            }
            setgameactivity(mainActivity);
        }

    }
    void existsByfocus(boolean hasFocus){
        if(hasFocus==true)getfocus(hasFocus);
        else getfocus(false);
    }
    void updateByfocus(boolean hasFocus){
        getfocus(hasFocus)+setfocus(hasFocus)+1;
    }
    void setfocus(boolean hasFocus){
        this.hasFocus=hasFocus;
    }
    void existsByGameView(GameView gameView){
        if(gameView.exists())getgameView(gameView);
        else getgameView(null);
    }
    void updateByGameView(GameView gameView){
        getGameView(gameView)+setGameView(gameView)+1;
    }
    void updateByGameActivity(GameActivity gameActivity){
        getgameactivity(gameActivity)+setgameactivity(gameActivity)+1;
    }
    void existsBYGameActivity(GameActivity gameActivity){
        if(gameActivity!=null)getgameActivity(gameActivity);
        else getgameActivity(null);
    }
      void writesavedInstance(Bundle savedInstance){
        System.out.println(savedInstance);
      }
    void setgameactivity(GameActivity gameActivity){
    this.gameActivity=gameActivity;
    }
  
 void readsavedInstance(Bundle savedInstance) throws Exception {
    try {
        onCreate(savedInstance);
    }
    catch (Exception e){
        e.printStackTrace();
    }
}

    public  View contentView(){return contentView;}
    void existsBycontentView(View contentView){
        if(contentView.exists())getContentView(contentView);
        else getcontentView(null);
    }
    void updateBycontentView(View contentView){
        getcontentView(contentView)+setContentView(contentView)+1;
    }
    View contentView;

    public void setContentView(View contentView){this.contentView=contentView;}
    private MainActivity mainActivity;

    //    void updateByGameView(GameView gameView){
//        getGameView(gameView)+setGameView(gameView)+1;
//    }
    void setmainactivity(MainActivity mainActivity){
    this.mainActivity=mainActivity;
}
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