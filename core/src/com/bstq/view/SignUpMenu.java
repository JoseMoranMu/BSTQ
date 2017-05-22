package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.utils.Align;
import com.bstq.Main;
import com.bstq.Service.UsersDAO;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Jose on 17/05/2017.
 */

public class SignUpMenu extends Menu {
    final Main main;
    TextField userName,email,pass,repeatPass;
    Label labelUser, labelEmail, labelPass, labelRepeat;
    Button singin;
    Stage stage;
    Skin sk;
    UsersDAO service;
    public SignUpMenu(Main main){
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        service = new UsersDAO();
        sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);
        initLabels();
        initFields();
        initButton();
        fillStage();
    }

    /**
     * Method to fill the stage with all the actors
     */
    private void fillStage() {
        stage.addActor(labelUser);
        stage.addActor(labelEmail);
        stage.addActor(labelPass);
        stage.addActor(labelRepeat);
        stage.addActor(userName);
        stage.addActor(email);
        stage.addActor(pass);
        stage.addActor(repeatPass);
        stage.addActor(singin);
    }

    /**
     * Method to initializes buttons
     */
    private void initButton() {
        ButtonHandler bh = new ButtonHandler();
        singin=bh.getButton(new Texture(Gdx.files.internal("button-register.png")),350,450);
        singin.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                singUp();
            }
        });

    }

    /**
     * Method to get all the data input by user, and reports to the user the result.
     * Check also if Email and Password is valid.
     */
    private void singUp() {
        String validateEmail = "([\\w.\\.]+@)((?:[\\w]+\\.)+)([a-zA-Z]{2,4})";
        String validatePassword = "[a-zA-Z0-9]{4,20}";
        String user = userName.getText();
        String userEmail = email.getText();
        String password = pass.getText();
        String passwordR = repeatPass.getText();
        if (!user.equals("") && !userEmail.equals("") && !password.equals("") && !passwordR.equals("")) {
            if (validate(userEmail, validateEmail) == false) {
                showDialog("You must enter a valid email", false);
            } else if (validate(password, validatePassword) == false) {
                showDialog("The password must be 4 to 20 alphanumeric characters", false);
            } else if (validate(userEmail, validateEmail) == true && validate(password, validatePassword) == true) {
                if (password.equals(passwordR)) {
                    String response = service.singUp(user, userEmail, password);
                    if(response.equals("connectionError")){
                        showDialog("Error connecting to server \n user not registered", false);
                    }else if (response.equals("ok")) {
                        showDialog("User signed up correctly", true);

                    } else if (response.equals("user_name")) {
                        showDialog("User name already exist", false);
                    } else if (response.equals("email")) {
                        showDialog("Email allready in use", false);
                    }
                } else {
                    showDialog("Password don't match", false);
                }
            }

        } else {
            showDialog("Complete all the fields to sign up", false);
        }
    }

    /**
     * Goes to Login Screen
     */
    private void showLogin() {
        main.setScreen(new LoginMenu(main));
    }

    /**
     * Method to show a dialog to report
     * @param s message to show
     * @param exit boolean, true if after dialog exit screen and goes to another one
     *                      false if dont
     */
    private void showDialog(String s, boolean exit) {
        Label label = new Label(s, sk);
        label.setWrap(true);
        label.setAlignment(Align.center);
        Dialog d =new Dialog("", sk, "dialog") {
            protected void result (Object object) {
                this.clear();

                if(object.equals(true)){
                    showLogin();
                }
            }
        };
        d.padTop(50).padBottom(50).padLeft(50).padRight(50);
        d.getContentTable().padBottom(50);
        d.getContentTable().add(label).width(850).row();
        d.button("Close", exit).show(stage);


    }

    /**
     * Method to initializes Fields
     */
    private void initFields() {
        //User name
        userName = new TextField("",sk);
        userName.setPosition(550,1300);
        userName.setSize(500,100);

        //Email
        email = new TextField("",sk);
        email.setPosition(550,1100);
        email.setSize(500,100);

        //Password
        pass = new TextField("",sk);
        pass.setPosition(550,900);
        pass.setSize(500,100);
        pass.setPasswordCharacter('*');
        pass.setPasswordMode(true);

        //repeat Password
        repeatPass = new TextField("",sk);
        repeatPass.setPosition(550,700);
        repeatPass.setSize(500,100);
        repeatPass.setPasswordCharacter('*');
        repeatPass.setPasswordMode(true);

    }

    /**
     * Method to initialize Labels
     */
    private void initLabels() {
        //User name
        labelUser = new Label("User name: ",sk);
        labelUser.setSize(500,100);
        labelUser.setPosition(100,1300);

        //Email
        labelEmail = new Label("Email: ",sk);
        labelEmail.setSize(500,100);
        labelEmail.setPosition(100,1100);

        //Password
        labelPass = new Label("Password: ",sk);
        labelPass.setSize(500,100);
        labelPass.setPosition(100,900);

        //Repeat Password
        labelRepeat = new Label("Repeat password: ",sk);
        labelRepeat.setSize(500,100);
        labelRepeat.setPosition(100,700);

    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
        //Gdx.input.getTextInput(listener, "Dialog Title", "Initial Textfield Value", "Hint Value");
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
        backButton();
    }
    private void backButton() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            showLogin();
        }
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
}
