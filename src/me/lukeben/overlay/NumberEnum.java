package me.lukeben.overlay;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import me.lukeben.animation.AnimationManager;

import java.awt.image.BufferedImage;

public enum NumberEnum {

    ZERO(AnimationManager.getSprite(0, 2)),
    ONE(AnimationManager.getSprite(1, 2)),
    TWO(AnimationManager.getSprite(2, 2)),
    THREE(AnimationManager.getSprite(3, 2)),
    FOUR(AnimationManager.getSprite(4, 2)),
    COMMA(AnimationManager.getSprite(6, 1));

    private BufferedImage image;

    NumberEnum(BufferedImage image) {
        this.image = image;
    }

    public Image getImage() {
        return SwingFXUtils.toFXImage(image, null);
    }

}
