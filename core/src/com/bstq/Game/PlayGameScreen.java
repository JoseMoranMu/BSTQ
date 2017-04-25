package com.bstq.Game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Timer;
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
    Texture casillaTextura,tuercaBlue,tuercaRead,explode;
    Table t,tc,bt;
    Barril bar;
    Tablero tablero;
    SpriteBatch b;
    BitmapFont font;
    int points;
    public PlayGameScreen(final Main main){
        points =0;
        font = new BitmapFont();
        font.getData().setScale(5,5);
        b= new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        tablero = new Tablero(6);
        tablero.generateAllContent();
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
        for(int p=100;p<=850;p=p+150){
            b = bh.getButton(new Texture(Gdx.files.internal("Gear.png")), p, 1250);
            b.setId(i);
            System.out.println("NUMERO"+b.getZIndex());
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveUpColumn(b.getId());
                    System.out.println("Entra");

                }
            });
            stage.addActor(b);
            i++;
        }
        i=0;
        for(int p=100;p<=850;p=p+150){
            b = bh.getButton(new Texture(Gdx.files.internal("Gear.png")), p, 200);
            b.setId(i);
            System.out.println("NUMERO"+b.getZIndex());
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveDownColumn(b.getId());
                    System.out.println("Entra");

                }
            });
            stage.addActor(b);
            i++;
        }
        i=0;
        for(int p=1100;p>=350;p=p-150){
            b = bh.getButton(new Texture(Gdx.files.internal("Gear.png")), 0, p);
            b.setId(i);
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveLeftRow(b.getId());
                    System.out.println("Entra");

                }
            });
            stage.addActor(b);
            i++;
        }
        i=0;

        for(int p=1100;p>=350;p=p-150){
            b = bh.getButton(new Texture(Gdx.files.internal("Gear.png")), 1000, p);
            b.setId(i);
            b.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Boton b = (Boton) actor;
                    tablero.moveRigthRow(b.getId());
                    System.out.println("Entra");

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
        for(int i=1100;i>=350;i=i-150){
            for(int p=100;p<=850;p=p+150){
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

    private void checkTable() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        tablero.generateNewContent();
    }


    private void createTableContent() {
        tc = new Table();
        refreshTable();

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


            /*
         t.add(new Casilla(gear,p,1250).addCaptureListener(new ChangeListener() {
             @Override
             public void changed(ChangeEvent event, Actor actor) {

             }
         }));
         */


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        boolean bool;
        BackButton();
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        b.begin();
        font.draw(b,Integer.toString(tablero.getPoints()),100,1600);
        b.end();

        bool =refreshTable();
        stage.act();
        stage.draw();

        if(bool) {

            Timer.schedule(new Timer.Task() {
                @Override
                public void run() {
                    tablero.generateNewContent();

                }
            }, 1);
        }

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
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("screenX: "+screenX+" screenY: "+screenY+" pointer: "+pointer);
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("screenX: "+screenX+" screenY: "+screenY+" pointer: "+pointer);
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
