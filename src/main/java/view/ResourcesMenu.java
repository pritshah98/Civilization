package view;

import controller.GameController;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import model.Civilization;
import javafx.scene.paint.Color;

public class ResourcesMenu {
    private HBox resourcesMenu = new HBox(15);
    private Civilization currentCiv = GameController.getCivilization();
    private Text stratLevel, resources, settlements, money, food, happiness;
    /**
    * creates a resource bar and display the current state of
    * your civilization's resouces
    */
    public ResourcesMenu() {
        stratLevel = new Text("Strategy Level:" + " "
            + currentCiv.getStrategy().getStrategyLevel());
        resources = new Text("Resources:" + " "
            + currentCiv.getResources());
        settlements = new Text("Settlements:"
            + " " + currentCiv.getNumSettlements());
        money = new Text("Money:" + " " + currentCiv.getTreasury().getCoins());
        food = new Text("Food:" + " " + currentCiv.getFood());
        happiness = new Text("Happiness:" + " " + currentCiv.getHappiness());
        stratLevel.setFill(Color.WHITE);
        resources.setFill(Color.WHITE);
        settlements.setFill(Color.WHITE);
        money.setFill(Color.WHITE);
        food.setFill(Color.WHITE);
        happiness.setFill(Color.WHITE);
        resourcesMenu.getChildren().addAll(stratLevel, resources,
            settlements, money, food, happiness);
        resourcesMenu.setStyle("-fx-background-color: #336699;");
    }
    /**
    * should update all the resouce values to the current
    * state of your resource values
    */
    public void update() {
        stratLevel.setText("Strategy Level:" + " " + currentCiv.
            getStrategy().getStrategyLevel());
        resources.setText("Resources:" + " " + currentCiv.getResources());
        settlements.setText("Settlements:" + " " + currentCiv
            .getNumSettlements());
        money.setText("Money:" + " " + currentCiv.getTreasury().getCoins());
        food.setText("Food:" + " " + currentCiv.getFood());
        happiness.setText("Happiness:" + " " + currentCiv.getHappiness());
    }
    /**
    * updates the resource bar and returns the resource bar
    * @return a hbox representation of the resource bar
    */
    public HBox getRootNode() {
        update();
        return resourcesMenu;
    }
}
