//WID(2/9/2026)(Sarthak Mittal)(DegamieSign)(GameView)#impl
package com.example.cargame.View;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceView;

import com.example.cargame.View.GameThread;
import com.example.cargame.View.Rectf;
public class GameView extends SurfaceView implements SurfaceHolders.CallBack{
    public int score=0;
    public GameThread gameThread;
    public Paint gamepaint;

    public int screeenWidth,screeenheight;
    // Game state
    private boolean gameOver = false;
    private int score = 0;
    private float gameSpeed = 12f;

    // Touch handling
    private float touchStartX;
    public Rectf playerCar;
    public float carlane=1;
    public float carwidth,carheight;
    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        if (canvas == null) return;

        // Background (grass)
        canvas.drawColor(Color.rgb(34, 139, 34));

        // Road
        paint.setColor(Color.DKGRAY);
        canvas.drawRect(roadLeft, 0, roadRight, screenHeight, paint);

        // Lane stripes
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(8);
        for (int lane = 1; lane < LANE_COUNT; lane++) {
            float x = roadLeft + laneWidth * lane;
            for (float y : stripeYPositions) {
                canvas.drawLine(x, y, x, y + screenHeight / 20f, paint);
            }
        }

        // Road edges
        paint.setColor(Color.YELLOW);
        canvas.drawRect(roadLeft - 6, 0, roadLeft, screenHeight, paint);
        canvas.drawRect(roadRight, 0, roadRight + 6, screenHeight, paint);

        // Player car
        paint.setColor(Color.RED);
        canvas.drawRoundRect(playerCar, 16, 16, paint);
        // windshield
        paint.setColor(Color.rgb(150, 220, 255));
        canvas.drawRect(playerCar.left + carWidth * 0.15f, playerCar.top + carHeight * 0.15f,
                playerCar.right - carWidth * 0.15f, playerCar.top + carHeight * 0.4f, paint);

        // Enemy cars
        paint.setColor(Color.BLUE);
        for (RectF enemy : enemyCars) {
            canvas.drawRoundRect(enemy, 16, 16, paint);
        }

        // Score
        paint.setColor(Color.WHITE);
        paint.setTextSize(60);
        canvas.drawText("Score: " + score, 40, 90, paint);

        // Game over overlay
        if (gameOver) {
            paint.setColor(Color.argb(180, 0, 0, 0));
            canvas.drawRect(0, 0, screenWidth, screenHeight, paint);

            paint.setColor(Color.WHITE);
            paint.setTextSize(90);
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText("GAME OVER", screenWidth / 2f, screenHeight / 2f - 40, paint);

            paint.setTextSize(50);
            canvas.drawText("Score: " + score, screenWidth / 2f, screenHeight / 2f + 40, paint);
            canvas.drawText("Tap to Restart", screenWidth / 2f, screenHeight / 2f + 120, paint);
            paint.setTextAlign(Paint.Align.LEFT);
        }
    }
    public void resume(){
        gameThread.setRunning(true);
        gameThread.start();
    }
    public void pause(){
        gameThread.setRunning(false);
    }
    public GameView(GameThread gameThread, Paint gamepaint, int screeenWidth, int screeenheight, boolean gameOver, int score, float gameSpeed, float touchStartX, Rectf playerCar, float carlane, float carwidth, float carheight) {
        super();
        this.gameThread = gameThread;
        this.gamepaint = gamepaint;
        this.screeenWidth = screeenWidth;
        this.screeenheight = screeenheight;
        this.gameOver = gameOver;
        this.score = score;
        this.gameSpeed = gameSpeed;
        this.touchStartX = touchStartX;
        this.playerCar = playerCar;
        this.carlane = carlane;
        this.carwidth = carwidth;
        this.carheight = carheight;
    }


}