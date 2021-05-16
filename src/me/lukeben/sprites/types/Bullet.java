package me.lukeben.sprites.types;

import javafx.scene.paint.Color;
import me.lukeben.animation.AnimationManager;
import me.lukeben.sprites.Sprite;
import me.lukeben.sprites.SpriteManager;
import me.lukeben.sprites.SpriteType;

import java.util.Random;

public abstract class Bullet extends Sprite {

    public Bullet(SpriteType type) {
        super(type, 16, 32, AnimationManager.getSprite(1, 1));
    }

}
