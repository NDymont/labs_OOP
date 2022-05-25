package tetris.Controller;

import javafx.stage.Stage;
import tetris.Model.ModelStatistic;
import tetris.View.*;

import java.io.IOException;
import java.util.*;

public class MenuController {
    private static final int EXIT = 0;

    public static void play(Stage primaryStage) {
        RegistrationView registration = new RegistrationView(primaryStage);
        registration.makeRegistration();
    }

    public static void exit() {
        System.exit(EXIT);
    }

    public static void showStatistic(Stage primaryStage) throws IOException {
        HashMap<String, Integer> statisticMap = ModelStatistic.getSortedMap();
        StatisticView game = new StatisticView(primaryStage);
        game.doStatistic(statisticMap);
    }

    public static void showAboutView(Stage stage) {
        AboutView about = new AboutView(stage);
        about.showAbout();
    }
}
