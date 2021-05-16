package me.lukeben.overlay;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import me.lukeben.animation.AnimationManager;
import me.lukeben.menus.MainMenu;

public class CoinsOverlay {

    public CoinsOverlay() {
        Rectangle rect = new Rectangle(70, 70);
        //rect.setFill(javafx.scene.paint.Color.GREEN);
        rect.setFill(new ImagePattern(SwingFXUtils.toFXImage(AnimationManager.getSprite(5, 1), null), 0, 0, 1, 1, true));
        MainMenu.getInstance().getRoot().getChildren().add(rect);
        rect.setTranslateX(40);
        rect.setTranslateY(10);
    }

}
