package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bstq.Main;

import static com.badlogic.gdx.graphics.profiling.GLProfiler.listener;

/**
 * Created by alumne on 31/03/2017.
 */

//   https://github.com/libgdx/libgdx/wiki/Simple-text-input
//   https://github.com/libgdx/libgdx/wiki/On-screen-keyboard

/** OVERLAP 2D
 * https://www.youtube.com/watch?v=Lp1GRyUUoFM overlap2D
 * https://www.youtube.com/watch?v=yeD8lVYwAWQ
  */



public class LoginMenu extends Menu  {
    final Main main;
    Button lvl1;
    Stage stage;
    //MyTextInputListener textInputListener;
    String txtUsername;
    Login listener = new Login();

    /*TextField textfield= new TextField("", skin);
    textfield.setSize(300, 50);
    textfield.setPosition((SCREEN.WIDTH/2) - textfield.getWidth()/2, 0)
    */


    public LoginMenu(Main main) {
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        prepareButtons();
        prepareListeners();
        stage.addActor(lvl1);

        /*txtUsername = new TextField("", stage);
        txtUsername.setMessageText("test");
        txtUsername.setPosition(30, 30);
        stage.addActor(txtUsername);
        String test = txtUsername.getText();
        System.out.println(test);*/
    }

    private void prepareListeners() {
        lvl1.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                /*pgs = new PlayGameScreen(main);
                main.setScreen(pgs);*/
                /*MyTextInputListener listener = new MyTextInputListener();
                Gdx.input.getTextInput(listener, "Dialog Title", "Initial Textfield Value", "Hint Value");*/
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
        Gdx.input.setCatchBackKey(true);
        Gdx.input.getTextInput(listener, "Dialog Title", "Initial Textfield Value", "Hint Value");
    }
    @Override
    public void dispose() {
        stage.dispose();

    }

    /*
    @Override
    public void input (String text) {
        Gdx.input.setOnscreenKeyboardVisible(true);
    }

    @Override
    public void canceled () {
        if (Gdx.input.isKeyPressed(Input.Keys.BACK)) {
            main.setScreen(new MainMenu(main));
        }
    }*/

    /* https://github.com/libgdx/libgdx/wiki/File-handling
    FileHandle file = Gdx.files.local("myfile.txt");
file.writeString("My god, it's full of stars", false);*/

}
