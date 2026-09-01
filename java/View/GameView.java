//WID(1/9/2026)(Sarthak Mittal)(DegamieSign)(GameView Model(layer)
public class GameView extends SurfaceView implements SurfaceHolders.CallBack{
    public GameThread gameThread;
    public Paint gamepaint;

    public int screeenWidth,screeenheight;


    // Game state
    private boolean gameOver = false;
    private int score = 0;
    private float gameSpeed = 12f;

    // Touch handling
    private float touchStartX;
}