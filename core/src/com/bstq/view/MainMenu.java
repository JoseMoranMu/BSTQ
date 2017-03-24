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
 * Created by Jose on 17/02/2017.
 */

public class MainMenu extends Menu{

    final Main main;
    Button arcade,survivor,multiplayer, settings, exit;
    Stage stage;

    public MainMenu(final Main main) {
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        prepareButtons();
        prepareListeners();
        stage.addActor(arcade);
        stage.addActor(survivor);
        stage.addActor(multiplayer);
        stage.addActor(settings);
        stage.addActor(exit);

    }

    private void prepareListeners() {
        arcade.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new MenuArcade(main));
            }
        });
        survivor.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new MenuSurvivor(main));
            }
        });
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
    }

    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        arcade =bh.getButton(new Texture(Gdx.files.internal("boton-arcade.png")),300,1300);
        survivor =bh.getButton(new Texture(Gdx.files.internal("boton-survivor.png")),300,1100);
        multiplayer = bh.getButton(new Texture(Gdx.files.internal("boton-multiplayer.png")),300,900);
        settings =bh.getButton(new Texture(Gdx.files.internal("boton-settings.png")),300,700);
        exit =bh.getButton(new Texture(Gdx.files.internal("boton-exit.png")),300,500);

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
