//WID(6/9/2026)(Sarthak Mittal)(DegamieSign)(GameThread)#impl.1.1.1.1.1
package com.example.cargame.View;

public class GameThread {
    private static final int MAX_FPS = 60;
    public GameView gameView;
    boolean b=false;
    public void setRunning(boolean b) {
        this.b = b;
    }

    public void start() {
        this.b = true;
    }
    @Override
    public void run(){
        long targetTime = 1000/MAX_FPS;
    }

}
