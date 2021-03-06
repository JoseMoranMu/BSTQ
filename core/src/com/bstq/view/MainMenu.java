package com.bstq.view;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bstq.Game.PlayGameScreen;
import com.bstq.Main;

/**
 * Created by Jose on 17/02/2017.
 */

public class MainMenu extends Menu{

    final Main main;
    Button arcade,survivor,multiplayer, settings, exit;
    Stage stage;
    Texture back;
    Sprite background;
    SpriteBatch sb;
    Sound buttonSound;

    public MainMenu(final Main main) {
        Gdx.input.setCatchBackKey(true);
        sb = new SpriteBatch();
        back = new Texture(Gdx.files.internal("background.jpg"));
        background = new Sprite(back);
        this.main=main;
        stage = new Stage();
        buttonSound = Gdx.audio.newSound( Gdx.files.getFileHandle("sounds/beep.mp3", Files.FileType.Internal) );
        prepareButtons();
        prepareListeners();
        fillStage();
    }

    /**
     * Method to fill the Stage object with actors of the Screen
     */
    private void fillStage() {
        stage.addActor(arcade);
        stage.addActor(survivor);
        //stage.addActor(multiplayer);
        // stage.addActor(settings);
        stage.addActor(exit);
    }

    /**
     * Method to prepare Listeners of the Buttons
     */
    private void prepareListeners() {

        arcade.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play();
                main.setScreen(new PlayGameScreen(main));
            }
        });
        survivor.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play();
                main.setScreen(new RankingScreen(main));
            }
        });
        /*
        multiplayer.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new MenuMultiplayer(main));
            }
        });
       settings.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new MenuGeneralSettings(main));
            }
        });
        */
        exit.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play();
                main.dispose();
                Gdx.app.exit();
            }
        });
    }

    /**
     * Method to initialize buttons
     */
    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        arcade =bh.getButton(new Texture(Gdx.files.internal("button-startgame.png")),297,1200);
        survivor =bh.getButton(new Texture(Gdx.files.internal("button-ranking.png")),297,900);
       // multiplayer = bh.getButton(new Texture(Gdx.files.internal("boton-multiplayer.png")),300,900);
       // settings =bh.getButton(new Texture(Gdx.files.internal("boton-settings.png")),300,700);
        exit =bh.getButton(new Texture(Gdx.files.internal("button-exit.png")),297,600);

    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);


    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        BackButton();
        sb.begin();
        background.draw(sb);
        sb.end();
        stage.act();
        stage.draw();

    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void dispose() {

        stage.dispose();
        sb.dispose();
        back.dispose();
        buttonSound.dispose();
    }
    private void BackButton() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            main.setScreen(new LoginMenu(main));
        }
    }
}
