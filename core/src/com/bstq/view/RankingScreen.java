package com.bstq.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.bstq.Main;
import com.bstq.Service.Ranking;
import com.bstq.Service.User;
import com.bstq.Service.UsersDAO;

import java.util.List;

/**
 * Created by Jose on 22/05/2017.
 */

public class RankingScreen extends Menu {

    final Main main;
    Ranking ranking;
    UsersDAO service;
    Stage stage;
    Skin sk;
    public RankingScreen(Main main){

        this.main = main;
        service = new UsersDAO();
        stage = new Stage();
        sk = new Skin(Gdx.files.internal("uiskin.json"),new TextureAtlas(Gdx.files.internal("uiskin.atlas")));
        sk.getFont("default-font").getData().setScale(3f,3f);
        loadList();
        printList();


    }

    private void printList() {
        for(int i=0;i<ranking.size();i++){
            System.out.println(ranking.get(i).toString());
            stage.addActor(new Label("User: "+ranking.get(i).getUserName()+" Score: "+ranking.get(i).getMaxScore(),sk));

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
        stage.act();
        stage.draw();
    }
    @Override
    public void dispose() {
        stage.dispose();

    }

}
