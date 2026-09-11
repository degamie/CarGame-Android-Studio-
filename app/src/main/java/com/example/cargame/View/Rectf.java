//WID(11/9/2026)(Sarthak Mittal)(DegamieSign)(Rectf)#impl.1.1.1.1.1.1
package com.example.cargame.View;

import android.app.GameManager;
import android.view.Window;

public class Rectf {
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
