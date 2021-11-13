package sample;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Play {
    public static double leftPaddleY = 150;
    public static double rightPaddleY = 150;
    private double leftPaddleDY;
    private double rightPaddleDY;
    public boolean single;
    private final Ball ball = new Ball();

    public Play(boolean single) {
        this.single = single;
    }

    public Rectangle rec1 = new Rectangle(10, 130, 10, 80);
    public Rectangle rec2 = new Rectangle(560, 130, 10, 80);

    public final AnimationTimer paddleTimer = new AnimationTimer() {

        @Override
        public void handle(long now) {

            leftPaddleY += leftPaddleDY;
            rightPaddleY += rightPaddleDY;
            if (leftPaddleY < 2) {
                leftPaddleY = 2;
            }
            if (rightPaddleY < 1) {
                rightPaddleY = 1;
            }
            if (leftPaddleY > 278) {
                leftPaddleY = 278;
            }
            if (rightPaddleY > 278) {
                rightPaddleY = 278;
            }
            if (single) {
                rec1.setY(leftPaddleY);
                rec2.setY(ball.circle.getLayoutY() - 35);
            } else {
                rec1.setY(leftPaddleY);
                rec2.setY(rightPaddleY);
            }
        }
    };

    public void start(Stage window) {
        rec1.setFill(Color.BLACK);
        rec1.setStroke(Color.RED);
        rec2.setFill(Color.BLACK);
        rec2.setStroke(Color.GREEN);

        Line line = new Line();
        line.setStartX(290);
        line.setStartY(0);
        line.setEndX(290);
        line.setEndY(365);
        line.setStyle("-fx-stroke: WHITE;");

        Pane root2 = new Pane();
        root2.setStyle("-fx-background-color: BLACK;");
        root2.getChildren().addAll(rec1, rec2, line, ball.circle);

        Scene scene2 = new Scene(root2);
        scene2.setOnKeyPressed(keyPressed);
        scene2.setOnKeyReleased(keyReleased);

        window.setScene(scene2);
        window.show();

        ball.start();
        ball.play();
        paddleTimer.start();
    }

    private final EventHandler<KeyEvent> keyReleased = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            switch (event.getCode()) {
                case W: leftPaddleDY = 0;
                case S: leftPaddleDY = 0;
                case P: rightPaddleDY = 0;
                case L: rightPaddleDY = 0;
            }
        }
    };

    private final EventHandler<KeyEvent> keyPressed = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().toString().equals("W")) {
                leftPaddleDY = -6;
            }
            if (event.getCode().toString().equals("S")) {
                leftPaddleDY = 6;
            }
            if (event.getCode().toString().equals("P")) {
                rightPaddleDY = -6;
            }
            if (event.getCode().toString().equals("L")) {
                rightPaddleDY = 6;
            }
        }
    };
}