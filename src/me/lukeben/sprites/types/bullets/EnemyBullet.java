package me.lukeben.sprites.types.bullets;

import me.lukeben.animation.AnimationManager;
import me.lukeben.sprites.Sprite;
import me.lukeben.sprites.SpriteManager;
import me.lukeben.sprites.SpriteType;
import me.lukeben.sprites.types.Bullet;
import me.lukeben.sprites.types.Enemy;
import me.lukeben.sprites.types.Player;

import java.awt.image.BufferedImage;

public class EnemyBullet extends Sprite {


    public EnemyBullet() {
        super(SpriteType.ENEMY_BULLET, 16, 32, AnimationManager.getSprite(3, 1));
    }

    @Override
    public void update(double time) {
        setTranslateY(getTranslateY() + 5);
        SpriteManager.getInstance().getSprites().forEach(sprite -> {
            if(sprite.getBoundsInParent().intersects(getBoundsInParent()) && (sprite instanceof Player)) {
                sprite.death(this);
                this.death(this);
            }
        });
    }

    @Override
    public void death(Sprite killer) {
        SpriteManager.getInstance().getSpritesToRemove().add(this);
        SpriteManager.getInstance().getSpritesToRemove().add(getKiller());
    }

}
