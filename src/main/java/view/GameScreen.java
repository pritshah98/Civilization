package view;

import controller.GameController;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
/**
 * This class represents the GameScreen class
 */
public class GameScreen extends BorderPane {
    private GridFX grid = GridFX.getInstance();
    private static ResourcesMenu resourcesMenu = new ResourcesMenu();
    private static StatusMenu statusMenu = new StatusMenu();
    private static VBox menuBox = new VBox(15);
    // private static MilitaryMenu military;
    // private static WorkerMenu workers;
    // private static BuildingMenu build;
    // private static RecruitMenu recruit;

    /**
     * Creates a new view into the game. this layout should include
     * the rescource bar, grid map, and action menus
     *
     */
    public GameScreen() {
        this.setTop(resourcesMenu.getRootNode());
        this.setCenter(grid);
        menuBox = statusMenu.getRootNode();
        menuBox.setStyle("-fx-background-color: #B4D8E7;");
        this.setLeft(menuBox);
        // workers = new WorkerMenu();
        // build = new BuildingMenu();
        // recruit = new RecruitMenu();
        // military = new MilitaryMenu();
    }

    /**
     * This method should update the gridfx and the resouce bar
     */
    public void update() {
        grid.update();
        GameController.updateResourcesBar();
    }
    /**
    * this method should return the resource menu
    * @return reosuce menu
    */
    public static ResourcesMenu getResources() {
        return resourcesMenu;
    }


    /**
     * This method switches menus based on passed in game state.
     * Game.java calls this to selectively control which menus are displayed
     * @param state
     */
    public static void switchMenu(GameController.GameState state) {
        if (state == GameController.GameState.NEUTRAL) {
            menuBox.getChildren().setAll((new StatusMenu()).getRootNode());
        } else if (state == GameController.GameState.MILITARY) {
            menuBox.getChildren().setAll((new MilitaryMenu()).getRootNode());
        } else if (state == GameController.GameState.WORKER) {
            menuBox.getChildren().setAll((new WorkerMenu()).getRootNode());
        } else if (state == GameController.GameState.BUILDING) {
            menuBox.getChildren().setAll((new BuildingMenu()).getRootNode());
        } else {
            menuBox.getChildren().setAll((new RecruitMenu()).getRootNode());
        }
    }
}
