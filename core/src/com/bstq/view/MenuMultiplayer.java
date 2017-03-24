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

class MenuMultiplayer extends Menu{
    final Main main;
    Button soundSettings,searchPlayer;
    Stage stage;
    
    public MenuMultiplayer(final Main main) {
        this.main=main;
        prepareButtons();
        prepareListeners();
        stage = new Stage();
        stage.addActor(soundSettings);
        stage.addActor(searchPlayer);
    }

    private void prepareListeners() {
        soundSettings.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new ScreenSoundSettings(main));
            }
        });
        searchPlayer.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new ScreenSearchPlayer(main));
            }
        });
    }

    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        searchPlayer =bh.getButton(new Texture(Gdx.files.internal("boton-arcade.png")),300,1300);
        soundSettings =bh.getButton(new Texture(Gdx.files.internal("boton-survivor.png")),300,1100);
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
