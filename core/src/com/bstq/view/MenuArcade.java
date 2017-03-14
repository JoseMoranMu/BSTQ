package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
    int k=Input.Keys.BACK;
    public MenuArcade(Main main){
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        prepareButtons();
        prepareListeners();
        stage.addActor(lvl1);
    }

    private void prepareListeners() {
        lvl1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                PlayGameScreen pgs = new PlayGameScreen(main);
                main.setScreen(pgs);

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
