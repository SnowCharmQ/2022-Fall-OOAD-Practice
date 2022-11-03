import java.awt.*;
import java.util.Random;

public class RedBall extends Ball {
    public RedBall() {
        super(Color.RED, 3, 2, 60);
    }

    public RedBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void update(Ball ball) {
        this.setVisible(isIntersect(ball));
    }

    @Override
    public void update(char keyChar, MainPanel.GameStatus status) {
        Random random = new Random();
        switch (keyChar) {
            case 'a' -> this.setXSpeed(-random.nextInt(3) - 1);
            case 'd' -> this.setXSpeed(random.nextInt(3) + 1);
            case 'w' -> this.setYSpeed(-random.nextInt(3) - 1);
            case 's' -> this.setYSpeed(random.nextInt(3) + 1);
        }
    }
}
