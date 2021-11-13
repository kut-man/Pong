package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
 
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Main extends Application {
    private final Button onePlayer = new Button();
    private final Button twoPlayer = new Button();

    @Override
    public void start(Stage window) throws FileNotFoundException {
        Pane root1 = new Pane();
        Scene scene1 = new Scene(root1);
        Image img = new Image(new FileInputStream("D:\\Pong\\src\\sample\\Pong.png"));
        ImageView image = new ImageView(img);
        Image one = new Image(new FileInputStream("D:\\Pong\\src\\sample\\one.png"));
        ImageView single = new ImageView(one);
        Image two = new Image(new FileInputStream("D:\\Pong\\src\\sample\\two.png"));
        ImageView couple = new ImageView(two);

        single.setFitWidth(150);
        single.setFitHeight(50);
        couple.setFitWidth(150);
        couple.setFitHeight(50);
        image.setFitHeight(365);
        image.setFitWidth(590);

        root1.getChildren().add(image);
        onePlayer.setLayoutX(330);
        onePlayer.setLayoutY(250);
        onePlayer.setStyle("-fx-background-color: BLACK;");
        onePlayer.setGraphic(single);
        twoPlayer.setLayoutX(100);
        twoPlayer.setLayoutY(250);
        twoPlayer.setStyle("-fx-background-color: BLACK;");
        twoPlayer.setGraphic(couple);

        root1.getChildren().addAll(onePlayer, twoPlayer);

        twoPlayer.setOnAction(actionEvent -> {

            Play play = new Play(false);
            play.start(window);

        });

        onePlayer.setOnAction(actionEvent -> {
            Play play = new Play(true);
            play.start(window);
        });

        window.setTitle("PONG");
        window.setScene(scene1);
        window.setWidth(600);
        window.setHeight(400);
        window.setResizable(false);
        window.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}