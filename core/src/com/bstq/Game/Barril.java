package com.bstq.Game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.moveBy;

/**
 * Created by Jose on 10/03/2017.
 */

public class Barril extends Actor {
    private Texture imagen;
    boolean right, left, up, down;
    int id;
    float x;
    float y;
    public Barril(Texture imagen, float x, float y,int id) {
        this.id=id;
        right = false;
        left = false;
        up = false;
        down = false;
        this.imagen = imagen;
        this.x=x;
        this.y=y;
        setWidth(150);
        setHeight(150);
        setBounds(x,y,getWidth(),getHeight());
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        batch.draw(imagen,x,y);
    }

    @Override
    public void setX(float x) {
        this.x = x;
    }

    @Override
    public void setY(float y) {
        this.y = y;
    }

    public void move(float x, float y) {
       // this.addAction(Actions.moveTo(100,1100,100));
        setX(x);
        setY(y);
    }
    public int getId(){
        return id;
    }
}