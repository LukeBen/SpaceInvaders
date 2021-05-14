package me.lukeben.entities;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public abstract class Sprite extends Rectangle {

    private final SpriteType type;
    private int xCord;
    private int yCord;

    private boolean dead = false;

    public Sprite(SpriteType type, int width, int height, Color color) {
        super(width, height, color);

        this.type = type;
        setTranslateX(xCord);
        setTranslateY(yCord);
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
        SpriteManager.getInstance().getSprites().add(this);
        return this;
    }

    public void update(double time) {}

}
