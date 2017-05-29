package com.bstq.view;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
import com.bstq.Model.User;
import com.bstq.Model.UsersDAO;

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
    Button login,register;
    Stage stage;
    TextField user,pass;
    UsersDAO service;
    Label userLabel, passLabel;
    Skin sk;
    User u;
    Sprite title;
    SpriteBatch sb;
    Texture back;
    Sprite background;
    Sound buttonSound, warningSound, succesSound;

    public LoginMenu(Main main) {
        sb = new SpriteBatch();
        service = new UsersDAO();
        Gdx.input.setCatchBackKey(true);
        this.main=main;
        stage = new Stage();
        sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);
        back = new Texture(Gdx.files.internal("background.jpg"));
        background = new Sprite(back);
        buttonSound = Gdx.audio.newSound( Gdx.files.getFileHandle("sounds/beep.mp3", Files.FileType.Internal) );
        prepareSounds();
        prepareButtons();
        prepareListeners();
        prepareForm();
        fillStage();
    }

    /**
     * Method to prepare Sounds
     */
    private void prepareSounds() {
        buttonSound = Gdx.audio.newSound( Gdx.files.getFileHandle("sounds/beep.mp3", Files.FileType.Internal) );
        warningSound = Gdx.audio.newSound( Gdx.files.getFileHandle("sounds/warning.wav", Files.FileType.Internal) );
        succesSound = Gdx.audio.newSound( Gdx.files.getFileHandle("sounds/succes.wav", Files.FileType.Internal) );
    }

    /**
     * Method to fill the Stage with all actors
     */
    private void fillStage() {

        stage.addActor(userLabel);
        stage.addActor(passLabel);
        stage.addActor(user);
        stage.addActor(pass);
        stage.addActor(login);
        stage.addActor(register);
    }

    /**
     * Method to prepare Labels and TextViews of the screen
     */
    private void prepareForm() {
        Texture titleTexture = new Texture(Gdx.files.internal("title.png"));
        title = new Sprite(titleTexture);
        title.setPosition(200,1400);
        userLabel = new Label("Email: ",sk);
        userLabel.setSize(500,100);
        userLabel.setPosition(150,1000);
        passLabel = new Label("Password: ",sk);
        passLabel.setSize(500,100);
        passLabel.setPosition(150,800);
        user = new TextField("",sk);

        pass = new TextField("",sk);
        user.setPosition(450,1000);
        user.setSize(500,100);
        pass.setPosition(450,800);
        pass.setSize(500,100);
        pass.setPasswordCharacter('*');
        pass.setPasswordMode(true);
    }

    /**
     * Method to add Listeners to buttons
     */
    private void prepareListeners() {
        login.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play();
                checkLogin();

            }
        });
        register.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                buttonSound.play();
                main.setScreen(new RegisterMenu(main));
            }
        });
    }

    /**
     * Method to check the data input by the user to login,
     * show a dialog reporting the result of loguin
     */
    private void checkLogin() {
        String email=user.getText();
        String password = pass.getText();
        if (!email.equals("") && !password.equals("")) {
            u = service.login(email, password);

            if (u == null) {
                showDialog("Login fail", false);
                warningSound.play();
            } else if (u.getId()==0){
                warningSound.play();
                showDialog("Error connecting to server", false);

            }else{
                showDialog("Login success", true);
                succesSound.play();
            }
        }
    }

    /**
     * Method to show a dialog
     * @param s message to show
     * @param exit boolean data if is true, goes to MainMenu screen.
     *             if is flase, only shows a message
     */
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

    /**
     * Method to prepare the buttons of the screen
     */
    private void prepareButtons() {
        ButtonHandler bh = new ButtonHandler();
        login=bh.getButton(new Texture(Gdx.files.internal("button-login.png")),65,400);
        register=bh.getButton(new Texture(Gdx.files.internal("button-register.png")),565,400);
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
        sb.begin();
        background.draw(sb);
        title.draw(sb);
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void show(){
        Gdx.input.setInputProcessor(stage);
        Gdx.input.setCatchBackKey(true);
    }
    @Override
    public void dispose() {
        stage.dispose();
        sb.dispose();
        back.dispose();
        buttonSound.dispose();
        warningSound.dispose();
        succesSound.dispose();
    }

}
