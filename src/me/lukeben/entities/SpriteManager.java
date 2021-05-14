package me.lukeben.entities;

import java.util.ArrayList;
import java.util.List;

public class SpriteManager {

    private static SpriteManager instance;
    private final List<Sprite> sprites = new ArrayList<>();

    public static SpriteManager getInstance() {
        if(instance == null) instance = new SpriteManager();
        return instance;
    }

    public List<Sprite> getSprites() {
        return sprites;
    }

}
