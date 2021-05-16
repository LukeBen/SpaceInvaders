package me.lukeben.sprites;

import java.util.ArrayList;
import java.util.List;

public class SpriteManager {

    private static SpriteManager instance;
    private final List<Sprite> sprites = new ArrayList<>();
    private final List<Sprite> spritesToRemove = new ArrayList<>();
    private final List<Sprite> spritesToAdd = new ArrayList<>();

    public static SpriteManager getInstance() {
        if(instance == null) instance = new SpriteManager();
        return instance;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

    public List<Sprite> getSpritesToRemove() {
        return spritesToRemove;
    }

    public List<Sprite> getSpritesToAdd() {
        return spritesToAdd;
    }

}
