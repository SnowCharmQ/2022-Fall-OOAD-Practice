import java.awt.*;

public class WhiteBall extends Ball {
    public WhiteBall() {
        super(Color.WHITE, 0, 0, 200);
    }

    public WhiteBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void update(Ball ball) {

    }

    @Override
    public void update(char keyChar, MainPanel.GameStatus status) {
        if (status == MainPanel.GameStatus.START) {
            switch (keyChar){
                case 'a' -> this.setXSpeed(-8);
                case 'd' -> this.setXSpeed(8);
                case 'w' -> this.setYSpeed(-8);
                case 's' -> this.setYSpeed(8);
            }
        }
    }
}
