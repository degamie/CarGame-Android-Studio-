//WID(4/9/2026)(Sarthak Mittal)(DegamieSign)(Rectf)#impl.1.1.1.1.1
package com.example.cargame.View;

import android.app.GameManager;
import android.view.Window;

public class Rectf {
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
