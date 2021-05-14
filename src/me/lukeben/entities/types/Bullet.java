package me.lukeben.entities.types;

import javafx.scene.paint.Color;
import me.lukeben.entities.Sprite;
import me.lukeben.entities.SpriteType;

public abstract class Bullet extends Sprite {

    public Bullet(SpriteType type) {
        super(type, 5, 20, Color.BLACK);
    }

}
