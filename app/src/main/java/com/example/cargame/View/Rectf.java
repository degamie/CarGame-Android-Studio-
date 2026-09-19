//WID(19/9/2026)(Sarthak Mittal)(DegamieSign)(Rectf)#impl.1.1.1.1.1.1..11/1.1.1
package com.example.cargame.View;

import android.app.GameManager;
import android.graphics.RectF;
import android.view.Window;

public class Rectf {
    void updateByrectf(Rectf rectf){getRectf(rectf)+setrectf(rectf)+1;}//updating rectf in Gameapp
    Rectf rectf;
    void setrectf(Rectf rectf){this.rectf=rectf;}
    Rectf getRectf(Rectf rectf){
        return rectf;
    }
    Window getWindow(Window window){return window;}
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
   // void updateBygameManager(GameManager gameManager) throws {getGameManager()+setGameManager(gameManager)+1;}

