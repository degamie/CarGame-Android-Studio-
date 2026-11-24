//WID(24/11/2026)(Sarthak Mittal(DegamieSign)(GameView)
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.RoundRectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
public class GameView extends JPanel{
    public void setViewWidth(int VIEW_WIDTH){
        this.VIEW_WIDTH=VIEW_WIDTH;
    }
    private static final int VIEW_WIDTH = 480;
    private static final int VIEW_HEIGHT = 760;
    private static final int LANE_COUNT = 3;
    private static final int ROAD_WIDTH = 300;
    private static final int ROAD_LEFT = (VIEW_WIDTH - ROAD_WIDTH) / 2;
    private static final int LANE_WIDTH = ROAD_WIDTH / LANE_COUNT;

    private static final int PLAYER_WIDTH = 46;
    private static final int PLAYER_HEIGHT = 82;

    private final Timer gameLoopTimer;
    private final Random random = new Random();
    private final List<TrafficCar> trafficCars = new ArrayList<>();
    private final List<SceneryProp> sceneryProps = new ArrayList<>();

    private long lastFrameNanos = System.nanoTime();
    private double roadScrollOffset = 0.0;
    private double spawnCooldown = 0.8;
    private double propCooldown = 0.35;

    private double playerX;
    private double playerY;
    private double baseSpeed = 340.0; // pixels per second
    private double currentSpeed = 340.0;
    private int score = 0;
    private boolean gameOver = false;

    private boolean moveLeft;
    private boolean moveRight;
    private boolean accelerate;
    private boolean brake;

    private static final Color[] TRAFFIC_COLORS = {
            new Color(239, 68, 68),   // Red
            new Color(16, 185, 129),  // Emerald
            new Color(245, 158, 11),  // Amber
            new Color(168, 85, 247)   // Purple
    };
    public GameView(){
        setPreferableSize(new Dimension(VIEW_WIDTH,VIEW_HEIGHT));
        setFocusable(true);
        bindInput();
gamelooptimer=new Timer(16,e->stepframe());
gamelooptimer.start();
}
private  void stepframe(){
        long now=System.nanoTime();
        lastFrameNanos=now;
}
private void resetGame(){
        ///
}
}