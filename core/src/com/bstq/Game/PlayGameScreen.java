package com.bstq.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.bstq.Main;
import com.bstq.view.ButtonHandler;
import com.bstq.view.MainMenu;
import com.bstq.view.MenuArcade;

import java.util.TimerTask;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by Jose on 07/03/2017.
 */

public class PlayGameScreen extends TableGame {

    final Main main;
    Stage stage;
    Texture casillaTextura,tuercaBlue,tuercaRead,explode;
    Table t,tc,bt;
    Barril bar;
    Tablero tablero;
    SpriteBatch b;
    BitmapFont font;
    Skin sk;
    public PlayGameScreen(final Main main){
        sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);
        font = new BitmapFont();
        font.getData().setScale(5,5);
        b= new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        tablero = new Tablero(6);
        tablero.generateAllContent();
        tablero.startTimer();
        stage = new Stage();
        tuercaBlue = new Texture("blueGear.png");
        tuercaRead = new Texture("redGear.png");
        explode = new Texture("explode.png");
        createTableGame();
        createTableContent();
        createTableButtons();
        stage.addActor(t);
        stage.addActor(tc);
        stage.addActor(bt);
    }

    private void createTableButtons() {
        bt = new Table();
        bt.setPosition(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        ButtonHandler bh = new ButtonHandler();
        Boton b;
        int i=0;
        for(int p=162;p<912;p=p+125){
            b = bh.getButton(new Texture(Gdx.files.internal("magnetUp.png")), p, 1225);
            b.setId(i);
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveUpColumn(b.getId());

                }
            });
            stage.addActor(b);
            i++;
        }
        i=0;
        for(int p=162;p<912;p=p+125){
            b = bh.getButton(new Texture(Gdx.files.internal("magnetDown.png")), p, 350);
            b.setId(i);
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveDownColumn(b.getId());

                }
            });
            stage.addActor(b);
            i++;
        }
        i=0;
        for(int p=1100;p>350;p=p-125){
            b = bh.getButton(new Texture(Gdx.files.internal("magnetLeft.png")), 37, p);
            b.setId(i);
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveLeftRow(b.getId());

                }
            });
            stage.addActor(b);
            i++;
        }
        i=0;

        for(int p=1100;p>350;p=p-125){
            b = bh.getButton(new Texture(Gdx.files.internal("magnetRight.png")), 912, p);
            b.setId(i);
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveRigthRow(b.getId());

                }
            });
            stage.addActor(b);
            i++;
        }

        }

    private boolean  refreshTable() {
        tc.clearChildren();
        boolean bo=tablero.check();
        int a=0;
        int b=0;
        for(int i=1100;i>350;i=i-125){
            for(int p=162;p<912;p=p+125){
                if(tablero.getCell(a,b)==1){

                    tc.add(new Barril(tuercaBlue,p,i,0));

                }else if(tablero.getCell(a,b)==2){

                    tc.add(new Barril(tuercaRead,p,i,0));

                }else if(tablero.getCell(a,b)==88){
                    tc.add(new Barril(explode,p,i,0));
                }
                b++;
            }
            b=0;
            a++;
        }
        tc.add(bar);
        return bo;
    }



    private void createTableContent() {
        tc = new Table();
        refreshTable();

    }

    private void createTableGame() {
        t = new Table();
        casillaTextura = new Texture("casillafin.png");
        for(int i=1100;i>350;i=i-125){
            for(int p=162;p<912;p=p+125){
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
        boolean bool;
        BackButton();
        Gdx.gl.glClearColor(0.0f, 153f/255.0f, 0.0f, 0.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b.begin();
        font.draw(b,"Points: "+Integer.toString(tablero.getPoints()),100,1600);
        font.draw(b,"Time: "+Integer.toString(tablero.getTime()),100,1500);
        b.end();

        if(refreshTable()){
            destroyContent();
        }
        if(tablero.getTime()==0) endGame();
        stage.act();
        stage.draw();


    }

    private void endGame() {
        int points = tablero.getPoints();
        if(points>main.getUserLoged().getMaxScore()){
            //String s ="Time's up!"

        }
    }

    private void destroyContent() {
        Timer.schedule(new Timer.Task() {
            @Override
            public void run() {
                tablero.generateNewContent();

            }
        }, 0.5f);
    }


    private void BackButton() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            main.setScreen(new MenuArcade(main));
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
/*
        stage.dispose();
        b.dispose();
        casillaTextura.dispose();
        tuercaBlue.dispose();
        tuercaRead.dispose();
        explode.dispose();
        */
    }

    @Override
    public void dispose() {

        stage.dispose();
        b.dispose();
        casillaTextura.dispose();
        tuercaBlue.dispose();
        tuercaRead.dispose();
        explode.dispose();

    }
    private void showDialog(String s, boolean exit) {
        Label label = new Label(s, sk);
        label.setWrap(true);
        label.setAlignment(Align.center);
        Dialog d =new Dialog("", sk, "dialog") {
            protected void result (Object object) {
                this.clear();
                if(object.equals(true)){

                }
            }
        };
        d.padTop(50).padBottom(50).padLeft(50).padRight(50);
        d.getContentTable().padBottom(50);
        d.getContentTable().add(label).width(850).row();
        d.button("Close", exit).show(stage);


    }
}
