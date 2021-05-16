package me.lukeben.overlay;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Group;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import me.lukeben.animation.AnimationManager;
import me.lukeben.menus.MainMenu;


public class LivesLeftOverlay {

    public LivesLeftOverlay(int x, int y) {
        Rectangle rect = new Rectangle(70, 70);
        //rect.setFill(javafx.scene.paint.Color.GREEN);
        rect.setFill(new ImagePattern(SwingFXUtils.toFXImage(AnimationManager.getSprite(5, 0), null), 0, 0, 1, 1, true));
        MainMenu.getInstance().getRoot().getChildren().add(rect);
        rect.setTranslateX(x);
        rect.setTranslateY(y);
    }

}
