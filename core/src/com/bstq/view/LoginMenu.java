package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.bstq.Main;
import com.bstq.Service.UsersDAO;

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
    Button login;
    Stage stage;
    TextField user;
    TextField pass;
    UsersDAO service;
    Label message;
    //MyTextInputListener textInputListener;

    //Login listener = new Login();

    /*TextField textfield= new TextField("", skin);
    textfield.setSize(300, 50);
    textfield.setPosition((SCREEN.WIDTH/2) - textfield.getWidth()/2, 0)
    */


    public LoginMenu(Main main) {
        service = new UsersDAO();
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        prepareButtons();
        prepareListeners();
        //stage.addActor(lvl1);
        Skin sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);
        TextField.TextFieldStyle textFieldStyle = sk.get(TextField.TextFieldStyle.class);
        Label userLabel = new Label("Email: ",sk);
        message = new Label("",sk);
        message.setPosition(150,1350);
        message.setSize(500,100);
        userLabel.setSize(500,100);
        userLabel.setPosition(150,1150);
        Label passLabel = new Label("Password: ",sk);
        passLabel.setSize(500,100);
        passLabel.setPosition(150,950);
        user = new TextField("",sk);

        pass = new TextField("",sk);
        user.setPosition(450,1150);
        user.setSize(500,100);
        pass.setPosition(450,950);
        pass.setSize(500,100);
        pass.setPasswordCharacter('*');
        pass.setPasswordMode(true);
        stage.addActor(userLabel);
        stage.addActor(passLabel);
        stage.addActor(user);
        stage.addActor(pass);
        stage.addActor(login);
        stage.addActor(message);
        /*txtUsername = new TextField("", stage);
        txtUsername.setMessageText("test");
        txtUsername.setPosition(30, 30);
        stage.addActor(txtUsername);
        String test = txtUsername.getText();
        System.out.println(test);*/
    }

    private void prepareListeners() {
        login.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                checkLogin();
                service.login(user.getText(),pass.getText());
            }
        });
    }

    private void checkLogin() {
        String response = service.login(user.getText(),pass.getText());
        if(response.contains("null")){
            message.setText("Login FAIL");
        }else{
            message.setText("Login OK");
        }
    }

    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        login=bh.getButton(new Texture(Gdx.files.internal("boton-login.png")),350,750);
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
        //Gdx.input.getTextInput(listener, "Dialog Title", "Initial Textfield Value", "Hint Value");
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
