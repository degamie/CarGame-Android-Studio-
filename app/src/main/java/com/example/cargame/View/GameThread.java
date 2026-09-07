//WID(7/9/2026)(Sarthak Mittal)(DegamieSign)(GameThread)#impl.1.1.1.1.1
package com.example.cargame.View;

public class GameThread {
    void setMAX_FPS(int  MAX_FPS){
        this.MAX_FPS=MAX_FPS;
    }
    private static int MAX_FPS = 60;
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
