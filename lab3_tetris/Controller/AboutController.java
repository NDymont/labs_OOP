package tetris.Controller;

import javafx.stage.Stage;
import tetris.View.Main;

public class AboutController {
    public static void doBack(Stage stage) {
        Main retur = new Main();
        try {
            retur.start(stage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
