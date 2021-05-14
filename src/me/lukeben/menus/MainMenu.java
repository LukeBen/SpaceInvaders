package me.lukeben.menus;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import me.lukeben.entities.*;
import me.lukeben.entities.types.Enemy;
import me.lukeben.entities.types.Player;

import java.util.List;
import java.util.stream.Collectors;


public class MainMenu extends Application {

    private Pane root = new Pane();

    private Sprite player = new Player().summon(300, 750);

    private double time = 0;

    private Parent createContent() {
        root.setPrefSize(600, 800);

        root.getChildren().add(player);

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                update();
            }
        };

        timer.start();

        nextLevel();

        return root;
    }

    //600x800
    //100 on each side
    // 600 - 2(100) = 400
    //

    private void nextLevel() {
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 5; y++) {
                root.getChildren().add(new Enemy().summon(50 + x*50, 250 - (y * 50)));
                //Sprite s = new Sprite(50 + x*50, 250 - (y * 50), 35, 35, "enemy", Color.RED);

               // root.getChildren().add(s);
            }
        }
    }

    private void update() {
        time += 0.016;


        SpriteManager.getInstance().getSprites().forEach(s -> {
            System.out.println(SpriteManager.getInstance().getSprites().toString());
            System.out.println("w?");
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

        root.getChildren().removeIf(n -> {
            Sprite s = (Sprite) n;
            return s.isDead();
        });

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
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
