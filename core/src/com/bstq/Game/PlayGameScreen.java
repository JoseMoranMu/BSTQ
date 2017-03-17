package com.bstq.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
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
    Texture bt;
    Texture casillaTextura;
    Casilla casilla1,casilla2,casilla3,casilla4,casilla5,casilla6;
    Button lvl1;
    Barril b;
    Actor a;
    float delta;
    Table t;

    public PlayGameScreen(final Main main){
        Gdx.input.setCatchBackKey(true);

        this.main=main;
        ButtonHandler bh = new ButtonHandler();
        lvl1=bh.getButton(new Texture(Gdx.files.internal("level-1.png")),300,1300);
        stage = new Stage();
        stage.setDebugAll(true);

        bt = new Texture("Gear.png");
        casillaTextura = new Texture("casilla.png");
        casilla1 = new Casilla(casillaTextura,100,1000);
        casilla2 = new Casilla(casillaTextura,200,1000);
        casilla3 = new Casilla(casillaTextura,300,1000);
        casilla4 = new Casilla(casillaTextura,400,1000);
        casilla5 = new Casilla(casillaTextura,500,1000);
        casilla6 = new Casilla(casillaTextura,600,1000);
        b = new Barril(bt);
        a = new Actor();
        a.setSize(225,225);
        a.setColor(Color.GREEN);
        lvl1.addCaptureListener(new EventListener() {
            @Override
            public boolean handle(Event event) {
                b.move();
                //comprobaciones(delta);
                return false;
            }
        });
        createTableGame();
        stage.addActor(b);
        stage.addActor(a);
        t.row().height(casillaTextura.getHeight());
        t.add(casilla1,casilla2,casilla3);
        t.row().height(casillaTextura.getHeight()*2);
        t.add(casilla4,casilla5,casilla6);
        stage.addActor(t);
        stage.addActor(lvl1);

        b.setPosition(500,500);
        a.setPosition(100,500);
        t.setPosition(300,500);
    }

    private void createTableGame() {
        t = new Table();
        t.setColor(Color.GREEN);
        t.debugTable();
        t.debugCell();
        t.debugAll();
        t.sizeBy(3);
        t.pad(3);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        this.delta=delta;
        stage.act();
        stage.draw();
    }

    private void comprobaciones(float delta) {
        b.setLeft(true);
        System.out.println("prueba");
        if(a.getX()==b.getX()) {

            b.setLeft(false);
        }
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
        bt.dispose();

    }

    @Override
    public void dispose() {

    }

}
