package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bstq.Main;

/**
 * Created by alumne on 24/03/2017.
 */

public class MenuSurvivor extends Menu {
    final Main main;
    Button selectStage, survivorRanking, soundSettings;
    Stage stage;


    public MenuSurvivor(final Main main) {
        this.main=main;
        prepareButtons();
        prepareListeners();
        stage = new Stage();
        stage.addActor(selectStage);
        stage.addActor(survivorRanking);
        stage.addActor(soundSettings);
    }

    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        selectStage =bh.getButton(new Texture(Gdx.files.internal("boton-arcade.png")),300,1300);
        survivorRanking =bh.getButton(new Texture(Gdx.files.internal("boton-survivor.png")),300,1100);
        soundSettings = bh.getButton(new Texture(Gdx.files.internal("boton-multiplayer.png")),300,900);
    }

    private void prepareListeners() {
        selectStage.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new ScreenSelectStage(main));
            }
        });
        survivorRanking.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new ScreenSurvivorRanking(main));
            }
        });
        soundSettings.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new ScreenSoundSettings(main));
            }
        });
    }

    @Override
    public void hide() {
        Gdx.input.setInputProcessor(null);
        stage.dispose();
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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
    }
}
