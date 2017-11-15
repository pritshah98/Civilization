package view;

import controller.GameController;
import model.Convertable;
import model.MapObject;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.media.AudioClip;
import model.TerrainTile;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class WorkerMenu extends AbstractMenu {
    private Button convert = new Button("CONVERT");
    private Button move = new Button("MOVE");
    private Alert error = new Alert(AlertType.ERROR);
    private MapObject occupant = GameController.getLastClicked()
        .getTile().getOccupant();
    private TerrainTile type = GameController.getLastClicked().getTile();
    /**
    * There should be a convert and move button
    * as well as the functions associated with those
    * buttons
    */
    public WorkerMenu() {
        addMenuItem(convert);
        addMenuItem(move);
        convert.setOnAction(e -> {
                if (occupant.isWorker()
                    && ((Convertable) occupant).canConvert(type.getType())) {
                    type.setOccupant(((Convertable) occupant).convert());
                    AudioClip convertAudio =
                        new AudioClip("File:./src/main/java/audio/Convert.mp3");
                    convertAudio.play();
                    GameController.getLastClicked().updateTileView();
                    GameController.updateResourcesBar();
                } else {
                    error.setTitle("ERROR");
                    AudioClip errorAudio =
                        new AudioClip("File:./src/main/java/audio/Error.mp3");
                    errorAudio.play();
                    Text convertError = new Text(
                        "Cannot convert selected tile.");
                    error.getDialogPane().setContent(convertError);
                    error.showAndWait();
                }
            });
        move.setOnAction(e -> {
                GameController.moving();
                GameController.updateResourcesBar();
            });
        GridFX.update();
    }
}
