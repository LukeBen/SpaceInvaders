package me.lukeben.sprites.types;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import me.lukeben.animation.Animation;
import me.lukeben.animation.AnimationManager;
import me.lukeben.sprites.Sprite;
import me.lukeben.sprites.SpriteManager;
import me.lukeben.sprites.SpriteType;
import me.lukeben.sprites.types.bullets.EnemyBullet;
import me.lukeben.sprites.types.bullets.PlayerBullet;

import java.awt.image.BufferedImage;

public class Player extends Sprite {

    private BufferedImage[] flying = {AnimationManager.getSprite(0, 1), AnimationManager.getSprite(1, 1)};
    private BufferedImage[] explosion = {AnimationManager.getSprite(2, 0), AnimationManager.getSprite(3, 0), AnimationManager.getSprite(3, 0)};

    private Animation fly = new Animation(flying, 50);
    private Animation death = new Animation(explosion, 10);

    private double xVelocity = 0;
    private double yVelocity = 0;
    private int deathTick = -1;

    //<-------------<Shooting Logic>------------------>//
    private int shootCooldown = 0;
    private boolean readyToShoot = true;

    public Player() {
        super( SpriteType.PLAYER, 75, 75, AnimationManager.getSprite(0, 1));
        fly.start();
    }

    public void controlPlayer(boolean released, KeyCode code) {

        // shoot(player);
        if(released) {
            if(code == KeyCode.W) setYVelocity(0);
            if(code == KeyCode.A) setXVelocity(0);
            if(code == KeyCode.S) setYVelocity(0);
            if(code == KeyCode.D) setXVelocity(0);
            if(code == KeyCode.SPACE) {
               readyToShoot = true;
            }
        } else {
            if(code == KeyCode.W) setYVelocity(-5);
            if(code == KeyCode.A) setXVelocity(-5);
            if(code == KeyCode.S) setYVelocity(5);
            if(code == KeyCode.D) setXVelocity(5);
            if(code == KeyCode.SPACE) {
                if(readyToShoot && shootCooldown <= 0) {
                    PlayerBullet bullet = (PlayerBullet) new PlayerBullet().summon((int) this.getX() + 34, (int) this.getY());
                    shootCooldown = 10;
                    readyToShoot = false;
                }
            }
        }

    }

    @Override
    public void update(double time) {
        shootCooldown--;
        if(deathTick > -1) {
            death.update();
            setFill(new ImagePattern(SwingFXUtils.toFXImage(death.getSprite(), null), 0, 0, 1, 1, true));
            deathTick++;
        }
        if(deathTick == 10) {
            death.stop();
            SpriteManager.getInstance().getSpritesToRemove().add(this);
            SpriteManager.getInstance().getSpritesToRemove().add(getKiller());
            deathTick = -1;
        }
        if(deathTick == -1) {
            fly.update();
            setFill(new ImagePattern(SwingFXUtils.toFXImage(fly.getSprite(), null), 0, 0, 1, 1, true));
            setX(getX() + xVelocity);
            setY(getY() + yVelocity);
        }

    }

    public void setXVelocity(double xVelocity) {
        this.xVelocity = xVelocity;
    }

    public void setYVelocity(double yVelocity) {
        this.yVelocity = yVelocity;
    }

    @Override
    public void death(Sprite killer) {
        fly.stop();
        setFill(new ImagePattern(SwingFXUtils.toFXImage(death.getSprite(), null), 0, 0, 1, 1, true));
        death.start();
        deathTick = 0;
        setDead(true);
    }

}
