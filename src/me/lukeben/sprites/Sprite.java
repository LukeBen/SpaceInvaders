package me.lukeben.sprites;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import me.lukeben.menus.MainMenu;

import java.awt.image.BufferedImage;

public abstract class Sprite extends Rectangle {

    private final SpriteType type;
    private final BufferedImage image;
    private Sprite killer;

    private boolean dead = false;

    public Sprite(SpriteType type, int width, int height, BufferedImage image) {
        super(width, height);

        this.type = type;
        this.image = image;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public SpriteType getType() {
        return type;
    }

    public Sprite summon(int xCord, int zCord) {
        setX(xCord);
        setY(zCord);
        setFill(new ImagePattern(SwingFXUtils.toFXImage(image, null), 0, 0, 1, 1, true));
        SpriteManager.getInstance().getSpritesToAdd().add(this);
        MainMenu.getInstance().getRoot().getChildren().add(this);
        return this;
    }

    public void death(Sprite killer) {
        this.killer = killer;
    }

    public void update(double time) {}

    public Sprite getKiller() {
        return killer;
    }

}
