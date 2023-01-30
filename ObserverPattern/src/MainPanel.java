import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class MainPanel extends JPanel implements KeyListener, Subject<Ball> {
    private List<Ball> paintingBallList = new ArrayList<>();
    private List<Ball> observers;


    @Override
    public void registerObserver(Ball ball) {
        observers.add(ball);
    }

    @Override
    public void removeObserver(Ball ball) {
        int i = observers.indexOf(ball);
        if (i >= 0) {
            observers.remove(i);
        }
    }

    @Override
    public void notifyObservers(char keyChar) {
        for (Ball observer : observers) {
            observer.update(keyChar, gameStatus);
        }
    }

    @Override
    public void notifyObservers() {

    }

    enum GameStatus {PREPARING, START, STOP}

    protected static GameStatus gameStatus;
    private int score;
    private Ball whiteBall;
    private Ball whiteBallRandom;
    Timer t;

    public MainPanel() {
        super();
        setLayout(null);
        setSize(590, 590);
        setFocusable(true);
        this.addKeyListener(this);
        t = new Timer(50, e -> moveBalls());
        restartGame();
        this.observers = new ArrayList<>();
    }


    public void startGame() {
        gameStatus = GameStatus.START;
        this.whiteBall.setVisible(true);
        this.whiteBallRandom.setVisible(true);
    }

    public void stopGame() {
        gameStatus = GameStatus.STOP;
        this.t.stop();
        paintingBallList.forEach(b -> {
            b.setVisible(true);
            if (isIntersect(b, whiteBall)) {
                if (b.getColor() == Color.RED) {
                    scoreIncrement(80);
                } else if (b.getColor() == Color.BLUE) {
                    scoreIncrement(-80);
                }
            }
        });
        repaint();
    }

    public boolean isIntersect(Ball a, Ball b) {
        int dis = (a.getWidth() + b.getWidth()) / 2;
        double diffXPow = Math.pow(a.convertToCentralX() - b.convertToCentralX(), 2);
        double diffYPow = Math.pow(a.convertToCentralY() - b.convertToCentralY(), 2);
        return diffXPow + diffYPow < dis * dis;
    }

    public void restartGame() {
        gameStatus = GameStatus.PREPARING;
        if (this.paintingBallList.size() > 0) {
            paintingBallList.forEach(o -> {
                observers.remove(o);
                this.remove(o);
            });
        }
        this.paintingBallList = new ArrayList<>();
        this.observers = new ArrayList<>();
        this.observers.add(whiteBall);
        WhiteRandomBall ball = this.getWhiteBallRandom();
        if (ball != null) {
            ball.setObservers(new ArrayList<>());
        }

        Ball.setCount(0);
        this.score = 100;
        if (this.whiteBall != null)
            this.whiteBall.setVisible(false);
        if (this.whiteBallRandom != null)
            this.whiteBallRandom.setVisible(false);

        this.t.start();
        repaint();
    }

    public void setWhiteBall(Ball whiteBall) {
        this.whiteBall = whiteBall;
        this.whiteBall.setVisible(false);
        add(whiteBall);
    }

    public WhiteRandomBall getWhiteBallRandom(){
        return (WhiteRandomBall) this.whiteBallRandom;
    }

    public void setWhiteBallRandom(Ball whiteBallRandom) {
        this.whiteBallRandom = whiteBallRandom;
        this.whiteBallRandom.setVisible(false);
        add(whiteBallRandom);
    }

    public void moveBalls() {
        paintingBallList.forEach(Ball::move);
        if (gameStatus == GameStatus.START) {
            score--;
            whiteBall.move();
            whiteBallRandom.move();
        }
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setFont(new Font("Arial", Font.PLAIN, 30));
        g.setColor(Color.BLACK);
        g.drawString("Score: " + score, 20, 40);

        if (gameStatus == GameStatus.START) {
            this.setBackground(Color.WHITE);
        }

        if (gameStatus == GameStatus.STOP) {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.BOLD, 45));
            g.drawString("Game Over!", 200, 200);
            g.setFont(new Font("", Font.BOLD, 40));
            g.drawString("Your score is " + score, 190, 280);
        }
    }

    public void scoreIncrement(int increment) {
        this.score += increment;
    }


    public void addBallToPanel(Ball ball) {
        paintingBallList.add(ball);
        this.add(ball);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        char keyChar = e.getKeyChar();
        System.out.println("Press: " + keyChar);
        notifyObservers(keyChar);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
