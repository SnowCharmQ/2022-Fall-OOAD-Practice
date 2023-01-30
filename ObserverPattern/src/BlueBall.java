import java.awt.*;

public class BlueBall extends Ball {
    public BlueBall() {
        super(Color.BLUE, 6, 4, 60);
    }

    public BlueBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void update(Ball ball) {
        this.setVisible(isIntersect(ball));
    }

    @Override
    public void update(char keyChar, MainPanel.GameStatus status) {
        this.setXSpeed(-1 * this.getXSpeed());
        this.setYSpeed(-1 * this.getYSpeed());
    }
}
