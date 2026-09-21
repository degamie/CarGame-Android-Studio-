//WID(21/9/2026)(Sarthak Mittal(DegamieSign)(GameView)(binding Paint)#1/1.1.1
package com.example.cargame.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.cargame.View.GameThread;

import java.util.ArrayList;
import java.util.Random;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    // ============================================================
    // Game objects
    // ============================================================

    private GameThread gameThread;
    private final Paint paint;
    void updateBypaint(Paint paint){
        getpaint(paint)+setpaint(paint)+1;
    }
    void setpaint(Paint paint){this.paint=paint;}

    private final Random random;

    private final ArrayList<Float> stripeYPositions;
    private final ArrayList<RectF> enemyCars;
    private final ArrayList<Integer> enemyLanes;
    void setStripeYPositions(ArrayList<Float>stripeYPositions){
        this.stripeYPositions=stripeYPositions;
    }
    // ============================================================
    // Screen
    // ============================================================

    private int screenWidth;
    private int screenHeight;
void setscreenWidth(int screenWidth){
    this.screenwidth=screenWidth;
}
void setscreenHeight(int screenHeight){
    this.screenHeight=screenHeight;
}
    // ============================================================
    // Road
    // ============================================================

    private static final int LANE_COUNT = 3;

    private float roadLeft;
    private float roadRight;
    private float laneWidth;
    void setlanewidth(flaot lanewidth){
        this.laneWidth=laneWidth;
    }
void setroadRight(float roadRight){
    this.roadRight=roadRight;
}
    // ============================================================
    // Player
    // ============================================================

    private RectF playerCar;
