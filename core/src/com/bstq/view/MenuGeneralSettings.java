package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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

public class MenuGeneralSettings extends Menu {
    final Main main;
    Button soundSettings,changeNickname,changeLenguaje;
    Stage stage;

    public MenuGeneralSettings(final Main main){
        this.main=main;
        prepareButtons();
        prepareListeners();
        stage = new Stage();
        stage.addActor(soundSettings);
        stage.addActor(changeNickname);
        stage.addActor(changeLenguaje);
    }

    private void prepareListeners() {
        soundSettings.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        changeNickname.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {

            }
        });
        changeLenguaje.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
            }
        });

    }

    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        changeNickname =bh.getButton(new Texture(Gdx.files.internal("boton-arcade.png")),300,1300);
        changeLenguaje =bh.getButton(new Texture(Gdx.files.internal("boton-survivor.png")),300,1100);
        soundSettings = bh.getButton(new Texture(Gdx.files.internal("boton-multiplayer.png")),300,900);
    }

    private void BackButton() {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            main.setScreen(new MainMenu(main));
        }
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
        BackButton();
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
