package me.lukeben.entities.types;

import javafx.scene.paint.Color;
import me.lukeben.entities.Sprite;
import me.lukeben.entities.SpriteManager;
import me.lukeben.entities.SpriteType;
import me.lukeben.entities.types.bullets.EnemyBullet;

public class Enemy extends Sprite {

    public Enemy() {
        super(SpriteType.ENEMY, 35, 35, Color.GREEN);
    }

    @Override
    public void update(double time) {
        if(time > 2) {
            if(Math.random() < 0.05) { ;
                SpriteManager.getInstance().getSprites().add(new EnemyBullet().summon((int) getX(), (int) getY() + 20));
            }
        }

    }

}
