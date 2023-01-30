import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class WhiteRandomBall extends Ball implements Subject<Ball> {

    private List<Ball> observers = new ArrayList<>();

    public void setObservers(List<Ball> observers) {
        this.observers = observers;
    }

    @Override
    public void move() {
        super.move();
        notifyObservers();
    }

    public WhiteRandomBall() {
        super(Color.WHITE, (int) (Math.random() * 3) + 4,
                (int) (Math.random() * 3) + 4, 300);
    }

    public WhiteRandomBall(Color color, int xSpeed, int ySpeed, int ballSize) {
        super(color, xSpeed, ySpeed, ballSize);
    }

    @Override
    public void update(Ball ball) {

    }

    @Override
    public void update(char keyChar, MainPanel.GameStatus status) {

    }

    @Override
    public void registerObserver(Ball ball) {
        this.observers.add(ball);
    }

    @Override
    public void removeObserver(Ball ball) {
        int i = this.observers.indexOf(ball);
        if (i >= 0) {
            this.observers.remove(i);
        }
    }

    @Override
    public void notifyObservers(char keyChar) {

    }

    @Override
    public void notifyObservers() {
        for (Ball observer : observers) {
            observer.update(this);
        }
    }

}
