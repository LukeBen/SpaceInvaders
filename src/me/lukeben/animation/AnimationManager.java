package me.lukeben.animation;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class AnimationManager {

    private static BufferedImage spriteSheet;
    private static final int TILE_SIZE = 64;

    public static BufferedImage loadSprite(String file) {

        BufferedImage sprite = null;

        try {
            new File("name.txt").createNewFile();
            sprite = ImageIO.read(new File("src/resources/" + file + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sprite;
    }

    public static BufferedImage getSprite(int xGrid, int yGrid) {

        if (spriteSheet == null) {
            spriteSheet = loadSprite("space_invaders_sprite_sheet");
        }

        return spriteSheet.getSubimage(xGrid * TILE_SIZE, yGrid * TILE_SIZE, TILE_SIZE, TILE_SIZE);
    }

}
