package runner;

import controller.GameController;
import view.StartScreen;
import view.CivEnum;
import view.GameScreen;
import model.Map;
import model.QinDynasty;
import model.RomanEmpire;
import model.Egypt;
import model.Bandit;
import view.GridFX;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.media.AudioClip;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 */
public class CivilizationGame extends Application {
    private Scene sceneStart, sceneSecondary;
    private StartScreen startScreen = new StartScreen();
    private TextInputDialog firstSettlement = new TextInputDialog();
    private TextInputDialog numBandits = new TextInputDialog();

    /**
     * this method is called upon running/launching the application
     * this method should display a scene on the stage
     */
    public void start(Stage primaryStage) throws Exception {
        sceneStart = new Scene(startScreen);
        Image startPic = new Image("File:./src/main/java/view/sword.png");
        startScreen.getStartButton().setGraphic(new ImageView(startPic));
        startScreen.getStartButton().setOnAction(e -> {
                CivEnum selectedCiv =
                    startScreen.getCivList()
                        .getSelectionModel().getSelectedItem();
                if (selectedCiv.toString().equals("Ancient Egypt")) {
                    GameController.setCivilization(new Egypt());
                } else if (selectedCiv.toString().equals("Qin Dynasty")) {
                    GameController.setCivilization(new QinDynasty());
                } else {
                    GameController.setCivilization(new RomanEmpire());
                }
                AudioClip begin =
                    new AudioClip("File:./src/main/java/audio/Begin.mp3");
                begin.play();
                primaryStage.setScene(startGame());
            });
        primaryStage.setScene(sceneStart);
        primaryStage.show();
    }
    /**
     * This is the main method that launches the javafx application
     */
    public static void main(String[] args) {
        launch(args);
    }
    /**
    * This method is responsible for setting the scene to the corresponding
    * layout.
    * and returning the scene.
    * @return Scene
    */

    public Scene startGame() {
        GameScreen gameScreen = new GameScreen();
        numBandits.setTitle("Bandits");
        numBandits.setHeaderText(
            "Enter desired difficulty (Easy, Hard, Difficult, or Legendary)");
        Optional<String> numEnemies = numBandits.showAndWait();
        if (numEnemies.isPresent()) {
            if (numEnemies.get().toLowerCase().equals("easy")) {
                Map.addEnemies(new Bandit(), 1);
            } else if (numEnemies.get().toLowerCase().equals("hard")) {
                Map.addEnemies(new Bandit(), 3);
            } else if (numEnemies.get().toLowerCase().equals("difficult")) {
                Map.addEnemies(new Bandit(), 5);
            } else if (numEnemies.get().toLowerCase().equals("legendary")) {
                Map.addEnemies(new Bandit(), 10);
            } else {
                Map.addEnemies(new Bandit(), 1);
            }
        }
        firstSettlement.setTitle("Settlement");
        firstSettlement.setHeaderText("Enter name of first settlement!");
        Optional<String> result = firstSettlement.showAndWait();
        if (result.isPresent()) {
            AudioClip settleAudio =
                new AudioClip("File:./src/main/java/audio/Settle.mp3");
            settleAudio.play();
            GridFX.getMap()
                .putSettlement(result.get(),
                GameController.getCivilization(), 0, 9);
        }
        gameScreen.update();
        sceneSecondary = new Scene(gameScreen);
        return sceneSecondary;
    }

}
