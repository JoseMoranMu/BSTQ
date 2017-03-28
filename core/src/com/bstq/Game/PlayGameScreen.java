package com.bstq.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.bstq.Main;
import com.bstq.view.ButtonHandler;
import com.bstq.view.MenuArcade;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by Jose on 07/03/2017.
 */

public class PlayGameScreen extends TableGame {
    final Main main;
    Stage stage;
    Texture casillaTextura;
    Button lvl1;
    Table t,tc;
    SpriteBatch sb;
    Barril bar;
    Button b,br;
    TextButton bt;
    public PlayGameScreen(final Main main){
        Gdx.input.setCatchBackKey(true);
        sb = new SpriteBatch();
        this.main=main;
        ButtonHandler bh = new ButtonHandler();
        lvl1=bh.getButton(new Texture(Gdx.files.internal("level-1.png")),300,1300);
        stage = new Stage();
      // casilla1 = new Casilla(casillaTextura,100,1000);
        createTableGame();
        createTableContent();
        createTableButtons();
        stage.addActor(lvl1);
        stage.addActor(t);
        stage.addActor(tc);
        stage.addActor(b);
        stage.addActor(br);
    }

    private void createTableButtons() {
        ButtonHandler bh = new ButtonHandler();
        b=bh.getButton(new Texture(Gdx.files.internal("transparente.png")),850,1100);
        b.addCaptureListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("pulsa");
                bar.move(850,1100);
                return false;
            }
        });
        br=bh.getButton(new Texture(Gdx.files.internal("transparente.png")),100,1100);
        br.addCaptureListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                System.out.println("pulsa");
                bar.move(100,1100);
                return false;
            }
        });
    }

    private void createTableContent() {
        tc = new Table();
        Texture tuerca = new Texture("Gear.png");
        bar =new Barril(tuerca,100,1100);
        tc.add(bar);


    }

    private void createTableGame() {
        t = new Table();
        casillaTextura = new Texture("casillafin.png");
        for(int i=1100;i>=350;i=i-150){
            for(int p=100;p<=850;p=p+150){
                System.out.println(p+" "+i);
                t.add(new Casilla(casillaTextura,p,i));
            }
        }
    }

    @Override
    public void show() {

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {
        stage.dispose();
        casillaTextura.dispose();


    }

    @Override
    public void dispose() {
        stage.dispose();
        casillaTextura.dispose();

    }

}
