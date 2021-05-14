package me.lukeben.entities.types.bullets;

import me.lukeben.entities.SpriteType;
import me.lukeben.entities.types.Bullet;

public class EnemyBullet extends Bullet {

    public EnemyBullet() {
        super(SpriteType.ENEMY_BULLET);
    }

    @Override
    public void update(double time) {
        setY(getY() + 5);
    }

}
