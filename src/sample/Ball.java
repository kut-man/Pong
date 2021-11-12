package sample;

import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Ball {
    public final Circle circle = new Circle(10);
    private int direction = 1;

    public void start() {
        circle.setRadius(10);
        circle.setFill(Color.WHITE);
        int x = (int)(Math.random() * 400) + 100;
        int y = (int)(Math.random() * 200) + 100;
        circle.relocate(x, y);
    }

    public void play() {
        final AnimationTimer ballTimer = new AnimationTimer() {

            double deltaX = 3 * direction;
            double deltaY = 3 * direction;

            @Override
            public void handle(long l) {
                circle.setLayoutX(circle.getLayoutX() + deltaX);
                circle.setLayoutY(circle.getLayoutY() + deltaY);

                boolean atRightBorder = circle.getLayoutX() >= 560;
                boolean atLeftBorder = circle.getLayoutX() <= 15;
                boolean atBottomBorder = circle.getLayoutY() >= (365 - circle.getRadius());
                boolean atTopBorder = circle.getLayoutY() <= (0 + circle.getRadius());

                if (atRightBorder || atLeftBorder) {
                    stop();
                    direction *= -1;
                    play();
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                }
                if (Play.rightPaddleY <= circle.getLayoutY() && Play.rightPaddleY + 80 >= circle.getLayoutY() && circle.getLayoutX() >= 548) {
                    deltaY *= -1;
                    deltaX *= -1;
                }
                if (Play.leftPaddleY <= circle.getLayoutY() && Play.leftPaddleY + 80 >= circle.getLayoutY() && circle.getLayoutX() <= 30) {
                    deltaY *= -1;
                    deltaX *= -1;
                }
            }
        };
        start();
        ballTimer.start();
    }
}