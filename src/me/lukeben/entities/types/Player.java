package me.lukeben.entities.types;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import me.lukeben.entities.Sprite;
import me.lukeben.entities.SpriteType;

public class Player extends Sprite {

    private double xVelocity = 0;
    private double yVelocity = 0;

    public Player() {
        super(SpriteType.PLAYER, 40, 40, Color.BLUE);
    }

    public void controlPlayer(boolean released, KeyCode code) {

        // shoot(player);
        if(released) {
            if(code == KeyCode.W) setYVelocity(0);
            if(code == KeyCode.A) setXVelocity(0);
            if(code == KeyCode.S) setYVelocity(0);
            if(code == KeyCode.D) setXVelocity(0);
        } else {
            if(code == KeyCode.W) setYVelocity(-5);
            if(code == KeyCode.A) setXVelocity(-5);
            if(code == KeyCode.S) setYVelocity(5);
            if(code == KeyCode.D) setXVelocity(5);
        }
        if(code == KeyCode.SPACE);// shoot(player);

    }

    @Override
    public void update(double time) {
        setX(getX() + xVelocity);
        setY(getY() + yVelocity);
    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

}
