package com.bstq.view;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bstq.Game.PlayGameScreen;
import com.bstq.Main;


/**
 * Created by Jose on 24/02/2017.
 */

public class MenuArcade extends Menu {
    final Main main;
    Button lvl1;
    Stage stage;
    PlayGameScreen pgs;
    Sound buttonsound;

    public MenuArcade(Main main){
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        prepareButtons();
        prepareListeners();
        stage.addActor(lvl1);
        buttonsound = Gdx.audio.newSound(Gdx.files.internal("sounds/menubutton.wav"));
    }

    private void prepareListeners() {
        lvl1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                pgs = new PlayGameScreen(main);
                main.setScreen(pgs);
                buttonsound.play();
            }
        });
    }

    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        lvl1=bh.getButton(new Texture(Gdx.files.internal("level-1.png")),300,1300);
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

    private void BackButton() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            main.setScreen(new MainMenu(main));
        }
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }
    @Override
    public void dispose() {
        stage.dispose();

    }

}
