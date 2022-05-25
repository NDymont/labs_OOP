package tetris.Controller;

import javafx.stage.Stage;
import tetris.View.Main;
import tetris.View.TetrisView;

public class RegistrartionController {
    public void doGame(Stage primaryStage) {
        TetrisView game = new TetrisView(primaryStage);
        try {
            game.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doBack(Stage stage) {
        Main retur = new Main();
        try {
            retur.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
