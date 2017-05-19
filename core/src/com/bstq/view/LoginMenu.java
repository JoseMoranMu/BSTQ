package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.bstq.Main;
import com.bstq.Service.User;
import com.bstq.Service.UsersDAO;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    Button login,singup;
    Stage stage;
    TextField user;
    TextField pass;
    UsersDAO service;
    Label message;
    Skin sk;
    User u;
    public LoginMenu(Main main) {
        service = new UsersDAO();
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        prepareButtons();
        prepareListeners();
        //stage.addActor(lvl1);
        sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);

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
        stage.addActor(singup);
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

            }
        });
        singup.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                main.setScreen(new SignUpMenu(main));
            }
        });
    }

    private void checkLogin() {
        String email=user.getText();
        String validateEmail = "([\\w.\\.]+@)((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";
        String password = pass.getText();
        String validatePassword = "[a-zA-Z0-9]{4,20}";
        if (validate(email,validateEmail)==false) {
            showDialog("You must enter a valid email", false);
        } else if (validate(password,validatePassword)==false){
            showDialog("The password must be 4 to 20 alphanumeric characters", false);
        } else if (validate(email,validateEmail)==true && validate(password,validatePassword)==true){
            if (!email.equals("") && !password.equals("")) {
                u = service.login(email, password);
                System.out.println(u.toString());
                if (u == null) {
                    showDialog("Login fail", false);
                } else {

                    showDialog("Login succes", true);

                }
            }
        }
    }
    private void showDialog(String s, boolean exit) {
        Label label = new Label(s, sk);
        label.setWrap(true);
        label.setAlignment(Align.center);
        Dialog d =new Dialog("", sk, "dialog") {
            protected void result (Object object) {
                this.clear();
                if(object.equals(true)){
                    main.setUserLoged(u);
                    main.setScreen(new MainMenu(main));
                }
            }
        };
        d.padTop(50).padBottom(50).padLeft(50).padRight(50);
        d.getContentTable().padBottom(50);
        d.getContentTable().add(label).width(850).row();
        d.button("Close", exit).show(stage);


    }
    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        login=bh.getButton(new Texture(Gdx.files.internal("boton-login.png")),100,650);
        singup=bh.getButton(new Texture(Gdx.files.internal("boton-registrar.png")),600,650);
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

    /**
     * Validates the string given with the regular expression that we want validate.
     * Return true if matches, false if not.
     * @param source
     * @param expression
     * @return
     */
    public static boolean validate(String source, String expression) {
        Pattern p = Pattern.compile(expression);
        Matcher m = p.matcher(source);
        return m.matches();
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
