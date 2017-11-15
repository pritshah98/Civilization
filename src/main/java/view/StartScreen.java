package view;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.Color;

/**
 * Created by Tian-Yo Yang on 11/11/2016.
 * This class represents the Start Screen for the Civ applicatios. This is the
 * layout that should be displayed upon running the Civ application.
 *
 * This class should have and display
 * 1. a background
 * 2. a list of Civilizations
 * 3. a Start button
 */
public class StartScreen extends StackPane {
    private Button start = new Button("START GAME");
    private ListView<CivEnum> civilizationList = new ListView<>();
    private VBox box = new VBox(20);
    /**
    * constuctor of the start screen. Should set the background
    * image and display a list of civilizations and a start button
    */
    public StartScreen() {
        start.setFont(Font.font(17));
        Image backgroundIm =
            new Image("File:./src/main/java/view/civ_background.png");
        ImageView imageView = new ImageView();
        imageView.setImage(backgroundIm);
        Text begin = new Text("Select a Civilization Below");
        begin.setFont(Font.font(25));
        begin.setFill(Color.WHITE);
        box.getChildren().add(begin);
        box.getChildren().add(getCivList());
        box.getChildren().add(start);
        box.setAlignment(Pos.BOTTOM_CENTER);
        this.getChildren().addAll(imageView, box);
    }
    /**
    * gets the start button
    * @return the start button
    */
    public Button getStartButton() {
        return start;
    }
    /**
    * return a ListView of CivEnums representing the list of
    * available civilizations to choose from
    * @return listview of CivEnum
    */
    public ListView<CivEnum> getCivList() {
        civilizationList.setItems(FXCollections
            .observableArrayList(CivEnum.values()));
        civilizationList.setMaxWidth(215);
        civilizationList.setMaxHeight(80);
        return civilizationList;
    }

}
