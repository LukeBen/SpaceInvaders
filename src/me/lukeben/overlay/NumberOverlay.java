package me.lukeben.overlay;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import me.lukeben.animation.AnimationManager;
import me.lukeben.menus.MainMenu;

import java.awt.image.BufferedImage;

public class NumberOverlay {

    private static int score = 0;
    private static Text text;

    public NumberOverlay(NumberEnum number, int x, int y) {
       /* Rectangle rect = new Rectangle(50, 50);
        //rect.setFill(javafx.scene.paint.Color.GREEN);
        rect.setFill(new ImagePattern(number.getImage(), 0, 0, 1, 1, true));
        MainMenu.getInstance().getRoot().getChildren().add(rect);
        rect.setTranslateX(x);
        rect.setTranslateY(y);*/


        text = new Text("100");
        text.setFont(Font.font("Russo One", FontWeight.EXTRA_BOLD, 36));
        text.setFill(Color.PURPLE);
        Group gr = new Group(text);
        MainMenu.getInstance().getRoot().getChildren().add(gr);
        gr.setTranslateX(x);
        gr.setTranslateY(y);

    }

    public static void score() {
        score += 10;
        text.setText(String.valueOf(score));
    }

}
