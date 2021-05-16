package me.lukeben.sprites.types;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import me.lukeben.animation.Animation;
import me.lukeben.animation.AnimationManager;
import me.lukeben.sprites.Sprite;
import me.lukeben.sprites.SpriteManager;
import me.lukeben.sprites.SpriteType;
import me.lukeben.sprites.types.bullets.EnemyBullet;

import java.awt.image.BufferedImage;

public class Enemy extends Sprite {

    private BufferedImage[] hovering = {AnimationManager.getSprite(0, 0), AnimationManager.getSprite(1, 0)};
    private BufferedImage[] explosion = {AnimationManager.getSprite(2, 0), AnimationManager.getSprite(3, 0), AnimationManager.getSprite(3, 0)};
    private Animation hover = new Animation(hovering, 50);
    private Animation death = new Animation(explosion, 10);
    private boolean direction = true;
    private int count = 0;
    private int deathTick = -1;

    private int xId;
    private int yId;

    public Enemy(int xId, int yId) {
        super(SpriteType.ENEMY, 70, 70, AnimationManager.getSprite(0, 0));
        this.xId = xId;
        this.yId = yId;
        hover.start();
    }

    @Override
    public void update(double time) {

        if(deathTick > -1) {
            death.update();
            setFill(new ImagePattern(SwingFXUtils.toFXImage(death.getSprite(), null), 0, 0, 1, 1, true));
            deathTick++;
        }
        if(deathTick == 2) {
            death.stop();
            SpriteManager.getInstance().getSpritesToRemove().add(this);
            SpriteManager.getInstance().getSpritesToRemove().add(getKiller());
            deathTick = -1;
        }
        if(deathTick == -1) {
            hover.update();
            setFill(new ImagePattern(SwingFXUtils.toFXImage(hover.getSprite(), null), 0, 0, 1, 1, true));
        }

        if(direction) {
            count--;
            if(count <= -100) {
                direction = false;
                setY(getY() + 10);
            }
        } else {
            count++;
            if(count >= 100) {
                direction = true;
                setY(getY() + 10);
            }
        }

        setX((205 + (xId*72) + count));

        if(time > 2) {
            if(Math.random() < 0.05) {
                EnemyBullet bullet = (EnemyBullet) new EnemyBullet().summon((int)getX(), (int) getY());
            }
        }

    }

    @Override
    public void death(Sprite killer) {
        hover.stop();
        setFill(new ImagePattern(SwingFXUtils.toFXImage(death.getSprite(), null), 0, 0, 1, 1, true));
        death.start();
        deathTick = 0;
        setDead(true);
    }

}
