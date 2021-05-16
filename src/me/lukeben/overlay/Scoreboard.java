package me.lukeben.overlay;

import javafx.scene.Group;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import me.lukeben.menus.MainMenu;

import java.awt.*;

public class Scoreboard {

    public Scoreboard() {
        Text text = new Text("Score: 0");
        text.setFill(javafx.scene.paint.Color.WHITE);
        text.setText("GHFGGG");
        Group group = new Group(text);
        MainMenu.getInstance().getRoot().getChildren().add(group);
        group.setTranslateX(400);
        group.setTranslateY(400);
    }

}
