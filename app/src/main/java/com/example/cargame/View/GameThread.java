//WID(4/9/2026)(Sarthak Mittal)(DegamieSign)(GameThread)#impl.1.1.1.1.1
package com.example.cargame.View;

public class GameThread {
    public GameView gameView;
    boolean b=false;
    public void setRunning(boolean b) {
        this.b = b;
    }

    public void start() {
        this.b = true;
    }

}
