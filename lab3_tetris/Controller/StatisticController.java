package tetris.Controller;

import javafx.stage.Stage;
import tetris.View.Main;

public class StatisticController {
    public static void back(Stage stage) {
        Main retur = new Main();
        try {
            retur.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
