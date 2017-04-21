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
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
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
    Table t,tc,bt;
    SpriteBatch sb;
    Barril bar;
    Tablero tablero;
    SpriteBatch b;
    Texture gear;
    public PlayGameScreen(final Main main){
        b= new SpriteBatch();
        Gdx.input.setCatchBackKey(true);
        sb = new SpriteBatch();
        this.main=main;
        tablero = new Tablero(6);
        tablero.generateAllContent();
        stage = new Stage();
      // casilla1 = new Casilla(casillaTextura,100,1000);
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
                    tc.clearChildren();
                    refreshTable();
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
                    tc.clearChildren();
                    refreshTable();
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
                    tc.clearChildren();
                    refreshTable();
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
                    tc.clearChildren();
                    refreshTable();
                    System.out.println("Entra");

                }
            });
            stage.addActor(b);
            i++;
        }

        }

    private void refreshTable() {
        Texture tuercaBlue = new Texture("GearBlue.png");
        Texture tuercaRead = new Texture("GearRed.png");
        int a=0;
        int b=0;
        for(int i=1100;i>=350;i=i-150){
            for(int p=100;p<=850;p=p+150){
                if(tablero.getCell(a,b)==1){

                    tc.add(new Barril(tuercaBlue,p,i,0));

                }else if(tablero.getCell(a,b)==2){

                    tc.add(new Barril(tuercaRead,p,i,0));

                }
                b++;
            }
            b=0;
            a++;
        }
        System.out.println(a+" "+b);
        tc.add(bar);

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
        Gdx.gl.glClearColor(0, 0.5f, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
      //  b.begin();
     //   t.draw(b,0f);
     //   tc.draw(b,0f);
     //   b.end();

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
        b.dispose();
        casillaTextura.dispose();


    }

    @Override
    public void dispose() {
        stage.dispose();
        b.dispose();
        casillaTextura.dispose();
        gear.dispose();

    }
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {

        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        System.out.println("screenX: "+screenX+" screenY: "+screenY+" pointer: "+pointer);
        Button b = new Button();
        if(b.isPressed()){

        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        System.out.println("screenX: "+screenX+" screenY: "+screenY+" pointer: "+pointer);
        return super.touchUp(screenX, screenY, pointer, button);
    }
}
