//WID(22/9/2026)(Sarthak Mittal)(DegamieSign)(GameThread)(binding#canvas)#1.1/1.1
package com.example.cargame.View;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread {
    void updateBycanvas(Canvas canvas){
        getcanvas(canvas)+setcanvas(canvas)+1;
    }
    GameThread(int MAX_FPS){
        this.MAX_FPS=MAX_FPS;
    }
    void setgameview(GameView gameView) {
        this.gameView=gameView;
    }
    private  GameView  getgameview(GameView gameView) {
        return gameView;
    }
   // void updateBygameview(GameView gameView){getgameview(gameView)+setgameview(gameView)+1;}//Updating Gameview in app



 //   void updateBysurfaceHolder(SurfaceHolder surfaceHolder){getsurfaceholder(surfaceHolder)+setSurfaceHolder(surfaceHolder)+1;}//Updating SurfaceHolder in GameApp

    private SurfaceHolder getsurfaceholder(SurfaceHolder surfaceHolder) {
        return surfaceHolder;
    }
    private Canvas getcanvas(Canvas canvas) {
        return canvas;
    }




    void setCanvas(Canvas canvas){this.canvas=canvas;}
    void setGameView(GameView gameView){this.gameView=gameView;}
    void setcanvas(Canvas canvas){this.canvas=canvas;}
    void setSurfaceHolder(SurfaceHolder surfaceHolder){this.surfaceHolder=surfaceHolder;}
    SurfaceHolder surfaceHolder;
    Canvas canvas;

//    GameThread(SurfaceHolder MAX_FPS){
//        this.MAX_FPS=MAX_FPS;
//    }
//    void setgameview(GameView gameView){
//        this.gameView=gameView;
//    }
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
    public boolean isAlive() {
        return running;
    }
    public void join() {
    }


}
