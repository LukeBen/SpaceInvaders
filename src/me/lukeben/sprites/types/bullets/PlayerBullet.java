package me.lukeben.sprites.types.bullets;

import me.lukeben.animation.AnimationManager;
import me.lukeben.overlay.NumberOverlay;
import me.lukeben.sprites.Sprite;
import me.lukeben.sprites.SpriteManager;
import me.lukeben.sprites.SpriteType;
import me.lukeben.sprites.types.Enemy;

public class PlayerBullet extends Sprite {


    public PlayerBullet() {
        super(SpriteType.PLAYER_BULLET, 16, 32, AnimationManager.getSprite(1, 1));
    }

    @Override
    public void update(double time) {
        setTranslateY(getTranslateY() - 5);
        SpriteManager.getInstance().getSprites().forEach(sprite -> {
            if(sprite.getBoundsInParent().intersects(getBoundsInParent()) && (sprite instanceof Enemy)) {
                sprite.death(this);
                this.death(this);
            }
        });
    }

    @Override
    public void death(Sprite killer) {
        NumberOverlay.score();
        SpriteManager.getInstance().getSpritesToRemove().add(this);
        SpriteManager.getInstance().getSpritesToRemove().add(getKiller());
    }

}
