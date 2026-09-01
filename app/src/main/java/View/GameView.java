//WID(1/9/2026)(Sarthak Mittal)(DegamieSign)(GameView Model(layer)(varibles and param constructor)
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
    public Rectf playerCar;
    public float carlane=1;
    public float carwidth,carheight;
    public GameView(GameThread gameThread, Paint gamepaint, int screeenWidth, int screeenheight, boolean gameOver, int score, float gameSpeed, float touchStartX, Rectf playerCar, float carlane, float carwidth, float carheight) {
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