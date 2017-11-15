package view;

import controller.GameController;
import model.Unit;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.text.Text;
import javafx.scene.control.TextInputDialog;
import java.util.Optional;
import javafx.scene.media.AudioClip;

/**
 * Created by RuYiMarone on 11/11/2016.
 */
public class RecruitMenu extends AbstractMenu {
    private ListView<String> unitList = new ListView<>();
    private Button select = new Button("SELECT");
    private TextInputDialog newSettlement = new TextInputDialog();
    private Alert error = new Alert(AlertType.ERROR);
    private Unit newUnit;
    /**
    * recuit menu should have a list of worker/units
    * to choose from. There should also be a select button
    * and the function of the button should be implemented
    *here
    */
    public RecruitMenu() {
        unitList.setItems(FXCollections.observableArrayList("MeleeUnit",
            "RangedUnit", "HybridUnit", "SiegeUnit", "SettlerUnit",
            "FarmerUnit", "CoalMinerUnit", "AnglerUnit", "MasterBuilderUnit"));
        unitList.setPrefWidth(100);
        addMenuItem(unitList);
        addMenuItem(select);
        unitList.setPrefHeight(230);
        unitList.setOrientation(Orientation.VERTICAL);
        select.setOnAction(e -> {
                String selectedUnit = unitList.getSelectionModel()
                    .getSelectedItem();
                switch (selectedUnit) {
                case "MeleeUnit":
                    newUnit = GameController.getCivilization()
                        .getMeleeUnit();
                    break;
                case "RangedUnit":
                    newUnit = GameController.getCivilization()
                        .getRangedUnit();
                    break;
                case "HybridUnit":
                    newUnit = GameController.getCivilization()
                        .getHybridUnit();
                    break;
                case "SiegeUnit":
                    newUnit = GameController.getCivilization()
                        .getSiegeUnit();
                    break;
                case "SettlerUnit":
                    newSettlement.setTitle("Settlement");
                    newSettlement.setHeaderText(
                        "Enter name of new settlement.");
                    Optional<String> result = newSettlement.showAndWait();
                    if (result.isPresent()) {
                        newUnit = GameController.getCivilization()
                            .getSettlerUnit(result.get());
                    }
                    break;
                case "FarmerUnit":
                    newUnit = GameController.getCivilization()
                        .getFarmerUnit();
                    break;
                case "CoalMinerUnit":
                    newUnit = GameController.getCivilization()
                        .getCoalMinerUnit();
                    break;
                case "AnglerUnit":
                    newUnit = GameController.getCivilization()
                        .getAnglerUnit();
                    break;
                case "MasterBuilderUnit":
                    newUnit = GameController.getCivilization()
                        .getMasterBuilderUnit();
                    break;
                default:
                    break;
                }
                if (newUnit.isAffordable()) {
                    AudioClip unitAudio =
                        new AudioClip("File:./src/main/java/audio/Unit.mp3");
                    unitAudio.play();
                    newUnit.applyInitialCosts();
                    GameController.getLastClicked()
                        .getTile().setOccupant(newUnit);
                    GridFX.update();
                } else {
                    error.setTitle("ERROR");
                    AudioClip errorAudio =
                        new AudioClip("File:./src/main/java/audio/Error.mp3");
                    errorAudio.play();
                    Text unitError = new Text(
                        "Not enough resources to build a unit.");
                    error.getDialogPane().setContent(unitError);
                    error.showAndWait();
                }
            });
        GameController.getLastClicked().updateTileView();
        GameController.updateResourcesBar();
    }
}
