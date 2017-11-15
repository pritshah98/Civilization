package view;

import controller.GameController;
import model.Building;
import model.Settlement;
import model.Civilization;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.media.AudioClip;

/**
 * This class should represents the bulding menu
 */
public class BuildingMenu extends AbstractMenu {
    private Button invest = new Button("INVEST");
    private Button demolish = new Button("DEMOLISH");
    private Civilization civ = GameController.getCivilization();
    private Alert error = new Alert(AlertType.ERROR);

    /**
    * there should be an invest and demolish button for this menu
    * as well as functions associated with the buttons
    */
    public BuildingMenu() {
        addMenuItem(invest);
        addMenuItem(demolish);
        invest.setOnAction(e -> {
                if (civ.getTreasury().getCoins() >= 25) {
                    civ.getTreasury().spend(25);
                    AudioClip investAudio =
                        new AudioClip("File:./src/main/java/audio/Money.mp3");
                    investAudio.play();
                    ((Settlement) GameController.getLastClicked()
                    .getTile().getOccupant()).invest();
                    GameController.updateResourcesBar();
                } else {
                    error.setTitle("ERROR");
                    AudioClip errorAudio =
                        new AudioClip("File:./src/main/java/audio/Error.mp3");
                    errorAudio.play();
                    Text investError = new Text("Not enough gold to invest.");
                    error.getDialogPane().setContent(investError);
                    error.showAndWait();
                }
            });
        demolish.setOnAction(e -> {
                if (civ.getNumSettlements() > 1
                    && GameController.getLastClicked().getTile().getOccupant()
                    instanceof Settlement) {
                    AudioClip demolishAudio =
                        new AudioClip(
                        "File:./src/main/java/audio/Demolish.mp3");
                    demolishAudio.play();
                    ((Building) GameController.getLastClicked()
                        .getTile().getOccupant()).demolish();
                    GameController.getLastClicked().getTile().setOccupant(null);
                    GameController.getLastClicked().updateTileView();
                } else if (GameController.getLastClicked().getTile()
                    .getOccupant().isBuilding()
                    && (!(GameController.getLastClicked()
                        .getTile().getOccupant()
                    instanceof Settlement))) {
                    AudioClip demolishAudio =
                        new AudioClip(
                        "File:./src/main/java/audio/Demolish.mp3");
                    demolishAudio.play();
                    ((Building) GameController.getLastClicked()
                        .getTile().getOccupant()).demolish();
                    GameController.getLastClicked().getTile().setOccupant(null);
                    GameController.getLastClicked().updateTileView();
                } else {
                    error.setTitle("ERROR");
                    AudioClip errorAudio =
                        new AudioClip("File:./src/main/java/audio/Error.mp3");
                    errorAudio.play();
                    Text demolishError =
                        new Text("Need to keep atleast 1 Settlement.");
                    error.getDialogPane().setContent(demolishError);
                    error.showAndWait();
                }
            });
        GridFX.update();
    }
}