void setPlayerCar(Rectf playerCar){
    this.playerCar=playerCar;
}
    private float carWidth;
    private float carHeight;

    private int playerLane = 1;

    // ============================================================
    // Game state
    // ============================================================

    private int score = 0;

    private float gameSpeed = 12f;

    private float stripeSpeed = 12f;

    public boolean gameOver = false;

    // ============================================================
    // Enemy spawning
    // ============================================================

    private long lastSpawnTime = 0L;

    private long spawnInterval = 1400L;

    // ============================================================
    // Touch
    // ============================================================

    private float touchStartX;
    void settouchStartX(float touchStartX){
        this.touchStartX=touchStartX;
    }

    // ============================================================
    // Constructor
    // ============================================================

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        random = new Random();

        stripeYPositions = new ArrayList<>();
        enemyCars = new ArrayList<>();
        enemyLanes = new ArrayList<>();

        setFocusable(true);
    }

    // ============================================================
    // Surface callbacks
    // ============================================================

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        screenWidth = getWidth();
        screenHeight = getHeight();

        initGameObjects();

        gameThread = new GameThread(getHolder());
        gameThread.setRunning(true);
        gameThread.start();
    }

    @Override
    public void surfaceChanged(
            SurfaceHolder holder,
            int format,
            int width,
            int height) {

        screenWidth = width;
        screenHeight = height;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        if (gameThread != null) {

            gameThread.setRunning(false);

            boolean retry = true;

            while (retry) {
                try {
                    gameThread.join();
                    retry = false;
                } catch (Exception e) {
                    Thread.currentThread().interrupt();
                    retry = false;
                }
            }
        }
    }

    // ============================================================
    // Initialize game
    // ============================================================

    private void initGameObjects() {

        roadLeft = screenWidth * 0.20f;
        roadRight = screenWidth * 0.80f;

        laneWidth = (roadRight - roadLeft) / LANE_COUNT;

        carWidth = laneWidth * 0.55f;
        carHeight = screenHeight * 0.13f;

        playerLane = 1;

        float playerX =
                roadLeft
                        + playerLane * laneWidth
                        + (laneWidth - carWidth) / 2f;

        float playerY =
                screenHeight - carHeight - 80f;

        playerCar = new RectF(
                playerX,
                playerY,
                playerX + carWidth,
                playerY + carHeight
        );

        // Road stripes
        stripeYPositions.clear();

        float stripeSpacing = screenHeight / 6f;

        for (int i = 0; i < 6; i++) {
            stripeYPositions.add(i * stripeSpacing);
        }

        // Remove previous enemies
        enemyCars.clear();
        enemyLanes.clear();

        // Reset game
        score = 0;
        gameSpeed = 12f;
        stripeSpeed = 12f;
        spawnInterval = 1400L;

        gameOver = false;

        lastSpawnTime = System.currentTimeMillis();
    }

    // ============================================================
    // Update game
    // ============================================================

    public void update() {

        if (gameOver || playerCar == null) {
            return;
        }

        // --------------------------------------------------------
        // Move road stripes
        // --------------------------------------------------------

        for (int i = 0; i < stripeYPositions.size(); i++) {

            float y =
                    stripeYPositions.get(i)
                            + stripeSpeed;

            if (y > screenHeight) {
                y -= screenHeight;
            }

            stripeYPositions.set(i, y);
        }

        // --------------------------------------------------------
        // Spawn enemies
        // --------------------------------------------------------

        long now = System.currentTimeMillis();

        if (now - lastSpawnTime > spawnInterval) {

            spawnEnemy();

            lastSpawnTime = now;

            // Increase difficulty
            if (spawnInterval > 500) {
                spawnInterval -= 15;
            }
        }

        // --------------------------------------------------------
        // Move enemies
        // --------------------------------------------------------

        for (int i = enemyCars.size() - 1; i >= 0; i--) {

            RectF enemy = enemyCars.get(i);

            enemy.top += gameSpeed;
            enemy.bottom += gameSpeed;

            // Enemy passed player
            if (enemy.top > screenHeight) {

                enemyCars.remove(i);
                enemyLanes.remove(i);

                score++;

                if (gameSpeed < 30f) {
                    gameSpeed += 0.15f;
                }

            } else if (RectF.intersects(enemy, playerCar)) {

                gameOver = true;
            }
        }
    }

    // ============================================================
    // Spawn enemy car
    // ============================================================

    private void spawnEnemy() {

        int lane = random.nextInt(LANE_COUNT);

        float enemyX =
                roadLeft
                        + lane * laneWidth
                        + (laneWidth - carWidth) / 2f;

        float enemyY = -carHeight - 20f;

        RectF enemy = new RectF(
                enemyX,
                enemyY,
                enemyX + carWidth,
                enemyY + carHeight
        );

        enemyCars.add(enemy);
        enemyLanes.add(lane);
    }

    // ============================================================
    // Draw
    // ============================================================

    @Override
    public void draw(Canvas canvas) {

        super.draw(canvas);

        if (canvas == null) {
            return;
        }

        // --------------------------------------------------------
        // Grass
        // --------------------------------------------------------

        canvas.drawColor(
                Color.rgb(34, 139, 34)
        );

        // --------------------------------------------------------
        // Road
        // --------------------------------------------------------

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.DKGRAY);

        canvas.drawRect(
                roadLeft,
                0,
                roadRight,
                screenHeight,
                paint
        );

        // --------------------------------------------------------
        // Lane stripes
        // --------------------------------------------------------

        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(8f);

        for (int lane = 1; lane < LANE_COUNT; lane++) {

            float x =
                    roadLeft + laneWidth * lane;

            for (float y : stripeYPositions) {

                canvas.drawLine(
                        x,
                        y,
                        x,
                        y + screenHeight / 20f,
                        paint
                );
            }
        }

        // --------------------------------------------------------
        // Road edges
        // --------------------------------------------------------

        paint.setColor(Color.YELLOW);

        canvas.drawRect(
                roadLeft - 6f,
                0,
                roadLeft,
                screenHeight,
                paint
        );

        canvas.drawRect(
                roadRight,
                0,
                roadRight + 6f,
                screenHeight,
                paint
        );

        // --------------------------------------------------------
        // Player car
        // --------------------------------------------------------

        if (playerCar != null) {

            paint.setColor(Color.RED);

            canvas.drawRoundRect(
                    playerCar,
                    16f,
                    16f,
                    paint
            );

            // Windshield
            paint.setColor(
                    Color.rgb(150, 220, 255)
            );

            canvas.drawRect(
                    playerCar.left + carWidth * 0.15f,
                    playerCar.top + carHeight * 0.15f,
                    playerCar.right - carWidth * 0.15f,
                    playerCar.top + carHeight * 0.40f,
                    paint
            );
        }

        // --------------------------------------------------------
        // Enemy cars
        // --------------------------------------------------------

        paint.setColor(Color.BLUE);

        for (RectF enemy : enemyCars) {

            canvas.drawRoundRect(
                    enemy,
                    16f,
                    16f,
                    paint
            );
        }

        // --------------------------------------------------------
        // Score
        // --------------------------------------------------------

        paint.setColor(Color.WHITE);
        paint.setTextSize(60f);
        paint.setTextAlign(Paint.Align.LEFT);

        canvas.drawText(
                "Score: " + score,
                40f,
                90f,
                paint
        );

        // --------------------------------------------------------
        // Game Over
        // --------------------------------------------------------

        if (gameOver) {

            paint.setColor(
                    Color.argb(180, 0, 0, 0)
            );

            canvas.drawRect(
                    0,
                    0,
                    screenWidth,
                    screenHeight,
                    paint
            );

            paint.setColor(Color.WHITE);

            paint.setTextAlign(
                    Paint.Align.CENTER
            );

            paint.setTextSize(90f);

            canvas.drawText(
                    "GAME OVER",
                    screenWidth / 2f,
                    screenHeight / 2f - 40f,
                    paint
            );

            paint.setTextSize(50f);

            canvas.drawText(
                    "Score: " + score,
                    screenWidth / 2f,
                    screenHeight / 2f + 40f,
                    paint
            );

            canvas.drawText(
                    "Tap to Restart",
                    screenWidth / 2f,
                    screenHeight / 2f + 120f,
                    paint
            );

            paint.setTextAlign(
                    Paint.Align.LEFT
            );
        }
    }

    // ============================================================
    // Touch controls
    // ============================================================

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getActionMasked()) {

            case MotionEvent.ACTION_DOWN:

                touchStartX = event.getX();

                return true;

            case MotionEvent.ACTION_UP:

                float touchEndX = event.getX();

                if (gameOver) {

                    restartGame();

                    return true;
                }

                float deltaX =
                        touchEndX - touchStartX;

                // Swipe left
                if (deltaX < -50f) {

                    movePlayerLeft();

                }
                // Swipe right
                else if (deltaX > 50f) {

                    movePlayerRight();
                }

                return true;
        }

        return true;
    }

    // ============================================================
    // Player movement
    // ============================================================

    private void movePlayerLeft() {

        if (playerLane > 0) {
            playerLane--;
            updatePlayerPosition();
        }
    }

    private void movePlayerRight() {

        if (playerLane < LANE_COUNT - 1) {
            playerLane++;
            updatePlayerPosition();
        }
    }

    private void updatePlayerPosition() {

        if (playerCar == null) {
            return;
        }

        float x =
                roadLeft
                        + playerLane * laneWidth
                        + (laneWidth - carWidth) / 2f;

        playerCar.left = x;
        playerCar.right = x + carWidth;
    }

    // ============================================================
    // Restart
    // ============================================================

    private void restartGame() {

        initGameObjects();
    }

    // ============================================================
    // Thread control
    // ============================================================

    public void resume() {

        if (gameThread != null) {
            gameThread.setRunning(true);

            if (!gameThread.isAlive()) {
                gameThread.start();
            }
        }
    }

    public void pause() {

        if (gameThread != null) {
            gameThread.setRunning(false);
        }
    }

    // ============================================================
    // Getters / Setters
    // ============================================================

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = Math.max(0, score);
    }

    public float getGameSpeed() {
        return gameSpeed;
    }

    public void setGameSpeed(float gameSpeed) {
        this.gameSpeed = Math.max(1f, gameSpeed);
    }

    public RectF getPlayerCar() {
        return playerCar;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public long getSpawnInterval() {
        return spawnInterval;
    }

    public void setSpawnInterval(long spawnInterval) {
        this.spawnInterval =
                Math.max(100L, spawnInterval);
    }

    public float getStripeSpeed() {
        return stripeSpeed;
    }

    public void setStripeSpeed(float stripeSpeed) {
        this.stripeSpeed =
                Math.max(1f, stripeSpeed);
    }
}

