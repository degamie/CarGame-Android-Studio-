//WID(9/9/2026)(Sarthak Mittal)(DegamieSign)(GameThread)#impl.1.1.1.1.1.1.1.1.1
package com.example.cargame.View;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread {
    SurfaceHolder surfaceHolder;
    Canvas canvas;

    GameThread(int MAX_FPS){
        this.MAX_FPS=MAX_FPS;
    }
    void setgameview(GameView gameView){
        this.gameView=gameView;
    }
    void setMAX_FPS(int  MAX_FPS){
        this.MAX_FPS=MAX_FPS;
    }
    private static int MAX_FPS = 60;
    public GameView gameView;
    boolean running=false;
    public void setRunning(boolean running) {
        this.running = running;
    }

    public void start() {
        this.running = true;
    }
//    @Override
    public void run(){
        long targetTime = 1000/MAX_FPS;
        while(running){
            long startTime=System.currentTimeMillis();
            Canvas canvas=null;
            try{
                canvas=surfaceHolder.lockCanvas();
                synchronized (surfaceHolder){
                    gameView.update();
                    gameView.draw(canvas);

                }
            } catch(Exception e){
                e.printStackTrace();
            }
       finally {
            if (canvas != null) {
                try {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        }


    }

}
