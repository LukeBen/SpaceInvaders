package me.lukeben.menus;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import me.lukeben.animation.AnimationManager;
import me.lukeben.overlay.*;
import me.lukeben.sprites.*;
import me.lukeben.sprites.types.Enemy;
import me.lukeben.sprites.types.Player;


public class MainMenu extends Application {

    private final Pane root = new Pane();

    public Pane getRoot() {
        return root;
    }

    private double time = 0;

    private static MainMenu instance;

    public MainMenu(){
        super();
        synchronized(MainMenu.class){
            if(instance != null) throw new UnsupportedOperationException(
                    getClass()+" is singleton but constructor called more than once");
            instance = this;
        }
    }

    public static MainMenu getInstance(){
        return instance;
    }

    private Sprite player;

    private Parent createContent() {
        root.setPrefSize(1200, 800);
        root.setBackground(new Background(new BackgroundImage(SwingFXUtils.toFXImage(AnimationManager.getSprite(4, 1), null), BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT)));

        player = new Player().summon(300, 750);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        timer.start();

        nextLevel();
        new LivesLeftOverlay(1100, 10);
        new LivesLeftOverlay(1040, 10);
        new LivesLeftOverlay(980, 10);
        new CoinsOverlay();
        new NumberOverlay(NumberEnum.FOUR, 100, 58);
        new Scoreboard();

        return root;
    }

    //600x800
    //100 on each side
    // 600 - 2(100) = 400
    //

    private void nextLevel() {
        for(int x = 0; x < 11; x++) {
            for(int y = 0; y < 5; y++) {
                if(x == 0 & y == 0) {
                    new Enemy(x, y).summon(205 + x*72, 250 - (y * 50));
                } else {
                    new Enemy(x, y).summon(205 + x*72, 250 - (y * 50));
                }
            }
        }
    }

    private void update() {
        time += 0.016;

        SpriteManager.getInstance().getSprites().forEach(s -> {
            s.update(time);

            switch (s.getType()) {

                case ENEMY_BULLET:

                    break;

                case PLAYER_BULLET:

                   /* sprites().stream().filter(e -> e.getType().equals("enemy")).forEach(enemy -> {
                        if(s.getBoundsInParent().intersects(enemy.getBoundsInParent())) {
                            enemy.setDead(true);
                            s.setDead(true);
                        }
                    });*/
                    break;

                case ENEMY:
                    break;

            }
        });

        SpriteManager.getInstance().getSprites().removeAll(SpriteManager.getInstance().getSpritesToRemove());
        root.getChildren().removeAll(SpriteManager.getInstance().getSpritesToRemove());
        SpriteManager.getInstance().getSprites().addAll(SpriteManager.getInstance().getSpritesToAdd());
        SpriteManager.getInstance().getSpritesToRemove().clear();
        SpriteManager.getInstance().getSpritesToAdd().clear();

        if(time > 2) {
            time = 0;
        }
    }

    public void shoot(Sprite who) {
        //Sprite s = new Sprite((int) who.getTranslateX() + 20, (int) who.getTranslateY(), 5, 20, who.getType() + "bullet", Color.GREEN);

        //root.getChildren().add(s);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = new Scene(createContent());

        scene.setOnKeyPressed(e -> {

            KeyCode code = e.getCode();

            SpriteManager.getInstance().getSprites().forEach(sprite -> {
                if(sprite.getType() != SpriteType.PLAYER) return;
                Player pl = (Player) sprite;
                pl.controlPlayer(false, code);
            });

        });

        scene.setOnKeyReleased(e -> {

            KeyCode code = e.getCode();

            SpriteManager.getInstance().getSprites().forEach(sprite -> {
                if(sprite.getType() != SpriteType.PLAYER) return;
                Player pl = (Player) sprite;
                pl.controlPlayer(true, code);
            });

        });

        primaryStage.setScene(scene);
        primaryStage.getIcons().add(SwingFXUtils.toFXImage(AnimationManager.getSprite(0, 0), null));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
