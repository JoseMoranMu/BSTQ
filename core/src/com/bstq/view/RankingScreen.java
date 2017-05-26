package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.bstq.Main;
import com.bstq.Model.Ranking;
import com.bstq.Model.UsersDAO;

/**
 * Created by Jose on 22/05/2017.
 */

public class RankingScreen extends Menu {

    final Main main;
    Ranking ranking;
    UsersDAO service;
    Stage stage;
    Skin sk;
    Texture back;
    Sprite background;
    SpriteBatch sb;

    public RankingScreen(Main main){
        Gdx.input.setCatchBackKey(true);
        this.main = main;
        sb = new SpriteBatch();
        back = new Texture(Gdx.files.internal("background.jpg"));
        background = new Sprite(back);
        service = new UsersDAO();
        stage = new Stage();
        sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);
        loadList();
        printList();


    }

    private void printList() {
        Label title = new Label("RANKING",sk);
        title.setFontScale(5f);
        int x =400;
        int y = 1500;
        Label contentN;
        Label contentP;
        title.setPosition(x-50,y);
        title.setColor(Color.YELLOW);
        stage.addActor(title);
        y-=100;
        for(int i=0;i<ranking.size();i++){
            y-=100;
            contentN = new Label(ranking.get(i).getUserName(),sk);
            contentN.setPosition(x-150,y);
            contentP = new Label(ranking.get(i).getMaxScore()+" pts",sk);
            contentP.setPosition(x+200,y);
            stage.addActor(contentN);
            stage.addActor(contentP);

        }
    }

    private void loadList() {
        ranking = service.loadRanking();
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
        backButton();
        sb.begin();
        background.draw(sb);
        sb.end();
        stage.act();
        stage.draw();


    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void dispose() {

        stage.dispose();
        sb.dispose();
        back.dispose();

    }

    private void backButton() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.BACK)) {
            main.setScreen(new MainMenu(main));
        }
    }
}
