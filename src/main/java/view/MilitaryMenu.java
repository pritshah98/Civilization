package view;

import controller.GameController;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 * Created by William on 11/11/2016.
 */

public class MilitaryMenu extends AbstractMenu {
    private Button attack = new Button("ATTACK");
    private Button move = new Button("MOVE");
    private Alert error = new Alert(AlertType.ERROR);
    /**
    * Implement the buttons and actions associated with
    * the buttons for the military menu
    */
    public MilitaryMenu() {
        addMenuItem(attack);
        addMenuItem(move);
        attack.setOnAction(e -> {
                GameController.attacking();
                GameController.updateResourcesBar();
            });
        move.setOnAction(e -> {
                GameController.moving();
                GameController.updateResourcesBar();
            });
        GridFX.update();
    }
}
