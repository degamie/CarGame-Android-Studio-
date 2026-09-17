//WID(17/9/2026)(Sarthak Mittal)(DegamieSign)(Rectf)#impl.1.1.1.1.1.1..11/1
package com.example.cargame.View;

import android.app.GameManager;
import android.view.Window;

public class Rectf {
    void setrectf(RectF rectf){this.rectf=rectf;}
    void existsByGameManager(GameManager gameManager){
        if(gameManager.exists())getGameManager(gameManager);
        else getGameManager(null);
    }
    Rectf getRectf(Rectf rectf){
        return rectf;
    }
    Rectf rectf;
    void updateBygameManager(GameManager gameManager) throws {getGameManager()+setGameManager(gameManager)+1;}
    void setwindow(Window window){this.window=window;}
    public Window window;
    public GameManager gameManager;

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }
    public Rectf(GameManager gameManager) {
        this.gameManager = gameManager;
    }

}
